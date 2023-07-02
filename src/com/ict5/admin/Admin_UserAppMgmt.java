package com.ict5.admin;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.ict5.admin.panel.Navi;
import com.ict5.admin.panel.UserAppEdit;

public class Admin_UserAppMgmt extends JPanel {
	Admin_main main;
	
	public Admin_UserAppMgmt(Admin_main main) {
		this.main = main;
		
	setLayout(new BorderLayout());
		add(new Navi(main),BorderLayout.NORTH);
		
		add(new UserAppEdit(main),BorderLayout.CENTER);
		
		
		
	}
	
}