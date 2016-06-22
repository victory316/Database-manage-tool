package Transformation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import Data.DataType;

public class CreateConceptionSchema {
	public boolean createDBMap( ArrayList<String> datas, ArrayList<String> interactionData, String fomName, String federationName, String version, String siteID ){
		BufferedWriter bf = null;
		FileWriter fileWrite = null;
		String returndata = federationName+"\r\n" + fomName + "\r\n" + "UDT"/*잠시 입력*/ + "\r\n" + datas.size() + "\r\n";
		String innerData = new String();
		try {
			if( siteID.trim().equals("") ) fileWrite = new FileWriter("./ConceptualSchema/FOM2DBMap.txt"); 
			else fileWrite = new FileWriter("./ConceptualSchema/"+siteID+".txt");
			bf = new BufferedWriter(fileWrite);
			int i = 0;
			for( String d : datas ){//object class에 대한 정보다 담김
				String objectClassName;
				String superClassName = null;
//				전 버전(수퍼가 서브의 oid를 가지는 경우의 수)
				objectClassName = d.split("==")[0];
				if(superClassExist(objectClassName)) superClassName = objectClassName.split("\\[")[1].split("\\]")[0];//superClassName
				objectClassName = objectClassName.split("\\]")[1];
				innerData += (superClassName != null ? superClassName+"." : "") + objectClassName + " " + objectClassName + "\r\n";
				innerData += d.split("==").length != 1 ? d.split("==")[1].split("\\\\").length + "\r\n" : 0 + "\r\n";
				innerData += insertInToFOM2DBMapInfo(d);
				innerData += "\r\n";
			}
			innerData += interactionData.size() + "\r\n";
			for( String d : interactionData ){//object class에 대한 정보다 담김
				String InteractionClassName;
				String superClassName = null;
//				전 버전(수퍼가 서브의 oid를 가지는 경우의 수)
				InteractionClassName = d.split("==")[0];
				if(superClassExist(InteractionClassName)) superClassName = InteractionClassName.split("\\[")[1].split("\\]")[0];//superClassName
				InteractionClassName = InteractionClassName.split("\\]")[1];
				innerData += (superClassName != null ? superClassName+"." : "") + InteractionClassName + " " + InteractionClassName + "\r\n";
				innerData += d.split("==").length != 1 ? d.split("==")[1].split("\\\\").length + "\r\n" : 0 + "\r\n";
				innerData += insertInToFOM2DBMapInfo(d);
				innerData += "\r\n";
			}
			bf.write(returndata);
			bf.write(innerData);
			bf.close();
			fileWrite.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally{
			try {
				if( fileWrite != null ) fileWrite.close();
				if( bf != null ) bf.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return true;
	}
	
	/**
	 * @param s []를 포함하는 object이름
	 * @return
	 */
	private boolean superClassExist( String s ){
		if( s.split("\\[")[1].split("\\]")[0].trim().equals("") ) 
			return false;
		return true;
	}
	
	private String insertInToFOM2DBMapInfo( String data ){
		DataType dt = new DataType();
		String innerData = ""; 
		if( data.split("==").length != 1 ){
			for( String st : data.split("==")[1].split("\\\\") ){//데이터 정보가 담김
				String dataName = st.split(",")[0];
				String dataType;
				String HLAType = null;
				boolean structBool = dataName.contains("_oid");
				if( structBool ) dataType = dataName.replaceAll("_oid", "Struct");
				else {
					HLAType = st.split(",")[1];
					dataType = dt.dataTypeStructType(HLAType);
				}
				dataName = dataName.replaceAll("_oid", "");
				innerData += dataName + " " + HLAType + " " + dataName + " " + dataType + "\r\n";
			}
		}
		return innerData;
	}
}
