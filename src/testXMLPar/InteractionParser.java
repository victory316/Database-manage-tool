package testXMLPar;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class InteractionParser {
	/**
	 * @param Node
	 * @return ����� �̸�
	 */
	private String attrNameString( Node n ){
		Element check = (Element)n;
		return check.getAttribute("name");
	}
	
	/**
	 * @param Node
	 * @return interactionClass�� Sharing Ÿ��.
	 */
	private String attrSharingString( Node n ){
		Element check = (Element)n;
		return check.getAttribute("sharing");
	}
	
	/**
	 * @param Node
	 * @return parameter�� ������ Ÿ��
	 */
	private String attrDataType( Node n ){
		Element check = (Element)n;
		return check.getAttribute("dataType");
	}
	
	/**
	 * @param Node
	 * @return �±��� ������ interactionClass �϶� ���
	 */
	public String printObjectClass( Node n ){
		return n.getNodeName()+", name == "+attrNameString(n) + ", shartingType == " + attrSharingString(n);
	}
	
	/**
	 * @param Node
	 * @return �±��� ������ interactionClass �϶� ���
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
