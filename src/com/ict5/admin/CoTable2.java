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

public class CoTable2 extends JPanel {

    JTextField searchTextField;
    JButton searchButton;
    JTable instrTable;
    JTable classTable;
    CardLayout cardLayout;
    Admin_main main;
    JComboBox<Integer> resultsComboBox;

    public CoTable2(Admin_main main) {
        this.main = main;
        //강사-수업목록
        //이후 삭제 추가? 필요
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

        // 중앙패널
        JPanel centerPanel = new JPanel(new BorderLayout());

        // 중앙왼쪽
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 중앙 헤더 위치조정
        JPanel headerPanel = new JPanel(new BorderLayout());

        JLabel instrLabel = new JLabel("강사 목록");
        instrLabel.setFont(instrLabel.getFont().deriveFont(Font.BOLD, 20f));
        headerPanel.add(instrLabel, BorderLayout.NORTH);

        // 검색 결과:
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

        leftPanel.add(headerPanel, BorderLayout.NORTH);

        // 강사목록 샘플데이터
        Object[][] data = {
            {"성심당", "010-5634-5753", "2021-06-01", "요가"},
            {"문준호", "010-1234-5678", "2023-07-15", "필라테스"},
            // 필요하면 더하기
        };

        Object[] columnNames = {"이름", "전화번호", "등록날짜", "담당운동"};
        instrTable = new JTable(data, columnNames);

        JScrollPane instrScrollPane = new JScrollPane(instrTable);
        leftPanel.add(instrScrollPane, BorderLayout.CENTER);


        centerPanel.add(leftPanel, BorderLayout.WEST);

        // 오른쪽
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel classLabel = new JLabel("OOO 선생님 담당 수업");
        classLabel.setFont(classLabel.getFont().deriveFont(Font.BOLD, 20f));
        rightPanel.add(classLabel, BorderLayout.NORTH);

     // 샘플 class data
        Object[][] classData = {
            {"5/10", "4층 401호", "2023-06-01", "17:00 ~ 19:00"},
            {"9/10", "2층 211호", "2023-06-03", "08:00 ~ 08:50"}

        };

        Object[] classColumnNames = {"인원", "장소", "날짜", "시간"};
        classTable = new JTable(classData, classColumnNames);

        JScrollPane classScrollPane = new JScrollPane(classTable);
        rightPanel.add(classScrollPane, BorderLayout.CENTER);

        //셀내용 가운데 정렬
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        classTable.setDefaultRenderer(Object.class, centerRenderer);
        instrTable.setDefaultRenderer(Object.class, centerRenderer);

        centerPanel.add(rightPanel, BorderLayout.CENTER);
        
        add(centerPanel, BorderLayout.CENTER);
    }
}