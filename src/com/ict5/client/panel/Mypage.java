package com.ict5.client.panel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.ict5.client.Client_main;

public class Mypage extends JPanel {
	Client_main main;
	CardLayout cardlayout;
	TabPage tab;

public Mypage(Client_main main) {
	this.main = main;
	this.cardlayout = main.cardlayout;
	  JTextArea jta = new JTextArea(8, 35); // 10행 20열의 JTextArea 생성
	  jta.setLineWrap(true);
	  JScrollPane jsp = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			  JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	 jta.setEditable(false);
	  Font font = new Font("돋움", Font.PLAIN, 20);
	  Font font2 = new Font("돋움", Font.PLAIN, 30);
	  
	  
	JPanel goal = new JPanel(); //goal -> 목표 표시칸을 위한 패널
	JPanel book = new JPanel(new BorderLayout()); // book -> 예약현황 페이지로 이동
	JPanel point =new JPanel(new BorderLayout()); // 포인트 조회 포인트로 이동
	JPanel noti=new JPanel(new BorderLayout()); // 알림 화면으로 이동
	JPanel setpass=new JPanel(new BorderLayout()); // 비밀번호 변경으로 이동
	JPanel logout=new JPanel(new BorderLayout()); // 로그아웃하기
	JButton jb1= new JButton("수정");
	JButton jb2= new JButton("저장");
	
	goal.setPreferredSize(new Dimension(400,180));
	goal.setBackground(Color.WHITE);
	goal.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	
	goal.add(jsp);
	goal.add(jb1);
	goal.add(jb2);
	
	// goal.add(*목표 불러오기*);
	
	book.setPreferredSize(new Dimension(400,80));
	book.setBackground(Color.WHITE);
	book.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	JLabel book1 =new JLabel("        예약 현황                 >>",JLabel.CENTER);
	book1.setFont(font);
	book.add(book1);
	
	
	point.setPreferredSize(new Dimension(400,80));
	point.setBackground(Color.WHITE);
	point.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	JLabel point1 =new JLabel("     내 포인트 현황               >>",JLabel.CENTER);
	point1.setFont(font);
	point.add(point1);
	
	noti.setPreferredSize(new Dimension(400,80));
	noti.setBackground(Color.WHITE);
	noti.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	JLabel noti1 =new JLabel("          알림                     >>",JLabel.CENTER);
	noti1.setFont(font);
	noti.add(noti1);
	
	 setpass.setPreferredSize(new Dimension(400,80));
	 setpass.setBackground(Color.WHITE);
	 setpass.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	 JLabel setpass1 =new JLabel("    비밀번호 변경               >>",JLabel.CENTER);
	 setpass1.setFont(font);
	 setpass.add(setpass1);
	 
	 logout.setPreferredSize(new Dimension(400,80));
	 logout.setBackground(Color.WHITE);
	 logout.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	 JLabel logout1 =new JLabel("      로그아웃                  >>",JLabel.CENTER);
	 logout1.setFont(font);
	 logout.add(logout1);
	 
	 JPanel jp = new JPanel(new GridLayout(0, 1));
	 JPanel jp2 = new JPanel(new BorderLayout());
	 

//	add(new JLabel("목표"));

	JLabel jl1 =new JLabel("목표",JLabel.CENTER);
	jl1.setFont(font2);
	jp2.add(jl1,BorderLayout.NORTH);
	jp2.add(goal);
	jp.add(book);
	jp.add(point);
	jp.add(noti);
	jp.add(setpass);
	jp.add(logout);
	add(jp2);
	add(jp);
	
	book.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			 main.cardlayout.show(main.pg1, "tab");
             TabPage.tabbedPane.setSelectedIndex(1);
		}
			

		});
	
		point.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.myPo.po.refresh();
	            main.cardlayout.show(main.pg1, "myPo");
			}

		});
		noti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 main.cardlayout.show(main.pg1, "tab");
	             TabPage.tabbedPane.setSelectedIndex(2);
			}

		});
		setpass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
	            main.cardlayout.show(main.pg1, "pwChan");
			}

		});
		logout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
	            main.cardlayout.show(main.pg1, "login");
			}

		});
	jb1.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	});

	}
}
