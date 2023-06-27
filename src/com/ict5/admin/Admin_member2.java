package com.ict5.admin;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class Admin_member2 extends JPanel{
	Admin_main main;
	
	public Admin_member2 (Admin_main main) {
		this.main = main;
		
		
		
		
		
		setLayout(new BorderLayout());
		add(new Admin_nav(main),BorderLayout.NORTH);
		add(new Member_view2(main),BorderLayout.CENTER);
		
//		JPanel jp_east = new JPanel(new BorderLayout());
//		jp_east.add(new Point_new(),BorderLayout.NORTH);
//		jp_east.add(new Member_new(),BorderLayout.SOUTH);
//		add(jp_east,BorderLayout.EAST);

		
	}
	
}