package com.ict5.client.panel;

import javax.swing.*;

import com.ict5.client.Client_main;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class TabPage extends JPanel {
    Client_main main;
    CardLayout cardlayout;
    public UserTop usertop;
    static JTabbedPane tabbedPane;
    public Schedule schedule;
    public Reservation reservation;
    public Notice noti;
    public TabPage(Client_main main) {
    	
    	 usertop= new UserTop(main, true);
    	 schedule= new Schedule(main);
    	 reservation = new Reservation(main);
    	noti= new Notice(main);
        // Tab 4개와 이름 출력, 로그아웃 알람 누르면 알람으로 가는 기능만 있습니다.
//
//        // 상단 패널
//        JPanel topPanel = new JPanel(new BorderLayout());
//        JButton alarmButton = new JButton("알람");
//        JButton logoutButton = new JButton("로그아웃");
//        JButton backButton = new JButton("<<");
//        JLabel customerLabel = new JLabel("OOO고객님");
//
//        // 버튼과 라벨을 한 줄로 배치하기 위한 패널
//        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        buttonPanel.add(alarmButton);
//        buttonPanel.add(logoutButton);
//        buttonPanel.add(customerLabel);
//
//        topPanel.add(backButton, BorderLayout.WEST);
//        topPanel.add(buttonPanel, BorderLayout.EAST);


        // 알림 탭
        JPanel notificationPanel = new JPanel(new FlowLayout());
        JScrollPane notificationScrollPane = new JScrollPane(notificationPanel);

        // 탭 패널
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("          수업일정          ", schedule);
        tabbedPane.addTab("          예약현황          ", reservation);
        tabbedPane.addTab("           알 림           ", noti);
        tabbedPane.addTab("           My           ", new Mypage(main));
       
        // JTabbedPane 가로 크기 설정
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension size = getSize();
                tabbedPane.setPreferredSize(size);
            }
        });

        // 알람 버튼 클릭 이벤트
//        alarmButton.addActionListener(e -> tabbedPane.setSelectedIndex(2));

        // 메인 패널에 컴포넌트 추가
        setLayout(new BorderLayout());
//        add(topPanel, BorderLayout.NORTH);
        add(usertop,BorderLayout.NORTH);
        
        add(tabbedPane, BorderLayout.CENTER);
        
//        //뒤로 버튼->홈으로
//        backButton.addActionListener(e -> {
//            main.cardlayout.show(main.pg1, "home");
//        });
//        //로그아웃->로그인
//        logoutButton.addActionListener(e -> {
//        	main.cardlayout.show(main.pg1, "login");
//        });

    }
}