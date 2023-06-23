package com.ict5.client;

import javax.swing.*;
import java.awt.*;

public class Complete extends JPanel {
	Huge_main main;
	CardLayout cardlayout;

	public Complete(Huge_main main) {
		this.main = main;
		this.cardlayout = main.cardlayout;
		setLayout(new BorderLayout());

		// 이미지 레이블 추가
		ImageIcon imageIcon = new ImageIcon("src/images/complete.png"); // 이미지 경로 수정
		JLabel imageLabel = new JLabel(imageIcon);
		add(imageLabel, BorderLayout.CENTER);

		// 중앙 패널 생성
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new FlowLayout());

		// 텍스트 레이블 추가
		JLabel textLabel = new JLabel("<html><div style='text-align: center;'>입금 예약완료<br>입금확인에는 시간이 걸릴 수 있습니다</div></html>");
        textLabel.setHorizontalAlignment(SwingConstants.CENTER); // 수평 가운데 정렬
        centerPanel.add(textLabel);

		// 로그인 버튼 추가
		JButton loginButton = new JButton("메인화면");
		centerPanel.add(loginButton);

		add(centerPanel, BorderLayout.SOUTH);

		// 하단 공백 패널 추가
		JPanel bottomPanel = new JPanel();
		add(bottomPanel, BorderLayout.NORTH);
	}
}
