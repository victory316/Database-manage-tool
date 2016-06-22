package Data;

public class JavaDataType {
	public String javaDataType(String dataType){
		String returnString = "";
		if (dataType.equals("varchar2(16), ") || dataType.equals("varchar2(8), ")){
			returnString = "String";
		} else if (dataType.equals("FLOAT(23), ")){
			returnString = "float";
		} else if (dataType.equals("FLOAT(49), ")){
			returnString = "double";
		} else if (dataType.equals("NUMBER(5), ")){
			returnString = "short";
		} else if (dataType.equals("NUMBER(10), ")){
			returnString = "int";
		} else if (dataType.equals("NUMBER(19), ")){
			returnString = "long";
		} else {
			returnString = dataType.contains("Struct,") == true ? dataType.split(",")[0] : "BLOB";
		}
		return returnString;
	}
}
