package com.ict5.admin;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.ict5.admin.panel.CoTable1;
import com.ict5.admin.panel.Navi;

public class Admin_CoMgmt1 extends JPanel {
	Admin_main main;
	CoTable1 coTable1;
	public Admin_CoMgmt1(Admin_main main) {
		this.main = main;
		
	setLayout(new BorderLayout());
		coTable1 = new CoTable1(main);
		add(new Navi(main),BorderLayout.NORTH);
		add(coTable1,BorderLayout.CENTER);
		
		
		
	}
	
}
