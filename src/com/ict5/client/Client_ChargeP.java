package com.ict5.client;

import java.awt.BorderLayout;

import javax.swing.JPanel;


public class Client_ChargeP extends JPanel {
    Client_main main;
    ChargeP cp;
    public Client_ChargeP(Client_main main) {
        this.main = main;
        cp =  new ChargeP(main);
        setLayout(new BorderLayout());
		add(new UserTop(main, true),BorderLayout.NORTH);
		add(cp,BorderLayout.CENTER);
	
    }
}
