package com.ict5.client;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.ict5.client.panel.PassChange;
import com.ict5.client.panel.UserTop;


public class Client_PassChange extends JPanel {
    Client_main main;

    public Client_PassChange(Client_main main) {
        this.main = main;

        setLayout(new BorderLayout());
		add(new UserTop(main, true),BorderLayout.NORTH);
		add(new PassChange(main),BorderLayout.CENTER);
	
    }
}
