package com.ict5.client;

import java.awt.BorderLayout;

import javax.swing.JPanel;


public class Client_MyPoint extends JPanel {
    Client_main main;

    public Client_MyPoint(Client_main main) {
        this.main = main;

        setLayout(new BorderLayout());
		add(new UserTop(main, true),BorderLayout.NORTH);
		add(new Mypoint(main),BorderLayout.CENTER);
	
    }
}
