package testXMLPar;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author CSW author
 * this Class is ObjectParser for xml DTD
 */
public class ObjectParser {
	/**
	 * @param Node
	 * @return 노드의 이름
	 */
	private String attrNameString( Node n ){
		Element check = (Element)n;
		return check.getAttribute("name");
	}
	
	/**
	 * @param Node
	 * @return ObjcectClass의 Sharing 타입.
	 */
	public String attrSharingString( Node n ){
		Element check = (Element)n;
		return check.getAttribute("sharing");
	}
	
	
	/**
	 * @param Node
	 * @return attribute의 데이터 타입
	 */
	private String attrDataType( Node n ){
		Element check = (Element)n;
		return check.getAttribute("dataType");
	}
	
	
	
	/**
	 * @param Node
	 * @return 태그의 종류가 objectClass일때 출력
	 */
	public String printObjectClass( Node n ){
		return n.getNodeName()+", name == "+attrNameString(n) + ", shartingType == " + attrSharingString(n);
	}

	/**
	 * @param Node
	 * @return 태그의 종류가 attribute일때 출력
	 */
	public String printattr( Node n ){
		return n.getNodeName()+", name == "+attrNameString(n) + ", dataType == " + attrDataType(n);
	}
	
	public String printObjectName( Node n ){
		return attrNameString(n);
	}
	
	public String printattrNameString( Node n ){
		return attrDataType(n);
	}
	
	
	
}
