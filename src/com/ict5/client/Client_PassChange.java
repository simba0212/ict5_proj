package com.ict5.client;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.ict5.client.panel.PassChange;
import com.ict5.client.panel.UserTop;


public class Client_PassChange extends JPanel {
	public Client_main main;
    public PassChange pwchan;
    public UserTop usertop;
    public Client_PassChange(Client_main main) {
        this.main = main;
        pwchan = new PassChange(main);
        usertop = new UserTop(main, true);
        setLayout(new BorderLayout());
        add(usertop, BorderLayout.NORTH);//
        add(pwchan, BorderLayout.CENTER);
	
    }
}
