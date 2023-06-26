package com.ict5.admin;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Admin_Login extends JPanel {
	Admin_main main;
	CardLayout cardLayout;

	public Admin_Login(Admin_main main) {
		this.main = main;
		this.cardLayout = main.cardlayout;

		JPanel login= new JPanel(new BorderLayout()); // 메인 패널
		JPanel login1 = new JPanel(new BorderLayout()); // 패널을 반으로 나눠서 상단패널
		JPanel login2 = new JPanel(new BorderLayout());// 패널을 반으로 나눠서 하단패널
		
		JPanel one = new JPanel();//공지글 패널
		JLabel notify = new JLabel("<html><div style= 'padding-top: 150px; padding-bottom: 40px;' ><p style='text-align:center'> *관리자 로그인 페이지 입니다*.</p><br>일반 회원인 경우, 다른프로그램을 실행해주세요.</div>");
		one.add(notify, BorderLayout.NORTH);
		
		JPanel two = new JPanel();//id 패널
		JLabel id = new JLabel("<html><h2>I D :</h2>");
		JTextField idf = new JTextField(30);
		idf.setPreferredSize(new Dimension(50, 40));
		two.add(id);
		two.add(idf);
		
		JPanel three = new JPanel();//pw 패널
		JLabel pw = new JLabel("<html><h2>PW :</h2>");
		JTextField pwf = new JTextField(30);
		pwf.setPreferredSize(new Dimension(50, 40));
		three.add(pw);
		three.add(pwf);
		
		JPanel four = new JPanel();//확인문구 패널
		JLabel check = new JLabel("<html><p style='text-align:center; padding-bottom: 40px;'>아이디 혹은 비밀번호를 확인해주세요</p>");
		four.add(check);
		
		JPanel five = new JPanel();//버튼 패널
		JButton login_bt = new JButton("로그인");
		login_bt.setPreferredSize(new Dimension(100, 40));
		five.add(login_bt);
		
		login1.add(one,BorderLayout.NORTH);
		login1.add(two,BorderLayout.CENTER);
		login2.add(three,BorderLayout.NORTH);
		login2.add(four,BorderLayout.CENTER);
		login2.add(five,BorderLayout.SOUTH);
		
		login.add(login1,BorderLayout.NORTH);
		login.add(login2,BorderLayout.CENTER);
		
		add(login);
	
	
	}

}
