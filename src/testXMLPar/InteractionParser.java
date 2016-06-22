package testXMLPar;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class InteractionParser {
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
	 * @return interactionClass의 Sharing 타입.
	 */
	private String attrSharingString( Node n ){
		Element check = (Element)n;
		return check.getAttribute("sharing");
	}
	
	/**
	 * @param Node
	 * @return parameter의 데이터 타입
	 */
	private String attrDataType( Node n ){
		Element check = (Element)n;
		return check.getAttribute("dataType");
	}
	
	/**
	 * @param Node
	 * @return 태그의 종류가 interactionClass 일때 출력
	 */
	public String printObjectClass( Node n ){
		return n.getNodeName()+", name == "+attrNameString(n) + ", shartingType == " + attrSharingString(n);
	}
	
	/**
	 * @param Node
	 * @return 태그의 종류가 interactionClass 일때 출력
	 */
	public String printParameter( Node n ){
		return n.getNodeName()+", name == "+attrNameString(n) + ", dataType == " + attrDataType(n);
	}

	public String printName( Node n ){
		return attrNameString(n);
	}
	
	public String printParameterName( Node n ){
		return attrDataType(n);
	}
}
