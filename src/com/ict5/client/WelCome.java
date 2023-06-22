package com.ict5.client;

import javax.swing.*;
import java.awt.*;

public class WelCome extends JPanel {
	Huge_main main;
	CardLayout cardlayout;
	
    public WelCome(Huge_main main) {
        setLayout(new BorderLayout());
        this.main = main;
		this.cardlayout = main.cardlayout;

        // 이미지 레이블 추가
        ImageIcon imageIcon = new ImageIcon("D:/YDP/javastudy/예비/src/images/welcome.png"); // 이미지 경로 수정
        JLabel imageLabel = new JLabel(imageIcon);
        add(imageLabel, BorderLayout.CENTER);

        // 중앙 패널 생성
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout());

        // 텍스트 레이블 추가
        JLabel textLabel = new JLabel("회원가입 완료, 5만 포인트 지급");
        centerPanel.add(textLabel);

        // 로그인 버튼 추가
        JButton loginButton = new JButton("로그인");
        centerPanel.add(loginButton);

        add(centerPanel, BorderLayout.SOUTH);

        // 하단 공백 패널 추가
        JPanel bottomPanel = new JPanel();
        add(bottomPanel, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Welcome");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane();
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
