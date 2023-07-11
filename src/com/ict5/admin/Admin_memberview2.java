
package com.ict5.admin;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.ict5.admin.panel.Member_view2;
import com.ict5.admin.panel.Navi;

public class Admin_memberview2 extends JPanel{
	public Admin_main main;
	public Member_view2 memberv2;
	public Admin_memberview2 (Admin_main main) {
		this.main = main;
		
		
		memberv2 = new Member_view2(main);
		setLayout(new BorderLayout());
		add(new Navi(main),BorderLayout.NORTH);
		add(memberv2,BorderLayout.CENTER);
		
//		JPanel jp_east = new JPanel(new BorderLayout());
//		jp_east.add(new Point_new(),BorderLayout.NORTH);
//		jp_east.add(new Member_new(),BorderLayout.SOUTH);
//		add(jp_east,BorderLayout.EAST);

		
	}
	

}