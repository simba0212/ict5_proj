package com.ict5.admin;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.ict5.admin.panel.Navi;
import com.ict5.admin.panel.Point_MgmtSub;

public class Admin_PointMgmt extends JPanel {
	Admin_main main;
	Point_MgmtSub sub;
	public Admin_PointMgmt(Admin_main main) {
		this.main = main;
		sub = new Point_MgmtSub(main);
		setLayout(new BorderLayout());
		add(new Navi(main),BorderLayout.NORTH);
		add(sub,BorderLayout.CENTER);

		
	}
	
}
