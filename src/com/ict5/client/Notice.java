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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class Notice extends JPanel {
	Huge_main main;
	CardLayout cardlayout;
	JScrollPane jsp;

	public Notice(Huge_main main) {
		this.main = main;
		this.cardlayout = main.cardlayout;
		setLayout(new BorderLayout());


		JPanel noti = new JPanel();
		noti.setLayout(new BoxLayout(noti, BoxLayout.Y_AXIS)); // 박스
	
		noti.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		int ii = 5; // 제공될 알림의 수 지정하는 변수

		// 패널 배열 생성
		JPanel[] panels = new JPanel[ii];
		for (int i = 0; i < panels.length; i++) {
			panels[i] = createPanel(); // 패널 생성 및 배열에 할당
			noti.add(panels[i]); // 프레임에 패널 추가
		}
		jsp = new JScrollPane(noti,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setPreferredSize(new Dimension(400, 380));
		add(jsp);
	}

	private static JPanel createPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1)); // 2x2 그리드 레이아웃 사용
		panel.setPreferredSize(new Dimension(400, 70));
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JLabel label = new JLabel("알림제목");
		JLabel label2 = new JLabel("알림 내용");
		JLabel label3 = new JLabel("알림 발생 시간");

		panel.add(label);
		panel.add(label2);
		panel.add(label3);

		return panel;
	}

}
