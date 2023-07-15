package com.ict5.admin;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.ict5.admin.panel.Navi;
import com.ict5.db.Protocol;
import com.ict5.db.VO;

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
	JPasswordField twof = new JPasswordField(30);
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
	 

	JLabel jl1 = new JLabel("회원관리");
	jl1.setFont(jl1.getFont().deriveFont(Font.BOLD,17f));
	jl1.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
	JLabel jl2 = new JLabel("회원검색");
	jl2.setFont(jl2.getFont().deriveFont(Font.BOLD,17f));
	jl2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	JLabel jl3 = new JLabel("포인트관리");
	jl3.setFont(jl3.getFont().deriveFont(Font.BOLD,17f));
	jl3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	
	
	
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
	add(new Navi(main),BorderLayout.NORTH);
	add(home,BorderLayout.CENTER);
	
	twof.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				Protocol p = new Protocol();
				VO vo = new VO();
				vo.setAdmin_id(main.admin_id);
				vo.setAdmin_pw(new String(twof.getPassword()));
				p.setCmd(1206);
				p.setVo(vo);
				main.out.writeObject(p);
				main.out.flush();
			} catch (Exception e2) {
			}
		}
	});
	
	login_bt.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				Protocol p = new Protocol();
				VO vo = new VO();
				vo.setAdmin_id(main.admin_id);
				vo.setAdmin_pw(new String(twof.getPassword()));
				p.setCmd(1206);
				p.setVo(vo);
				main.out.writeObject(p);
				main.out.flush();
			} catch (Exception e2) {
			}
		}
	});
	
	jl1.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseEntered(MouseEvent e) {
	    	jl1.setForeground(Color.red);
	    }
	    
	    @Override
	    public void mouseExited(MouseEvent e) {
	    	jl1.setForeground(Color.black);
	    }
	});
	
	jl1.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
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
	});
	 
}
}
