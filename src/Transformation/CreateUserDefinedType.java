package Transformation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import Data.DataType;
import Data.FixedRecordData;


public class CreateUserDefinedType {
	public void transformationJavaFile( ArrayList<String> fixedData ){
		String dataNameAndType[] = new String[fixedData.size()];
		String dataName = null;
		String dataType = null;
		int i = 0;
		String insertUDTInfo = null;
		BufferedWriter bf = null;
		FileWriter fileWrite = null;
		DataType dt = new DataType();
		insertUDTInfo = fixedData.size() + "\r\n";
		try {
			fileWrite = new FileWriter("./javaFile/UDTInfo.txt");
			bf = new BufferedWriter(fileWrite);
			for( String d : fixedData ){
				String userdefinedName = d.split("==")[0];
				dataNameAndType[i] = d.split("==")[1];
				insertUDTInfo += userdefinedName + "\t" + dataNameAndType[i].split("\\\\").length + "\t";
				for( String st : dataNameAndType[i].split("\\\\") ){
					dataName = st.split(",")[0];
					dataType = st.split(",")[1];
					dataType = dt.dataTypeJavaType(dataType);
					insertUDTInfo += dataName + " " + dataType + "\t";
				}
				i++;
			}
			bf.write(insertUDTInfo);
			bf.close();
			fileWrite.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if( fileWrite != null ) fileWrite.close();
				if( bf != null ) bf.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		FixedRecordData frd = new FixedRecordData();
		/*�ӵ� ������ ����*/
		frd.createFixedData(fixedData);
		frd.createFixedData(fixedData);//2���� �̻��� ���ؼ�.
//		createUserDefinedType();
	}
	
	public void createUserDefinedType(){
		File udtFile = new File("./javaFile/UDTInfo.txt");
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(udtFile);
			br = new BufferedReader(fr);
			String line;
			//file�д´�. 7��22�Ͽ� ���� �������� ���پ� �����ͼ� ���� �غ� �Ѵ�.
			while( (line = br.readLine() ) != null ) System.out.println(line);
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
