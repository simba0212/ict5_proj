package com.ict5.admin;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ClassCheck extends JPanel {
	Admin_main main;
	CardLayout cardLayout;

	public ClassCheck(Admin_main main) {
		this.main = main;
		this.cardLayout = main.cardlayout;

		setBorder(BorderFactory.createLineBorder(Color.black));

		setBackground(Color.white);
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(600, 600));
		JPanel north = new JPanel(new BorderLayout());
		north.setPreferredSize(new Dimension(0, 60));
		north.setBackground(Color.lightGray);
		JPanel top_R = new JPanel();
		top_R.setBackground(Color.lightGray);
		JLabel titleLabel = new JLabel("수업관리");
		Font labelFont = titleLabel.getFont().deriveFont(Font.BOLD, 20f);
		titleLabel.setFont(labelFont);
		north.add(titleLabel, BorderLayout.WEST);
		top_R.setLayout(new BorderLayout(0, 0));
		JLabel label = new JLabel("<html><h2>수업등록/수정</h2></html>");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		top_R.add(label, BorderLayout.WEST);
		JLabel label_1 = new JLabel("<html><h2>수업확인</h2></html>");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		top_R.add(label_1, BorderLayout.EAST);
		north.add(top_R, BorderLayout.EAST);
		north.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 30));

		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setPreferredSize(new Dimension(30, 30));
		lblNewLabel_8.setMaximumSize(new Dimension(20, 20));
		lblNewLabel_8.setMinimumSize(new Dimension(20, 20));
		top_R.add(lblNewLabel_8, BorderLayout.CENTER);

		JPanel jp_L = new JPanel(new GridLayout(7, 1));

		add(north, BorderLayout.NORTH);

		JPanel center = new JPanel(new GridLayout(10, 2)); // center 패널 생성

//		 JPanel center = new JPanel();
//		    center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

		JLabel label1 = new JLabel("수업종류");
		center.add(label1);

		JLabel label11 = new JLabel("요가");
		center.add(label11);

		JLabel label2 = new JLabel("수업장소");
		center.add(label2);

		JLabel label12 = new JLabel("303호");
		center.add(label12);

		JLabel label3 = new JLabel("강사");
		center.add(label3);

		JLabel label13 = new JLabel("요가 1");
		center.add(label13);

		JLabel label4 = new JLabel("정원");
		center.add(label4);

		JLabel label14 = new JLabel("7 / 10 명");
		center.add(label14);

		JLabel label5 = new JLabel("예약명단");
		center.add(label5);

		JLabel label15 = new JLabel("둘리, 고길동, 심바, 마이콜, 또치, 도우너, 가시고기");
		center.add(label15);

		JLabel label6 = new JLabel("대기명단");
		center.add(label6);

		JLabel label16 = new JLabel("대기인원1, 대기인원2");
		center.add(label16);

		JLabel label7 = new JLabel("강의시간");
		center.add(label7);

		JLabel label17 = new JLabel("10:00~10:50");
		center.add(label17);

		JLabel label8 = new JLabel("강의날짜");
		center.add(label8);

		JLabel label18 = new JLabel("2023-06-10");
		center.add(label18);

		JLabel label9 = new JLabel("강의료");
		center.add(label9);

		JLabel label19 = new JLabel("25000포인트");
		center.add(label19);

		JLabel label10 = new JLabel("강의번호");
		center.add(label10);

		JLabel label20 = new JLabel("16824");
		center.add(label20);

		add(center, BorderLayout.CENTER); // center 패널을 추가
		center.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 10));

		// Add '수업삭제' button
		JButton deleteButton = new JButton("수업삭제");
		JPanel bottomRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomRightPanel.add(deleteButton);
		add(bottomRightPanel, BorderLayout.SOUTH);

		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label.setForeground(Color.red);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				label.setForeground(Color.black);
			}
		});

		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.cardlayout.show(main.pg1, "classEdit"); // "member" 페이지로 이동
			}
		});

	}

}
