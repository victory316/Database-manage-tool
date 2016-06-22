package ui.fileMneu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class UserDefinedTypeList extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<String> definedData = new JList<String>();
	private JButton finish;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public UserDefinedTypeList( String dataType ) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 431, 398);
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(((screenSize.width - frameSize.width)/2), ((screenSize.height - frameSize.height)/2));
		setTitle("사용자정의타입 목록");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_5.add(panel_3, BorderLayout.WEST);
		
		JLabel list = new JLabel("사용자 정의 타입 목록");
		list.setVerticalAlignment(SwingConstants.TOP);
		panel_3.add(list);
		list.setHorizontalAlignment(SwingConstants.LEFT);
		
		JPanel panel_4 = new JPanel();
		panel_5.add(panel_4, BorderLayout.EAST);
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		scrollPane.setEnabled(false);
		
		JPanel layeredPane = new JPanel();
		scrollPane.setViewportView(layeredPane);
		layeredPane.add(definedData);
		
		
		
		definedData.setSelectedIndex(0);
		
		

		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_1.add(panel_2, BorderLayout.NORTH);
		
		finish = new JButton("마침");
		
		panel_2.add(finish);
	}

	public JList<String> getDefinedData() {
		return definedData;
	}

	public JButton getFinish() {
		return finish;
	}

	
	
}
