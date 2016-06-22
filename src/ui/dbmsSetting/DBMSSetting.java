package ui.dbmsSetting;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
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
		setBounds(100, 100, 300, 101); 
		setTitle("DBMS 종류 선택");
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
		//panel_3.setBorder(radioBorder);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));

		JRadioButton ORACLE = new JRadioButton("ORACLE");
		JRadioButton MSSQLServer = new JRadioButton("DB2");
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
		
		JPanel buttons = new JPanel();
		contentPane.add(buttons, BorderLayout.EAST);
		buttons.setLayout(new BorderLayout(0, 0));
		
		JPanel inButton = new JPanel();
		buttons.add(inButton, BorderLayout.CENTER);
		inButton.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton check = new JButton("확인");
		JButton cancle = new JButton("취소");
		
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		check.setAlignmentX(Component.CENTER_ALIGNMENT);
		cancle.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		inButton.add(check);
		inButton.add(cancle);
		
	}

}
