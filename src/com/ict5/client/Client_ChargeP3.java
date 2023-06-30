package com.ict5.client;

import java.awt.BorderLayout;

import javax.swing.JPanel;


public class Client_ChargeP3 extends JPanel {
    Client_main main;

    public Client_ChargeP3(Client_main main) {
        this.main = main;

        setLayout(new BorderLayout());
		add(new UserTop(main, true),BorderLayout.NORTH);
		add(new ChargeP3(main),BorderLayout.CENTER);
	
    }
}
