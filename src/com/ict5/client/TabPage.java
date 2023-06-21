package com.ict5.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Date;

public class TabPage extends JFrame {
	public TabPage() {

		// 상단 패널
		JPanel topPanel = new JPanel(new BorderLayout());
		JButton alarmButton = new JButton("알람");
		JButton logoutButton = new JButton("로그아웃");
		JLabel customerLabel = new JLabel("OOO고객님");
		topPanel.add(alarmButton, BorderLayout.WEST);
		topPanel.add(logoutButton, BorderLayout.EAST);
		topPanel.add(customerLabel, BorderLayout.CENTER);

		// 알림 탭
		JPanel notificationPanel = new JPanel(new FlowLayout());
		JScrollPane notificationScrollPane = new JScrollPane(notificationPanel);

		// 탭 패널
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("     수업일정     ", new JPanel());
		tabbedPane.addTab("     예약현황     ", new JPanel());
		tabbedPane.addTab("      알 림      ", notificationScrollPane);
		tabbedPane.addTab("       My       ", new JPanel());

		// 알람 버튼 클릭 이벤트
		alarmButton.addActionListener(e -> tabbedPane.setSelectedIndex(2));

//        // 알림 패널에 알림 추가 (샘플 데이터)
//        addNotification(notificationPanel, "A수업 30분전", true);
//        addNotification(notificationPanel, "B수업 1시간전", false);

		// 메인 패널에 컴포넌트 추가
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(tabbedPane, BorderLayout.CENTER);
		add(mainPanel);

		// 프레임 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 800);
		setLayout(new BorderLayout());
		setVisible(true);

		// JTabbedPane 가로 크기 설정
		mainPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Dimension size = mainPanel.getSize();
				tabbedPane.setPreferredSize(size);
			}

		});

	}

	private void addNotification(JPanel panel, String message, boolean isNew, Date timestamp) {
		NotificationPanel notificationPanel = new NotificationPanel(message, isNew, timestamp);
		panel.add(notificationPanel);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(TabPage::new);
	}
}
