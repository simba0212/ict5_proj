package com.ict5.client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.MouseInputAdapter;

public class Myp extends JPanel{
	Huge_main main;
CardLayout cardlayout;

public Myp(Huge_main main) {
	this.main = main;
	this.cardlayout = main.cardlayout;
	  JTextArea jta = new JTextArea(10, 35); // 10행 20열의 JTextArea 생성
	  jta.setLineWrap(true);
	  JScrollPane jsp = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			  JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	JPanel goal = new JPanel(); //goal -> 목표 표시칸을 위한 패널
	JPanel book = new JPanel(new BorderLayout()); // book -> 예약현황 페이지로 이동
	JPanel point =new JPanel(new BorderLayout()); // 포인트 조회 포인트로 이동
	JPanel noti=new JPanel(new BorderLayout()); // 알림 화면으로 이동
	JPanel setpass=new JPanel(new BorderLayout()); // 비밀번호 변경으로 이동
	JPanel logout=new JPanel(new BorderLayout()); // 로그아웃하기
	JButton jb1= new JButton("수정");
	JButton jb2= new JButton("저장");
	
	goal.setPreferredSize(new Dimension(400,200));
	goal.setBackground(Color.WHITE);
	goal.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	
	goal.add(jsp);
	goal.add(jb1);
	goal.add(jb2);
	
	// goal.add(*목표 불러오기*);
	
	book.setPreferredSize(new Dimension(400,100));
	book.setBackground(Color.WHITE);
	book.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	book.add(new JLabel("예약 현황",JLabel.CENTER));
	
	point.setPreferredSize(new Dimension(400,80));
	point.setBackground(Color.WHITE);
	point.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	point.add(new JLabel("내 포인트 현황",JLabel.CENTER));
	
	noti.setPreferredSize(new Dimension(400,80));
	noti.setBackground(Color.WHITE);
	noti.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	noti.add(new JLabel("알림",JLabel.CENTER));
	
	 setpass.setPreferredSize(new Dimension(400,80));
	 setpass.setBackground(Color.WHITE);
	 setpass.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	 setpass.add(new JLabel("비밀번호 변경",JLabel.CENTER));
	 
	 logout.setPreferredSize(new Dimension(400,80));
	 logout.setBackground(Color.WHITE);
	 logout.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	 
	 logout.add(new JLabel("로그아웃",JLabel.CENTER));
	 JPanel jp = new JPanel(new GridLayout(0, 1));
	 JPanel jp2 = new JPanel(new BorderLayout());
	 
//	add(new JLabel("목표"));
	 JLabel jl1 =new JLabel("목표",JLabel.CENTER);
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
			// TODO Auto-generated method stub
			cardlayout.show(main.pg1, "nt"); // 이동 타갯 변경해야함
		}
			
		});
	point.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			cardlayout.show(main.pg1, "nt");// 이동 타갯 변경해야함
		}
			
		});
	noti.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			cardlayout.show(main.pg1, "nt");// 이동 타갯 변경해야함
		}
			
		});
	setpass.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			cardlayout.show(main.pg1, "nt");// 이동 타갯 변경해야함
		}
			
		});
	logout.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			cardlayout.show(main.pg1, "nt");// 이동 타갯 변경해야함
		}
			
		});

}
}
