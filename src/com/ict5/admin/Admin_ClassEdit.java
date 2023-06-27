package com.ict5.admin;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class Admin_ClassEdit extends JPanel {
	Admin_main main;
	
	public Admin_ClassEdit(Admin_main main) {
		this.main = main;
		
		
		setLayout(new BorderLayout());
		setBackground(Color.white);
		add(new Admin_nav(main),BorderLayout.NORTH);
		add(new Admin_TimeTable(main),BorderLayout.WEST);
		add(new ClassEdit1(),BorderLayout.EAST);
		
	}
}
