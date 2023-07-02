package com.ict5.admin;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.ict5.admin.panel.CoMgmt4;
import com.ict5.admin.panel.Navi;

public class Admin_CoMgmt3 extends JPanel {
	Admin_main main;
	
	public Admin_CoMgmt3(Admin_main main) {
		this.main = main;
		
		setLayout(new BorderLayout());

	
		add(new Navi(main),BorderLayout.NORTH);
		add(new CoMgmt4(main),BorderLayout.CENTER);

		
	}
	
}
