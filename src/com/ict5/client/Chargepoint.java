package com.ict5.client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Chargepoint extends JPanel{
	Huge_main main;
	CardLayout cardlayout;
	public Chargepoint(Huge_main main) {
		this.main = main;
		this.cardlayout = main.cardlayout;
		JPanel top =new JPanel(new BorderLayout());
		JPanel mid =new JPanel();
		JPanel mid_1 =new JPanel();
		JPanel mid_2 =new JPanel(new GridLayout(0,1));
		JPanel bot =new JPanel();
		Icon icon = new ImageIcon("../images/complete.png"); // 아이콘 이미지 파일 경로에 맞게 수정
		Font font = new Font("돋움", Font.PLAIN, 20);
		JButton bot_but =new JButton("다음");
		JLabel top1 = new JLabel("   포인트 충전");
		top1.setFont(font);
		top.setPreferredSize(new Dimension(450,100));
		top.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // 아래쪽 테두리 설정
		top.add(top1);
		
		
		
		
		
		JLabel midlabel = new JLabel("충전 금액 선택",JLabel.CENTER);
		
		midlabel.setFont(font);
		midlabel.setPreferredSize(new Dimension(420,60));
		mid_1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		mid.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		mid_1.setPreferredSize(new Dimension(420,70));
		mid_1.add(midlabel);
		
		JRadioButton radioButton1 = new JRadioButton("50만 포인트");
		radioButton1.setIcon(icon);
		radioButton1.setFont(font);
		JRadioButton radioButton2 = new JRadioButton("10만 포인트");
		radioButton2.setIcon(icon);
		radioButton2.setFont(font);
		JRadioButton radioButton3 = new JRadioButton("5만 포인트");
		radioButton3.setIcon(icon);
		radioButton3.setFont(font);
		JRadioButton radioButton4 = new JRadioButton("1만 포인트");
		radioButton4.setIcon(icon);
		radioButton4.setFont(font);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(radioButton1);
		buttonGroup.add(radioButton2);
		buttonGroup.add(radioButton3);
		buttonGroup.add(radioButton4);
		mid_1.setBackground(Color.WHITE);
		radioButton1.setBackground(Color.WHITE);
		radioButton2.setBackground(Color.WHITE);
		radioButton3.setBackground(Color.WHITE);
		radioButton4.setBackground(Color.WHITE);
		mid_2.add(radioButton1);
		mid_2.add(radioButton2);
		mid_2.add(radioButton3);
		mid_2.add(radioButton4);
		mid_2.setPreferredSize(new Dimension(440,300));
//		mid_1.add("");
		
		mid.setPreferredSize(new Dimension(450,400));
		mid.add(mid_1);
		mid.add(mid_2);
		bot_but.setFont(font);
		bot.add(bot_but);
		bot_but.setPreferredSize(new Dimension(150,70));
		
		
		add(top,BorderLayout.NORTH);
		add(mid,BorderLayout.CENTER);
		add(bot,BorderLayout.SOUTH);
		
		
	}
}
