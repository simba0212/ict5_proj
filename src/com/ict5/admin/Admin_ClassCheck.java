package com.ict5.admin;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import com.ict5.admin.panel.ClassCheck;
import com.ict5.admin.panel.ClassCheck2;
import com.ict5.admin.panel.Navi;
import com.ict5.admin.panel.TimeTable;

public class Admin_ClassCheck extends JPanel {
    Admin_main main;

    public Admin_ClassCheck(Admin_main main) {
        this.main = main;

        setLayout(new BorderLayout());

        add(new Navi(main), BorderLayout.NORTH);
        add(new TimeTable(main), BorderLayout.WEST);

        // Create a new panel to hold ClassCheck and ClassCheck2
        JPanel centerPanel = new JPanel(new BorderLayout());

        ClassCheck classCheck = new ClassCheck(main);
        ClassCheck2 classCheck2 = new ClassCheck2(main);

        centerPanel.add(classCheck, BorderLayout.CENTER);
        centerPanel.add(classCheck2, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);
    }
}
