package com.ict5.admin;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.ict5.admin.panel.Member_new;
import com.ict5.admin.panel.Navi;
import com.ict5.admin.panel.Point_new;
import com.ict5.admin.panel.TimeTable;

public class Admin_Home extends JPanel {
    Admin_main main;
    TimeTable timetable;
    Admin_ClassCheck classCheck;
    
    public Admin_Home(Admin_main main) {
        this.main = main;
        timetable = new TimeTable(main);
        setLayout(new BorderLayout());
        add(new Navi(main), BorderLayout.NORTH);
        add(timetable, BorderLayout.WEST);

        JPanel jp_east = new JPanel(new BorderLayout());
        jp_east.setBorder(BorderFactory.createEmptyBorder(0, 1, 0, 0)); // 패널의 테두리 설정

        // Point_new 패널의 테두리 설정
        JPanel pointPanel = new Point_new();
        Border pointBorder = BorderFactory.createLineBorder(Color.BLACK, 1); // 테두리 스타일 설정
        pointPanel.setBorder(pointBorder);
        jp_east.add(pointPanel, BorderLayout.NORTH);

        // Member_new 패널의 테두리 설정
        JPanel memberPanel = new Member_new();
        Border memberBorder = BorderFactory.createLineBorder(Color.BLACK, 1); // 테두리 스타일 설정
        memberPanel.setBorder(memberBorder);
        jp_east.add(memberPanel, BorderLayout.SOUTH);

        add(jp_east, BorderLayout.CENTER);
    }
}
