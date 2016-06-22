package ui.fileMneu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class AddFederate extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel federateName;   
	private JLabel siteID;
	private JLabel section;
	private JLabel somFileSelect; 
	private JTextField federateNameField;
	private JTextField sectionField;
	private JTextField siteIDField;
	private JTextField filePath;
	private JPanel panel;
	private JButton makeButton;
	private JButton cancleButton;
	private JButton deleteButton;
	private String filePaths;
	public AddFederate( ArrayList<String> data ) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("페더레이트 추가");
		setBounds(100, 100, 479, 283);
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(((screenSize.width - frameSize.width)/2), ((screenSize.height - frameSize.height)/2));
		
		JPanel input = new JPanel();
		getContentPane().add(input, BorderLayout.CENTER);
		
		federateName   = new JLabel("페더레이트명");
		siteID = new JLabel("사이트ID");
		section = new JLabel("모의영역");
		somFileSelect = new JLabel("SOM파일");
		
		filePath = new JTextField();
		filePath.setEnabled(false);
		filePath.setColumns(55);
		input.setLayout(new MigLayout("", "[97px][185.00px][]", "[15px][21px][15px][21px][15px][21px][15px][21px][23px]"));
		input.add(federateName, "cell 0 0,alignx left,aligny top");
		
		federateNameField = new JTextField();
		federateNameField.setColumns(10);
		input.add(federateNameField, "cell 1 0,alignx left,aligny top");
		input.add(siteID, "cell 0 2,alignx left,aligny top");
		
		siteIDField = new JTextField();
		siteIDField.setColumns(10);
		input.add(siteIDField, "cell 1 2,alignx left,aligny top");
		input.add(section, "cell 0 4,alignx left,aligny top");
		
		sectionField = new JTextField();
		sectionField.setColumns(10);
		input.add(sectionField, "cell 1 4,alignx left,aligny top");
		input.add(somFileSelect, "cell 0 6,alignx left,aligny top");
		input.add(filePath, "cell 1 6 1 2,alignx left,aligny top");
		
		JButton findFom = new JButton("찾아보기");
		
		findFom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame file = new Frame();
				JFileChooser fileChooser = new JFileChooser("E:\\lab\\국방과제\\workspace\\testXMLpar\\src");
				int result = fileChooser.showOpenDialog(file);
				if( result == JFileChooser.APPROVE_OPTION ){
					File selectedFile = fileChooser.getSelectedFile();
					if( selectedFile.toString().contains(".xml"))	filePath.setText(selectedFile.toString());
					else JOptionPane.showMessageDialog(file, "xml파일을 선택하여 주십시오.");
				}
				filePaths = filePath.getText();
			}
		});
		
//		panel.add(findFom);
		input.add(findFom, "cell 2 6,alignx left,aligny top");
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		makeButton = new JButton("확인");
		panel.add(makeButton);
		
		cancleButton = new JButton("취소");
		cancleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		panel.add(cancleButton);
		setVisible(true);
	}
	
	
	public JButton getMakeButton() {
		return makeButton;
	}
	
	public ArrayList<String> insertTable(){
		ArrayList<String> returndata = new ArrayList<String>();
		returndata.add(federateNameField.getText());
		returndata.add(siteIDField.getText());
		returndata.add(sectionField.getText());
		returndata.add(filePath.getText());
		dispose();
		return returndata;
	}
	public JButton getDeleteButton() {
		return deleteButton;
	}
	
}
