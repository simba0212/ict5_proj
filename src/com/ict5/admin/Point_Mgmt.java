package com.ict5.admin;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class Point_Mgmt extends JPanel {
	Admin_main main;
	
	public Point_Mgmt(Admin_main main) {
		this.main = main;
		
		setLayout(new BorderLayout());
		add(new Admin_nav(main),BorderLayout.NORTH);
		add(new Point_MgmtSub(main),BorderLayout.CENTER);

		
	}
	
}
