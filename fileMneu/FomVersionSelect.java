package ui.fileMneu;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

public class FomVersionSelect extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	private JButton check;
	private JRadioButton radioButton;
	private JRadioButton rdbtnNewRadioButton;
	public FomVersionSelect() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//해당 프레임 삭제
		setBounds(100, 100, 253, 101);
		setTitle("FOM버젼선택");
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setLocation(((screenSize.width - frameSize.width)/2), ((screenSize.height - frameSize.height)/2));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel layout = new JPanel();
		contentPane.add(layout, BorderLayout.CENTER);
		layout.setLayout(new BorderLayout(0, 0));
		
		JPanel IEEEselect = new JPanel();
		layout.add(IEEEselect, BorderLayout.WEST);
		IEEEselect.setLayout(new BorderLayout(0, 0));
		
		Border radioBorder = BorderFactory.createEtchedBorder();
		radioBorder = BorderFactory.createTitledBorder(radioBorder, "FOM 버전");
		contentPane.setBorder(radioBorder);
		radioButton = new JRadioButton("IEEE 1516.2", true);
		
		JPanel hlaSelect = new JPanel();
		layout.add(hlaSelect);
		hlaSelect.setLayout(new BorderLayout(0, 0));
		
		rdbtnNewRadioButton = new JRadioButton("HLA 1.3");
		
		ButtonGroup group = new ButtonGroup();
		group.add(radioButton);
		group.add(rdbtnNewRadioButton);
		
		IEEEselect.add(radioButton, BorderLayout.EAST);
		hlaSelect.add(rdbtnNewRadioButton, BorderLayout.CENTER);
		
		JPanel buttons = new JPanel();
		contentPane.add(buttons, BorderLayout.EAST);
		buttons.setLayout(new BorderLayout(0, 0));
		
		JPanel inButton = new JPanel();
		buttons.add(inButton, BorderLayout.CENTER);
		inButton.setLayout(new GridLayout(0, 1, 0, 0));
		
		check = new JButton("확인");
		
		check.setAlignmentX(Component.CENTER_ALIGNMENT);
		inButton.add(check);
		
		JButton cancle = new JButton("취소");
		cancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancle.setAlignmentX(Component.CENTER_ALIGNMENT);
		inButton.add(cancle);
		inButton.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{check, cancle}));
	}
	public JButton getCheck() {
		return check;
	}
	public JRadioButton getRadioButton() {
		return radioButton;
	}
	public JRadioButton getRdbtnNewRadioButton() {
		return rdbtnNewRadioButton;
	}
	
	
}
