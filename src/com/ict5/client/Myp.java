package com.ict5.client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class Myp extends JPanel{
	Huge_main main;
CardLayout cardlayout;

public Myp(Huge_main main) {
	this.main = main;
	this.cardlayout = main.cardlayout;
	
	JPanel goal = new JPanel(); //goal -> 목표 표시칸을 위한 패널
	JPanel book = new JPanel(); // book -> 예약현황 페이지로 이동
	JPanel point =new JPanel(); // 포인트 조회 포인트로 이동
	JPanel noti=new JPanel(); // 알림 화면으로 이동
	JPanel setpass=new JPanel(); // 비밀번호 변경으로 이동
	JPanel logout=new JPanel(); // 로그아웃하기
	
	goal.setPreferredSize(new Dimension(400,200));
	goal.setBackground(Color.WHITE);
	goal.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	goal.add(new JLabel("목표"));
	// goal.add(*목표 불러오기*);
	
	book.setPreferredSize(new Dimension(400,120));
	book.setBackground(Color.WHITE);
	book.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	book.add(new JLabel("예약 현황"));
	
	point.setPreferredSize(new Dimension(400,80));
	point.setBackground(Color.WHITE);
	point.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	point.add(new JLabel("내 포인트 확인"));
	
	noti.setPreferredSize(new Dimension(400,80));
	noti.setBackground(Color.WHITE);
	noti.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	noti.add(new JLabel("알림 확인"));
	
	 setpass.setPreferredSize(new Dimension(400,80));
	 setpass.setBackground(Color.WHITE);
	 setpass.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	 setpass.add(new JLabel("비밀번호 변경"));
	 
	 logout.setPreferredSize(new Dimension(400,80));
	 logout.setBackground(Color.WHITE);
	 logout.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	 logout.add(new JLabel("로그아웃"));
	
	add(goal);
	add(book);
	add(point);
	add(noti);
	add(setpass);
	add(logout);
	

}
}
//import javax.swing.*;
//import java.awt.*;
//
//public class Myp extends JPanel {
//    Huge_main main;
//    CardLayout cardlayout;
//    private static final int PANEL_WIDTH = 400;
//    private static final int PANEL_HEIGHT = 80;
//
//    public Myp(Huge_main main) {
//        this.main = main;
//        this.cardlayout = main.cardlayout;
//
//        setLayout(new FlowLayout(FlowLayout.CENTER));
//
//        JPanel goal = createPanel("목표");
//        JPanel book = createPanel("예약 현황");
//        JPanel point = createPanel("내 포인트 확인");
//        JPanel noti = createPanel("알림 확인");
//        JPanel setpass = createPanel("비밀번호 변경");
//        JPanel logout = createPanel("로그아웃");
//
//        add(goal);
//        add(book);
//        add(point);
//        add(noti);
//        add(setpass);
//        add(logout);
//    }
//
//    private JPanel createPanel(String labelText) {
//        JPanel panel = new JPanel();
//        panel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
//        panel.setBackground(Color.WHITE);
//        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//        panel.setLayout(new BorderLayout());
//
//        JLabel label = new JLabel(labelText);
//        label.setHorizontalAlignment(JLabel.CENTER);
//        panel.add(label, BorderLayout.CENTER);
//
//        return panel;
//    }
//}

