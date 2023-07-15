package com.ict5.admin;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.ict5.admin.panel.CoTable1;
import com.ict5.admin.panel.CoTable2;
import com.ict5.admin.panel.Navi;

public class Admin_CoMgmt2 extends JPanel {
	Admin_main main;
	public CoTable2 coTable2;
	
	public Admin_CoMgmt2(Admin_main main) {
		this.main = main;
		
	setLayout(new BorderLayout());
		coTable2 = new CoTable2(main);
		add(new Navi(main),BorderLayout.NORTH);
		add(coTable2,BorderLayout.CENTER);
		
		
		
	}
	
}
