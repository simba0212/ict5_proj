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
        centerPanel.add(nowPw);
        centerPanel.add(id_tf);

        // 변경할 비번
        JLabel changePw = new JLabel("변경할 비밀번호");
        changePw.setFont(new Font("굴림", Font.BOLD, 13));
        id_tf2 = new JPasswordField(15);
        centerPanel.add(changePw);
        centerPanel.add(id_tf2);

        // 변경할 비번 확인
        JLabel changePwCon = new JLabel("변경할 비밀번호 확인");
        changePwCon.setFont(new Font("굴림", Font.BOLD, 13));
        id_tf3 = new JPasswordField(15);
        centerPanel.add(changePwCon);
        centerPanel.add(id_tf3);

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

    }

  

    public boolean changePassword(String nowPw, String changePw, String changePwCon) {
        this.vo = main.vo; // 중요!
        Protocol p = new Protocol();
        System.out.println("changePassword 호출됨 nowPw :"+nowPw+"/ changePw :"+changePw+"/ changePwCon :"+changePwCon);
        System.out.println(vo.getMember_pw());
        // DB의 비밀번호와 입력한 현재 비밀번호를 비교, 새비밀번호와 새 비밀번호 확인이 일치하면 변경하고, 일치하지 않으면 변경하지 않습니다.
        if (nowPw.equals(vo.getMember_pw())) {
        	System.out.println("현재비번은 일치함");
            if (changePw.equals(changePwCon)) {
                try {
                	vo.setOld_pw(nowPw);
                    vo.setNew_pw(changePw);
                    System.out.println(vo.getMember_id());
                    
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