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











// 브라우징 트리에 필요한 파일들
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
		//default frame 생성
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 530); // 기본 창 크기 조정
		setTitle("PROTOTYPE");
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(((screenSize.width - frameSize.width)/2), ((screenSize.height - frameSize.height)/2));
		
		
		// ------------------------------------ 기본 패널정의 ----------------------------------
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
	  
		// -------------------------------  트리 테이블 및 선택한 트리의 테이블 이름 토큰화 및 테이블 새로고침 -------------------------------
	
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("DB");
		
		JTree tree = new JTree(root);
		
		// 트리 클릭 리스너
		tree.addMouseListener(new MouseAdapter() {
		      public void mouseClicked(MouseEvent me) {
		        doMouseClicked(me);
		      }

			private void doMouseClicked(MouseEvent me) {

				TreePath tp = tree.getPathForLocation(me.getX(), me.getY());
				String result;
				
				if (tp != null){
				
					// component에 선택한 트리 이름을 저장하고 스키마 이름인 CSH. 를 포함하는 부분의 인덱스를 1 추가하여 index에 저장
					// substring으로 해당 인덱스부터의 이름을 result에 저장, dbmanager.createTable에 테이블모델 model, 토큰화된 이름 result 를 넣어 실행.  
					
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
		
		
		
		// ------------------------------- 테이블 구현 -------------------------------------------
		
		JTable table;
		TextField tfSchema, tfTable, tfName;
		JTable table2;

		
		String [][] data = null;
		String title[]={"스키마","테이블","이름","유형"};
		
		JPanel pTop = new JPanel();
		tfSchema = new TextField(5);
		pTop.add(new JLabel("스키마"));
		pTop.add(tfSchema);
		
		tfTable = new TextField(5);
		pTop.add(new JLabel("테이블"));
		pTop.add(tfTable);
		
		tfName = new TextField(5);
		pTop.add(new JLabel("이름"));
		pTop.add(tfName);
		
		model = new DefaultTableModel(data, title);
		table2 =new JTable(model);
		jsp = new JScrollPane(table2);
		jsp.setBounds(300, 5, 580, 450);
		jsp.setVisible(false);
		panel.add(jsp, BorderLayout.CENTER);
		
		this.add(panel);
		this.setVisible(true);
		
		// ------------------------------  메뉴바 및 메뉴버튼 추가부분  -------------------------------
		 
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		/*
		JButton inputSetting = new JButton("입력 설정");
		menuBar.add(inputSetting);
		
		inputSetting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fomSetting.setVisible(true);
			}
		});
		
		JButton outputSetting = new JButton("출력 설정");
		menuBar.add(outputSetting);
		
		outputSetting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DBMSSettingFrame.setVisible(true);
			}
		});
		*/
		
		JButton convert = new JButton(" 스키마 생성 ");
		menuBar.add(convert);
		
		JButton sync = new JButton(" 동기화 확인 ");
		menuBar.add(sync);
		
		JButton pfm = new JButton(" 성능 측정");
		menuBar.add(pfm);
		
		JButton help = new JButton(" 도움말 ");
		menuBar.add(help);
	
		
		// ------------------------ 스키마 생성 버튼 convert에 파일 선택 및 스키마 생성 이벤트 추가  ---------------------------------------
		

		filePath = new JTextField();
		filePath.setEnabled(false);
		
		convert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Frame file = new Frame();
				JFileChooser fileChooser = new JFileChooser("D:\\Dropbox\\졸업프로젝트의 팀 폴더\\testXMLPar\\testXMLPar\\FOM");
				int result = fileChooser.showOpenDialog(file);
				if( result == JFileChooser.APPROVE_OPTION ){
					File selectedFile = fileChooser.getSelectedFile();
					if( selectedFile.toString().contains(".xml")) {
						System.out.println("XML FILE SELECTED : " + selectedFile.toString());
						filePath.setText(selectedFile.toString()); 
						JOptionPane.showMessageDialog(file, "스키마 생성이 완료되었습니다.");
					} else {
						JOptionPane.showMessageDialog(file, "xml파일을 선택하여 주십시오.");
					}

					filePaths = filePath.getText();
					if ((filePaths.toString().contains(".xml"))) createSchema(filePaths);
					
					dbManager.createTree(root);   // 트리 생성
				}
			}
		});
		
	}
	
	// -------------------------------- 스키마 생성 함수 --------------------------------------
	 
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
				
				// 현재 DB에 이미 스키마가 있을시엔 생성되지 않는 오류 있음. 오류없이 생성 위해서는 DB에서 해당 스키마를 제거해 주어야 함.
				
				
			} else {
				System.out.println("NO FILE IN test.filePaths");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
