package com.ict5.client;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.ict5.client.panel.ChargeP3;
import com.ict5.client.panel.UserTop;


public class Client_ChargeP3 extends JPanel {
	public Client_main main;
	public ChargeP3 cp3;
    public UserTop usertop;
    public Client_ChargeP3(Client_main main) {
        this.main = main;
        cp3 = new ChargeP3(main);
        usertop = new UserTop(main, true);
        setLayout(new BorderLayout());
		add(usertop,BorderLayout.NORTH);
		add(cp3,BorderLayout.CENTER);
	
    }
}
