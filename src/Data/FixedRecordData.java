package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DBManager;

public class FixedRecordData {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DBManager dbManager;
	private String sqlset;
	public void createFixedData( ArrayList<String> data ){
		for( int i = 0; i < data.size(); i++ ){
			sqlset = sqlSetting(data.get(i));
			createData(sqlset);
		}
			
	}
	
	private boolean createData( String datasql ){
		dbManager = new DBManager();
		conn = dbManager.getConn();
		StringBuffer sql = new StringBuffer();
		sql.append(datasql);
		try {
			pstmt = conn.prepareStatement(sql.toString());
			int result = pstmt.executeUpdate();
			if( result != 0 ){
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				dbManager.allClose(pstmt, rs, conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return true;
	}
	
	
	public String fixedToBasicData( String dataType ){
		String returnData = dataType + ", ";
		ArrayListData al = new ArrayListData();
		returnData = al.arraylistToSimpleData(dataType);
		return returnData;
	}
	
	private String sqlSetting( String sql ){
		String[] datas = sql.split("==");
		String[] column = datas[1].split("\\\\");
		if( datas[0].length() > 26 ){
			datas[0] = datas[0].substring(0, 25);
		}
		String sqlset = "create or replace type " + datas[0] + " as object ( ";
		for( int i = 0; i < column.length; i++ ){
			String culumnName = column[i].split(",")[0].contains("-") ? column[i].split(",")[0].replace("-", "") : column[i].split(",")[0];
			if( culumnName.length() > 26 ){
				culumnName = culumnName.substring(0,25);
			}
			sqlset += culumnName + " " + fixedToBasicData(column[i].split(",")[1]);
		}
		sqlset = sqlset.substring(0, sqlset.length()-2);//마지막 문자 제거
		sqlset += ")";
		return sqlset;
	}
	
}
