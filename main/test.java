package ui.main;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import testXMLPar.ParsingXML;
import ui.api.ButtonEditor;
import ui.api.ButtonRenderer;
import ui.api.CsCreateButtonActionListener;
import ui.dbmsSetting.DBConnetionStructClass;
import ui.dbmsSetting.DBMSSetting;
import ui.fileMneu.AddFederate;
import ui.fileMneu.FederationSetting;
import ui.fileMneu.FomSelect;
import ui.fileMneu.FomVersionSelect;
import ui.fileMneu.UserDefined;
import ui.fileMneu.UserDefinedTypeList;
import ui.fileMneu.federateSetting;
import Data.DataType;
import Transformation.ReplicationDirectoryInfo;
import Transformation.CreateUserDefinedType;

public class test extends JFrame{
	private static final long serialVersionUID = 1L;
	private JFrame federationSetting = new FederationSetting();//페더레이트셋팅
	private JFrame fomSetting = new FomVersionSelect();//폼셋팅
	private JFrame fomSelect = new FomSelect("");//폼 선택
	private JFrame uesrDefineType = new UserDefinedTypeList("");//유저디파인드타입 리스트 보기
	private JFrame userDefined = new UserDefined();//유저디파인드타입 자세히 보기
	private JFrame DBMSSettingFrame = new DBMSSetting();//db선택 하는 창
	private ParsingXML txml = new ParsingXML();//기존 소스
	private DataType dataTypeSelect = new DataType();//기존소스에 있는 데이터 타입 가져오는 클레스
	private federateSetting federateSetting = new federateSetting();//페더레트세팅창
	private AddFederate addFederate = null;//페더레이트 추가
	private DBConnetionStructClass dbConnetionStructClass = new DBConnetionStructClass();//연동 모드 설정
	private ArrayList<String> fixedData;
	/**
	 * 
	 */
	public test() {
		//default frame 생성
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("FOM2DB");
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(((screenSize.width - frameSize.width)/2), ((screenSize.height - frameSize.height)/2));

		final ImageIcon image = new ImageIcon("E:\\wargame.jpg");
		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {
				// Approach 1: Dispaly image at at full size
				g.drawImage(image.getImage(), 0, 0, null);
				// Approach 2: Scale image to size of component
				 Dimension d = getSize();
				 g.drawImage(image.getImage(), 0, 0, d.width, d.height, null);
//				 Approach 3: Fix the image position in the scroll pane
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		add(panel);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu filemenu = new JMenu("파일(F)");
		menuBar.add(filemenu);
		
		JMenu inputSetting = new JMenu("입력 설정(I)");
		menuBar.add(inputSetting);
		JMenuItem inputFederation = new JMenuItem("페더레이션 설정(F)");
		inputFederation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				federationSetting.setVisible(true);
			}
		});
		inputSetting.add(inputFederation);
		
		
		JMenuItem FOMSettingMenu = new JMenuItem("FOM 설정(O)");
		FOMSettingMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fomSetting.setVisible(true);
				((FomVersionSelect)fomSetting).getCheck().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						fomSetting.dispose();
						fomSelect.setVisible(true);
					}
				});
			}
		});
		
		//fomSelect의 check 버튼 취하기
		JButton check = ((FomSelect)fomSelect).getCheck();
		check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fomSelect.setVisible(false);
				//
				//유저 definde 가져오기
				//
				String filepath = ((FomSelect)fomSelect).getFilePaths();
				txml.setData(filepath);
				fixedData = txml.getUserDefinedType();
				final String datas[] = new String[fixedData.size()];
				final String dataNameAndType[] = new String[fixedData.size()];
				int i = 0;
				for( String d : fixedData ){
					String userdefinedName = d.split("==")[0];
					datas[i] = userdefinedName;
					dataNameAndType[i++] = d.split("==")[1];
				}
				uesrDefineType.setVisible(true);
				
				
				final JList<String> list = ((UserDefinedTypeList)uesrDefineType).getDefinedData();
				list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				list.setModel(new AbstractListModel<String>() {
					/**
					 * serialVersionUID
					 */
					private static final long serialVersionUID = 1L;
					String[] values = datas;
					public int getSize() {
						return values.length;
					}
					public String getElementAt(int index) {
						return values[index];
					}
				});
				
				
				//사용자 정의타입 클릭 후 보이는 화면 출력
				list.addListSelectionListener(new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						JPanel definedTypeName =  new JPanel();
						JPanel definedTypeNameCount = new JPanel();
						
						JScrollPane scrollPane = ((UserDefined)userDefined).getScrollPane();
						scrollPane.setColumnHeaderView(definedTypeName);
						scrollPane.setRowHeaderView(definedTypeNameCount);
						
						JLabel name = new JLabel(datas[list.getSelectedIndex()]);
						definedTypeName.add(name);
						
						
						JLabel count = new JLabel();
						String datasType[] = dataNameAndType[list.getSelectedIndex()].split("\\\\");
						count.setText("사용자 정의 타입 객체 수 : " + datasType.length );
						definedTypeNameCount.add(count);
						
						//페널에 넣기
						JPanel datasPanel = ((UserDefined)userDefined).getDatasPanel();
						datasPanel.removeAll();
						JList<String> dataName = new JList<String>();
						JList<String> dataType = new JList<String>();
						JList<String> javaDataType = new JList<String>();
						final String[] dataNames = new String[datasType.length];
						final String[] dataTypes = new String[datasType.length];
						final String[] javaDataTypes = new String[datasType.length];
						int i = 0;
						
						for( String data : datasType ){
							String[] datas = data.split(",");
							dataNames[i] = datas[0];
							dataTypes[i] = dataTypeSelect.dataTypeStructType(datas[1]);
							javaDataTypes[i++] = dataTypeSelect.dataTypeJavaType(datas[1]);
						}
						dataName.setModel(new AbstractListModel<String>() {
							private static final long serialVersionUID = 1L;
							String[] values = dataNames;
							public int getSize() {
								return values.length;
							}
							public String getElementAt(int index) {
								return values[index];
							}
						});
						
						javaDataType.setModel(new AbstractListModel<String>() {
							private static final long serialVersionUID = 2L;
							String[] values = javaDataTypes;
							public int getSize() {
								return values.length;
							}
							public String getElementAt(int index) {
								return values[index];
							}
						});
						
						dataType.setModel(new AbstractListModel<String>() {
							private static final long serialVersionUID = 3L;
							String[] values = dataTypes;
							public int getSize() {
								return values.length;
							}
							public String getElementAt(int index) {
								return values[index];
							}
						});
						
						
						
						datasPanel.add(dataName);
						datasPanel.add(dataType);
						datasPanel.add(javaDataType);
						
						userDefined.setVisible(true);
					}
				});
				
				
			}
		});
		
		
//				UDTInfo.txt만드는 버튼
		JButton testButton = ((UserDefinedTypeList)uesrDefineType).getFinish();
		testButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateUserDefinedType udttj = new CreateUserDefinedType();
				if( fixedData != null ) udttj.transformationJavaFile(fixedData);
				uesrDefineType.setVisible(false);
			}
		});		
		
		
		
		inputSetting.add(FOMSettingMenu);
		
		
		
		JMenuItem userSetting = new JMenuItem("사용자정의타입 설정(U)");
		userSetting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uesrDefineType.setVisible(true);
			}
		});
		
		inputSetting.add(userSetting);
		JMenuItem ferateSetting = new JMenuItem("페더레이트 설정(S)");
		ferateSetting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				federateSetting.setVisible(true);
				JLabel federateNameData = ((federateSetting)federateSetting).getFederationName();
				JLabel federateCountData = ((federateSetting)federateSetting).getFerateCount();
				String data;
				int count = federateCountData.getText().split(":")[1].replaceAll(" ", "").equals("") == true ? 0 : Integer.parseInt(federateCountData.getText().split(":")[1].replaceAll(" ", ""));
				if( ((FederationSetting)federationSetting).getTextField().getText().equals("") ) data = "이름을 설정해 주십시오.";
				else data = ((FederationSetting)federationSetting).getTextField().getText();
				federateNameData.setText("페더레이션 이름 : " + data);
				federateCountData.setText("페더레이트 수 : " + count);
			}
		});
		
		JButton federateInsertButton = ((federateSetting)federateSetting).getInsert();//페더레이트 추가 버튼
		federateInsertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addFederate = new AddFederate(null);
				JButton makeButton = ((AddFederate)addFederate).getMakeButton();
				makeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ArrayList<String> datas = ((AddFederate)addFederate).insertTable();
						DefaultTableModel model = ((federateSetting)federateSetting).getDefaultTableModel();
						JTable table = ((federateSetting)federateSetting).getTable();
						Object[] data = new Object[6];
						int i = 0;
						data[i++] = model.getRowCount()+1;
						
						for( String d : datas ) data[i++] = d.replaceAll(" ", "").equals("") == true ? "미입력" : d;
						
						data[i++] = "편집";
						model.addRow(data);
						
						table.getColumn("편집").setCellRenderer(new ButtonRenderer());
						table.getColumn("편집").setCellEditor(new ButtonEditor(new JCheckBox()));
						
						JLabel federateCountData = ((federateSetting)federateSetting).getFerateCount();
						federateCountData.setText("페더레이트 수 : " + model.getRowCount());
					}
				});
				
			}
		});
		
//		lrd 설정
		JButton federateFinishButton = ((federateSetting)federateSetting).getSettingFinish();//페더레이트 완료 버튼
		federateFinishButton.addActionListener(new ReplicationDirectoryInfo(((federateSetting)federateSetting).getTable()){});
		
		
		
		
		
		
		inputSetting.add(ferateSetting);
		JMenu outputSetting = new JMenu("출력 설정(O)");
		menuBar.add(outputSetting);
		
		JMenuItem DBConnection = new JMenuItem("DB연동구조 설정(D)");
		DBConnection.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dbConnetionStructClass.setVisible(true);
			}
		});
		outputSetting.add(DBConnection);
		
		JMenuItem DBMSSetting = new JMenuItem("DBMS 설정(I)");
		DBMSSetting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DBMSSettingFrame.setVisible(true);
			}
		});
		outputSetting.add(DBMSSetting);
		
		JMenuItem outputFileSetting = new JMenuItem("출력 파일 설정(S)");
		outputSetting.add(outputFileSetting);
		
		
		JMenu convert = new JMenu("스키마 생성(C)");
		
//		GCS Create
		JMenuItem globalDBCreate = new JMenuItem("Global DB 스키마 생성(G)");
		globalDBCreate.addActionListener(new CsCreateButtonActionListener(federationSetting, fomSetting, fomSelect, txml));
		menuBar.add(convert);
		
		convert.add(globalDBCreate);

//		LCS Create
		JMenuItem localDBCreate = new JMenuItem("Local DB 스키마 생성(L)");
		localDBCreate.addActionListener(new CsCreateButtonActionListener(federateSetting){});		
		convert.add(localDBCreate);
		
		
		JMenuItem resultPrint = new JMenuItem("변환 결과 보기(R)");
		convert.add(resultPrint);
		
		
		JMenu search = new JMenu("FOMDB조회(V)");
		menuBar.add(search);
		JMenuItem searchTable = new JMenuItem("FOMDB 테이블 조회(T)");
		search.add(searchTable);
		JMenuItem searchQuery = new JMenuItem("FOMDB 질의입력(Q)");
		searchQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(((FederationSetting) federationSetting).getTextField().getText());//출력 가능한 상태
			}
		});
		search.add(searchQuery);
		
		
		JMenu help = new JMenu("도움말(H)");
		menuBar.add(help);
	}
}
