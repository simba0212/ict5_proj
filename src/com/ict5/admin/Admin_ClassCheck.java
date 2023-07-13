package com.ict5.admin;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import com.ict5.admin.panel.ClassCheck;
import com.ict5.admin.panel.ClassCheck2;
import com.ict5.admin.panel.Navi;
import com.ict5.admin.panel.TimeTable;

public class Admin_ClassCheck extends JPanel {
    Admin_main main;
    TimeTable timetable;
    ClassCheck classCheck;
    ClassCheck2 classCheck2;

    public Admin_ClassCheck(Admin_main main) {
        this.main = main;
        classCheck = new ClassCheck(main);
        classCheck2 = new ClassCheck2(main);
        timetable = new TimeTable(main); // timetable 객체 생성

        setLayout(new BorderLayout());

        add(new Navi(main), BorderLayout.NORTH);

        add(timetable, BorderLayout.WEST); // timetable 추가

        // Create a new panel to hold ClassCheck and ClassCheck2
        JPanel centerPanel = new JPanel(new BorderLayout());

        centerPanel.add(classCheck, BorderLayout.CENTER);
        centerPanel.add(classCheck2, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);
    }

    public void refreshData() {
        // timetable을 갱신하기 위해 refreshData() 메서드 호출
        timetable.refreshData();
    }
}
