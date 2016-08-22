package ui.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import util.DBManager;
import ui.main.test;
import net.miginfocom.swing.MigLayout;

public class DbQuery extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel schemaName;   
	private JLabel tableName;
	private JLabel column;
	private JLabel type; 
	private JTextField schemaNameField;
	private JTextField columnField;
	private JTextField tableNameField;
	private JTextField typeField;
	private JPanel panel;
	private JButton makeButton;
	private JButton cancleButton;
	private JButton deleteButton;
	private Connection conn;
	private Font f1;
	private PreparedStatement pstmt = null;
	
	
	public DbQuery() {
		
		Frame file = new Frame();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(" 테이블 검색 ");
		setBounds(100, 100, 380, 230);
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(((screenSize.width - frameSize.width)/2), ((screenSize.height - frameSize.height)/2));
		
		JPanel input = new JPanel();
		getContentPane().add(input, BorderLayout.CENTER);
		
		try {
			conn = DriverManager.getConnection("jdbc:db2://localhost:50000/TEST", "CSH", "qnfehwj");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		schemaName   = new JLabel(" 스키마 ");
		tableName = new JLabel(" 테이블 ");
		column = new JLabel(" 컬럼 이름 ");
		type = new JLabel(" 자료 유형 ");
		
		input.setLayout(new MigLayout("", "[97px][185.00px][]", "[15px][21px][15px][21px][15px][21px][15px][21px][23px]"));
		
		input.add(schemaName, "cell 0 2,alignx left,aligny top");
		schemaNameField = new JTextField();
		schemaNameField.setColumns(50);
		input.add(schemaNameField, "cell 1 2,alignx left,aligny top");
		
		input.add(tableName, "cell 0 4,alignx left,aligny top");
		tableNameField = new JTextField();
		tableNameField.setColumns(50);
		input.add(tableNameField, "cell 1 4,alignx left,aligny top");
		
		input.add(column, "cell 0 6,alignx left,aligny top");
		columnField = new JTextField();
		columnField.setColumns(50);
		input.add(columnField, "cell 1 6,alignx left,aligny top");
		
		input.add(type, "cell 0 8,alignx left,aligny top");
		typeField = new JTextField();
		typeField.setColumns(50);
		input.add(typeField, "cell 1 8,alignx left,aligny top");
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		makeButton = new JButton("검색");
		panel.add(makeButton);

		
		makeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				JOptionPane.showMessageDialog(file, "데이터 검색을 시작합니다.");
				// 텍스트 필드 초기화
				createQueryTable(test.getTable());
				schemaNameField.setText(null);
				tableNameField.setText(null);
				columnField.setText(null);
				typeField.setText(null);
			}
		});
		
		cancleButton = new JButton("취소");
		cancleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		panel.add(cancleButton);
	
    	// ----------------- 글씨체 설정 --------------------------------------------------------------
        
        f1 = new Font("맑은 고딕", Font.PLAIN, 13);
        schemaName.setFont(f1);   
    	tableName.setFont(f1);
    	column.setFont(f1);
    	type.setFont(f1);
    	makeButton.setFont(f1);
    	cancleButton.setFont(f1);
	}
	

	
	public JButton getMakeButton() {
		return makeButton;
	}
	
	public ArrayList<String> insertTable(){
		ArrayList<String> returndata = new ArrayList<String>();
		returndata.add(schemaNameField.getText());
		returndata.add(tableNameField.getText());
		returndata.add(columnField.getText());
		returndata.add(typeField.getText());
		dispose();
		return returndata;
	}
	
	public JButton getDeleteButton() {
		return deleteButton;
	}
	
    public void createQueryTable(DefaultTableModel model) {

		Frame file = new Frame();
    	String schema = this.insertTable().get(0);
    	String table = this.insertTable().get(1);
    	String column = this.insertTable().get(2);
    	String type = this.insertTable().get(3);
    	model.setNumRows(0);  // 테이블 초기화 구문
    	
    	if (schema != null) {
    		
    		try {
    			String sql = "SELECT TABNAME FROM SYSCAT.TABLES WHERE TABSCHEMA = '" + schema + "'";
    			pstmt = conn.prepareStatement(sql);
    			ResultSet rs = pstmt.executeQuery();
    			
    			while(rs.next()){
    				System.out.println("schema : " + rs.getString(1));
    	    		try {
        				String sql2 = "SELECT * FROM SYSCAT.COLUMNS WHERE TABNAME = '" + rs.getString(1) + "'";
        				pstmt = conn.prepareStatement(sql2);
        				ResultSet rs2 = pstmt.executeQuery();
        				
        				while(rs2.next()){
            				System.out.println("sql2 : " + sql2);
            				
        					String[] add = { rs2.getString(1), rs2.getString(2), rs2.getString(3), rs2.getString(6) };
        					model.addRow(add);
        			    }
        			} catch (Exception e) {
        				e.printStackTrace();
        			}
    		    }

				if (!test.getJsp().isVisible()) test.getJsp().setVisible(true);
				
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	
    	if (table != null) {
    		
    		try {
    			String sql = "SELECT TABNAME FROM SYSCAT.TABLES WHERE TABSCHEMA = '" + schema + "'";
    			pstmt = conn.prepareStatement(sql);
    			ResultSet rs = pstmt.executeQuery();
    			
    			while(rs.next()){
    	    		try {
        				String sql2 = "SELECT * FROM SYSCAT.COLUMNS WHERE TABNAME = '" + rs.getString(1) + "'";
        				pstmt = conn.prepareStatement(sql2);
        				ResultSet rs2 = pstmt.executeQuery();
        				
        				while(rs2.next()){
        					String[] add = { rs2.getString(1), rs2.getString(2), rs2.getString(3), rs2.getString(6) };
        					model.addRow(add);
        			    }
        				
						if (!test.getJsp().isVisible()) test.getJsp().setVisible(true);
						
        			} catch (Exception e) {
        				e.printStackTrace();
        			}
    		    }
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	
    	if (column != null) {
    		
    		try {
    			String sql = "SELECT TABNAME FROM SYSCAT.TABLES WHERE TABSCHEMA = '" + schema + "'";
    			pstmt = conn.prepareStatement(sql);
    			ResultSet rs = pstmt.executeQuery();
    			
    			while(rs.next()){
    	    		try {
        				String sql2 = "SELECT * FROM SYSCAT.COLUMNS WHERE TABNAME = '" + rs.getString(1) + "'";
        				pstmt = conn.prepareStatement(sql2);
        				ResultSet rs2 = pstmt.executeQuery();
        				
        				while(rs2.next()){
        					String[] add = { rs2.getString(1), rs2.getString(2), rs2.getString(3), rs2.getString(6) };
        					model.addRow(add);
        			    }
        				
						if (!test.getJsp().isVisible()) test.getJsp().setVisible(true);
						
        			} catch (Exception e) {
        				e.printStackTrace();
        			}
    		    }
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	if (type != null) {
    		
    		try {
    			String sql = "SELECT TABNAME FROM SYSCAT.TABLES WHERE TABSCHEMA = '" + schema + "'";
    			pstmt = conn.prepareStatement(sql);
    			ResultSet rs = pstmt.executeQuery();
    			
    			while(rs.next()){
    	    		try {
        				String sql2 = "SELECT * FROM SYSCAT.COLUMNS WHERE TABNAME = '" + rs.getString(1) + "'";
        				pstmt = conn.prepareStatement(sql2);
        				ResultSet rs2 = pstmt.executeQuery();
        				
        				while(rs2.next()){
        					String[] add = { rs2.getString(1), rs2.getString(2), rs2.getString(3), rs2.getString(6) };
        					model.addRow(add);
        			    }
        				
						if (!test.getJsp().isVisible()) test.getJsp().setVisible(true);

        			} catch (Exception e) {
        				e.printStackTrace();
        			}
    		    }
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	
    	// 결과값이 있을 때와 없을 때에 따라 메세지 출력
    	if (model.getRowCount() != 0) {
    		JOptionPane.showMessageDialog(file, "데이터 검색을 완료했습니다.");
    	} else {
    		JOptionPane.showMessageDialog(file, "조건에 해당하는 데이터가 없습니다.");
    	}
		
    }
    
    
	
}
