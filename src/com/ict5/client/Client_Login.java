package com.ict5.client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ict5.db.Protocol;
import com.ict5.db.VO;

public class Client_Login extends JPanel {
	Socket s;
	ObjectOutputStream out;
	ObjectInputStream in;

	Client_main main;
	CardLayout cardlayout;

	JPanel login_p;
	JButton login_btn, join_btn;
	JLabel img;
	JTextField id_tf, pw_tf;

	public Client_Login(Client_main main) {
		setLayout(new BorderLayout());
		this.main = main;
		this.cardlayout = main.cardlayout;
		this.s = main.s;
		this.out = main.out;
		this.in = main.in;

		JPanel jp1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jp1.setPreferredSize(new Dimension(500, 300));
		jp1.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 130));
		JPanel jp1_1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel jp1_2 = new JPanel(new FlowLayout(FlowLayout.CENTER));

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
		JPanel jp2_1 = new JPanel();
		JPanel jp2_2 = new JPanel();

		login_btn = new JButton("   로그인   ");
		login_btn.setPreferredSize(new Dimension(120, 40));
		jp2_1.add(login_btn);

		join_btn = new JButton(" 회원가입 ");
		join_btn.setPreferredSize(new Dimension(120, 40));
		jp2_2.add(join_btn);

		JPanel jp3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jp3.setPreferredSize(new Dimension(200, 70));

		jp1.add(jp1_1);
		jp1.add(jp1_2);
		jp2.add(jp2_1);
		jp2.add(jp2_2);
		jp2.add(jp3);

		add(imageLabel, BorderLayout.NORTH);
		add(jp1, BorderLayout.EAST);
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
		// 로그인 버튼->홈으로
		login_btn.addActionListener(e -> {
			try {
				VO vo = new VO();
				Protocol p = new Protocol();
				vo.setMember_id(id_tf.getText());
				vo.setMember_pw(pw_tf.getText());
				p.setCmd(1);
				p.setVo(vo);
				main.out.writeObject(p);
				main.out.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		// 가입 버튼->가입으로
		join_btn.addActionListener(e -> {
			main.cardlayout.show(main.pg1, "createId");
		});
	}

}
