package com.ict5.client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CreateAccount extends JPanel {
	Huge_main main;
	CardLayout cardlayout;
	// 수정중
	// 한번 더 수정
	
	public CreateAccount(Huge_main main) {
		this.main = main;
		this.cardlayout = main.cardlayout;
		setLayout(new BorderLayout());
		JLabel titleLabel = new JLabel("회원가입");
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(titleLabel, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(14, 1));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.add(new JLabel());
		panel.add(new JLabel());
		panel.add(new JLabel());
		panel.add(new JLabel());
		panel.add(new JLabel());
		panel.add(new JLabel());

		JLabel label1 = new JLabel("아이디:");
		JTextField textField1 = new JTextField();
		panel.add(label1);
		panel.add(textField1);

		JLabel label2 = new JLabel("비밀번호:");
		JTextField textField2 = new JTextField();
		panel.add(label2);
		panel.add(textField2);

		JLabel label3 = new JLabel("비밀번호 확인:");
		JTextField textField3 = new JTextField();
		panel.add(label3);
		panel.add(textField3);

		JLabel label4 = new JLabel("이름:");
		JTextField textField4 = new JTextField();
		panel.add(label4);
		panel.add(textField4);

		JLabel label5 = new JLabel("연락처:");
		JTextField textField5 = new JTextField();
		panel.add(label5);
		panel.add(textField5);

		JLabel label6 = new JLabel("생년월일:");
		JTextField textField6 = new JTextField();
		panel.add(label6);
		panel.add(textField6);

		JLabel label7 = new JLabel("주소:");
		JTextField textField7 = new JTextField();
		panel.add(label7);
		panel.add(textField7);

		JLabel label8 = new JLabel("이메일:");
		JTextField textField8 = new JTextField();
		panel.add(label8);
		panel.add(textField8);

		JLabel label9 = new JLabel("성별:");
		panel.add(label9);

		ButtonGroup genderGroup = new ButtonGroup();

		JRadioButton maleRadioButton = new JRadioButton("남성");
		JRadioButton femaleRadioButton = new JRadioButton("여성");

		genderGroup.add(maleRadioButton);
		genderGroup.add(femaleRadioButton);

		JPanel genderPanel = new JPanel();
		genderPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		genderPanel.add(maleRadioButton);
		genderPanel.add(femaleRadioButton);

		panel.add(genderPanel);

		JLabel label10 = new JLabel("약관동의");
		panel.add(label10);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton backButton = new JButton("뒤로가기");
		backButton.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		backButton.setPreferredSize(new Dimension(100, 30));
		JButton nextButton = new JButton("다음");
		nextButton.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		nextButton.setPreferredSize(new Dimension(100, 30));
		buttonPanel.add(backButton);
		buttonPanel.add(nextButton);
		add(panel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		
	}
}