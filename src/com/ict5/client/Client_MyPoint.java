package com.ict5.client;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.ict5.client.panel.Mypoint;
import com.ict5.client.panel.UserTop;


public class Client_MyPoint extends JPanel {
    Client_main main;
    UserTop usertop;
    Mypoint po;
    public Client_MyPoint(Client_main main) {
        this.main = main;
        po = new Mypoint(main);
        usertop = new UserTop(main, true);
        setLayout(new BorderLayout());
        add(usertop, BorderLayout.NORTH);
		add(po,BorderLayout.CENTER);
	
    }
}
