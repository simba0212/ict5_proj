package com.ict5.admin;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Admin_main extends JFrame {
	CardLayout cardlayout;
	JPanel pg1;
	
	
	public Admin_main() {
		super("거구로 거듭나자 거구장센터");
		cardlayout = new CardLayout();
		pg1 = new JPanel();
		pg1.setLayout(cardlayout);
		
//	클래스명 변수명 = new 클래스명(this);  이 클래스들은 각각의 페이지(카드)를 의미합니다.
		Admin_Home home = new Admin_Home(this);
		Admin_Login login = new Admin_Login(this);
		Admin_CheckAgain checkagain = new Admin_CheckAgain(this);
		Admin_ClassEdit classEdit = new Admin_ClassEdit(this);
		Admin_memberview member = new Admin_memberview(this);
		Admin_memberview2 member2 = new Admin_memberview2(this);
		Admin_CoMgmt1 coMg1 = new Admin_CoMgmt1(this);
		Admin_CoMgmt2 coMg2 = new Admin_CoMgmt2(this);
		Admin_PointMgmt point_Mgmt = new Admin_PointMgmt(this);
		Admin_CoMgmt3 coMg3 = new Admin_CoMgmt3(this);
		Admin_UserAppMgmt userMg = new Admin_UserAppMgmt(this);
		Admin_ClassCheck classcheck = new Admin_ClassCheck(this);
		
//	클래스명 변수명 = new 클래스명(this);
//	클래스명 변수명 = new 클래스명(this);
//	클래스명 변수명 = new 클래스명(this);
    
//	pg1.add("페이지명",객체이름);	각 페이지들의 이름을 지정해주고, 각 객체들로 해당 페이지로 이동합니다.


		pg1.add("home", home); // 홈
		pg1.add("login", login); // 로그인
		pg1.add("classEdit", classEdit); // 수업 수정
		pg1.add("member", member); // 회원 목록
		pg1.add("member2", member2); // 회원 세부사항
		pg1.add("checkagain", checkagain); // 보안 로그인
		pg1.add("coMg1", coMg1); // 강사관리-강사목록
		pg1.add("coMg2", coMg2); // 강사관리-해당강사의 수업
		pg1.add("coMg3", coMg3); // 강사 등록
		pg1.add("userMg", userMg); // 유저앱관리
		pg1.add("point_Mgmt", point_Mgmt); // 포인트 관리
		pg1.add("classcheck", classcheck); // 수업 확인

//		pg1.add("페이지명",객체이름);
	

		
		add(pg1);

		cardlayout.show(pg1, "login");
		
		setResizable(false);
		getContentPane().setBackground(Color.white);
		setSize(1280, 840);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
					Admin_main frame = new Admin_main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

}