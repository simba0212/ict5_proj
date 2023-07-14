package com.ict5.client.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ict5.client.Client_main;
import com.ict5.db.Protocol;
import com.ict5.db.VO;

public class UserTop extends JPanel {
	Client_main main;
	TabPage tab;
	JLabel customerLabel;
	VO vo;
	public UserTop(Client_main main, boolean showBackButton) {
		this.main = main;//

		// 상단 유저기능 따로
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setPreferredSize(new Dimension(500, 35));
		JButton alarmButton = new JButton("알람");
		JButton logoutButton = new JButton("로그아웃");
		customerLabel = new JLabel("OOO고객님");
		JButton backButton = new JButton("<<");

		// 버튼과 라벨을 한 줄로 배치하기 위한 패널
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		buttonPanel.add(alarmButton);
		buttonPanel.add(logoutButton);
		buttonPanel.add(customerLabel);

		// topPanel.add(backButton, BorderLayout.WEST);
		if (showBackButton) {
			topPanel.add(backButton, BorderLayout.WEST);
		}

		topPanel.add(buttonPanel, BorderLayout.EAST);

		setLayout(new BorderLayout());
		setBackground(Color.gray);

		topPanel.add(buttonPanel, BorderLayout.EAST);
		add(topPanel, BorderLayout.NORTH);

		if (showBackButton) {
			topPanel.add(backButton, BorderLayout.WEST);
		}

		// 로그아웃 버튼 클릭 이벤트
		logoutButton.addActionListener(e -> {
			main.cardlayout.show(main.pg1, "login");
		});

		// 알람 버튼 클릭 이벤트
		alarmButton.addActionListener(e -> {
			main.cardlayout.show(main.pg1, "tab");
			TabPage.tabbedPane.setSelectedIndex(2);
		});

		// 뒤로 버튼 클릭 이벤트
		backButton.addActionListener(e -> {
			main.cardlayout.show(main.pg1, "home");
		});
	}

	public void refresh() {
		vo = main.vo;
		customerLabel.setText(vo.getMember_name() + "고객님");
	}
}
