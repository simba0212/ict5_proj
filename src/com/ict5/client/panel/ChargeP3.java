package com.ict5.client.panel;

import javax.swing.*;

import com.ict5.client.Client_Login;
import com.ict5.client.Client_main;
import com.ict5.db.VO;

import java.awt.*;

public class ChargeP3 extends JPanel {
	Client_main main;
	CardLayout cardlayout;
	
	VO vo;

	public ChargeP3(Client_main main) {
		this.main = main;
		this.cardlayout = main.cardlayout;
		this.vo = main.vo; // vo 동기화
		setLayout(new BorderLayout());

		// 이미지 레이블 추가
		ImageIcon imageIcon = new ImageIcon("src/images/complete.png"); // 이미지 경로 수정
		JLabel imageLabel = new JLabel(imageIcon);
		imageLabel.setBorder(BorderFactory.createEmptyBorder(80, 10, 10, 10));
		add(imageLabel, BorderLayout.NORTH);

		// 중앙 패널 생성
		JPanel centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension(450, 300));
		centerPanel.setLayout(new FlowLayout());

		// 텍스트 레이블 추가
		JLabel textLabel = new JLabel(
				"<html><div style='text-align: center;'>입금 예약완료<br>입금확인에는 시간이 걸릴 수 있습니다</div></html>");
		textLabel.setFont(textLabel.getFont().deriveFont(Font.BOLD, 20f));
		textLabel.setHorizontalAlignment(SwingConstants.CENTER); // 수평 가운데 정렬
		centerPanel.add(textLabel, BorderLayout.SOUTH);
		add(centerPanel, BorderLayout.EAST);
		centerPanel.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 40));

		// 하단 공백 패널 추가
		JPanel bottomPanel = new JPanel();
		// 로그인 버튼 추가
		JButton loginButton = new JButton("메인화면");
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
