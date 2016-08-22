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
import ui.menu.DbQuery;
import testXMLPar.ParsingXML;
import ui.api.ButtonEditor;
import ui.api.ButtonRenderer;
import util.DBManager;
import Data.DataType;




// 브라우징 트리에 필요한 파일들
import java.awt.BorderLayout;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreePath;
import javax.swing.*;
import javax.swing.tree.TreeNode;

import java.awt.*;
import java.util.Enumeration;

public class test extends JFrame{
	private static final long serialVersionUID = 1L;
	private String filePaths;
	private JTextField filePath;
	private JTextField queryField;
	private static DefaultTableModel model;
	private static JScrollPane jsp;
	private DBManager dbManager = new DBManager();
	private JFrame dbQuery = new DbQuery();
	private Font f1, f2;
	private JOptionPane messagebox = new JOptionPane();
	
	/*      
	 *  			160803 수정사항 : 스키마 생성시 데이터베이스에 이미 스키마가 존재하면 스키마를 생성하지 않음.
	 *  			160804 수정사항 : 데이터 질의메뉴 추가 및 메뉴창 구현
	 *  			160821 수정사항 : 메뉴 구성 변경, 폰트 변경
	 */
	public test() {
		
		//default frame 생성
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900,530);
		setTitle("분산 시뮬레이션 DB 관리도구");
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(((screenSize.width - frameSize.width)/2), ((screenSize.height - frameSize.height)/2));
		
		
		// ------------------------------------ 기본 패널정의 ----------------------------------
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
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
					if(true){
						int index = component.indexOf(".") + 1;
						result = component.substring(index);
						dbManager.createTable(model, result);
						if (!jsp.isVisible()) jsp.setVisible(true);
					}
				}
			}
		    });
		
		JScrollPane scrollPane = new JScrollPane(tree);
		scrollPane.setSize(100,100);
		panel.add(scrollPane, BorderLayout.WEST);
		
		
		// ------------------------------- 테이블 구현 -------------------------------------------
		
		JTable table2;

		
		String [][] data = null;
		String title[]={"스키마","테이블","이름","유형"};
		
		model = new DefaultTableModel(data, title);
		table2 = new JTable(model);
		jsp = new JScrollPane(table2);
		//jsp.setBounds(340, 35, 580, 450);
		//jsp.setSize(500,450);
		jsp.setVisible(false);
		panel.add(jsp, BorderLayout.CENTER);
		
		this.add(panel);
		this.setVisible(true);
		
		// ------------------------------  메뉴바 및 메뉴버튼 추가부분  -------------------------------
		 
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem convert = new JMenuItem(" 스키마 생성 ");
		JMenuItem delete = new JMenuItem(" 스키마 삭제 ");
		JMenuItem option = new JMenuItem(" BASE 모델 설정 ");
		JMenuItem search = new JMenuItem(" 데이터 검색 ");
		
		JMenu tableM = new JMenu(" 스키마 관리 ");
		menuBar.add(tableM);
		tableM.add(convert);
		tableM.add(delete);
		tableM.add(option);
		
		JMenu searchM = new JMenu(" 데이터 검색 ");
		searchM.add(search);
		menuBar.add(searchM);
		
		JMenu syncM = new JMenu(" 동기화 확인 ");
		menuBar.add(syncM);
		
		JMenu helpM = new JMenu(" 도움말 ");
		menuBar.add(helpM);
		

		// -------------------------------  데이터 질의 위한 텍스트 필드 정의 -----------------------------------------------------
		
		JPanel queryPanel = new JPanel(new BorderLayout());
		queryField = new JTextField();
		
		JLabel queryLabel = new JLabel(" 데이터 질의 : ");
		
		queryPanel.add(queryField);
		queryPanel.add(queryLabel, BorderLayout.WEST);
		panel.add(queryPanel, BorderLayout.NORTH);
		
		queryPanel.setVisible(true);
	
		// ------------------------ 테이블 생성 버튼 convert에 파일 선택 및 스키마 생성 이벤트 추가  ---------------------------------------
		

		filePath = new JTextField();
		filePath.setEnabled(false);
		dbManager.createTree(root); // 스키마가 이미 생성되었을때 테이블 조회를 가능하도록 한다. 
		
		
		convert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Frame file = new Frame();
				JFileChooser fileChooser = new JFileChooser("E:\\Dropbox\\졸업프로젝트의 팀 폴더\\testXMLPar\\testXMLPar\\FOM");
				setFileChooserFont(fileChooser.getComponents());
				
				int result = fileChooser.showOpenDialog(file);
				if( result == JFileChooser.APPROVE_OPTION ){
					File selectedFile = fileChooser.getSelectedFile();
					if( selectedFile.toString().contains(".xml")) {
						System.out.println("XML FILE SELECTED : " + selectedFile.toString());
						filePath.setText(selectedFile.toString());
					} else {
						JOptionPane.showMessageDialog(file, "xml파일을 선택하여 주십시오.");
					}

					filePaths = filePath.getText();
					if ((filePaths.toString().contains(".xml"))) createSchema(filePaths);
					
					dbManager.createTree(root);   // 트리 생성
					tree.repaint();
					for (int i = 0; i < tree.getRowCount(); i++) {
						tree.expandRow(i);
					}
				}
			}
		});
		  
		
		// ------------------------- 테이블 삭제 버튼 및 이벤트 추가  ---------------------------------------------
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dbManager.deleteTree(root);
				Frame file = new Frame();
				tree.repaint();
				System.out.println("\nSCHEMA DELETED");
				JOptionPane.showMessageDialog(file, "스키마가 삭제되었습니다.");
				}
		});
		
		// ------------------------ 테이블 검색 버튼 및 이벤트 추가  ---------------------------------------------
		search.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dbQuery.setVisible(true);
			}
		});
		

		// ------------------------------  UI 글씨체 설정  ------------------------------------------------
		
		f1 = new Font("맑은 고딕", Font.PLAIN, 13);
		f2 = new Font("맑은 고딕", Font.BOLD, 15);
		
		
		convert.setFont(f1);
		delete.setFont(f1);
		search.setFont(f1);
		option.setFont(f1);
		
		tableM.setFont(f1);
		syncM.setFont(f1);
		searchM.setFont(f1);
		helpM.setFont(f1);
		table2.setFont(f1);
		queryLabel.setFont(f1);
	
		UIManager.put("OptionPane.messageFont", f1);
		UIManager.put("OptionPane.buttonFont", f1);
		
		dbQuery.setFont(f1);
	}
	
	public void setFileChooserFont(Component[] comp)
	  {
	    for(int x = 0; x < comp.length; x++)
	    {
	      if(comp[x] instanceof Container) setFileChooserFont(((Container)comp[x]).getComponents());
	      try{comp[x].setFont(f1);} // 글씨체 설정
	      catch(Exception e){}//do nothing
	    }
	  }
	
	
	// -------------------------------- 스키마 생성 함수 --------------------------------------
	public void createSchema(String path) {
		Frame file = new Frame();
		try {
			ParsingXML txml = new ParsingXML();
			if (filePaths != null ) {
				txml.setData(filePaths);
				XmlToDBSchema xtdb = new XmlToDBSchema();
				txml.csCreate("TestFOM2DBMap", "", "");
				System.out.println("TXT FILE CREATED\n");
				System.out.println("CREATING SCHEMA ..");
				
				if(dbManager.checkTable(model) == 0) {
					xtdb.createDBSchma();
					System.out.println("\nSCHEMA CREATED");
					JOptionPane.showMessageDialog(file, "스키마 생성이 완료되었습니다.");
		
				} else {
					System.out.println("\nSCHEMA ALREADY EXIST");
					JOptionPane.showMessageDialog(file, "생성할 스키마가 DB에 이미 존재합니다.");
				}
				
			} else {
				System.out.println("NO FILE IN test.filePaths");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static DefaultTableModel getTable(){
		return model;
	}
	
	public static JScrollPane getJsp(){
		return jsp;
	}
}
