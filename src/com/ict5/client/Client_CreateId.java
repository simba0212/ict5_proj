package com.ict5.client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Client_CreateId extends JPanel {
	Client_main main;
	CardLayout cardlayout;
	// 수정중
	// 한번 더 수정

	public Client_CreateId(Client_main main) {
		this.main = main;
		this.cardlayout = main.cardlayout;
		setLayout(new BorderLayout());
		JLabel titleLabel = new JLabel("회원가입");
		titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 17f));
		titleLabel.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(titleLabel, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 30, 80, 30));
		panel.setLayout(new GridLayout(14, 1));
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

		// "약관동의" 글자와 "링크" 글자 추가
		JPanel agreementPanel = new JPanel(new BorderLayout());
		panel.add(agreementPanel, BorderLayout.NORTH);

		JLabel agreementLabel = new JLabel("약관동의 [링크]");
		// agreementLabel.setFont(agreementLabel.getFont().deriveFont(Font.BOLD, 17f));
		agreementPanel.add(agreementLabel, BorderLayout.WEST);

		// 체크박스와 "약관에 동의합니다" 글자 추가
		JPanel checkboxPanel = new JPanel(new BorderLayout());
		panel.add(checkboxPanel, BorderLayout.CENTER);
		

		JCheckBox agreementCheckbox = new JCheckBox();
		checkboxPanel.add(agreementCheckbox, BorderLayout.WEST);
		agreementCheckbox.setEnabled(false);

		JLabel agreeText = new JLabel("약관에 동의합니다");
		checkboxPanel.add(agreeText, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton backButton = new JButton("뒤로가기");
		backButton.setPreferredSize(new Dimension(100, 40));
		JButton nextButton = new JButton("다음");
		nextButton.setEnabled(false);
		nextButton.setPreferredSize(new Dimension(100, 40));
		buttonPanel.add(backButton);
		buttonPanel.add(nextButton);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 90, 30));
		add(panel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		// 백 버튼->로그인으로
		backButton.addActionListener(e -> {
			Client_Login login = new Client_Login(main);
			main.pg1.add("login", login);
			main.cardlayout.show(main.pg1, "login");
		});
		// 다음 버튼->가입완료로
		nextButton.addActionListener(e -> {
			CreateId_2 createId2 = new CreateId_2(main);
			main.pg1.add("createId2", createId2);
			main.cardlayout.show(main.pg1, "createId2");
		});
		// 약관동의[링크] 버튼
		agreementLabel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				showAgreementDialog();
			}
		});

	}

// Method to show agreement dialog
	private void showAgreementDialog() {
		String agreementText = "\r\n" + "서비스이용약관(자사 제품, 서비스 공급용)\r\n" + "\r\n"
				+ "회사가 제품의 사용 조건과 서비스 이용 절차 등에 관한 사항을 규정하고, 이용자에게 그 내용을 고지하고자 하는 경우에 사용하는 약관입니다\r\n" + "\r\n"
				+ "서비스이용약관(플랫폼-공급회원용)\r\n" + "\r\n" + "회사가 플랫폼의 사용 조건과 서비스 이용 절차 등에 관한 사항을 규정하고, "
				+ "제품 판매 회원사에게 그 내용을 고지하고자 하는 경우에 사용하는 약관입니다\r\n" + "\r\n" + "서비스이용약관(플랫폼-일반 및 공급기업용)\r\n" + "\r\n"
				+ "회사가 플랫폼의 사용 조건과 서비스 이용 절차 등에 관한 사항을 규정하고, " + "제품 판매자 및 서비스 이용자에게 그 내용을 고지하고자 할 때 사용하는 약관입니다.\r\n"
				+ "\r\n" + "서비스이용약관(플랫폼-일반회원용)\r\n" + "\r\n" + "회사가 플랫폼의 사용 조건과 서비스 이용 절차 등에 관한 사항을 규정하고,"
				+ " 자사 플랫폼을 이용하는 회원에게 그 내용을 고지하고자 할 때 사용하는 약관입니다.\r\n" + "\r\n" + "위치기반 서비스 이용약관\r\n" + "\r\n"
				+ "회사가 이용자의 개인위치정보를 사용하여 서비스를 제공하고자 하는 경우, 위치정보를 " + "수집 및 사용한다는 내용을 이용자에게 고지하고 동의를 받고자 할 때 작성하는 약관입니다";

	    JTextArea textArea = new JTextArea(agreementText);
	    textArea.setEditable(false);
	    textArea.setLineWrap(true);
	    textArea.setWrapStyleWord(true); // 단어 경계에서 줄 바꿈

	    JScrollPane scrollPane = new JScrollPane(textArea);
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scrollPane.setPreferredSize(new Dimension(400, 300));

	    JOptionPane.showMessageDialog(this, scrollPane, "약관", JOptionPane.PLAIN_MESSAGE);

	    // 계약을 보고나서 결제 버튼 사용가능
	    Component[] components = this.getComponents();
	    for (Component component : components) {
	        if (component instanceof JPanel) {
	            JPanel panel = (JPanel) component;
	            Component[] subComponents = panel.getComponents();
	            for (Component subComponent : subComponents) {
	                if (subComponent instanceof JButton) {
	                    JButton button = (JButton) subComponent;
	                    if (button.getText().equals("다음")) {
	                        button.setEnabled(true);
	                        break;
	                    }
	                }
	            }
	        }
	    }
	}
}