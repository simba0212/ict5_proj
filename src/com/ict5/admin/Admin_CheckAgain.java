package com.ict5.admin;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Admin_CheckAgain extends JPanel{
	Admin_main main;
	CardLayout cardLayout;
	
	
 public Admin_CheckAgain(Admin_main main) {
	this.main = main;
	this.cardLayout = main.cardlayout;
	
	
	JPanel inner1 = new JPanel(new BorderLayout());
	
	// 공지 패드
	JPanel one = new JPanel();
	JLabel one1 =new JLabel("<html><p style = 'text-align:center; padding-top: 100px'> *보안을 위해 비밀번호를 다시 입력하세요*</p> <br>"); 
	one.add(one1);
	
	// 패스워드 넣는 패드
	JPanel two = new JPanel();
	JLabel two1 = new JLabel("<html><h2> PW : </h2> ");
	JTextField twof = new JTextField(30);
	twof.setPreferredSize(new Dimension(50, 40));
	two.add(two1);
	two.add(twof);
	
	inner1.add(one, BorderLayout.NORTH);
	inner1.add(two, BorderLayout.CENTER);
	
	JPanel inner2 = new JPanel(new BorderLayout());
	
	// 알림 패드
	JPanel three = new JPanel();
	JLabel three1 = new JLabel("<html><p style = 'text-align:center; padding-bottom : 40px'>아이디 혹은 비밀번호를 확인하세요</p>");
	three.add(three1);
	
	// 로그인 버튼 
	JPanel four = new JPanel();
	JButton login_bt = new JButton("로그인");
	login_bt.setPreferredSize(new Dimension(100, 40));
	four.add(login_bt);
	
	inner2.add(three,BorderLayout.NORTH);
	inner2.add(four,BorderLayout.CENTER);
	
	JPanel inner = new JPanel(new BorderLayout());
	inner.add(inner1,BorderLayout.NORTH);
	inner.add(inner2,BorderLayout.CENTER);
	
	JPanel home = new JPanel(new BorderLayout());
	
	JPanel jp1 =new JPanel(new BorderLayout());
	JPanel jp1_1 =new JPanel(new BorderLayout());
	JPanel jp2 =new JPanel(new BorderLayout());
	JPanel jp2_1 =new JPanel(new BorderLayout());
	 Font font = new Font("돋움", Font.BOLD, 30);
	 

	JLabel jl1 = new JLabel("     회원관리");
	JLabel jl2 = new JLabel("회원검색");
	JLabel jl3 = new JLabel("     포인트관리    ");
	jl1.setFont(font);
	jl2.setFont(font);
	jl3.setFont(font);
	
	
	
	jp1.add(jl1,BorderLayout.WEST);
	jp1_1.add(jl2,BorderLayout.WEST);
	jp1_1.add(jl3,BorderLayout.EAST);
	
	jp1_1.setBackground(Color.lightGray);
	jp1.setBackground(Color.lightGray);
	jp1.add(jp1_1,BorderLayout.AFTER_LINE_ENDS);
	jp1.setPreferredSize(new Dimension(1280, 60));
	
	home.add(jp1,BorderLayout.NORTH);
	home.add(inner, BorderLayout.CENTER);
	
	
	
	setLayout(new BorderLayout());
	add(new Admin_nav(main),BorderLayout.NORTH);
	add(home,BorderLayout.CENTER);
	
	login_bt.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	});
	 
}
}
