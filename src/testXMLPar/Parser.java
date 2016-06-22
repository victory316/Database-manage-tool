package testXMLPar;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Parser {
	private xmlCheck xml;
	public void parseXML( Document doc ){
		Element root = doc.getDocumentElement(); // root == objectModel
		NodeList objects = root.getChildNodes(); // objects, interactions, dimensions, time, tags etc....
		xml = new xmlCheck();
		for(int i = 1; i < objects.getLength(); i+=2 ){ //i+=2 �߰� �߰��� #text�� ���� ������. ���� xml���������� ������� �ʴ´�.
			Node node = objects.item(i); 				//i��° item ���ϴ� Two Level Nodes
			if( node.getNodeType() == 1 ){ 				// Element Type ( Not #text )
//				System.out.println("------------------------------------------------");
//				System.out.print(node.getNodeName()); //Element Node Name
				NodeList tempNodeList = node.getChildNodes();
//				System.out.println("( Childs == " + tempNodeList.getLength()/2 + " )"); //�Ƶ��� ���� == n / 2; ��Ģ���̴�.
				if( node.getNodeName().equals("objects") || node.getNodeName().equals("interactions")){
					xml.createNodeList(tempNodeList,1);
					xml.emptyNodeRemove(xml.getNodes());
					xml.emptyNodeRemove(xml.getInteractionNodes());
				}
				if( node.getNodeName().equals("dataTypes")){
					NodeList dataTypeList = node.getChildNodes();
					xml.dataTypeFixedArray(dataTypeList);
					xml.emptyNodeRemove(xml.getDataNodes());
				}
			}
		}
	}
	
	/**
	 * RiplicationDirectory�� ����� ���� �Լ�
	 * @param doc
	 */
	public void parseXMLForRD( Document doc ){
		Element root = doc.getDocumentElement(); // root == objectModel
		NodeList objects = root.getChildNodes(); // objects, interactions, dimensions, time, tags etc....
		xml = new xmlCheck();
		for(int i = 1; i < objects.getLength(); i+=2 ){ //i+=2 �߰� �߰��� #text�� ���� ������. ���� xml���������� ������� �ʴ´�.
			Node node = objects.item(i); 				//i��° item ���ϴ� Two Level Nodes
			if( node.getNodeType() == 1 ){ 				// Element Type ( Not #text )
				NodeList tempNodeList = node.getChildNodes();
				if( node.getNodeName().equals("objects") || node.getNodeName().equals("interactions")){
					xml.objectAndParshing(tempNodeList,1);
				}
			}
		}
	}
	
	public xmlCheck getXml() {
		return xml;
	}
	public void setXml(xmlCheck xml) {
		this.xml = xml;
	}
}
