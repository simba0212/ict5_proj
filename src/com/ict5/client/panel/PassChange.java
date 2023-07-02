package com.ict5.client.panel;
import javax.swing.*;

import com.ict5.client.Client_main;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PassChange extends JPanel {
	Client_main main;
    CardLayout cardlayout;

    public PassChange(Client_main main) {
        setLayout(null); // Use AbsoluteLayout
        this.main = main;
        this.cardlayout = main.cardlayout;

        // 상단 제목 라벨
        JLabel titleLabel = new JLabel("비밀번호 변경");
        titleLabel.setFont(new Font("굴림", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(0, 100, 500, 50);
        add(titleLabel);

        JPanel centerPanel = new JPanel();
        centerPanel.setBounds(0, 270, 500, 300);
        GridLayout gridLayout = new GridLayout(0, 1);
        gridLayout.setHgap(10);
        centerPanel.setLayout(gridLayout);

        // 현재 비번
        JLabel nowPw = new JLabel("현재 비밀번호");
        nowPw.setFont(new Font("굴림", Font.BOLD, 13));
        JTextField id_tf = new JTextField(15);
        JLabel condition1 = new JLabel("6글자 이상");
        JLabel condition2 = new JLabel("특수문자");
        centerPanel.add(nowPw);
        centerPanel.add(id_tf);
        centerPanel.add(condition1);
        centerPanel.add(condition2);

        // 변경할 비번
        JLabel changePw = new JLabel("변경할 비밀번호");
        changePw.setFont(new Font("굴림", Font.BOLD, 13));
        JTextField id_tf2 = new JTextField(15);
        JLabel condition3 = new JLabel("6글자 이상");
        JLabel condition4 = new JLabel("특수문자");
        centerPanel.add(changePw);
        centerPanel.add(id_tf2);
        centerPanel.add(condition3);
        centerPanel.add(condition4);

        // 변경할 비번 확인
        JLabel changePwCon = new JLabel("변경할 비밀번호 확인");
        changePwCon.setFont(new Font("굴림", Font.BOLD, 13));
        JTextField id_tf3 = new JTextField(15);
        JLabel condition5 = new JLabel("6글자 이상");
        JLabel condition6 = new JLabel("특수문자");
        centerPanel.add(changePwCon);
        centerPanel.add(id_tf3);
        centerPanel.add(condition5);
        centerPanel.add(condition6);

        add(centerPanel);

        // 하단 확인 버튼
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(0, 650, 500, 50);
        JButton confirmButton = new JButton("확인");
        buttonPanel.add(confirmButton);
        add(buttonPanel);

        // Add focus listeners to the text fields
        id_tf.addFocusListener(new CustomFocusListener(condition1, condition2));
        id_tf2.addFocusListener(new CustomFocusListener(condition3, condition4));
        id_tf3.addFocusListener(new CustomFocusListener(condition5, condition6));
    }
    // Custom focus listener class
    private class CustomFocusListener implements FocusListener {
        private JLabel conditionLabel1;
        private JLabel conditionLabel2;
        private Color defaultColor;

        public CustomFocusListener(JLabel conditionLabel1, JLabel conditionLabel2) {
            this.conditionLabel1 = conditionLabel1;
            this.conditionLabel2 = conditionLabel2;
            this.defaultColor = conditionLabel1.getForeground();
        }

        @Override
        public void focusGained(FocusEvent e) {
            // Do nothing when the text field gains focus
        }

        @Override
        public void focusLost(FocusEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();

            // Check conditions and update the labels accordingly
            if (text.length() >= 6) {
                conditionLabel1.setText("6글자이상 O");
                conditionLabel1.setForeground(Color.GREEN);
            } else {
                conditionLabel1.setText("6글자이상 X");
                conditionLabel1.setForeground(Color.RED);
            }

            if (text.matches(".*[!@#$%^&*()].*")) {
                conditionLabel2.setText("특수문자 O");
                conditionLabel2.setForeground(Color.GREEN);
            } else {
                conditionLabel2.setText("특수문자 X");
                conditionLabel2.setForeground(Color.RED);
            }
        }
    }
}
