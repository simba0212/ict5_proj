package com.ict5.client;

import javax.swing.*;
import java.awt.*;

public class CreateId_2 extends JPanel {
	Client_main main;
	CardLayout cardlayout;

	public CreateId_2(Client_main main) {
		setLayout(new BorderLayout());
		this.main = main;
		this.cardlayout = main.cardlayout;

		// 이미지 레이블 추가
		ImageIcon imageIcon = new ImageIcon("src/images/welcome.png"); // 이미지 경로 수정
		JLabel imageLabel = new JLabel(imageIcon);
		imageLabel.setBorder(BorderFactory.createEmptyBorder(80, 10, 10, 10));
		add(imageLabel, BorderLayout.NORTH);

		// 중앙 패널 생성
		JPanel centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension(450, 300));
		centerPanel.setLayout(new FlowLayout());

		// 텍스트 레이블 추가
		JLabel textLabel1 = new JLabel("회원가입 완료");
		textLabel1.setFont(textLabel1.getFont().deriveFont(Font.BOLD, 20f));
		centerPanel.add(textLabel1, BorderLayout.NORTH);
		JLabel textLabel2 = new JLabel("회원가입 기념으로 5만포인트가 발급되었습니다.");
		textLabel2.setFont(textLabel2.getFont().deriveFont(Font.PLAIN, 17f));
		centerPanel.add(textLabel2, BorderLayout.SOUTH);
		add(centerPanel, BorderLayout.EAST);
		centerPanel.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 40));

		// 하단 공백 패널 추가
		JPanel bottomPanel = new JPanel();
		// 로그인 버튼 추가
		JButton loginButton = new JButton("로그인");
		loginButton.setPreferredSize(new Dimension(120, 40));
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 90, 0));
		bottomPanel.add(loginButton);
		add(bottomPanel, BorderLayout.SOUTH);

		// 로그인 버튼->로그인으로
		loginButton.addActionListener(e -> {
			Client_Login login = new Client_Login(main);
			main.pg1.add("login", login);
			main.cardlayout.show(main.pg1, "login");
		});
	}
}
