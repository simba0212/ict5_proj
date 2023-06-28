package com.ict5.admin;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class Admin_PointMgmt extends JPanel {
	Admin_main main;
	
	public Admin_PointMgmt(Admin_main main) {
		this.main = main;
		
		setLayout(new BorderLayout());
		add(new Navi(main),BorderLayout.NORTH);
		add(new Point_MgmtSub(main),BorderLayout.CENTER);

		
	}
	
}
