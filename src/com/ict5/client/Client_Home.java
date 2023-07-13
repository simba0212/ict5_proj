package com.ict5.client;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.ict5.client.panel.Home;
import com.ict5.client.panel.UserTop;


public class Client_Home extends JPanel {
    Client_main main;
    Home home;
    UserTop usertop;
    public Client_Home(Client_main main) {
        this.main = main;
        home = new Home(main);
        usertop = new UserTop(main, false);
        setLayout(new BorderLayout());
        add(usertop, BorderLayout.NORTH);//
        add(home, BorderLayout.CENTER);
    }
}