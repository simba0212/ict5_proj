package com.ict5.client;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.ict5.client.panel.ChargeP;
import com.ict5.client.panel.UserTop;


public class Client_ChargeP extends JPanel {
    Client_main main;
    public ChargeP cp;
    UserTop usertop;
    public Client_ChargeP(Client_main main) {
        this.main = main;
        cp =  new ChargeP(main);
        usertop = new UserTop(main, true);
        setLayout(new BorderLayout());
		add(usertop,BorderLayout.NORTH);
		add(cp,BorderLayout.CENTER);
	
    }
}
