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
	 * @return ����� �̸�
	 */
	private String attrNameString( Node n ){
		Element check = (Element)n;
		return check.getAttribute("name");
	}
	
	/**
	 * @param Node
	 * @return ObjcectClass�� Sharing Ÿ��.
	 */
	public String attrSharingString( Node n ){
		Element check = (Element)n;
		return check.getAttribute("sharing");
	}
	
	
	/**
	 * @param Node
	 * @return attribute�� ������ Ÿ��
	 */
	private String attrDataType( Node n ){
		Element check = (Element)n;
		return check.getAttribute("dataType");
	}
	
	
	
	/**
	 * @param Node
	 * @return �±��� ������ objectClass�϶� ���
	 */
	public String printObjectClass( Node n ){
		return n.getNodeName()+", name == "+attrNameString(n) + ", shartingType == " + attrSharingString(n);
	}

	/**
	 * @param Node
	 * @return �±��� ������ attribute�϶� ���
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
