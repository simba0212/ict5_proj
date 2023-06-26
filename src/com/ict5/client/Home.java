package com.ict5.client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Home extends JPanel {
	Huge_main main;
	CardLayout cardlayout;

	public Home(Huge_main main) {
		this.main = main;
		this.cardlayout = main.cardlayout;

		// 화면단 패널을 공지사항, 가까운수업, 버튼 총3개로 나눔
		setLayout(new BorderLayout());
		
		
		JPanel topPanel = new JPanel(new BorderLayout());
        JButton alarmButton = new JButton("알람");
        JButton logoutButton = new JButton("로그아웃");
        JButton backButton = new JButton("<<");
        JLabel customerLabel = new JLabel("OOO고객님");

        // 버튼과 라벨을 한 줄로 배치하기 위한 패널
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(alarmButton);
        buttonPanel.add(logoutButton);//12312314123
        buttonPanel.add(customerLabel);

        topPanel.add(backButton, BorderLayout.WEST);
        topPanel.add(buttonPanel, BorderLayout.EAST);


		// 공지사항 패널
		JPanel first = new JPanel();
		JTextArea notice = new JTextArea();
		notice = new JTextArea(10, 40);
		notice.setLineWrap(true); // 자동 줄 바꿈 jta 는 무조건 jscrollpane 과 짝궁
		notice.setEditable(false);
		JScrollPane jsp = new JScrollPane(notice, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		first.add(new JLabel(" 공  지  사  항 "));
		first.add(notice);

		// 가까운 수업 패널
		JPanel second = new JPanel(new GridLayout(0, 1)); // 중앙 기본베이스 패널
		second.setBorder(new EmptyBorder(20, 20, 20, 20)); // 화면 양 사이드에 간격 주기

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

		second.add(second0);
		second.add(second1);
		second.add(second2);
		second.add(second3);

		// 버튼 패널
		JPanel last = new JPanel(new FlowLayout(FlowLayout.CENTER, 150, 20)); //플로우레이아웃을 이용해서 버튼을 아래로 내리기
		last.setPreferredSize(new Dimension(50, 300)); // 패널 사이즈
		JButton point_bt = new JButton();
		point_bt = new JButton(" 포인트 충전 ");
		point_bt.setPreferredSize(new Dimension(300, 50));// 버튼 사이즈
		JButton book_bt = new JButton();
		book_bt = new JButton(" 수업 예약 ");
		book_bt.setPreferredSize(new Dimension(300, 50));
		JButton attend_bt = new JButton();
		attend_bt = new JButton(" 출결 체크 ");
		attend_bt.setPreferredSize(new Dimension(300, 50));

		last.add(point_bt, BorderLayout.NORTH);
		last.add(book_bt, BorderLayout.CENTER);
		last.add(attend_bt, BorderLayout.SOUTH);

		
		add(topPanel, new BorderLayout().NORTH);
		add(first, new BorderLayout().NORTH);
		add(second, new BorderLayout().CENTER);
		add(last, new BorderLayout().SOUTH);

	}
}
