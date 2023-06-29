package com.ict5.client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Home extends JPanel {
	Client_main main;
	CardLayout cardlayout;
	TabPage tab;

	public Home(Client_main main) {
		this.main = main;
		this.cardlayout = main.cardlayout;

		// 화면단 패널을 공지사항, 가까운수업, 버튼 총3개로 나눔
		setLayout(new BorderLayout());

		// 공지사항 패널
		JPanel noticePanel = new JPanel();
		JTextArea notice = new JTextArea();
		notice = new JTextArea(10, 40);
		notice.setBorder(BorderFactory.createEtchedBorder()); // JTextArea에 테두리 설정
		notice.setLineWrap(true); // 자동 줄 바꿈 jta 는 무조건 jscrollpane 과 짝궁
		notice.setEditable(false);
		JScrollPane jsp = new JScrollPane(notice, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		noticePanel.add(new JLabel(" 공  지  사  항 "), BorderLayout.NORTH);
		noticePanel.add(notice, BorderLayout.SOUTH);

		// 가까운 수업 패널
		JPanel near_class = new JPanel(new GridLayout(0, 1)); // 중앙 기본베이스 패널
		near_class.setBorder(BorderFactory.createEtchedBorder()); // JPanel에 테두리 설정
		near_class.setBorder(new EmptyBorder(20, 20, 20, 20)); // 화면 양 사이드에 간격 주기

		JPanel second0 = new JPanel(new GridLayout(1, 0));
		JLabel label1 = new JLabel("가장가까운수업", JLabel.CENTER);
		second0.add(label1);

		JPanel second1 = new JPanel(new GridLayout(0, 1));
		second1.setBackground(Color.WHITE);
		JLabel label2 = new JLabel("알림제목");
		second1.add(label2);

		JPanel second2 = new JPanel(new GridLayout(0, 2));
		second2.setBackground(Color.WHITE);
		JLabel label3 = new JLabel("알림 내용");
		JLabel label5 = new JLabel("알림 내용", JLabel.RIGHT);
		second2.add(label3);
		second2.add(label5);

		JPanel second3 = new JPanel(new GridLayout(0, 1));
		second3.setBackground(Color.WHITE);
		JLabel label4 = new JLabel("알림 발생 시간 ");
		second3.add(label4);

		near_class.add(second0);
		near_class.add(second1);
		near_class.add(second2);
		near_class.add(second3);

		// 버튼 패널
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 150, 20)); // 플로우레이아웃을 이용해서 버튼을 아래로 내리기
		btnPanel.setPreferredSize(new Dimension(50, 300)); // 패널 사이즈
		JButton point_bt = new JButton();
		point_bt = new JButton(" 포인트 충전 ");
		point_bt.setPreferredSize(new Dimension(300, 50));// 버튼 사이즈
		JButton book_bt = new JButton();
		book_bt = new JButton(" 수업 예약 ");
		book_bt.setPreferredSize(new Dimension(300, 50));
		JButton attend_bt = new JButton();
		attend_bt = new JButton(" 출결 체크 ");
		attend_bt.setPreferredSize(new Dimension(300, 50));

		btnPanel.add(point_bt, BorderLayout.NORTH);
		btnPanel.add(book_bt, BorderLayout.CENTER);
		btnPanel.add(attend_bt, BorderLayout.SOUTH);

		add(noticePanel, BorderLayout.NORTH);
		add(near_class, BorderLayout.CENTER);
		add(btnPanel, BorderLayout.SOUTH);

		// 포인트충전 버튼
		point_bt.addActionListener(e -> {
			Client_ChargeP chargeP = new Client_ChargeP(main);
			main.pg1.add("chargeP", chargeP);
			main.cardlayout.show(main.pg1, "chargeP");
		});
		// 수업예약 클릭 이벤트
		book_bt.addActionListener(e -> {
			TabPage tab = new TabPage(main);
			main.pg1.add("tab", tab);
			main.cardlayout.show(main.pg1, "tab");
			TabPage.tabbedPane.setSelectedIndex(0);
		});
		// 출결체크 클릭 이벤트
		attend_bt.addActionListener(e -> {
			TabPage tab = new TabPage(main);
			main.pg1.add("tab", tab);
			main.cardlayout.show(main.pg1, "tab");
			TabPage.tabbedPane.setSelectedIndex(1);
		});

	}
}
