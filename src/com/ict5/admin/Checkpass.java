package com.ict5.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Checkpass extends JFrame {
	public Checkpass() {
		JPanel left =new JPanel();
		JPanel right =new JPanel();
		left.setPreferredSize(new Dimension(640,840 ));
		right.setPreferredSize(new Dimension(640, 840));
		left.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		right.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
			add(left,BorderLayout.WEST);
			add(right,BorderLayout.EAST);
			
			setVisible(true);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setSize(1280, 840);
			setLocationRelativeTo(null);
			setResizable(true);
//		setSize(1280, 840);

		// 하단 공백 패널 추가
	
	}
	public static void main(String[] args) {
		new Checkpass();
	}
	
}
