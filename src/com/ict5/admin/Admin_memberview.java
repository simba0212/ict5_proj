package com.ict5.admin;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.ict5.admin.panel.Member_view;
import com.ict5.admin.panel.Navi;

public class Admin_memberview extends JPanel {
	Admin_main main;
	
	public Admin_memberview (Admin_main main) {
		this.main = main;
		
		
		
		
		
		setLayout(new BorderLayout());
		add(new Navi(main),BorderLayout.NORTH);
		add(new Member_view(main),BorderLayout.CENTER);
		
//		JPanel jp_east = new JPanel(new BorderLayout());
//		jp_east.add(new Point_new(),BorderLayout.NORTH);
//		jp_east.add(new Member_new(),BorderLayout.SOUTH);
//		add(jp_east,BorderLayout.EAST);

		
	}
	

}