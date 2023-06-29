package com.ict5.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserTop extends JPanel {
    Client_main main;
    TabPage tab;
    public UserTop(Client_main main) {
        this.main = main;

        // 상단 유저기능 따로
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(500, 35));
        JButton alarmButton = new JButton("알람");
        JButton logoutButton = new JButton("로그아웃");
        //JButton backButton = new JButton("<<");
        JLabel customerLabel = new JLabel("OOO고객님");

        // 버튼과 라벨을 한 줄로 배치하기 위한 패널
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(alarmButton);
        buttonPanel.add(logoutButton);
        //buttonPanel.add(backButton);
        buttonPanel.add(customerLabel);

        setLayout(new BorderLayout());
        setBackground(Color.gray);

        topPanel.add(buttonPanel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // 로그아웃 버튼 클릭 이벤트
        logoutButton.addActionListener(e -> {
            Client_Login login = new Client_Login(main);
            main.pg1.add("login", login);
            main.cardlayout.show(main.pg1, "login");
        });

        // 알람 버튼 클릭 이벤트
        alarmButton.addActionListener(e -> {
        	 TabPage tab = new TabPage(main);
             main.pg1.add("tab", tab);
             main.cardlayout.show(main.pg1, "tab");
             TabPage.tabbedPane.setSelectedIndex(2);
        });

//        // 뒤로 버튼 클릭 이벤트
//        backButton.addActionListener(e -> {
//        	Client_Home home = new Client_Home(main);
//        	main.pg1.add("home", home);
//            main.cardlayout.show(main.pg1, "home");
//        });
    }
}
