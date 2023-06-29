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

public class Schedule_bottom extends JPanel {
	Client_main main;
	CardLayout cardlayout;
	JScrollPane jsp;

	public Schedule_bottom(Client_main main) {
		this.main = main;
		this.cardlayout = main.cardlayout;
		setLayout(new BorderLayout());

		JPanel bt = new JPanel();
		bt.setLayout(new BoxLayout(bt, BoxLayout.Y_AXIS)); // 박스

		int ii = 10; // 제공될 알림의 수 지정하는 변수
		// 패널 배열 생성
		JPanel[] panels = new JPanel[ii];
		for (int i = 0; i < panels.length; i++) {
			panels[i] = createPanel(); // 패널 생성 및 배열에 할당
			bt.add(panels[i]); // 프레임에 패널 추가
		}
		jsp = new JScrollPane(bt, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setPreferredSize(new Dimension(480, 350));
		add(new JLabel("<html><h3>수업 (18일)</h2></html>"), BorderLayout.NORTH);
		add(jsp);
	}

	private static JPanel createPanel() {

		JPanel panel = new JPanel(new GridLayout(3, 3));

		panel.setPreferredSize(new Dimension(300, 80));
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.add(new JLabel("수업종류", JLabel.LEFT));
		panel.add(new JLabel());
		panel.add(new JLabel("강의실", JLabel.RIGHT));
		panel.add(new JLabel("강사이름", JLabel.LEFT));
		panel.add(new JLabel());
		panel.add(new JButton("예약"));
		panel.add(new JLabel("수업시간", JLabel.LEFT));
		panel.add(new JLabel());
		panel.add(new JLabel("정원", JLabel.RIGHT));

		return panel;
	}

}
