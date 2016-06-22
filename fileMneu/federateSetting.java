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
		setTitle("�������Ʈ ����");
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
		
		federationName = new JLabel("������̼� �̸� : ");
		ferationNameAndCount.add(federationName);
		
		
		
		ferateCount = new JLabel("�������Ʈ �� : ");
		ferationNameAndCount.add(ferateCount);
		//���̺� ���� ���
		
		String columnNames[] = { " ", "�������Ʈ��", "����ƮID", "���ǿ���", "SOM����", "����" };
		Object rowData[][] = null;
		
		tableScollPane = new JScrollPane();
		tableScollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tablePanel.add(tableScollPane, BorderLayout.CENTER);

		
		defaultTableModel = new DefaultTableModel(rowData,columnNames);
		table = new JTable(defaultTableModel);
		table.getTableHeader().setReorderingAllowed(false);
		tableScollPane.setViewportView(table);
		
		//���̺� ���� ���

		
		//��ư ���
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		//��ư ���
		//��ư��
		insert = new JButton("�������Ʈ �߰�");
		panel.add(insert);
		settingFinish = new JButton("���� �Ϸ�");
		panel.add(settingFinish);
		settingFinish.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		//��ư��
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
