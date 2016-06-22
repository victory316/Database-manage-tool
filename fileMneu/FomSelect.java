package ui.fileMneu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FomSelect extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField filePath;
	private String datas; 
	private String filePaths;
	private JButton check;
	/**
	 * Create the frame.
	 */
	public FomSelect( String dataType ) {
		this.datas = dataType;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 562, 105);
		setTitle("FOM ���� ����");
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(((screenSize.width - frameSize.width)/2), ((screenSize.height - frameSize.height)/2));
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JLabel label = new JLabel("FOM ���ϸ� :");
		panel.add(label);
		
		filePath = new JTextField();
		filePath.setEnabled(false);
		panel.add(filePath);
		filePath.setColumns(3);
		
		JButton findFom = new JButton("ã�ƺ���");
		findFom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame file = new Frame();
				JFileChooser fileChooser = new JFileChooser("D:\\lab\\3.�������� �� �ڷ�\\3. �������\\FOM2DB\\fom2db\\m&s");
				int result = fileChooser.showOpenDialog(file);
				if( result == JFileChooser.APPROVE_OPTION ){
					File selectedFile = fileChooser.getSelectedFile();
					if( selectedFile.toString().contains(".xml"))	filePath.setText(selectedFile.toString());
					else JOptionPane.showMessageDialog(file, "xml������ �����Ͽ� �ֽʽÿ�.");
				}
				
				filePaths = filePath.getText();
			}
		});
		panel.add(findFom);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		check = new JButton("Ȯ��");
		panel_1.add(check);
		
		JButton cancle = new JButton("���");
		cancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		panel_1.add(cancle);
		
	}
	public JButton getCheck() {
		return check;
	}
	public String getFilePaths() {
		return filePaths;
	}
}
