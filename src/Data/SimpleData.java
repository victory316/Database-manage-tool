package Data;

public class SimpleData {
	public String simpleDataString(String dataType){
		BasicData bs = new BasicData();
		if( dataType.equalsIgnoreCase("HLAASCIIchar") || dataType.equalsIgnoreCase("HLAbyte"))
		{
			return bs.basicDataString("HLAoctet");
		}
		else if( dataType.equalsIgnoreCase("HLAunicodeChar"))
		{
			return bs.basicDataString("HLAoctetPairBE");
		}
		else if(dataType.equalsIgnoreCase("Acceleration") || dataType.equalsIgnoreCase("PhaseAngle") || dataType.equalsIgnoreCase("ElectircFiled") || dataType.equalsIgnoreCase("Beamwidth") || dataType.equalsIgnoreCase("OrientationAngle") || dataType.equalsIgnoreCase("CollisionIntermediateResultComponent") || dataType.equalsIgnoreCase("Mass") || dataType.equalsIgnoreCase("Bandwidth") || dataType.equalsIgnoreCase("Temperature") || dataType.equalsIgnoreCase("SweepSyncPercent") || dataType.equalsIgnoreCase("Power_Watts") || dataType.equalsIgnoreCase("WaveLength") || dataType.equalsIgnoreCase("WorldLocationOffset") || dataType.equalsIgnoreCase("DeflectionAngle") ||  dataType.equalsIgnoreCase("RPM") || dataType.equalsIgnoreCase("GirdDataOffset") || dataType.equalsIgnoreCase("int32") || dataType.equalsIgnoreCase("Microseconds") ||  dataType.equalsIgnoreCase("InterrogartionRate") || dataType.equalsIgnoreCase("Frequency") || dataType.equalsIgnoreCase("Power_dBm") || dataType.equalsIgnoreCase("Meters") || dataType.equalsIgnoreCase("Velocity") || dataType.equalsIgnoreCase("float32") || dataType.equalsIgnoreCase("Angle") || dataType.equalsIgnoreCase("AngularVelocity")
				|| dataType.equalsIgnoreCase("InterrogationRate") || dataType.equals("ElectricField") || dataType.equals("HLAmsec"))
		{
			return bs.basicDataString("HLAfloat32BE");
		}
		else if( dataType.equalsIgnoreCase("unsignedInt64"))
		{
			return bs.basicDataString("Unsignedinteger64BE");
		}
		else if(dataType.equalsIgnoreCase("DataRate") || dataType.equalsIgnoreCase("Milliseconds") || dataType.equalsIgnoreCase("UnsignedInt32") || dataType.equalsIgnoreCase("Ticks") || dataType.equalsIgnoreCase("PercentComplete"))
		{
			return bs.basicDataString("Unsignedinteger32BE");
		}
		else if( dataType.equalsIgnoreCase("DataLength") || dataType.equalsIgnoreCase("MeasuredSpeed") || dataType.equalsIgnoreCase("unsignedInt16"))
		{
			return bs.basicDataString("Unsigngedinteger16BE");
		}
		else if( dataType.equalsIgnoreCase("Hours") || dataType.equalsIgnoreCase("Seconds") || dataType.equalsIgnoreCase("HLAcount"))
		{
			return bs.basicDataString("HLAinteger32BE");
		}
		else if( dataType.equalsIgnoreCase("GridAxisValue"))
		{
			return bs.basicDataString("HLAfloat64BE");
		}
		else if(dataType.equalsIgnoreCase("int16") || dataType.equalsIgnoreCase("NATOIFFMode5CAltitude") || dataType.equalsIgnoreCase("RPMRate16") || dataType.equalsIgnoreCase("RPM16") || dataType.equalsIgnoreCase("HLAinteger16BE"))
		{
			return bs.basicDataString("HLAinteger16BE");
		}
		else
		{
			return bs.basicDataString(dataType);
		}
	}
}
