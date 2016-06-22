package ui.main;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;

import testDB.XmlToDBSchema;
import testXMLPar.ParsingXML;
import ui.api.ButtonEditor;
import ui.api.ButtonRenderer;
import ui.dbmsSetting.DBMSSetting;
import util.DBManager;
import Data.DataType;
import Transformation.CreateUserDefinedType;











// ����¡ Ʈ���� �ʿ��� ���ϵ�
import java.awt.BorderLayout;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreePath;

public class test extends JFrame{
	private static final long serialVersionUID = 1L;
	private String filePaths;
	private JTextField filePath;
	private DefaultTableModel model;
	private JScrollPane jsp;
	private DBManager dbManager = new DBManager();
	
	/**
	 * 
	 */
	public test() {
		//default frame ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 530); // �⺻ â ũ�� ����
		setTitle("PROTOTYPE");
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(((screenSize.width - frameSize.width)/2), ((screenSize.height - frameSize.height)/2));
		
		
		// ------------------------------------ �⺻ �г����� ----------------------------------
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
	  
		// -------------------------------  Ʈ�� ���̺� �� ������ Ʈ���� ���̺� �̸� ��ūȭ �� ���̺� ���ΰ�ħ -------------------------------
	
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("DB");
		
		JTree tree = new JTree(root);
		
		// Ʈ�� Ŭ�� ������
		tree.addMouseListener(new MouseAdapter() {
		      public void mouseClicked(MouseEvent me) {
		        doMouseClicked(me);
		      }

			private void doMouseClicked(MouseEvent me) {

				TreePath tp = tree.getPathForLocation(me.getX(), me.getY());
				String result;
				
				if (tp != null){
				
					// component�� ������ Ʈ�� �̸��� �����ϰ� ��Ű�� �̸��� CSH. �� �����ϴ� �κ��� �ε����� 1 �߰��Ͽ� index�� ����
					// substring���� �ش� �ε��������� �̸��� result�� ����, dbmanager.createTable�� ���̺�� model, ��ūȭ�� �̸� result �� �־� ����.  
					
					String component = tp.getLastPathComponent().toString();
					if(component.contains("CSH.")){
						int index = component.indexOf(".") + 1;
						result = component.substring(index);
						dbManager.createTable(model, result);
						if (!jsp.isVisible()) jsp.setVisible(true);
					}
				}
			}
		    });
		
		JScrollPane scrollPane = new JScrollPane(tree);
		scrollPane.setBounds(5, 5, 290, 450);
		panel.add(scrollPane, BorderLayout.CENTER);
		
		
		
		// ------------------------------- ���̺� ���� -------------------------------------------
		
		JTable table;
		TextField tfSchema, tfTable, tfName;
		JTable table2;

		
		String [][] data = null;
		String title[]={"��Ű��","���̺�","�̸�","����"};
		
		JPanel pTop = new JPanel();
		tfSchema = new TextField(5);
		pTop.add(new JLabel("��Ű��"));
		pTop.add(tfSchema);
		
		tfTable = new TextField(5);
		pTop.add(new JLabel("���̺�"));
		pTop.add(tfTable);
		
		tfName = new TextField(5);
		pTop.add(new JLabel("�̸�"));
		pTop.add(tfName);
		
		model = new DefaultTableModel(data, title);
		table2 =new JTable(model);
		jsp = new JScrollPane(table2);
		jsp.setBounds(300, 5, 580, 450);
		jsp.setVisible(false);
		panel.add(jsp, BorderLayout.CENTER);
		
		this.add(panel);
		this.setVisible(true);
		
		// ------------------------------  �޴��� �� �޴���ư �߰��κ�  -------------------------------
		 
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		/*
		JButton inputSetting = new JButton("�Է� ����");
		menuBar.add(inputSetting);
		
		inputSetting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fomSetting.setVisible(true);
			}
		});
		
		JButton outputSetting = new JButton("��� ����");
		menuBar.add(outputSetting);
		
		outputSetting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DBMSSettingFrame.setVisible(true);
			}
		});
		*/
		
		JButton convert = new JButton(" ��Ű�� ���� ");
		menuBar.add(convert);
		
		JButton sync = new JButton(" ����ȭ Ȯ�� ");
		menuBar.add(sync);
		
		JButton pfm = new JButton(" ���� ����");
		menuBar.add(pfm);
		
		JButton help = new JButton(" ���� ");
		menuBar.add(help);
	
		
		// ------------------------ ��Ű�� ���� ��ư convert�� ���� ���� �� ��Ű�� ���� �̺�Ʈ �߰�  ---------------------------------------
		

		filePath = new JTextField();
		filePath.setEnabled(false);
		
		convert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Frame file = new Frame();
				JFileChooser fileChooser = new JFileChooser("D:\\Dropbox\\����������Ʈ�� �� ����\\testXMLPar\\testXMLPar\\FOM");
				int result = fileChooser.showOpenDialog(file);
				if( result == JFileChooser.APPROVE_OPTION ){
					File selectedFile = fileChooser.getSelectedFile();
					if( selectedFile.toString().contains(".xml")) {
						System.out.println("XML FILE SELECTED : " + selectedFile.toString());
						filePath.setText(selectedFile.toString()); 
						JOptionPane.showMessageDialog(file, "��Ű�� ������ �Ϸ�Ǿ����ϴ�.");
					} else {
						JOptionPane.showMessageDialog(file, "xml������ �����Ͽ� �ֽʽÿ�.");
					}

					filePaths = filePath.getText();
					if ((filePaths.toString().contains(".xml"))) createSchema(filePaths);
					
					dbManager.createTree(root);   // Ʈ�� ����
				}
			}
		});
		
	}
	
	// -------------------------------- ��Ű�� ���� �Լ� --------------------------------------
	 
	public void createSchema(String path) {
		try {
			ParsingXML txml = new ParsingXML();
			if (filePaths != null ) {
				txml.setData(filePaths);
				XmlToDBSchema xtdb = new XmlToDBSchema();

				txml.csCreate("TestFOM2DBMap", "", "");
				System.out.println("TXT FILE CREATED\n");
				

				System.out.println("CREATING SCHEMA ..");
				
				xtdb.createDBSchma();
				System.out.println("\nSCHEMA CREATED");
				
				// ���� DB�� �̹� ��Ű���� �����ÿ� �������� �ʴ� ���� ����. �������� ���� ���ؼ��� DB���� �ش� ��Ű���� ������ �־�� ��.
				
				
			} else {
				System.out.println("NO FILE IN test.filePaths");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
