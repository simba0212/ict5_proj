package com.ict5.client.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;

import com.ict5.client.Client_main;
import com.ict5.db.Protocol;
import com.ict5.db.VO;

public class PassChange extends JPanel {
    Client_main main;
    CardLayout cardlayout;
    JPasswordField id_tf;
    JPasswordField id_tf2;
    JPasswordField id_tf3;

    VO vo;
    JLabel usernameLabel;

    public PassChange(Client_main main) {
        setLayout(null);
        this.main = main;
        this.cardlayout = main.cardlayout;
        this.vo = main.vo;
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
        id_tf = new JPasswordField(15);
        //JLabel condition1 = new JLabel("6글자 이상");
        //JLabel condition2 = new JLabel("특수문자");
        centerPanel.add(nowPw);
        centerPanel.add(id_tf);
        //centerPanel.add(condition1);
        //centerPanel.add(condition2);

        // 변경할 비번
        JLabel changePw = new JLabel("변경할 비밀번호");
        changePw.setFont(new Font("굴림", Font.BOLD, 13));
        id_tf2 = new JPasswordField(15);
       // JLabel condition3 = new JLabel("6글자 이상");
        //JLabel condition4 = new JLabel("특수문자");
        centerPanel.add(changePw);
        centerPanel.add(id_tf2);
       // centerPanel.add(condition3);
       // centerPanel.add(condition4);

        // 변경할 비번 확인
        JLabel changePwCon = new JLabel("변경할 비밀번호 확인");
        changePwCon.setFont(new Font("굴림", Font.BOLD, 13));
        id_tf3 = new JPasswordField(15);
        //JLabel condition5 = new JLabel("6글자 이상");
        //JLabel condition6 = new JLabel("특수문자");
        centerPanel.add(changePwCon);
        centerPanel.add(id_tf3);
       // centerPanel.add(condition5);
        //centerPanel.add(condition6);

        add(centerPanel);

        // 하단 확인 버튼
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(0, 650, 500, 50);
        JButton confirmButton = new JButton("확인");
        buttonPanel.add(confirmButton);
        add(buttonPanel);

        confirmButton.addActionListener(e -> {
            // 사용자가 입력한 이전 비밀번호와 새 비밀번호 가져오기
            String nowPwText = new String(id_tf.getPassword());
            String changePwText = new String(id_tf2.getPassword());
            String changePwConText = new String(id_tf3.getPassword());

            // 비밀번호 변경 함수 호출
            boolean passwordChanged = changePassword(nowPwText, changePwText, changePwConText);
            // 비밀번호 변경 결과 대화상자로 출력
            if (passwordChanged) {
                JOptionPane.showMessageDialog(this, "비밀번호가 성공적으로 변경되었습니다.");
                resetFields();
            } else {
                JOptionPane.showMessageDialog(this, "비밀번호 변경에 실패했습니다. 이전 비밀번호가 올바르지 않거나 새 비밀번호와 확인이 일치하지 않습니다.");
                resetFields();
            }
        });

/*        // OX 처리관련
        id_tf.addFocusListener(new CustomFocusListener(condition1, condition2));
        id_tf2.addFocusListener(new CustomFocusListener(condition3, condition4));
        id_tf3.addFocusListener(new CustomFocusListener(condition5, condition6));*/
    }

    /* // Custom focus listener class
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
        }

        @Override
        public void focusLost(FocusEvent e) {
            JPasswordField passwordField = (JPasswordField) e.getSource();
            String text = new String(passwordField.getPassword());

            // Check conditions and update the labels accordingly
            if (text.length() >= 6) {
                conditionLabel1.setText("6글자 이상 O");
                conditionLabel1.setForeground(Color.GREEN);
            } else {
                conditionLabel1.setText("6글자 이상 X");
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
    }*/

    public boolean changePassword(String nowPw, String changePw, String changePwCon) {
        this.vo = main.vo; // 중요!
        Protocol p = new Protocol();
        // DB의 비밀번호와 입력한 현재 비밀번호를 비교, 새비밀번호와 새 비밀번호 확인이 일치하면 변경하고, 일치하지 않으면 변경하지 않습니다.
        if (nowPw.equals(vo.getMember_pw())) {
            if (changePw.equals(changePwCon)) {
                try {
                	vo.setOld_pw(nowPw);
                    vo.setNew_pw(changePw);
                    vo.getMember_id();
                    p.setVo(vo);
                    p.setCmd(2103); // 변경 필요
                    main.out.writeObject(p);
                    main.out.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return true; // 비밀번호 변경 성공
            }
        }
        return false; // 비밀번호 변경 실패
    }

    public void resetFields() { //화면리셋
    	id_tf.setText("");
    	id_tf2.setText("");
    	id_tf3.setText("");
    }
    public void refresh() {
    }
}