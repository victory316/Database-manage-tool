package ui.dbmsSetting;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

import java.awt.FlowLayout;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DBConnetionStructClass extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JRadioButton dual;
	private JRadioButton mixed;
	private JRadioButton aar;
	private JButton checkButton;
	private JButton btnNewButton;
	/**
	 * Create the frame.
	 */
	public DBConnetionStructClass() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("DB 연동 구조 설정");
		setBounds(100, 100, 478, 101);
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(((screenSize.width - frameSize.width)/2), ((screenSize.height - frameSize.height)/2));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		Border radioBorder = BorderFactory.createEtchedBorder();
		radioBorder = BorderFactory.createTitledBorder(radioBorder, "DB 연동 구조 설정");
		contentPane.setBorder(radioBorder);
		
		dual = new JRadioButton("Dual연동 모드",true);
		mixed = new JRadioButton("Mixed연동 모드");
		aar = new JRadioButton("AAR연동 모드");
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		contentPane.add(dual);
		contentPane.add(mixed);
		contentPane.add(aar);
		
		ButtonGroup group = new ButtonGroup();
		group.add(dual);
		group.add(mixed);
		group.add(aar);
		
		
		checkButton = new JButton("확인");
		checkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		contentPane.add(checkButton);
		
		
		
		
	}

}
