package Data;

public class ArrayListData {
	public String arraylistToSimpleData (String dataType) {
		EnumeratedData em = new EnumeratedData();
		if( dataType.equals("HLAASCIIstring") || dataType.equals("OMT13string") ){
			return em.enumeratedDataString("HLAASCIIchar");
		} else if( dataType.equals("HLAunicodeString")){
			return em.enumeratedDataString("HLAunicodeChar");
		} else if( dataType.equals("HLAopaqueData") || dataType.equals("MarkingDataArrayLen31")
				|| dataType.equals("PaddingArrayLen4") || dataType.equals("PaddingArrayLen2")
				|| dataType.equals("PaddingTo64ByteArray") || dataType.equals("PaddingArrayLen3")
				|| dataType.equals("PaddingTo32ByteArray") || dataType.equals("MarkingDataArrayLen11")
				|| dataType.equals("ByteArray") || dataType.equals("PaddingArrayLen8")
				|| dataType.equals("ByteArrayLengthless") || dataType.equals("HLAhandle") || dataType.equals("HLAhandleList") 
				|| dataType.equals("HLAlogicalTime") || dataType.equals("HLAtimeInterval")){
			return em.enumeratedDataString("HLAbyte");
		} else if( dataType.equals("int16Array")){
			return em.enumeratedDataString("int16");
		} else if( dataType.equals("float32Array") || dataType.equals("float32ArrayLengthless")){
			return em.enumeratedDataString("float32");
		} else if( dataType.equals("TemperatureArray")){
			return em.enumeratedDataString("Temperature");
		} else if( dataType.equals("unsignedInt16Array") || dataType.equals("unsignedInt16ArrayLengthless")
				){
			return em.enumeratedDataString("unsignedInt16");
		} else if( dataType.equals("DatumIdentifierArray")){
			return em.enumeratedDataString("DatumIdentifierEnum32");
		}
		else if( dataType.equals("AttributeValueSetStruct")){
			return em.enumeratedDataString("AttributePairStruct");
			// 아래쪽부턴 fixed있음
		} else if( dataType.equals("BreachedStatusArrayLen8")){ // fixed에 있음
			return em.enumeratedDataString("BreachedStatusArrayLen8");
		} else if( dataType.equals("EnvironmentRecordList")){ // fixed에 있음
			return em.enumeratedDataString("EnvironmentRecStruct");
		} else if( dataType.equals("RecordSetList")){ // fixed에 있음
			return em.enumeratedDataString("RecordSetStruct");
		} else if( dataType.equals("RecordArray")){ // fixed에 있음
			return em.enumeratedDataString("RecordStruct");
		} else if( dataType.equals("RTIObjectIdArrayStruct")){ // fixed에 있음
			return em.enumeratedDataString("RTIObjectIdStruct"); 
		} else if( dataType.equals("VariableDatumSetStruct") || 
				dataType.equals("VariableDatumArrayLengthless" )
				){ //fixed에 있음
			return em.enumeratedDataString("VariableDatumStruct");
		} else if( dataType.equals("unsignedInt64Array")){
			return em.enumeratedDataString("unsignedInt64");
		} else if( dataType.equals("SilentAggregateArray")){ // fixed에 있음
			return em.enumeratedDataString("SilentAggregateStruct");
		} else if( dataType.equals("SilentEntityArray")){ //fixed에 있음
			return em.enumeratedDataString("SilentEntityStruct");
		} else if( dataType.equals("ArticulatedParameterArray")){
			return em.enumeratedDataString("ArticulatedParameterStruct");
		} else if( dataType.equals("PropulsionSystemDataArray")){
			return em.enumeratedDataString("PropulsionSystemDataStruct");
		} else if( dataType.equals("VectoringNozzleSystemDataArray")){
			return em.enumeratedDataString("VectoringNozzleSystemDataStruct");
		} else if( dataType.equals("FundamentalParameterArray")){
			return em.enumeratedDataString("FundamentalParameterDataStruct");
		} else if( dataType.equals("MineFusingArray")){
			return em.enumeratedDataString("MineFusingStruct");
		} else if( dataType.equals("ClockTimeArray")){
			return em.enumeratedDataString("ClockTimeStruct");
		} else if( dataType.equals("WorldLocationArray")){
			return em.enumeratedDataString("WorldLocationStruct");
		} else if( dataType.equals("OrientationArray")){
			return em.enumeratedDataString("OrientationStruct");
		} else if( dataType.equals("MinefieldPaintSchemeArray")){
			return em.enumeratedDataString("MinefieldPaintSchemeEnum32");
		} else if( dataType.equals("MinefieldSensorTypeArray")){
			return em.enumeratedDataString("MinefieldSensorTypeEnum32");
		} else if( dataType.equals("ShaftDataArray")){
			return em.enumeratedDataString("ShaftDataStruct");
		} else if( dataType.equals("BreachableSegmentArray")){
			return em.enumeratedDataString("BreachableSegmentStruct");
		} else if( dataType.equals("BreachArray")){
			return em.enumeratedDataString("BreachStruct");
		} else if( dataType.equals("ExhaustSmokeArray")){
			return em.enumeratedDataString("ExhaustSmokeStruct");
		} else if( dataType.equals("MinefieldLaneMarkerArray")){
			return em.enumeratedDataString("MinefieldLaneMarkerStruct");
		} else if( dataType.equals("GridAxisArray")){
			return em.enumeratedDataString("GridAxisStruct");
		} else if( dataType.equals("GridDataArray")){
			return em.enumeratedDataString("GridDataStruct");
		} else if( dataType.equals("EntityTypeArray")){
			return em.enumeratedDataString("EntityTypeStruct");
		} else if( dataType.equals("PerimeterPointArray")){
			return em.enumeratedDataString("PerimeterPointStruct");
		} else if( dataType.equals("FixedDatumArray")){
			return em.enumeratedDataString("FixedDatumStruct");
		} else if( dataType.equals("DepthOffsetArray")){
			return em.enumeratedDataString("Meters");
		} else if( dataType.equals("SupplyArray")){
			return em.enumeratedDataString("SupplyStruct");
		} else if( dataType.equals("AntennaPatternArray")){ // variantRecordData 있음
			return em.enumeratedDataString("AntennaPatternStruct");
		} else {
			return em.enumeratedDataString(dataType);
		}
	}
}
