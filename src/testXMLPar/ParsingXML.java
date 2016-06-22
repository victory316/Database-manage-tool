package testXMLPar;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import Transformation.CreateConceptionSchema;

public class ParsingXML {
	private String data;
	
	/**
	 * first Test Function
	 * @param filePath
	 */
	public void csCreate( String federationName, String version, String siteID ) {
		try {
			File file = new File(data);
			String fomName = data.split("\\\\")[data.split("\\\\").length-1];
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			Parser objectParse = new Parser();
			CreateConceptionSchema ccs = new CreateConceptionSchema();

			
			objectParse.parseXML(doc);
			xmlCheck xml = objectParse.getXml();
			ArrayList<String> nodes = xml.getNodes();
			ArrayList<String> interaction = xml.getInteractionNodes();
			ccs.createDBMap(nodes,interaction,fomName,federationName, version, siteID );
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * rd를 만들기 위한 Read 함수
	 */
	public ArrayList<String> rdRead( ) {
		try {
			File file = new File(data);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			Parser objectParse = new Parser();
			objectParse.parseXMLForRD(doc);
			xmlCheck xml = objectParse.getXml();
			
			ArrayList<String> nodes = xml.getNodes();
			return nodes;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public ParsingXML() {
		super();
	}
	/**
	 * userDefinedType 가져오는 함수.
	 * @return userdefinedType of ArrayList type 
	 */
	public ArrayList<String> getUserDefinedType(){
		File file = new File(data);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		ArrayList<String> fixedData = null;
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			Parser objectParse = new Parser();
			objectParse.parseXML(doc);
			xmlCheck xml = objectParse.getXml();
			fixedData = xml.getDataNodes();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fixedData;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
		
	
}
