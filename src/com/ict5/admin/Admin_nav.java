package com.ict5.admin;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Admin_nav extends JFrame {
	CardLayout cardLayout;
	JPanel pg1;

	public Admin_nav() {
		super("거구장센터 회원관리 프로그램");
		JPanel nav = new JPanel(new BorderLayout()); // 내비게이션패널

		JPanel navN = new JPanel(new BorderLayout()); // 내비 첫줄

		// 내비게이션 위/왼쪽 NorthWest
		JPanel navNW = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JButton bt_newPoint = new JButton("포인트 신청 N건", new ImageIcon("src/images/bell.png"));
		JButton bt_newClient = new JButton("회원등록");
		JButton bt_newClass = new JButton("수업등록");
		bt_newPoint.setPreferredSize(new Dimension(160, 40));
		bt_newClient.setPreferredSize(new Dimension(80, 40));
		bt_newClass.setPreferredSize(new Dimension(80, 40));
		navNW.add(bt_newPoint);
		navNW.add(bt_newClient);
		navNW.add(bt_newClass);
		navN.add(navNW, BorderLayout.WEST);

		// 내비게이션 위/오른쪽 NorthEast
		JPanel navNE = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		JButton logout = new JButton("로그아웃");
		logout.setPreferredSize(new Dimension(80, 40));

		navNE.add(new JLabel("관리자 로그인중"));
		navNE.add(new JLabel("|"));
		navNE.add(logout);
		navN.add(navNE, BorderLayout.EAST);
		// 첫줄 완료

		// 내비 두번째줄
		JPanel navS = new JPanel(new BorderLayout());

		// 내비 두번째 왼쪽
		JPanel navSW = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JButton bt_back = new JButton(new ImageIcon("src/images/back.png"));
		JButton bt_refresh = new JButton(new ImageIcon("src/images/refresh.png"));
		JTextField jtfMember = new JTextField("회원검색", 20);
		JButton bt_search = new JButton(new ImageIcon("src/images/search.png"));
		bt_back.setPreferredSize(new Dimension(80, 40));
		bt_refresh.setPreferredSize(new Dimension(80, 40));
		bt_search.setPreferredSize(new Dimension(80, 40));
		jtfMember.setPreferredSize(new Dimension(80, 40));
		navSW.add(bt_back);
		navSW.add(bt_refresh);
		navSW.add(jtfMember);
		navSW.add(bt_search);
		navS.add(navSW, BorderLayout.WEST);

		// 내비 두번째 오른쪽
		JPanel navSE = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 10));
		navSE.setBackground(Color.WHITE);
		JLabel sche = new JLabel("스케줄러", JLabel.CENTER);
		String[] options1 = { "수업관리", "수업등록 / 수정", "수업확인" };
		JComboBox<String> dropdown1 = new JComboBox<>(options1);
		String[] options2 = { "회원관리", "회원검색", "포인트관리" };
		JComboBox<String> dropdown2 = new JComboBox<>(options2);
		String[] options3 = { "강사관리", "강사목록", "강사등록", "강사수정 / 삭제" };
		JComboBox<String> dropdown3 = new JComboBox<>(options3);
		JLabel userApp = new JLabel("사용자 앱 관리", JLabel.CENTER);
		JLabel stat = new JLabel("통계보기", JLabel.CENTER);
		navSE.add(sche);
		navSE.add(dropdown1);
		navSE.add(dropdown2);
		navSE.add(dropdown3);
		navSE.add(userApp);
		navSE.add(stat);
		for (Component k : navSE.getComponents()) {
			k.setPreferredSize(new Dimension(100, 40));
		}
		navS.add(navSE, BorderLayout.EAST);

		// 내비 완성
		nav.add(navN, BorderLayout.NORTH);
		nav.add(navS, BorderLayout.SOUTH);
		getContentPane().add(nav, BorderLayout.NORTH);

		getContentPane().setBackground(Color.gray);
		setVisible(true);
		setResizable(false);
		setSize(1280, 840);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
			new Admin_nav();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
