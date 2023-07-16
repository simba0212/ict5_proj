package com.ict5.admin.panel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.ict5.admin.Admin_main;
import com.ict5.db.Protocol;
import com.ict5.db.VO;

public class CoTable2 extends JPanel {

    JTextField searchTextField;
    JButton searchButton;
    JTable instrTable;
    JTable classTable;
    CardLayout cardLayout;
    Admin_main main;
    JComboBox<Integer> resultsComboBox;
    DefaultTableModel model;
    DefaultTableModel model1;
    JLabel classLabel;
    JButton fix;
    JPanel bottom;
    String teanum;
    

    public CoTable2(Admin_main main) {
        this.main = main;
        //강사-수업목록
        //이후 삭제 추가? 필요
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        // Top panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.lightGray);

        // 3번째 패널 (상단패널)
        JLabel titleLabel = new JLabel("강사 검색");
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 10));
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 17f));
        topPanel.add(titleLabel);

        // 3번째 패널 (상단패널)
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(Color.lightGray);

        searchTextField = new JTextField("강사 검색", 20);
        searchTextField.setPreferredSize(new Dimension(200, 30));
        searchPanel.add(searchTextField);
        searchTextField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				searchTextField.setText("");
			}
		});
        searchTextField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (searchTextField.getText().trim().isEmpty()) { // 빈칸 검색시
					try {
						Protocol p = new Protocol();
						p.setCmd(1301);
						p.setResult(1);
						main.out.writeObject(p);
						main.out.flush();
					} catch (Exception e2) {
					}
				} else {
					try {
						String name = searchTextField.getText().trim();
						Protocol p = new Protocol();
						VO vo = new VO();
						vo.setTeacher_name(name);
						p.setCmd(1302);
						p.setResult(1);
						p.setVo(vo);
						main.out.writeObject(p);
						main.out.flush();
					} catch (Exception e2) {

					}
				}
			}
		});

        searchButton = new JButton("검색");
        searchButton.setPreferredSize(new Dimension(80, 30));
        searchPanel.add(searchButton);
        searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (searchTextField.getText().trim().isEmpty()) { // 빈칸 검색시
					try {
						Protocol p = new Protocol();
						p.setCmd(1301);
						p.setResult(1);
						main.out.writeObject(p);
						main.out.flush();
					} catch (Exception e2) {
					}

				} else {
					try {
						String name = searchTextField.getText().trim();
						Protocol p = new Protocol();
						VO vo = new VO();
						vo.setTeacher_name(name);
						p.setCmd(1302);
						p.setResult(1);
						p.setVo(vo);
						main.out.writeObject(p);
						main.out.flush();
					} catch (Exception e2) {

					}
				}
			}
		});

        topPanel.add(searchPanel);

        add(topPanel, BorderLayout.NORTH);

        // 중앙패널
        JPanel centerPanel = new JPanel(new BorderLayout());

        // 중앙왼쪽
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 중앙 헤더 위치조정
        JPanel headerPanel = new JPanel(new BorderLayout());

        JLabel instrLabel = new JLabel("검색 결과");
        instrLabel.setFont(instrLabel.getFont().deriveFont(Font.BOLD, 20f));
        headerPanel.add(instrLabel, BorderLayout.NORTH);

        // 검색 결과:
        JLabel resultsLabel = new JLabel(" ");
        headerPanel.add(resultsLabel, BorderLayout.CENTER);

        // 콤보박스
        JPanel comboPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        headerPanel.add(comboPanel, BorderLayout.EAST);

        leftPanel.add(headerPanel, BorderLayout.NORTH);

        // 강사목록 샘플데이터
        Object[][] data = {
            {}
            
            // 필요하면 더하기
        };

        Object[] columnNames = {"번호", "이름", "전화번호", "성별", "담당운동", "삭제버튼"};
        model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //마지막 열을 제외한 모든 셀을 편집할 수 없도록 설정
                return column == columnNames.length - 1;
            }
        };
        instrTable = new JTable(model);
        //instrTable.setEnabled(false);
        instrTable.getTableHeader().setReorderingAllowed(false);

        // 마지막 열에 단추 렌더러 및 편집기 추가
        TableColumnButtonRenderer buttonRenderer = new TableColumnButtonRenderer();
        TableColumnButtonEditor buttonEditor = new TableColumnButtonEditor();
        instrTable.getColumnModel().getColumn(columnNames.length - 1).setCellRenderer(buttonRenderer);
        instrTable.getColumnModel().getColumn(columnNames.length - 1).setCellEditor(buttonEditor);	

        JScrollPane instrScrollPane = new JScrollPane(instrTable);
        leftPanel.add(instrScrollPane, BorderLayout.CENTER);

        centerPanel.add(leftPanel, BorderLayout.WEST);

        // 오른쪽
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    	classLabel = new JLabel("OOO 선생님 담당 수업");
        classLabel.setFont(classLabel.getFont().deriveFont(Font.BOLD, 20f));
        rightPanel.add(classLabel, BorderLayout.NORTH);

        String[] classColumnNames = {"인원", "장소", "날짜", "시간"};
        model1 = new DefaultTableModel(classColumnNames,0); 
        
        classTable = new JTable(model1);
        classTable.getTableHeader().setReorderingAllowed(false);
        
        JScrollPane classScrollPane = new JScrollPane(classTable);
        
        bottom= new JPanel();
        fix = new JButton("수정");
        fix.setPreferredSize(new Dimension(400, 30));
        bottom.add(fix);
        rightPanel.add(bottom,BorderLayout.SOUTH);
        rightPanel.add(classScrollPane, BorderLayout.CENTER);

        centerPanel.add(rightPanel, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);
        
        // 수정버튼 클릭 시 화면 이동
        fix.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Protocol p = new Protocol();
					VO vo = new VO();
					p.setCmd(1309); // 수정하는 프로토콜
					vo.setTeacher_num(teanum);
					p.setVo(vo);
					main.out.writeObject(p);
					main.out.flush();
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}
		});
        
        instrTable.addMouseListener(new MouseAdapter() {
        	@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Protocol p = new Protocol();
					VO vo = new VO();
					p.setCmd(1303);
					int row = instrTable.getSelectedRow();
					Object value = instrTable.getValueAt(row, 0);
					String teacher_num = value.toString();
					vo.setTeacher_num(teacher_num);
					p.setVo(vo);
					main.out.writeObject(p);
					main.out.flush();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
    }
    	
    	public void refresh1() {// 선생님 한명정보 불러오기
    		VO vo = main.vo;
    		teanum = vo.getTeacher_num();
    		classLabel.setText(vo.getTeacher_name()+ " 선생님 담당 수업");
   		// 오른쪽 테이블 불러오기
   			try {
   				Protocol p = new Protocol();
   				p.setVo(vo);
   				p.setCmd(1304);
   				main.out.writeObject(p);
   				main.out.flush();

   			} catch (Exception e) {
   				// TODO: handle exception
   			}
   			
    	}
    
    	public void refresh2() { // 오른쪽 테이블 예약목록
    		model1.setRowCount(0);
    		List<VO> list = main.list;
    		for (VO k : list) {
    			switch (k.getClass_time()) {
    			case "1":
    				k.setClass_time("09:00");
    				break;
    			case "2":
    				k.setClass_time("10:00");
    				break;
    			case "3":
    				k.setClass_time("11:00");
    				break;
    			case "4":
    				k.setClass_time("12:00");
    				break;
    			case "5":
    				k.setClass_time("13:00");
    				break;
    			case "6":
    				k.setClass_time("14:00");
    				break;
    			case "7":
    				k.setClass_time("15:00");
    				break;
    			case "8":
    				k.setClass_time("16:00");
    				break;
    			case "9":
    				k.setClass_time("17:00");
    				break;
    			case "10":
    				k.setClass_time("18:00");
    				break;
    			case "11":
    				k.setClass_time("19:00");
    				break;
    			case "12":
    				k.setClass_time("20:00");
    				break;

    			default:
    				break;
    			}
    			Object[] rowData = { k.getClass_res(),k.getClass_room(),k.getClass_date().substring(0, 11),k.getClass_time()};
    			model1.addRow(rowData);
    		}
    		
    	}
    	
    	
    // 삭제 버튼
    private class TableColumnButtonRenderer extends JButton implements TableCellRenderer {
        public TableColumnButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText("삭제");
            return this;
        }
    }

    // 삭제 버튼 관련
    private class TableColumnButtonEditor extends DefaultCellEditor {
        private JButton button;

        public TableColumnButtonEditor() {
            super(new JTextField());
            setClickCountToStart(1);

            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> fireEditingStopped());
            
            button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
            		try {
            			int res=JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?");
            			if(res==0) {
            				Protocol p = new Protocol();
            				VO vo = new VO();
            				p.setCmd(1310);
            				int row = instrTable.getSelectedRow();
            				Object value = instrTable.getValueAt(row, 0);
            				String teacher_num = value.toString();
            				vo.setTeacher_num(teacher_num);
            				p.setVo(vo);
            				main.out.writeObject(p);
            				main.out.flush();
            			}
					} catch (Exception e2) {
						// TODO: handle exception
					}
            	}
			});
            
            
            
            
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            button.setText("삭제");
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return "삭제";
        }
    }
    
   
    
    
 // 테이블 속 텍스트를 가운데정렬하기 위한 클래스
 	public class CenterTableCellRenderer implements TableCellRenderer {
 		@Override
 		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
 				int row, int column) {
 			DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
 			renderer.setHorizontalAlignment(SwingConstants.CENTER);
 			return renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
 		}
 	}

 	@SuppressWarnings("serial")
 	public class UnmodifiableTableModel extends DefaultTableModel {
 		public UnmodifiableTableModel(String[][] data, String[] columnNames) {
 			super(data, columnNames);
 		}

 		public UnmodifiableTableModel(String[] columnNames2, int i) {
 			super(columnNames2, i);
 		}

 		@Override
 		public boolean isCellEditable(int row, int column) {
 			return false;
 		}
 	}

	public void refresh() {
		classLabel.setText("강사를 선택해주세요");
		List<VO> list = main.list;
		model.setRowCount(0);
		for (VO vo : list) {
			Vector<Object> rowData = new Vector<>();
			rowData.add(vo.getTeacher_num());
			rowData.add(vo.getTeacher_name());
			rowData.add(vo.getTeacher_phone());
			rowData.add(vo.getTeacher_gen());
			
			switch (Integer.parseInt(vo.getTeacher_type())) {
			case 1:
				rowData.add("헬스");
				break;
			case 2:
				rowData.add("요가");
				break;
			case 3:
				rowData.add("수영");
				break;
			case 4:
				rowData.add("필라테스");
				break;
			}
			model.addRow(rowData);
		}
		model1.setRowCount(0);
		main.cardlayout.show(main.pg1, "coMg2");
	}

	public void search() {
		model.setRowCount(0);
		List<VO> list = main.list;
		for (VO vo : list) {
			Vector<Object> rowData = new Vector<>();
			rowData.add(vo.getTeacher_num());
			rowData.add(vo.getTeacher_name());
			rowData.add(vo.getTeacher_phone());
			rowData.add(vo.getTeacher_gen());
			
			switch (Integer.parseInt(vo.getTeacher_type())) {
			case 1:
				rowData.add("헬스");
				break;
			case 2:
				rowData.add("요가");
				break;
			case 3:
				rowData.add("수영");
				break;
			case 4:
				rowData.add("필라테스");
				break;
			}
			model.addRow(rowData);
		}
		
	}
    
    
}