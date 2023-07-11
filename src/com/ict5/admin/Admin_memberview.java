package com.ict5.admin;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.ict5.admin.panel.Member_view;
import com.ict5.admin.panel.Navi;

public class Admin_memberview extends JPanel {
	Admin_main main;
	Member_view memberv;
	
	public Admin_memberview (Admin_main main) {
		this.main = main;
		memberv = new Member_view(main);
		
		
		
		
		setLayout(new BorderLayout());
		add(new Navi(main),BorderLayout.NORTH);
		add(memberv,BorderLayout.CENTER);
		

		
	}
	

}