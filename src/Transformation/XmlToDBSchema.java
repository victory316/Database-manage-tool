package Transformation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.regex.Pattern;

import Data.DataType;
import util.DBManager;

public class XmlToDBSchema {
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Connection conn;
	private int count = 0;
	public boolean insertIntoDB( ArrayList<String> data, String logTable, String logTableSeq ){
		String[] sqlSet = new String[data.size()];
		int j = 0;
		for( int i = 0; i < data.size() ; i++ ){
			sqlSet[j++] = SettingSql(data.get(i),logTable, logTableSeq);
		}
		
		DBManager dbManager = new DBManager();
		conn = dbManager.getConn();
		try{
			conn.setAutoCommit(false);
			StringBuffer sql = null;
			for( String s : sqlSet ){
				sql = new StringBuffer();
				sql.append(s);
				pstmt = conn.prepareStatement(sql.toString());
				System.out.println(sql);
				pstmt.executeUpdate();
			}
			
			conn.commit();
			count++;
			return true;
		} catch( Exception e ){
			try {
				conn.rollback();	
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				dbManager.allClose(pstmt, rs, conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}
	
	private String SettingSql( String sql, String logTable, String logTableSeq){
		if( !logTableSeq.equals("") ){
			return SeqCreate(sql);
		} else {
			String sqlSet = "CREATE TABLE ";
			String[] separation = sql.split("==");
			String tableName = logTable.equals("logTable") ? separation[0]+"Log" : separation[0];
			String exist;//나머지 어트리뷰트들 저장 할 공간
			sqlSet += tableName + "( wts Timestamp, siteID varchar2(5), ";
			
			sqlSet += logTable.equals("logTable") ? "id NUMBER primary key," : "";
			try{
				exist = sentence(separation[1]);
				sqlSet += primarykeySetting(logTable,0);
				sqlSet += exist;
			} catch( Exception e ){
				sqlSet += primarykeySetting(logTable,1);
			}
			sqlSet += " )";
			return sqlSet;
		}
	}
	
	private String SeqCreate( String sql ){
		String sqlSet = "CREATE SEQUENCE ";
		String[] separation = sql.split("==");
		String tableName = separation[0]+"LogSEQ";
		sqlSet += tableName + " start with 1 INCREMENT BY 1 ";
		return sqlSet;
	}
	
	private String sentence( String sql ){
		String[] separation = sql.split(",");
		String sentence = "";
		for( int i = 0; i < separation.length; i++ ){
			sentence += separation[i].split("\\\\")[0] + " " + separation[i].split("\\\\")[1] + ","; 
			if( i == separation.length -1 ) {
				sentence = sentence.substring(0, sentence.length()-1);//마지막 문자 제거
			}
		}
		return sentence;
	}
	
	private String AttributeString( String sql ){
		String[] dataType = sql.split("\\,");
		String parameterDatatype;
		DataType dt = new DataType();
		if( dataType[0].length() > 26 ){
			parameterDatatype = dataType[0].substring(0, 25);
		} else {
			parameterDatatype = dataType[0];
		}
		if( count == 0 ){
			return parameterDatatype + " " + dt.dataTypeToBlobType(dataType[1]);
		} else {
			return parameterDatatype + " " + dt.dataTypeStructType(dataType[1]);
		}
	}
	
	//공통 으로 사용할 함수.
	private String foreignKeySetting( String sql ){
		String tableName = sql.substring(0, sql.length()-3);
		return sql + " INT, ";
//				실험을 위해 외래키 삭제+ ", CONSTRAINT FK_" + sql + " FOREIGN KEY (" + sql +") " + "REFERENCES " + tableName + " (oid), ";
	}
	
	private String primarykeySetting( String logTable, int bool ){
		String returndata = "oid VARCHAR2(11) ";
		String primaryKey = logTable.equals("logTable") ? "" : "PRIMARY KEY";
		if( bool == 1 )	return returndata + primaryKey;
		else return returndata + primaryKey + ",";
	}
	
	/**
	 * db스키마 생성
	 */
	public void createDBSchma(){
		File udtFile = new File("./ConceptualSchema/FOM2DBMap.txt");
		FileReader fr = null;
		BufferedReader br = null;
		ArrayList<String> objectDatas = new ArrayList<String>();
		ArrayList<String> interactionDatas = new ArrayList<String>();
		try {
			fr = new FileReader(udtFile);
			br = new BufferedReader(fr);
			String line;
			//file읽는다. 7월22일에 파일 읽은것을 한줄씩 가져와서 만들 준비를 한다.
			int i = 0;
			int count = 0; //objecet
			int count2 = 0;//interaction count
			int j = -1;//objecet읽기 위한 int
			int k = -1;//interaction을 읽기 위한 int
			int datasSize = 0;
			while( (line = br.readLine() ) != null ){
				if( i < 3 ) i++;
				else if( i == 3 ) {
					count = Integer.parseInt(line);//count 확인.
					i++;//i의 역할은 끝
				}
				else if( j < count - 1 ){//objectClass sql문 만들기 위한 준비 과정
					if( Pattern.matches("^[0-9]+$",line) ){//숫자일경우
						datasSize = Integer.parseInt(line);
						j++;
					} else {
						if( datasSize == 0 )
							objectDatas.add(line.split(" ")[0] + "==");
						else if( line.trim().equals("") )
							datasSize = 0;
						else {
							objectDatas.set(j,objectDatas.get(j) + line.split(" ")[0] + "\\" + line.split(" ")[3]);
						}
					}
				} else if( j == count -1 && !line.trim().equals("") ){
					count2 = Integer.parseInt(line);
					j++;//j의 역할 끝
				}
				else if( k < count2 - 1 ){
					if( Pattern.matches("^[0-9]+$",line) ){//숫자일경우
						datasSize = Integer.parseInt(line);
						k++;
					} else {
						if( datasSize == 0 )
							interactionDatas.add(line.split(" ")[0] + "==");
						else if( line.trim().equals("") )
							datasSize = 0;
						else {
							interactionDatas.set(k,interactionDatas.get(k) + line.split(" ")[0] + "\\" + line.split(" ")[3]);
						}
					}
				}
			}
			insertIntoDB(objectDatas,"NonLogTable", "");
			insertIntoDB(objectDatas,"logTable","");
			insertIntoDB(objectDatas,"logTableSeq","seq");//시퀀스 생성
			insertIntoDB(interactionDatas,"NonLogTable","");
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if( br != null ) br.close();
				if( fr != null ) fr.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
}
