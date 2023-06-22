package com.ict5.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class TabPage extends JPanel {
    Huge_main main;
    CardLayout cardlayout;

    public TabPage(Huge_main main) {
        // Tab 4개와 이름 출력, 로그아웃 알람 누르면 알람으로 가는 기능만 있습니다.

        // 상단 패널
        JPanel topPanel = new JPanel(new BorderLayout());
        JButton alarmButton = new JButton("알람");
        JButton logoutButton = new JButton("로그아웃");
        JButton backButton = new JButton("<<");
        JLabel customerLabel = new JLabel("OOO고객님");

        // 버튼과 라벨을 한 줄로 배치하기 위한 패널
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(alarmButton);
        buttonPanel.add(logoutButton);//12312314123
        buttonPanel.add(customerLabel);

        topPanel.add(backButton, BorderLayout.WEST);
        topPanel.add(buttonPanel, BorderLayout.EAST);

        // 알림 탭
        JPanel notificationPanel = new JPanel(new FlowLayout());
        JScrollPane notificationScrollPane = new JScrollPane(notificationPanel);

        // 탭 패널
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("          수업일정          ", new JPanel());
        tabbedPane.addTab("          예약현황          ", new JPanel());
        tabbedPane.addTab("           알 림           ", notificationScrollPane);
        tabbedPane.addTab("           My           ", new JPanel());

        // JTabbedPane 가로 크기 설정
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension size = getSize();
                tabbedPane.setPreferredSize(size);
            }
        });

        // 알람 버튼 클릭 이벤트
        alarmButton.addActionListener(e -> tabbedPane.setSelectedIndex(2));

        // 메인 패널에 컴포넌트 추가
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);

    }
}