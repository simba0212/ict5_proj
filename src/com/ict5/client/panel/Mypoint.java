package com.ict5.client.panel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ict5.client.Client_main;

public class Mypoint extends JPanel {
    Client_main main;
    CardLayout cardlayout;

    public Mypoint(Client_main main) {
        this.main = main;
        this.cardlayout = main.cardlayout;
        JPanel jp1 = new JPanel();
        JPanel mp = new JPanel();

        Font font = new Font("돋움", Font.PLAIN, 30);

        jp1.setLayout(new BorderLayout());
        mp.setLayout(new BorderLayout());
        JPanel mp1, mp2;
        mp1 = new JPanel();
        mp2 = new JPanel();

        int point_num = 5000; // 데이터베이스에서 포인트 가져오기
        JLabel point = new JLabel(); // 포인트 가져오기
        JLabel mypoint = new JLabel("내 포인트"); // 포인트 가져오기
        point.setText(String.valueOf(point_num)); // 데이터 형식 String으로 변환
        point.setFont(font);
        mypoint.setFont(font);

        mp1.add(mypoint);

        mp2.add(point);

        mp.add(mp1, BorderLayout.NORTH);
        mp.add(mp2, BorderLayout.EAST);
        mp.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // 아래쪽 테두리 설정

        JPanel plist = new JPanel();
        JLabel jl1 = new JLabel("포인트 이력");
        Font font2 = new Font("돋움", Font.PLAIN, 20);
        jl1.setFont(font2);
        plist.add(jl1);

        plist.setLayout(new GridLayout(0, 1));
        int ii = 4; // 제공될 알림의 수 지정하는 변수
        // 패널 배열 생성
        JPanel[] panels = new JPanel[ii];
        for (int i = 0; i < panels.length; i++) {

            panels[i] = createPanel(); // 패널 생성 및 배열에 할당
            plist.add(panels[i]); // 프레임에 패널 추가

        }
        jp1.add(mp, BorderLayout.CENTER);
        jp1.add(plist, BorderLayout.SOUTH);

        add(jp1);

    }

    private static JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(400, 100));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.setPreferredSize(new Dimension(400, 30));
        JPanel panel2 = new JPanel(new BorderLayout());
        JPanel panel3 = new JPanel(new BorderLayout());
        panel1.setBackground(Color.WHITE);
        panel2.setBackground(Color.WHITE);
        panel3.setBackground(Color.WHITE);

        JLabel label = new JLabel("p+15000");
        Font labelFont = label.getFont();
        label.setFont(labelFont.deriveFont(20f));
        JButton button = new JButton("입금알람");
        JLabel label2 = new JLabel("무통장입금");
        JLabel label3 = new JLabel("알림 발생 시간 date", JLabel.RIGHT);

        panel1.add(label, BorderLayout.WEST);
        panel1.add(button, BorderLayout.EAST);
        panel2.add(label2, BorderLayout.WEST);
        panel3.add(label3, BorderLayout.EAST);

        panel.add(panel1, BorderLayout.NORTH);
        panel.add(panel2, BorderLayout.CENTER);
        panel.add(panel3, BorderLayout.SOUTH);

        
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton clickedButton = (JButton) e.getSource();
                if (clickedButton.getText().equals("입금알람")) {
                    clickedButton.setText("승인대기");
                    clickedButton.setEnabled(false);
                   
                }
            }
        });

        return panel;
    }

    public void refresh() {

    }
}
