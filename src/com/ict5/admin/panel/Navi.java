package com.ict5.admin.panel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ict5.admin.Admin_main;
import com.ict5.db.Protocol;

public class Navi extends JPanel {
	Admin_main main;

	// 새로고침, 회원검색 기능 추가해야됨

	public Navi(Admin_main main) {
		this.main = main;
		JPanel nav = new JPanel(new BorderLayout()); // 내비게이션패널
		nav.setPreferredSize(new Dimension(1280, 120));
		nav.setBackground(Color.white);

		JPanel navN = new JPanel(new BorderLayout()); // 내비 첫줄
		navN.setBackground(Color.white);

		// 내비게이션 위/왼쪽 NorthWest
		JPanel navNW = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		navNW.setBackground(Color.white);
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
		navNE.setBackground(Color.white);
		JButton logout = new JButton("로그아웃");
		logout.setPreferredSize(new Dimension(90, 40));
		navNE.add(new JLabel("관리자 로그인중"));
		navNE.add(new JLabel("|"));
		navNE.add(logout);
		navN.add(navNE, BorderLayout.EAST);
		// 첫줄 완료

		// 내비 두번째줄
		JPanel navS = new JPanel(new BorderLayout());
		navS.setBackground(Color.white);

		// 내비 두번째 왼쪽
		JPanel navSW = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
		navSW.setBackground(Color.white);
		JButton bt_back = new JButton(new ImageIcon("src/images/back.png"));
		JButton bt_refresh = new JButton(new ImageIcon("src/images/refresh.png"));
		JTextField jtfMember = new JTextField(" 회원검색", 20);
		jtfMember.setEditable(false);
		JButton bt_search = new JButton(new ImageIcon("src/images/search.png"));
		bt_back.setPreferredSize(new Dimension(80, 40));
		bt_refresh.setPreferredSize(new Dimension(80, 40));
		bt_search.setPreferredSize(new Dimension(80, 40));
		jtfMember.setPreferredSize(new Dimension(80, 40));
		navSW.add(bt_back);
		navSW.add(bt_refresh);
		navSW.add(jtfMember);
		navSW.add(new JLabel("")); // 공백넣기
		navSW.add(bt_search);
		navS.add(navSW, BorderLayout.WEST);

		// 내비 두번째 오른쪽
		JPanel navSE = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		navSE.setBackground(Color.WHITE);
		JLabel sche = new JLabel("스케줄러", JLabel.CENTER);
		String[] options1 = { "수업관리", "수업등록 / 수정", "수업확인" };
		JComboBox<String> dropdown1 = new JComboBox<>(options1);
		String[] options2 = { "회원관리", "회원목록", "포인트관리" };
		JComboBox<String> dropdown2 = new JComboBox<>(options2);
		String[] options3 = { "강사관리", "강사목록", "강사스케줄", "강사등록" }; // 패널에 숫자에 맞춰서 목록 수정함
		JComboBox<String> dropdown3 = new JComboBox<>(options3);
		JLabel userApp = new JLabel("사용자 앱 관리", JLabel.CENTER);
		navSE.add(sche);
		navSE.add(dropdown1);
		navSE.add(dropdown2);
		navSE.add(dropdown3);
		navSE.add(userApp);
		for (Component k : navSE.getComponents()) {
			k.setPreferredSize(new Dimension(100, 40));
		}
		navS.add(navSE, BorderLayout.EAST);

		// 내비 완성
		nav.add(navN, BorderLayout.NORTH);
		nav.add(navS, BorderLayout.SOUTH);
		setBackground(Color.gray);
		setLayout(new BorderLayout());
		add(nav, BorderLayout.NORTH);

		//
		// 마우스 클릭 이벤트 처리
		jtfMember.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtfMember.setText("");
				jtfMember.setEditable(true); // 편집 가능하도록 설정
				jtfMember.requestFocus(); // 커서 포커스 설정
			}
		});

		bt_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				main.cardlayout.show(main.pg1, "home"); // 홈으로 이동
			}
		});

		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				main.cardlayout.show(main.pg1, "login");
			}
		});

		bt_newClass.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				main.cardlayout.show(main.pg1, "classEdit");
			}
		});

		bt_newClient.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				main.cardlayout.show(main.pg1, "member");
			}
		});

		bt_newPoint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				main.cardlayout.show(main.pg1, "checkagain");
			}
		});

		dropdown3.addActionListener(new ActionListener() { // 강사목록 보기
			@Override
			public void actionPerformed(ActionEvent e) {
				String selected = (String) dropdown3.getSelectedItem();
				if (selected.equals("강사목록")) {
					try {
						Protocol p = new Protocol();
						p.setCmd(1301);
						main.out.writeObject(p);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else if (selected.equals("강사스케줄")) {
					main.cardlayout.show(main.pg1, "coMg2");
				} else if (selected.equals("강사등록")) {
					try {
						Protocol p = new Protocol();
						p.setCmd(1308);
						main.out.writeObject(p);
						//main.cardlayout.show(main.pg1, "coMg3");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		dropdown2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selected = (String) dropdown2.getSelectedItem();
				if (selected.equals("회원목록")) {
					try {
						Protocol p = new Protocol();
						p.setCmd(1201);
						main.out.writeObject(p);
						main.out.flush();
						main.cardlayout.show(main.pg1, "member");
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					
				}
			}
		});


		dropdown2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selected = (String) dropdown2.getSelectedItem();
				if (selected.equals("포인트관리")) {
					main.cardlayout.show(main.pg1, "checkagain");
				}
			}
		});

		dropdown1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selected = (String) dropdown1.getSelectedItem();
				if (selected.equals("수업등록 / 수정")) {
					main.cardlayout.show(main.pg1, "classEdit");
				}
			}
		});

		dropdown1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selected = (String) dropdown1.getSelectedItem();
				if (selected.equals("수업확인")) {
					main.cardlayout.show(main.pg1, "classcheck");
				}
			}
		});

		userApp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				userApp.setForeground(Color.red);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				userApp.setForeground(Color.black);
			}
		});

		userApp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.cardlayout.show(main.pg1, "userMg"); // "member" 페이지로 이동
			}
		});

	}

}
