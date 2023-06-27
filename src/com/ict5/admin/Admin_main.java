package com.ict5.admin;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
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

//		클래스명 변수명 = new 클래스명(this);  이 클래스들은 각각의 페이지(카드)를 의미합니다.
		Admin_Home home = new Admin_Home(this);
		Admin_ClassEdit classEdit = new Admin_ClassEdit(this);
		Admin_memberview member = new Admin_memberview(this);
		Admin_memberview2 member2 = new Admin_memberview2(this);
//		클래스명 변수명 = new 클래스명(this);
//		클래스명 변수명 = new 클래스명(this);
//		클래스명 변수명 = new 클래스명(this);
//		pg1.add("페이지명",객체이름);	각 페이지들의 이름을 지정해주고, 각 객체들로 해당 페이지로 이동합니다.
		pg1.add("home", home);
		pg1.add("classEdit", classEdit);
		pg1.add("member", member);
		pg1.add("member2", member2);
//		pg1.add("페이지명",객체이름);

		
		add(pg1);
		cardlayout.show(pg1, "member2");
		
		
		setResizable(true);
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