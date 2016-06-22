package ui.dbmsSetting;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class DBMSSetting extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public DBMSSetting() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 409, 168);
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(((screenSize.width - frameSize.width)/2), ((screenSize.height - frameSize.height)/2));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		Border radioBorder = BorderFactory.createEtchedBorder();
		radioBorder = BorderFactory.createTitledBorder(radioBorder, "DBMS 종류");
		panel_3.setBorder(radioBorder);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));

		JRadioButton ORACLE = new JRadioButton("ORACLE");
		JRadioButton MSSQLServer = new JRadioButton("MS SQLServer");
		ButtonGroup bgDB = new ButtonGroup();
		bgDB.add(ORACLE);bgDB.add(MSSQLServer);
		ORACLE.setSelected(true);
		
		
		panel_3.add(ORACLE);
		panel_3.add(MSSQLServer);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		Border radioBorder1 = BorderFactory.createEtchedBorder();
		radioBorder1 = BorderFactory.createTitledBorder(radioBorder, "UDT 저장 옵션");
		panel_4.setBorder(radioBorder1);
		
		
		
		panel_2.add(panel_4, BorderLayout.CENTER);
		JRadioButton object = new JRadioButton("객체 유형");
		JRadioButton binary = new JRadioButton("이진 데이터");
		ButtonGroup bgData = new ButtonGroup();
		bgData.add(object);bgData.add(binary);
		object.setSelected(true);

		
		
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		panel_4.add(object);
		panel_4.add(binary);
		
		JPanel panel_5 = new JPanel();
		contentPane.add(panel_5, BorderLayout.SOUTH);
		
		JButton check = new JButton("확인");
		JButton cancle = new JButton("취소");
		cancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel_5.add(check);
		panel_5.add(cancle);
	}

}
