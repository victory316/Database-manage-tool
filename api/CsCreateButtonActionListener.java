package ui.api;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import Transformation.XmlToDBSchema;
import testXMLPar.ParsingXML;
import ui.fileMneu.FederationSetting;
import ui.fileMneu.FomSelect;
import ui.fileMneu.FomVersionSelect;
import ui.fileMneu.federateSetting;

public class CsCreateButtonActionListener implements ActionListener{
	private JFrame federationSetting;
	private JFrame fomSetting;
	private JFrame fomSelect;
	private ParsingXML txml;
	private JFrame somFilesTable;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if( somFilesTable == null ) gcsCreate();
		else lcsCraete();
	}
	public CsCreateButtonActionListener(JFrame federationSetting,
			JFrame fomSetting, JFrame fomSelect, ParsingXML txml) {
		super();
		this.federationSetting = federationSetting;
		this.fomSetting = fomSetting;
		this.fomSelect = fomSelect;
		this.txml = txml;
	}
	
	public CsCreateButtonActionListener( JFrame somFiles ) {
		super();
		this.somFilesTable = somFiles;
		// TODO Auto-generated constructor stub
	}
	public void setFederationSetting(JFrame federationSetting) {
		this.federationSetting = federationSetting;
	}
	public void setFomSetting(JFrame fomSetting) {
		this.fomSetting = fomSetting;
	}
	public void setFomSelect(JFrame fomSelect) {
		this.fomSelect = fomSelect;
	}
	public void setTxml(ParsingXML txml) {
		this.txml = txml;
	}
	
	private void gcsCreate(){
		JTextField federationName = ((FederationSetting)federationSetting).getTextField();
		if( federationName.getText().trim().equals("") ){
			JOptionPane.showMessageDialog(null, "페더레이션 이름을 설정하세요.");
			return;
		}
		String selected = "";
		JRadioButton radioButton = ((FomVersionSelect)fomSetting).getRadioButton();
		JRadioButton rdbtnNewRadioButton = ((FomVersionSelect)fomSetting).getRdbtnNewRadioButton();
		if( radioButton.isSelected() ) selected = radioButton.getText(); 
		else selected = rdbtnNewRadioButton.getText();
		
		if(selected.trim().equals("")){
			JOptionPane.showMessageDialog(null, "버젼을 설정하세요.");//메세지 나올 가능성은 없다.
			return;
		}
		
		String filePath = ((FomSelect)fomSelect).getFilePaths();
		if(filePath.trim().equals("")){
			JOptionPane.showMessageDialog(null, "FOM파일을 선택 하세요.");
			return;
		}
		txml.setData(filePath);
		txml.csCreate( federationName.getText(), selected, "" );
		XmlToDBSchema xtds = new XmlToDBSchema();
		xtds.createDBSchma();
	}
	
	private void lcsCraete() {
		// TODO Auto-generated method stub
		JTable somFiles = ((federateSetting)somFilesTable).getTable();
		String federateName;
		String siteId;
		String som;
		ParsingXML txml = new ParsingXML();
		for(int i = 0; i < somFiles.getRowCount(); i++ ){
			int j = 1;
			federateName = (String) somFiles.getValueAt(i, j++);
			siteId = (String) somFiles.getValueAt(i, j++);
			som = (String) somFiles.getValueAt(i, j++ + 1);
			txml.setData(som);
			txml.csCreate(federateName, "IEEE 1516.2", siteId);
		}
	}
}
