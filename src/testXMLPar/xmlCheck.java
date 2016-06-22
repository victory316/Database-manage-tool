package testXMLPar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class xmlCheck {
	private ObjectParser op = new ObjectParser();
	private InteractionParser ip = new InteractionParser();
	private ArrayList<String> objectClassNodes = new ArrayList<String>();
	private ArrayList<String> interactionNodes = new ArrayList<String>();
	private ArrayList<String> fixedData = new ArrayList<String>();
	private int dataCount = 1;
	private boolean interaction = false;
	public NodeList nextLevelList( Node n ){
		return n.getChildNodes();
	}
	
	/**
	 * 
	 * super�� subclass�� oid�� ���� ��������
	 * @param nodeList
	 * @param count
	 */
//	public void showNodeList( NodeList nodeList, int count ){
//		String separation = "";
//		for( int i = 0; i < count; i++ ){
//			separation += "\t";
//		}
//		String tempString = "";
//		
//		try{ //arraylist�� count�� ������ �ȉ��� ��.
//			nodes.get(count-1);
//		}catch( IndexOutOfBoundsException e ){
//			nodes.add(tempString);
//		}
//		
//		
//		separation += (count+1) + "Level -- ";
//		for( int i = 1; i < nodeList.getLength(); i+=2 ){ //i+=2 �߰� �߰��� #text�� ���� ������. ���� xml���������� ������� �ʴ´�. if���� �ϳ� ���̱� ���� ���
//			Node temp = nodeList.item(i);				  //node�� �ӽð���
//			if(temp.getNodeName().equals("objectClass")){
////				System.out.println(separation+op.printObjectClass(temp));
//				tempString = op.printObjectName(temp) + "_oid\\";
//				nodes.set(count-1,nodes.get(count-1)+tempString);
//				nodes.add(count,op.printObjectName(temp)+"==");
//			} else if( temp.getNodeName().equals("attribute")){
////				System.out.println(separation+op.printattr(temp));
//				tempString = op.printObjectName(temp) + "," + op.printattrNameString(temp) + "\\";
//				nodes.set(count-1,nodes.get(count-1)+tempString);
//			} else if( temp.getNodeName().equals("interactionClass")){
////				System.out.println(separation+ip.printObjectClass(temp));
//			} else if( temp.getNodeName().equals("parameter")){
////				System.out.println(separation+ip.printParameter(temp));
//			}
//			if( temp.getChildNodes().getLength() / 2 != 0 ){
//				showNodeList(temp.getChildNodes(), count+1);
//			}
//		}
//	}
	public void createNodeList( NodeList nodeList, int count ){
		String tempString = "";
		
		try{ //arraylist�� count�� ������ �ȉ��� ��.
			objectClassNodes.get(count-1);
		}catch( IndexOutOfBoundsException e ){
			objectClassNodes.add(tempString);
		}
		
		try {//arraylist�� count�� ������ �ȉ��� ��.
			interactionNodes.get(count-1);
		} catch (Exception e) {
			interactionNodes.add(tempString);
		}
		
		
		for( int i = 1; i < nodeList.getLength(); i+=2 ){ //i+=2 �߰� �߰��� #text�� ���� ������. ���� xml���������� ������� �ʴ´�. if���� �ϳ� ���̱� ���� ���
			Node temp = nodeList.item(i);				  //node�� �ӽð���
			if(temp.getNodeName().equals("objectClass")){
				if(!op.printObjectName(temp).contains("HLA")){
					String superName = objectClassNodes.get(count-1);
					if( count != 1 && count != 2 ) 	superName = superName.split("\\]")[1].split("==")[0];
					else superName = "";
//					nodes.set(count-1,nodes.get(count-1)+tempString);
					objectClassNodes.add(count,"["+superName+"]"+ op.printObjectName(temp)+"==");
				}
			} else if( temp.getNodeName().equals("attribute")){
//				System.out.println(separation+op.printattr(temp));
				if(!op.printObjectName(temp).contains("HLA")){
					tempString = op.printObjectName(temp) + "," + op.printattrNameString(temp) + "\\";
					objectClassNodes.set(count-1,objectClassNodes.get(count-1)+tempString);
				}
			} else if( temp.getNodeName().equals("interactionClass")){
//				System.out.println(separation+ip.printObjectClass(temp));
				if( interaction ) {
					interaction = true;
					count = 1;
				}
				if(!ip.printName(temp).contains("HLA")){
					String superName = interactionNodes.get(count-1);
					if( count != 1 && count != 2 ) 	superName = superName.split("\\]")[1].split("==")[0];
					else superName = "";
					interactionNodes.add(count,"["+superName+"]"+ ip.printName(temp)+"==");
				}
			} else if( temp.getNodeName().equals("parameter")){
//				System.out.println(separation+ip.printParameter(temp));
				if(!ip.printName(temp).contains("HLA")){
					tempString = ip.printName(temp) + "," + ip.printParameterName(temp) + "\\";
					interactionNodes.set(count-1,interactionNodes.get(count-1)+tempString);
				}
			}
			if( temp.getChildNodes().getLength() / 2 != 0 ){
				createNodeList(temp.getChildNodes(), count+1);
			}
		}
	}
	
	public void objectAndParshing( NodeList nodeList, int count ){
		String tempString = "";
		try{ //arraylist�� count�� ������ �ȉ��� ��.
			objectClassNodes.get(count-1);
		}catch( IndexOutOfBoundsException e ){
			objectClassNodes.add(tempString);
		}
		
		
		for( int i = 1; i < nodeList.getLength(); i+=2 ){ //i+=2 �߰� �߰��� #text�� ���� ������. ���� xml���������� ������� �ʴ´�. if���� �ϳ� ���̱� ���� ���
			Node temp = nodeList.item(i);				  //node�� �ӽð���
			if(temp.getNodeName().equals("objectClass"))
				if(!op.printObjectName(temp).trim().contains("HLA")) objectClassNodes.add(op.printObjectName(temp)+"//"+op.attrSharingString(temp));
			if( temp.getChildNodes().getLength() / 2 != 0 ) objectAndParshing(temp.getChildNodes(), count+1);
		}
	}

	
	public void dataTypeFixedArray( NodeList nodeList ){
		String tempString = "";
		try{ //arraylist�� count�� ������ �ȉ��� ��.
			fixedData.get(dataCount-1);
		}catch( IndexOutOfBoundsException e ){
			fixedData.add(tempString);
		}
		
		
		for( int i = 1; i < nodeList.getLength(); i+=2 ){ //i+=2 �߰� �߰��� #text�� ���� ������. ���� xml���������� ������� �ʴ´�. if���� �ϳ� ���̱� ���� ���
			Node temp = nodeList.item(i);				  //node�� �ӽð���
			if(!op.printObjectName(temp).trim().contains("HLA")){
				if(temp.getNodeName().equals("fixedRecordData")){
					tempString = op.printObjectName(temp) + "_\\";
					fixedData.set(dataCount-1,fixedData.get(dataCount-1));
					fixedData.add(dataCount,op.printObjectName(temp)+"==");
					dataCount +=1;
				} else if( temp.getNodeName().equals("field")){
					tempString = op.printObjectName(temp) + "," + op.printattrNameString(temp) + "\\";
					fixedData.set(dataCount-1,fixedData.get(dataCount-1)+tempString);
				} else if( temp.getNodeName().equals("variantRecordData")){
					tempString = op.printObjectName(temp) + "_\\";
					fixedData.set(dataCount-1,fixedData.get(dataCount-1));
					fixedData.add(dataCount,op.printObjectName(temp)+"==");
					dataCount +=1;
				} else if( temp.getNodeName().equals("alternative")){
					tempString = op.printObjectName(temp) + "," + op.printattrNameString(temp) + "\\";
					fixedData.set(dataCount-1,fixedData.get(dataCount-1)+tempString);
				}
			}
			if( temp.getChildNodes().getLength() / 2 != 0 ){
				dataTypeFixedArray(temp.getChildNodes());
			} 
		}
		
	}
	
	public void emptyNodeRemove( ArrayList<String> datas ){
		List<String> empty = datas;
		for(Iterator<String> it = empty.iterator(); it.hasNext(); ){
			String value = it.next();
			if( value.trim().equals("") ){
				it.remove();
			}
		}
	}
	
	public ArrayList<String> getNodes() {
		return objectClassNodes;
	}
	
	public ArrayList<String> getInteractionNodes() {
		return interactionNodes;
	}
	
	public ArrayList<String> getFixedData() {
		return fixedData;
	}


	public boolean isInteraction() {
		return interaction;
	}

	public void setInteraction(boolean interaction) {
		this.interaction = interaction;
	}

	public ArrayList<String> getDataNodes(){
		return fixedData;
	}
	
	
}
