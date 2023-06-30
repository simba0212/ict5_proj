package com.ict5.client;

import java.awt.BorderLayout;

import javax.swing.JPanel;


public class Client_Home extends JPanel {
    Client_main main;

    public Client_Home(Client_main main) {
        this.main = main;

        setLayout(new BorderLayout());
        add(new UserTop(main, false), BorderLayout.NORTH);//
        add(new Home(main), BorderLayout.CENTER);
    }
}

