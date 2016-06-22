package ui.fileMneu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class federateSetting extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton insert;
	private JButton settingFinish;
	private JPanel ferationNameAndCount;
	private JLabel federationName;
	private JLabel ferateCount;
	private JScrollPane tableScollPane;
	private JTable table;
	private DefaultTableModel defaultTableModel;
	public federateSetting() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("페더레이트 설정");
		setBounds(100, 100, 486, 230);
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(((screenSize.width - frameSize.width)/2), ((screenSize.height - frameSize.height)/2));
		
		
		JPanel tablePanel = new JPanel();
		getContentPane().add(tablePanel, BorderLayout.CENTER);
		tablePanel.setLayout(new BorderLayout(0, 0));
		
		ferationNameAndCount = new JPanel();
		tablePanel.add(ferationNameAndCount, BorderLayout.NORTH);
		ferationNameAndCount.setLayout(new GridLayout(0, 1, 0, 0));
		
		federationName = new JLabel("페더레이션 이름 : ");
		ferationNameAndCount.add(federationName);
		
		
		
		ferateCount = new JLabel("페더레이트 수 : ");
		ferationNameAndCount.add(ferateCount);
		//테이블 만들 페널
		
		String columnNames[] = { " ", "페더레이트명", "사이트ID", "모의영역", "SOM파일", "편집" };
		Object rowData[][] = null;
		
		tableScollPane = new JScrollPane();
		tableScollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tablePanel.add(tableScollPane, BorderLayout.CENTER);

		
		defaultTableModel = new DefaultTableModel(rowData,columnNames);
		table = new JTable(defaultTableModel);
		table.getTableHeader().setReorderingAllowed(false);
		tableScollPane.setViewportView(table);
		
		//테이블 만들 페널

		
		//버튼 페널
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		//버튼 페널
		//버튼들
		insert = new JButton("페더레이트 추가");
		panel.add(insert);
		settingFinish = new JButton("설정 완료");
		panel.add(settingFinish);
		settingFinish.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		//버튼들
	}
	public JTable getTable() {
		return table;
	}
	public JButton getInsert() {
		return insert;
	}
	public JButton getSettingFinish() {
		return settingFinish;
	}
	
	public JLabel getFederationName() {
		return federationName;
	}
	public JLabel getFerateCount() {
		return ferateCount;
	}
	public DefaultTableModel getDefaultTableModel() {
		return defaultTableModel;
	}
	
	
}
