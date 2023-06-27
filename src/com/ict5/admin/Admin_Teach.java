package com.ict5.admin;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class Admin_Teach extends JPanel {
	Admin_main main;
	
	public Admin_Teach(Admin_main main) {
		this.main = main;
		
		setLayout(new BorderLayout());
		add(new Admin_nav(main),BorderLayout.NORTH);
		add(new Teachsub(main),BorderLayout.CENTER);

		
	}
	
}
