package com.ict5.admin;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

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

public class CoTable1 extends JPanel {

    JTextField searchTextField;
    JButton searchButton;
    JTable instrTable;
    JTable classTable;
    CardLayout cardLayout;
    Admin_main main;
    JComboBox<Integer> resultsComboBox;

    public CoTable1(Admin_main main) {
        this.main = main;
        //강사관리 목록
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        // Top panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.GRAY);

        // 3번째 패널 (상단패널)
        JLabel titleLabel = new JLabel("강사 관리");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 15f));
        topPanel.add(titleLabel);

        // 3번째 패널 (상단패널)
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(Color.GRAY);

        searchTextField = new JTextField("강사 검색", 20);
        searchTextField.setPreferredSize(new Dimension(200, 30));
        searchPanel.add(searchTextField);

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

        Integer[] options = {10, 20, 50};
        resultsComboBox = new JComboBox<>(options);
        resultsComboBox.setPreferredSize(new Dimension(80, 30));
        resultsComboBox.setSelectedIndex(0);
        comboPanel.add(resultsComboBox);

        comboPanel.add(new JLabel("개씩 보기"));

        headerPanel.add(comboPanel, BorderLayout.EAST);

        centerPanel.add(headerPanel, BorderLayout.NORTH);

        // 강사목록 샘플데이터
        Object[][] data = {
            {"1", "성심당", "010-5634-5753", "남자", "1896-02-21", "서울시 중구 면목동", "2021-06-01", "요가", "월,화,수,목"},
            {"2", "문준호", "010-1234-5678", "남자", "1996-12-05", "서울시 강서구 보배동", "2023-07-15", "필라테스","금,토"},
            // 필요하면 더하기
        };

        Object[] columnNames = {"강사 번호", "이름", "전화 번호", "성별", "생년월일", "주소", "등록날짜", "담당 운동", "근무 요일"};
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //마지막 열을 제외한 모든 셀을 편집할 수 없도록 설정
                return column == columnNames.length;
            }
        };
        instrTable = new JTable(model);
        instrTable.getTableHeader().setReorderingAllowed(false);

        JScrollPane instrScrollPane = new JScrollPane(instrTable);
        centerPanel.add(instrScrollPane, BorderLayout.CENTER);

        //셀내용 가운데 정렬
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        instrTable.setDefaultRenderer(Object.class, centerRenderer);
        add(centerPanel, BorderLayout.CENTER);
    }
}