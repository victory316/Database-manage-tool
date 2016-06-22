package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
 
public class DBManager {
    private Connection conn;
	PreparedStatement pstmt = null;
 
    public DBManager(/*parameter oracl or db2 check*/) {
        try {
        	/*
        	*
        	*mssql 접속.
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://168.188.127.88:1433;DatabaseName=simpleData", 
                    "simpleData", "simple");
        	*/
//        	Class.forName("oracle.jdbc.driver.OracleDriver");
//        	conn = DriverManager.getConnection("jdbc:oracle:thin:@168.188.127.88:1521:testdb","testdb","1234");
        
        	
        	Class.forName("com.ibm.db2.jcc.DB2Driver");
			conn = DriverManager.getConnection("jdbc:db2://localhost:50000/TESTS", "CSH", "qnfehwj");
			
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    public Connection getConn() {
        return conn;
    }
    
    public void createTree(DefaultMutableTreeNode root) {

		try {
			String sql = "SELECT  substr(rtrim(TABSCHEMA)||'.'||TABNAME ,1,50) FROM SYSCAT.TABLES WHERE TABSCHEMA IN('CSH')";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
                DefaultMutableTreeNode tree1 = new DefaultMutableTreeNode(rs.getString(1));
        		root.add(tree1);
           }
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//pstmt.close();
				//conn.close();
			} catch( Exception e) {
				e.printStackTrace();
			}
		}
    	
    }
    
    public void createTable(DefaultTableModel model, String tabname) {
    	try {
			String sql = "SELECT * FROM SYSCAT.COLUMNS WHERE TABNAME = '" + tabname + "'";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			model.setNumRows(0);  // 테이블 초기화 구문
			
			while(rs.next()){
				String[] add = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(6) };
				model.addRow(add);
		    }
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//pstmt.close();
				//conn.close();
			} catch( Exception e) {
				e.printStackTrace();
			}
		}
    }
    
    public void allClose( PreparedStatement pstmt, ResultSet rs, Connection conn ){
			try {
				if( pstmt != null )	pstmt.close();
				if( rs != null ) rs.close();
				if( conn != null ) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
}
