package Transformation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import Data.DataType;

public class CreateConceptionSchema {
	public boolean createDBMap( ArrayList<String> datas, ArrayList<String> interactionData, String fomName, String federationName, String version, String siteID ){
		BufferedWriter bf = null;
		FileWriter fileWrite = null;
		String returndata = federationName+"\r\n" + fomName + "\r\n" + "UDT"/*��� �Է�*/ + "\r\n" + datas.size() + "\r\n";
		String innerData = new String();
		try {
			if( siteID.trim().equals("") ) fileWrite = new FileWriter("./ConceptualSchema/FOM2DBMap.txt"); 
			else fileWrite = new FileWriter("./ConceptualSchema/"+siteID+".txt");
			bf = new BufferedWriter(fileWrite);
			int i = 0;
			for( String d : datas ){//object class�� ���� ������ ���
				String objectClassName;
				String superClassName = null;
//				�� ����(���۰� ������ oid�� ������ ����� ��)
				objectClassName = d.split("==")[0];
				if(superClassExist(objectClassName)) superClassName = objectClassName.split("\\[")[1].split("\\]")[0];//superClassName
				objectClassName = objectClassName.split("\\]")[1];
				innerData += (superClassName != null ? superClassName+"." : "") + objectClassName + " " + objectClassName + "\r\n";
				innerData += d.split("==").length != 1 ? d.split("==")[1].split("\\\\").length + "\r\n" : 0 + "\r\n";
				innerData += insertInToFOM2DBMapInfo(d);
				innerData += "\r\n";
			}
			innerData += interactionData.size() + "\r\n";
			for( String d : interactionData ){//object class�� ���� ������ ���
				String InteractionClassName;
				String superClassName = null;
//				�� ����(���۰� ������ oid�� ������ ����� ��)
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
	 * @param s []�� �����ϴ� object�̸�
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
			for( String st : data.split("==")[1].split("\\\\") ){//������ ������ ���
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
