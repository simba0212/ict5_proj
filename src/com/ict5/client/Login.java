package com.ict5.client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Login extends JPanel {
    Huge_main main;
    CardLayout cardlayout;

    JPanel login_p;
    JButton login_btn, join_btn;
    JLabel img;
    JTextField id_tf,pw_tf;

    public Login(Huge_main main) {
    	setLayout(new BorderLayout());
        this.main = main;
        this.cardlayout = main.cardlayout;


        JPanel jp1 = new JPanel(new GridLayout(0, 1));
        JPanel jp1_1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel jp1_2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel jp4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        ImageIcon imageIcon = new ImageIcon("src/images/gym.png"); // 이미지 경로 수정
		JLabel imageLabel = new JLabel(imageIcon);
		imageLabel.setPreferredSize(new Dimension(300, 400));

        jp1_1.add(new JLabel("아이디 : "));

        id_tf = new JTextField(20);
        String id = "        ID를 입력해주세요.       ";
        id_tf = new JTextField(id);
        id_tf.setEditable(false); // 편집 불가능하도록 설정

        jp1_1.add(id_tf);

        jp1_2.add(new JLabel("비밀번호 : "));
        String pw = "Password를 입력해주세요.";
        pw_tf = new JTextField(pw);
        pw_tf.setEditable(false);

        jp1_2.add(pw_tf);

        JPanel jp2 = new JPanel(new GridLayout(0, 1));
        JPanel jp2_1 =new JPanel();
        JPanel jp2_2 =new JPanel();

        login_btn = new JButton("   로그인   ");
        login_btn.setPreferredSize(new Dimension(120, 40));
        jp2_1.add(login_btn);

        join_btn = new JButton(" 회원가입 ");
        join_btn.setPreferredSize(new Dimension(120, 40));
        jp2_2.add(join_btn);
        

        JPanel jp3 = new JPanel();
        jp3.setPreferredSize(new Dimension(300, 80));

        jp1.add(jp1_1);
        jp1.add(jp1_2);
        jp2.add(jp2_1);
        jp2.add(jp2_2);
        jp2.add(jp3);

        add(imageLabel, BorderLayout.NORTH);
        add(jp1, BorderLayout.CENTER);
        add(jp2, BorderLayout.SOUTH);

        // 마우스 클릭 이벤트 처리
        id_tf.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	id_tf.setText("");
                id_tf.setEditable(true); // 편집 가능하도록 설정
                id_tf.requestFocus(); // 커서 포커스 설정
            }
        });
        
        // 마우스 클릭 이벤트 처리
        pw_tf.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	pw_tf.setText("");
            	pw_tf.setEditable(true); // 편집 가능하도록 설정
            	pw_tf.requestFocus(); // 커서 포커스 설정
            }
        });
    }
}
