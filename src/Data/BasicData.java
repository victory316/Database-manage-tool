package Data;

public class BasicData {
	public String basicDataString(String dataType){
		String returnString = "";
		if (dataType.equals("HLAoctetPairBE")
				|| dataType.equals("HLAoctetPairLE")) {
			returnString = "varchar(16), ";
		} else if (dataType.equals("HLAfloat32BE")
				|| dataType.equals("HLAfloat32LE")) {
			returnString = "FLOAT(23), ";
		} else if (dataType.equals("HLAfloat64BE")
				|| dataType.equals("HLAfloat64LE")) {
			returnString = "FLOAT(49), ";
		} else if (dataType.equals("HLAinteger16BE")
				|| dataType.equals("HLAinteger16LE")
				|| dataType.equals("Unsigngedinteger16BE")) {
			returnString = "NUMBER(5), ";
		} else if (dataType.equals("HLAinteger32BE")
				|| dataType.equals("HLAinteger32LE")
				) {
			returnString = "NUMBER(10), ";
		} else if (dataType.equals("HLAinteger64BE")
				|| dataType.equals("HLAinteger64LE")
				|| dataType.equals("Unsignedinteger64BE")
				|| dataType.equals("Unsignedinteger32BE") ) {
			returnString = "NUMBER(19), ";
		} else if (dataType.equals("HLAoctet")) {
			returnString = "varchar(8), ";
		} else {
			returnString = dataType.toLowerCase().contains("struct") == true ? dataType +"," : "BLOB, ";
		}
		return returnString;
	}
	
}
