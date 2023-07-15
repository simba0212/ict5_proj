package com.ict5.admin.panel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.ict5.admin.Admin_main;
import com.ict5.db.DAO;
import com.ict5.db.Protocol;
import com.ict5.db.VO;

public class CoTable1 extends JPanel {

	JTextField searchTextField;
	JButton searchButton;
	JTable instrTable;
	JTable classTable;
	CardLayout cardLayout;
	Admin_main main;
	JComboBox<Integer> resultsComboBox;
	DefaultTableModel model;
	Object[] dataObjects;

	public CoTable1(Admin_main main) {
		this.main = main;
		// 강사관리 목록
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(800, 600));

		// Top panel
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		topPanel.setPreferredSize(new Dimension(200, 60));
		topPanel.setBackground(Color.lightGray);

		// 3번째 패널 (상단패널)
		JLabel titleLabel = new JLabel("강사 관리");
		titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 10));
		titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 17f));
		topPanel.add(titleLabel);

		// 3번째 패널 (상단패널)
		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		searchPanel.setBackground(Color.lightGray);

		searchTextField = new JTextField("강사 검색", 20);
		searchTextField.setPreferredSize(new Dimension(200, 30));
		searchPanel.add(searchTextField);
		searchPanel.setBorder(BorderFactory.createEmptyBorder(7, 0, 0, 0));

		searchButton = new JButton("검색");
		searchButton.setPreferredSize(new Dimension(80, 30));
		searchPanel.add(searchButton);

		topPanel.add(searchPanel);

		add(topPanel, BorderLayout.NORTH);

		// 중앙
		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// 중앙 헤더 위치조정
		JPanel headerPanel = new JPanel(new BorderLayout());

		JLabel instrLabel = new JLabel("강사 목록");
		instrLabel.setFont(instrLabel.getFont().deriveFont(Font.BOLD, 20f));
		headerPanel.add(instrLabel, BorderLayout.NORTH);
		centerPanel.add(headerPanel, BorderLayout.NORTH);

		Object[] columnNames = { "강사 번호", "이름", "전화 번호", "주소", "성별", "경력사항", "사진", "담당 운동" };
		model = new DefaultTableModel(columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// 마지막 열을 제외한 모든 셀을 편집할 수 없도록 설정
				return column == columnNames.length;
			}
		};
//		for (int i = 0; i < columnNames.length; i++) {
//			model.addColumn(columnNames[i]);
//		}

		instrTable = new JTable(model);
		instrTable.getTableHeader().setReorderingAllowed(false);

		JScrollPane instrScrollPane = new JScrollPane(instrTable);
		centerPanel.add(instrScrollPane, BorderLayout.CENTER);

		// 셀내용 가운데 정렬
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		instrTable.setDefaultRenderer(Object.class, centerRenderer);
		add(centerPanel, BorderLayout.CENTER);
		
		searchTextField.addFocusListener(new FocusListener() { // 강사검색클릭
			@Override
			public void focusLost(FocusEvent e) {
			}

			@Override
			public void focusGained(FocusEvent e) {
				searchTextField.setText("");
			}
		});
		
		// 데이터 클릭 이벤트 리스너 등록
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
					main.cardlayout.show(main.pg1, "coMg2");
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		
		// 검색필드
		searchTextField.addActionListener(new ActionListener() { // 검색창 엔터

			@Override
			public void actionPerformed(ActionEvent e) {
				if (searchTextField.getText().trim().isEmpty()) { // 빈칸 검색시
					try {
						Protocol p = new Protocol();
						p.setCmd(1301);
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
						p.setVo(vo);
						main.out.writeObject(p);
						main.out.flush();
					} catch (Exception e2) {

					}
				}

			}

		});
		
		searchButton.addActionListener(new ActionListener() { // 검색버튼

			@Override
			public void actionPerformed(ActionEvent e) {
				if (searchTextField.getText().trim().isEmpty()) { // 빈칸 검색시
					try {
						Protocol p = new Protocol();
						p.setCmd(1301);
						p.setResult(0);
						main.out.writeObject(p);
						main.out.flush();
					} catch (Exception e2) {
						// TODO: handle exception
					}

				} else {
					try {
						String name = searchTextField.getText().trim();
						Protocol p = new Protocol();
						VO vo = new VO();
						vo.setTeacher_name(name);
						p.setCmd(1302);
						p.setVo(vo);
						main.out.writeObject(p);
						main.out.flush();
					} catch (Exception e2) {

					}
				}

			}
		});
		
	}
	
	public void refresh() { // 테이블 최신화
		
		model.setRowCount(0);
		List<VO> list = main.list;
		for (VO k : list) {
			
			Vector<Object> rowData = new Vector<>();
			rowData.add(k.getTeacher_num());
			rowData.add(k.getTeacher_name());
			rowData.add(k.getTeacher_phone());
			rowData.add(k.getTeacher_addr());
			rowData.add(k.getTeacher_gen());
			rowData.add(k.getTeacher_career());
			rowData.add(k.getTeacher_img());
			
			
			switch (Integer.parseInt(k.getTeacher_type())) {
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
		main.cardlayout.show(main.pg1, "coMg1");
	}
	
	public void search() { // 검색
		model.setRowCount(0);
		List<VO> list = main.list;
		for (VO k : list) {

			Vector<Object> rowData = new Vector<>();
			rowData.add(k.getTeacher_num());
			rowData.add(k.getTeacher_name());
			rowData.add(k.getTeacher_phone());
			rowData.add(k.getTeacher_addr());
			rowData.add(k.getTeacher_gen());
			rowData.add(k.getTeacher_career());
			rowData.add(k.getTeacher_img());
			
			switch (Integer.parseInt(k.getTeacher_type())) {
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