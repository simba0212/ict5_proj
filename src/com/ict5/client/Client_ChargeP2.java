package com.ict5.client;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.ict5.client.panel.ChargeP2;
import com.ict5.client.panel.UserTop;


public class Client_ChargeP2 extends JPanel {
    Client_main main;
    ChargeP2 cp2;
    UserTop usertop;
    public Client_ChargeP2(Client_main main) {
        this.main = main;
        cp2 = new ChargeP2(main);
        usertop = new UserTop(main, true);
        setLayout(new BorderLayout());
		add(usertop,BorderLayout.NORTH);
		add(cp2,BorderLayout.CENTER);
	
    }
}
