package com.ict5.admin;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class Admin_Mgmt extends JPanel {
	Admin_main main;
	
	public Admin_Mgmt(Admin_main main) {
		this.main = main;
		
	setLayout(new BorderLayout());
		add(new Admin_nav(main),BorderLayout.NORTH);

		add(new Admin_MgmtTable(main),BorderLayout.CENTER);
		
	}
	
}
