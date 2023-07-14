package com.ict5.client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import java.util.Date;
import java.text.SimpleDateFormat;


import com.ict5.db.Protocol;
import com.ict5.db.VO;

public class Client_CreateId extends JPanel {
	Socket s;
	ObjectOutputStream out;
	ObjectInputStream in;
	
	Client_main main;
	CardLayout cardlayout;

	JButton nextButton;
	JCheckBox agreementCheckbox;
	JTextField[] getJoinFields;
	
	VO vo;

	public Client_CreateId(Client_main main) {
		this.vo = main.vo; // vo 동기화
		this.main = main;
		this.cardlayout = main.cardlayout;
		this.s = main.s;
		this.out = main.out;
		this.in = main.in;
		
		setLayout(new BorderLayout());
		JPanel topPanel = new JPanel(new BorderLayout());
		JLabel titleLabel = new JLabel("회원가입");
		JLabel titleLabel2 = new JLabel("*모든 항목을 입력해주세요");
		titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 17f));
		titleLabel.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
		titleLabel2.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		topPanel.add(titleLabel, BorderLayout.NORTH);
		topPanel.add(titleLabel2, BorderLayout.CENTER); 
		add(topPanel,BorderLayout.NORTH);

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
		((AbstractDocument) textField5.getDocument()).setDocumentFilter(new DigitDocumentFilter(11));
		panel.add(label5);
		panel.add(textField5);

		JLabel label6 = new JLabel("생년월일:");
		JTextField textField6 = new JTextField();
		((AbstractDocument) textField6.getDocument()).setDocumentFilter(new DigitDocumentFilter(8));
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

		JLabel agreementLabel = new JLabel("<html>약관동의 <font color='blue'>[링크]</font></html>");
		agreementLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		agreementPanel.add(agreementLabel, BorderLayout.WEST);

		// 체크박스와 "약관에 동의합니다" 글자 추가
		JPanel checkboxPanel = new JPanel(new BorderLayout());
		panel.add(checkboxPanel, BorderLayout.CENTER);

		agreementCheckbox = new JCheckBox();
		checkboxPanel.add(agreementCheckbox, BorderLayout.WEST);
		agreementCheckbox.setEnabled(false);

		JLabel agreeText = new JLabel("약관에 동의합니다");
		checkboxPanel.add(agreeText, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton backButton = new JButton("뒤로가기");
		backButton.setPreferredSize(new Dimension(100, 40));
		nextButton = new JButton("다음");
		nextButton.setEnabled(false);
		nextButton.setPreferredSize(new Dimension(100, 40));
		buttonPanel.add(backButton);
		buttonPanel.add(nextButton);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 90, 30));
		add(panel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		//텍스트필드 모음
		getJoinFields = new JTextField[] { textField1, textField2, textField3, textField4, textField5, textField6,
				textField7, textField8 };
		
		//비밀번호 일치
		textField3.addFocusListener((FocusListener) new FocusAdapter() {
		    @Override
		    public void focusLost(FocusEvent e) {
		        String password = textField2.getText().trim();
		        String confirmPassword = textField3.getText().trim();
		        if (!password.equals(confirmPassword)) {
		            JOptionPane.showMessageDialog(Client_CreateId.this, "비밀번호가 일치하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

		// 생년월일 8자리
		textField6.addFocusListener(new FocusAdapter() {
		    @Override
		    public void focusLost(FocusEvent e) {
		        String birthday = textField6.getText().trim();
		        if (birthday.length() != 8) {
		            JOptionPane.showMessageDialog(Client_CreateId.this, "생년월일은 8자리로 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

		// 백 버튼->로그인으로
		backButton.addActionListener(e -> {
			resetFields();
			main.cardlayout.show(main.pg1, "login");
		});
		// 다음 버튼->가입완료로
		nextButton.addActionListener(e -> {
		    boolean allFieldsFilled = true;
		    for (JTextField textField : getJoinFields) {
		        if (textField.getText().trim().isEmpty()) {
		            allFieldsFilled = false;
		            break;
		        }
		    }
		    if (!allFieldsFilled) {
		        JOptionPane.showMessageDialog(this, "모든 항목을 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
		        return;
		    }

		    if (!maleRadioButton.isSelected() && !femaleRadioButton.isSelected()) {
		        JOptionPane.showMessageDialog(this, "성별을 선택해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
		        return;
		    }
		    // 비밀번호오류
		    String password = textField2.getText().trim();
		    String confirmPassword = textField3.getText().trim();
		    if (!password.equals(confirmPassword)) {
		        JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
		        return;
		    }

		    // 생년월일은 8자리
		    String birthday = textField6.getText().trim();
		    if (birthday.length() != 8) {
		        JOptionPane.showMessageDialog(this, "생년월일은 8자리로 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
		        return;
		    }
		    //서버로 전송
		    try {
				VO vo = new VO();
				Protocol p = new Protocol();
				vo.setMember_id(textField1.getText());
				vo.setMember_pw(textField2.getText());
				vo.setMember_name(textField4.getText());
				vo.setMember_phone(textField5.getText());
				vo.setMember_birth(textField6.getText());
				vo.setMember_addr(textField7.getText());
				vo.setMember_mail(textField8.getText());
							
			    //성별 선택
				String gender = null;
				if (maleRadioButton.isSelected()) {
				    gender = "남성";
				} else if (femaleRadioButton.isSelected()) {
				    gender = "여성";
				}
				vo.setMember_gen(gender);
				
				p.setVo(vo);
				p.setCmd(2101);
				main.out.writeObject(p);
				main.out.flush();
				main.cardlayout.show(main.pg1, "createId2");
				System.out.println("넘김완료");
			} catch (IOException e1) {
				e1.printStackTrace();
				System.out.println("넘김오류");
			}
		    resetFields();
		});
		
		// 약관동의[링크] 버튼
		agreementLabel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				showAgreementDialog();
			}
		});
	
	}

	// 약관기능
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

		JScrollBar scrollBar = scrollPane.getVerticalScrollBar();
		scrollBar.addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				// 스크롤바 다 내려가면 체크온 버튼활성화
				if (scrollBar.getValue() + scrollBar.getModel().getExtent() == scrollBar.getMaximum()) {
					nextButton.setEnabled(true);
					agreementCheckbox.setSelected(true);
				} else {
					nextButton.setEnabled(false);
					agreementCheckbox.setSelected(false);
				}
			}
		});

		JOptionPane.showMessageDialog(this, scrollPane, "약관", JOptionPane.PLAIN_MESSAGE);
	}
	public void resetFields() { //화면리셋
        agreementCheckbox.setSelected(false);
        for (JTextField textField : getJoinFields) {
            textField.setText("");
        }
    }
	
	
	//숫자만 입력하게
	private static class DigitDocumentFilter extends DocumentFilter {
        private final int maxLength;

        public DigitDocumentFilter(int maxLength) {
            this.maxLength = maxLength;
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (isDigitString(string) && fb.getDocument().getLength() + string.length() <= maxLength) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (isDigitString(text) && fb.getDocument().getLength() - length + text.length() <= maxLength) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        private boolean isDigitString(String string) {
            return string != null && string.matches("\\d*");
        }
	}
}