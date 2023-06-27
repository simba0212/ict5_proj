package com.ict5.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UserAppEdit extends JPanel {
    Admin_main main;

    public UserAppEdit(Admin_main main) {
        this.main = main;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        // Top panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.GRAY);
        JLabel titleLabel = new JLabel("사용자 앱 관리");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 15f));
        topPanel.add(titleLabel);
        add(topPanel, BorderLayout.NORTH);
        
        // 왼쪽패널
        JPanel leftPanel = new JPanel(new BorderLayout());
        JLabel leftLabel = new JLabel("센터 소개글 작성");
        leftLabel.setFont(leftLabel.getFont().deriveFont(Font.BOLD, 20f));
        leftPanel.add(leftLabel, BorderLayout.NORTH);

        String introText = "센터 소개 글을 작성해주세요.";
        JTextArea leftTextArea = new JTextArea(introText);
        leftTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        leftTextArea.setPreferredSize(new Dimension(400, 300));
        leftTextArea.setEditable(false);
        leftPanel.add(leftTextArea, BorderLayout.CENTER);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 10));
        add(leftPanel, BorderLayout.WEST);

        // 오른쪽 패널
        JPanel rightPanel = new JPanel(new BorderLayout());
        JLabel announcementLabel = new JLabel("공지 작성");
        announcementLabel.setFont(announcementLabel.getFont().deriveFont(Font.BOLD, 20f));
        rightPanel.add(announcementLabel, BorderLayout.NORTH);
        
        String annoText = "공지 글을 작성해주세요.";
        JTextArea announcementTextArea = new JTextArea(annoText);
        announcementTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        announcementTextArea.setPreferredSize(new Dimension(600, 300));
        announcementTextArea.setEditable(false);
        rightPanel.add(announcementTextArea, BorderLayout.CENTER);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 100));
        add(rightPanel, BorderLayout.EAST);

        // 하단패널
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        JButton registerButton = new JButton("등록");
        registerButton.setPreferredSize(new Dimension(100, 50));
        JButton cancelButton = new JButton("취소");
        cancelButton.setPreferredSize(new Dimension(100, 50));
        bottomPanel.add(registerButton);
        bottomPanel.add(cancelButton);
        add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.setPreferredSize(new Dimension(200, 70));
    }
}