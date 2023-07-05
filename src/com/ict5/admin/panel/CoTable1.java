package com.ict5.admin.panel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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

		// 검색결과:
		JLabel resultsLabel = new JLabel("검색결과: nn 명");
		headerPanel.add(resultsLabel, BorderLayout.CENTER);

		// 콤보박스
		JPanel comboPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		Integer[] options = { 10, 20, 50 };
		resultsComboBox = new JComboBox<>(options);
		resultsComboBox.setPreferredSize(new Dimension(80, 30));
		resultsComboBox.setSelectedIndex(0);
		comboPanel.add(resultsComboBox);

		comboPanel.add(new JLabel("개씩 보기"));

		headerPanel.add(comboPanel, BorderLayout.EAST);

		centerPanel.add(headerPanel, BorderLayout.NORTH);

		Object[] columnNames = { "강사 번호", "이름", "전화 번호", "성별", "생년월일", "주소", "등록날짜", "담당 운동" };
		model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// 마지막 열을 제외한 모든 셀을 편집할 수 없도록 설정
				return column == columnNames.length;
			}
		};
		for (int i = 0; i < columnNames.length; i++) {
			model.addColumn(columnNames[i]);
		}

		instrTable = new JTable(model);
		instrTable.getTableHeader().setReorderingAllowed(false);

		JScrollPane instrScrollPane = new JScrollPane(instrTable);
		centerPanel.add(instrScrollPane, BorderLayout.CENTER);

		// 셀내용 가운데 정렬
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		instrTable.setDefaultRenderer(Object.class, centerRenderer);
		add(centerPanel, BorderLayout.CENTER);
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
				rowData.add("수영");
				break;
			case 2:
				rowData.add("헬스");
				break;
			case 3:
				rowData.add("요가");
				break;
			case 4:
				rowData.add("필라테스");
				break;
			}

			model.addRow(rowData);
		}
	}
}