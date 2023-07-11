package com.ict5.client.panel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.ict5.client.Client_main;
import com.ict5.db.VO;

public class ChargeP extends JPanel {
    Client_main main;
    CardLayout cardlayout;
    int selectedPoints;
    JButton bot_btn;
    JLabel selectedPointsLabel;
    
    VO vo;

    public ChargeP(Client_main main) {
        this.main = main;
        this.cardlayout = main.cardlayout;
        this.vo = main.vo; // vo 동기화

        JPanel top = new JPanel(new BorderLayout());
        JLabel top1 = new JLabel("   포인트 충전");
        top1.setFont(top1.getFont().deriveFont(Font.BOLD, 20f));
        top.setPreferredSize(new Dimension(450, 100));
        top.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // 아래쪽 테두리 설정
        top.add(top1);

        JPanel mid = new JPanel();
        JPanel mid_1 = new JPanel();
        JPanel mid_2 = new JPanel(new GridLayout(0, 1));
        Icon icon = new ImageIcon("../images/complete.png"); // 아이콘 이미지 파일 경로에 맞게 수정

        JLabel midlabel = new JLabel("충전 금액 선택", JLabel.CENTER);
        midlabel.setFont(midlabel.getFont().deriveFont(Font.BOLD, 17f));

        midlabel.setPreferredSize(new Dimension(420, 60));
        mid_1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mid.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mid_1.setPreferredSize(new Dimension(420, 70));
        mid_1.add(midlabel);

        JRadioButton radioButton1 = new JRadioButton("50만 포인트");
        radioButton1.setFont(radioButton1.getFont().deriveFont(Font.BOLD, 20f));
        radioButton1.setIcon(icon);
        JRadioButton radioButton2 = new JRadioButton("10만 포인트");
        radioButton2.setFont(radioButton2.getFont().deriveFont(Font.BOLD, 20f));
        radioButton2.setIcon(icon);
        JRadioButton radioButton3 = new JRadioButton("5만 포인트");
        radioButton3.setFont(radioButton3.getFont().deriveFont(Font.BOLD, 20f));
        radioButton3.setIcon(icon);
        JRadioButton radioButton4 = new JRadioButton("1만 포인트");
        radioButton4.setFont(radioButton4.getFont().deriveFont(Font.BOLD, 20f));
        radioButton4.setIcon(icon);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        buttonGroup.add(radioButton3);
        buttonGroup.add(radioButton4);
        mid_1.setBackground(Color.WHITE);
        radioButton1.setBackground(Color.WHITE);
        radioButton2.setBackground(Color.WHITE);
        radioButton3.setBackground(Color.WHITE);
        radioButton4.setBackground(Color.WHITE);
        mid_2.add(radioButton1);
        mid_2.add(radioButton2);
        mid_2.add(radioButton3);
        mid_2.add(radioButton4);
        mid_2.setPreferredSize(new Dimension(440, 300));

        mid.setPreferredSize(new Dimension(450, 420));
        mid.add(mid_1);
        mid.add(mid_2);

        JPanel bot = new JPanel();
        JButton bot_btn = new JButton("다음");
        bot.add(bot_btn);
        bot_btn.setPreferredSize(new Dimension(120, 40));
        bot.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
        bot_btn.setEnabled(false);

        selectedPointsLabel = new JLabel("0 포인트");
        selectedPointsLabel.setFont(selectedPointsLabel.getFont().deriveFont(Font.BOLD, 16f));
        mid.add(selectedPointsLabel);

        add(top, BorderLayout.NORTH);
        add(mid, BorderLayout.CENTER);
        add(bot, BorderLayout.SOUTH);

        // 다음 버튼-> 결제
        radioButton1.addActionListener(e -> {
            if (radioButton1.isSelected()) {
                selectedPoints = 500000;
                selectedPointsLabel.setText(String.valueOf(selectedPoints) + " 포인트");
                bot_btn.setEnabled(true);
            }
        });

        radioButton2.addActionListener(e -> {
            if (radioButton2.isSelected()) {
                selectedPoints = 100000;
                selectedPointsLabel.setText(String.valueOf(selectedPoints) + " 포인트");
                bot_btn.setEnabled(true);
            }
        });

        radioButton3.addActionListener(e -> {
            if (radioButton3.isSelected()) {
                selectedPoints = 50000;
                selectedPointsLabel.setText(String.valueOf(selectedPoints) + " 포인트");
                bot_btn.setEnabled(true);
            }
        });

        radioButton4.addActionListener(e -> {
            if (radioButton4.isSelected()) {
                selectedPoints = 10000;
                selectedPointsLabel.setText(String.valueOf(selectedPoints) + " 포인트");
                bot_btn.setEnabled(true);
            }
        });
        //다음
        bot_btn.addActionListener(e -> {
        	String str = selectedPoints+"";
        	main.cp2.selectedPointsLabel.setText(str);
            main.cardlayout.show(main.pg1, "chargeP2");
        });
    }
    // selectedPoints 변수의 값을 반환하는 메서드
    public int getSelectedPoints() {
        return selectedPoints;
    }
	public void refresh() {
		
	}
}