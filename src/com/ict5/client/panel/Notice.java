package com.ict5.client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class Notice extends JPanel {
	Client_main main;
	CardLayout cardlayout;
	JScrollPane jsp;

	public Notice(Client_main main) {
		this.main = main;
		this.cardlayout = main.cardlayout;
		
		JPanel noti = new JPanel();
		noti.setLayout(new BoxLayout(noti, BoxLayout.Y_AXIS)); // 박스
//		noti.setPreferredSize(new Dimension(480,700));
		int ii = 10; // 제공될 알림의 수 지정하는 변수

		// 패널 배열 생성
		JPanel[] panels = new JPanel[ii];
		for (int i = 0; i < panels.length; i++) {
			panels[i] = createPanel(); // 패널 생성 및 배열에 할당
			noti.add(panels[i]); // 프레임에 패널 추가
		}
		
		jsp = new JScrollPane(noti, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setPreferredSize(new Dimension(480, 700));
		add(jsp);
	}

	private static JPanel createPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2,10,5));
		panel.setMaximumSize(new Dimension(500,100));
		panel.setPreferredSize(new Dimension(100, 100));
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		panel.add(new JLabel(" 수업 10분전",JLabel.LEFT));
		panel.add(new JLabel("<html><p style=\"color:red;\">New !&nbsp</html>",JLabel.RIGHT));
		panel.add(new JLabel(" 늦지 않도록 도착하세요~",JLabel.LEFT));
		panel.add(new JLabel());
		panel.add(new JLabel());
		panel.add(new JLabel("2023.06.24 ",JLabel.RIGHT));
		
		return panel;
	}

}
