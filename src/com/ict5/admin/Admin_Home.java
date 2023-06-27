package com.ict5.admin;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class Admin_Home extends JPanel {
	Admin_main main;
	
	public Admin_Home(Admin_main main) {
		this.main = main;
		
		
		
		
		
		setLayout(new BorderLayout());
		add(new Navi(main),BorderLayout.NORTH);



		add(new TimeTable(main),BorderLayout.WEST);

		
		JPanel jp_east = new JPanel(new BorderLayout());
		jp_east.add(new Point_new(),BorderLayout.NORTH);
		jp_east.add(new Member_new(),BorderLayout.SOUTH);
		add(jp_east,BorderLayout.EAST);

		
	}
	
}
