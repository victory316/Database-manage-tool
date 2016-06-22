package ui.fileMneu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;
import javax.swing.ScrollPaneConstants;

/**
 * @author sonjinhyuk
 *
 */
public class UserDefined extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JPanel datasPanel;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public UserDefined( ) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("유저 defined Type 정의");
		setBounds(100, 100, 717, 465);
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
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scrollPane);
		
		datasPanel = new JPanel();
		scrollPane.setViewportView(datasPanel);
		datasPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		
		JButton createButton = new JButton("확인");
		buttonPanel.add(createButton);
		
		JButton cancle = new JButton("취소");
		cancle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		buttonPanel.add(cancle);
		
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public JPanel getDatasPanel() {
		return datasPanel;
	}

	
}	
