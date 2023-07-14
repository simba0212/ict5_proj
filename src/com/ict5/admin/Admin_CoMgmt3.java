package com.ict5.admin;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.ict5.admin.panel.CoMgmt4;
import com.ict5.admin.panel.Navi;

public class Admin_CoMgmt3 extends JPanel {
	Admin_main main;
	public CoMgmt4 coMgmt4;
	
	public Admin_CoMgmt3(Admin_main main) {
		this.main = main;
		
		setLayout(new BorderLayout());

		coMgmt4 = new CoMgmt4(main);
		add(new Navi(main),BorderLayout.NORTH);
		add(coMgmt4,BorderLayout.CENTER);

		
	}
	
}
