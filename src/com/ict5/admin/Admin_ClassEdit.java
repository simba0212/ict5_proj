package com.ict5.admin;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import com.ict5.admin.panel.ClassEdit1;
import com.ict5.admin.panel.Navi;
import com.ict5.admin.panel.TimeTable;

public class Admin_ClassEdit extends JPanel {
    Admin_main main;

    public Admin_ClassEdit(Admin_main main) {
        this.main = main;

        setLayout(new BorderLayout());
        setBackground(Color.white);
        add(new Navi(main), BorderLayout.NORTH);
        add(new TimeTable(main), BorderLayout.WEST);
        add(new ClassEdit1(main), BorderLayout.CENTER);
    }
}

