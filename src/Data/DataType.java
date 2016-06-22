package Data;


public class DataType {
	public String dataTypeToBlobType(String dataType){
		ArrayListData ald = new ArrayListData();
		String returnString;
		returnString = ald.arraylistToSimpleData(dataType);
		return returnString;
	}
	
	public String dataTypeStructType( String dataType ){
		String returnString ="";
		FixedRecordData frd = new FixedRecordData();
		returnString = frd.fixedToBasicData(dataType);
		return returnString;
	}
	public String dataTypeJavaType( String dataType ){
		String returnString ="";
		FixedRecordData frd = new FixedRecordData();
		JavaDataType jdt = new JavaDataType();
		returnString = frd.fixedToBasicData(dataType);
		returnString = jdt.javaDataType(returnString);
		return returnString;
	}
}
