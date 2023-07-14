package com.ict5.admin.panel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.ict5.admin.Admin_CoMgmt1;
import com.ict5.admin.Admin_main;
import com.ict5.db.Protocol;
import com.ict5.db.VO;

public class CoMgmt4 extends JPanel {
	CardLayout cardlayout;
	JPanel pg1,imagePanel;

	Admin_main main;
	String filePath;
	JTextField[] getTeacherFields;
	CoTable1 coTable1;
	Admin_CoMgmt1 coMg1;
	JTextField nameField, phoneField, addressField;
	ButtonGroup genderGroup, courseGroup;
	JTextArea experienceTextArea;
	ImageIcon imageIcon;
	JRadioButton maleRadioButton, femaleRadioButton, radioButton1, radioButton2, radioButton3, radioButton4;
	String imagePath;
	Image image;
	JLabel imageLabel;
	JButton addButton,editButton;

	VO vo;
	public CoMgmt4(Admin_main main) {
		this.main = main;

		setLayout(new BorderLayout());

		// 상단 패널 생성
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.setBackground(Color.lightGray); // 배경색을 회색으로 설정

		// "강사 관리" 패널을 생성하여 왼쪽에 추가
		JPanel teacherManagementPanel = new JPanel();
		teacherManagementPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel("강사 관리");
		teacherManagementPanel.add(label);
		label.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 10));
		label.setFont(label.getFont().deriveFont(Font.BOLD, 17f));
		teacherManagementPanel.setBackground(Color.lightGray); // 배경색을 회색으로 설정
		teacherManagementPanel.setPreferredSize(new Dimension(200, 30)); // 원하는 크기로 지정
		topPanel.add(teacherManagementPanel, BorderLayout.WEST);

		// "강사 등록" 라벨과 "강사 목록" 라벨을 포함하는 패널을 생성하여 오른쪽에 추가
		JPanel labelsPanel = new JPanel();
		labelsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel registerLabel = new JLabel(" ");
		registerLabel.setFont(registerLabel.getFont().deriveFont(Font.BOLD, 17f));
		labelsPanel.add(registerLabel);
		JLabel listLabel = new JLabel(" ");
		listLabel.setFont(listLabel.getFont().deriveFont(Font.BOLD, 17f));
		labelsPanel.add(listLabel);
		listLabel.setBorder(BorderFactory.createEmptyBorder(15, 10, 20, 20));
		labelsPanel.setBackground(Color.lightGray); // 배경색을 회색으로 설정
		labelsPanel.setPreferredSize(new Dimension(200, 60)); // 원하는 크기로 지정
		topPanel.add(labelsPanel, BorderLayout.EAST);

		// 상단 패널을 BorderLayout의 NORTH 위치에 배치
		add(topPanel, BorderLayout.NORTH);

		// 중앙 패널 생성
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); // BoxLayout으로 변경
		centerPanel.setAlignmentX(Component.LEFT_ALIGNMENT); // 왼쪽 정렬 설정
		centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 80));

		// "수업 종류" 라디오 버튼 그룹을 포함한 패널 생성
		JPanel coursePanel = new JPanel();
		coursePanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // 왼쪽 정렬로 변경
		JLabel courseLabel = new JLabel("수업 종류 ");
		coursePanel.add(courseLabel);

		// 라디오 버튼 그룹 생성
		courseGroup = new ButtonGroup();
		radioButton1 = new JRadioButton("헬스");
		radioButton2 = new JRadioButton("요가");
		radioButton3 = new JRadioButton("수영");
		radioButton4 = new JRadioButton("필라테스");
		courseGroup.add(radioButton1);
		courseGroup.add(radioButton2);
		courseGroup.add(radioButton3);
		courseGroup.add(radioButton4);
		coursePanel.add(radioButton1);
		coursePanel.add(radioButton2);
		coursePanel.add(radioButton3);
		coursePanel.add(radioButton4);

		centerPanel.add(coursePanel);

		// "이름" 텍스트 필드를 포함한 패널 생성
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // 가운데 정렬로 변경
		JLabel nameLabel = new JLabel("이름: ");
		nameField = new JTextField(20);
		namePanel.add(nameLabel);
		namePanel.add(nameField);

		centerPanel.add(namePanel);

		// "전화번호" 텍스트 필드를 포함한 패널 생성
		JPanel phonePanel = new JPanel();
		phonePanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // 가운데 정렬로 변경
		JLabel phoneLabel = new JLabel("전화번호: ");
		phoneField = new JTextField(20);
		phonePanel.add(phoneLabel);
		phonePanel.add(phoneField);

		centerPanel.add(phonePanel);

		// "주소" 텍스트 필드를 포함한 패널 생성
		JPanel addressPanel = new JPanel();
		addressPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // 가운데 정렬로 변경
		JLabel addressLabel = new JLabel("주소: ");
		addressField = new JTextField(20);
		addressPanel.add(addressLabel);
		addressPanel.add(addressField);

		centerPanel.add(addressPanel);

		// "성별" 라디오 버튼을 포함한 패널 생성
		JPanel genderPanel = new JPanel();
		genderPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // 가운데 정렬로 변경
		JLabel genderLabel = new JLabel("성별: ");
		maleRadioButton = new JRadioButton("남");
		femaleRadioButton = new JRadioButton("여");
		genderGroup = new ButtonGroup();
		genderGroup.add(maleRadioButton);
		genderGroup.add(femaleRadioButton);
		genderPanel.add(genderLabel);
		genderPanel.add(maleRadioButton);
		genderPanel.add(femaleRadioButton);

		centerPanel.add(genderPanel);

		// 중앙 패널을 BorderLayout의 CENTER 위치에 배치
		add(centerPanel, BorderLayout.CENTER);

		// 우측 패널 생성
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());

		// "경력사항"을 표시하는 레이블과 텍스트 영역을 포함한 패널 생성
		JPanel experiencePanel = new JPanel();
		experiencePanel.setLayout(new BorderLayout());
		experiencePanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 80, 50));

		// "경력사항" 레이블 생성
		JLabel experienceLabel = new JLabel("경력사항");
		experienceLabel.setFont(experienceLabel.getFont().deriveFont(Font.BOLD, 17f));

		// "경력사항" 텍스트 영역 생성
		experienceTextArea = new JTextArea(5, 20);
		experienceTextArea.setLineWrap(true);

		// 텍스트 영역을 포함한 스크롤 패널 생성
		JScrollPane scrollPane = new JScrollPane(experienceTextArea);

		// "경력사항" 레이블과 스크롤 패널을 패널에 추가
		experiencePanel.add(experienceLabel, BorderLayout.NORTH);
		experiencePanel.add(scrollPane, BorderLayout.CENTER);

		// 오른쪽 패널에 "경력사항" 패널 추가
		rightPanel.add(experiencePanel);

		// 우측 패널을 BorderLayout의 EAST 위치에 배치
		add(rightPanel, BorderLayout.EAST);

		// 이미지 경로
		imagePath = "src/images/photo.jpg";

		// 이미지 레이블 생성
		imageLabel = new JLabel();
		imageIcon = new ImageIcon(imagePath);
		image = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT); // 원하는 크기로 조정
		imageLabel.setIcon(new ImageIcon(image));
		
		// 이미지 패널 생성
		imagePanel = new JPanel();
		imagePanel.setLayout(new BorderLayout());
		
		imagePanel.add(imageLabel, BorderLayout.CENTER);
		add(imagePanel, BorderLayout.WEST);
		imagePanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 10));

		// "사진 첨부" 버튼 생성
		JButton attachButton = new JButton("사진 첨부");
		attachButton.setFont(attachButton.getFont().deriveFont(17f));
		attachButton.setPreferredSize(new Dimension(120, 20));

		// 버튼을 이미지 패널의 오른쪽 아래에 추가
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.add(attachButton);
		imagePanel.add(buttonPanel, BorderLayout.SOUTH);

		// 이미지 레이블을 이미지 패널의 가운데에 추가
		imagePanel.add(imageLabel, BorderLayout.CENTER);
		add(imagePanel, BorderLayout.WEST);

		// 하단 패널 생성
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // 가운데 정렬로 변경

		// "등록" 버튼 생성
		addButton = new JButton("등록");
		addButton.setFont(addButton.getFont().deriveFont(17f));

		// "수정" 버튼 생성
		editButton = new JButton("수정");
		editButton.setFont(editButton.getFont().deriveFont(17f));
		editButton.setEnabled(false);

		// 버튼들을 하단 패널에 추가

		bottomPanel.add(addButton);
		bottomPanel.add(editButton);
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 40, 10));

		vo = new VO();
		// 하단 패널을 BorderLayout의 SOUTH 위치에 배치
		add(bottomPanel, BorderLayout.SOUTH);

		// 텍스트필드 모음
		getTeacherFields = new JTextField[] { nameField, phoneField, addressField };

		
		// 이미지 첨부하는 버튼
		attachButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser fileChooser = new JFileChooser();
					int option = fileChooser.showOpenDialog(CoMgmt4.this);
					if (option == JFileChooser.APPROVE_OPTION) {
						File selectedFile = fileChooser.getSelectedFile();
						filePath = selectedFile.getAbsolutePath();
               	
               
						// 이미지를 삽입하는 로직
						imageIcon = new ImageIcon(filePath);
						image = imageIcon.getImage();
						Image scaledImage = image.getScaledInstance(200, 300, Image.SCALE_SMOOTH); // 원하는 크기로 조정
						ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
						imageLabel.setIcon(scaledImageIcon);

						if (filePath != null) {
							vo.setTeacher_img(filePath);
						}
					}
				} catch (Exception e2) {
				}
			}
		});

		// 등록하는 버튼
		addButton.addActionListener(e -> {
	
			try {
				boolean allTeacherFilled = true;
				for (JTextField textField : getTeacherFields) {
					if (textField.getText().trim().isEmpty()) {
						allTeacherFilled = false;
						break;
					}
				}
				if (!allTeacherFilled) {
					JOptionPane.showMessageDialog(this, "모든 항목을 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (!radioButton1.isSelected() && !radioButton2.isSelected() && !radioButton3.isSelected()
						&& !radioButton4.isSelected()) {
					JOptionPane.showMessageDialog(this, "수업종류를 선택해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (!maleRadioButton.isSelected() && !femaleRadioButton.isSelected()) {
					JOptionPane.showMessageDialog(this, "성별을 선택해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (filePath == null) {
					JOptionPane.showMessageDialog(this, "사진을 등록해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (experienceTextArea.getText().isEmpty()) {
					JOptionPane.showMessageDialog(this, "경력사항을 등록해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
	
	
				String gender = "";
			
				Protocol p = new Protocol();
				vo.setTeacher_name(nameField.getText().trim());// 이름
				vo.setTeacher_phone(phoneField.getText().trim());// 전화번호
				vo.setTeacher_addr(addressField.getText().trim());// 주소
				if (maleRadioButton.isSelected()) {
					gender = maleRadioButton.getText();
				} else if (femaleRadioButton.isSelected()) {
					gender = femaleRadioButton.getText();
				}
				vo.setTeacher_gen(gender);// 성별
				vo.setTeacher_career(experienceTextArea.getText()); // 경력사항

				// 수업종류 숫자로 나타냄
				String type = "1";
				if (radioButton1.isSelected()) {
					type = "1";
				} else if (radioButton2.isSelected()) {
					type = "2";
				} else if (radioButton3.isSelected()) {
					type = "3";
				} else if (radioButton4.isSelected()) {
					type = "4";
				}
				vo.setTeacher_type(type);

				p.setVo(vo);
				p.setCmd(1318);
				main.out.writeObject(p);
				main.out.flush();
				
				JOptionPane.showMessageDialog(null, "등록 되었습니다", "등록", JOptionPane.PLAIN_MESSAGE);
				
				addButton.setEnabled(true);
				main.cardlayout.show(main.pg1, "coMg1");
			} catch (Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(null, "등록 실패!!", "등록 실패", JOptionPane.ERROR_MESSAGE);
			}
		});

		// 수정 update 버튼
		editButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vo = main.vo;

				boolean allTeacherFilled = true;
				for (JTextField textField : getTeacherFields) {
					if (textField.getText().trim().isEmpty()) {
						allTeacherFilled = false;
						break;
					}
				}
				if (!allTeacherFilled) {
					JOptionPane.showMessageDialog(null, "모든 항목을 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (!radioButton1.isSelected() && !radioButton2.isSelected() && !radioButton3.isSelected()
						&& !radioButton4.isSelected()) {
					JOptionPane.showMessageDialog(null, "수업종류를 선택해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (!maleRadioButton.isSelected() && !femaleRadioButton.isSelected()) {
					JOptionPane.showMessageDialog(null, "성별을 선택해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (filePath == null) {
					JOptionPane.showMessageDialog(null, "사진을 등록해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (experienceTextArea.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "경력사항을 등록해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
			
				int result = JOptionPane.showConfirmDialog(null, "수정 되었습니다", "수정", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {
					// 확인 버튼을 눌렀을 때 처리할 동작 추가
					String gender = "";
					try {
						Protocol p = new Protocol();
						vo.setTeacher_num(vo.getTeacher_num());
						vo.setTeacher_name(nameField.getText().trim());// 이름
						vo.setTeacher_phone(phoneField.getText().trim());// 전화번호
						vo.setTeacher_addr(addressField.getText().trim());// 주소
						if (maleRadioButton.isSelected()) {
							gender = maleRadioButton.getText();
						} else if (femaleRadioButton.isSelected()) {
							gender = femaleRadioButton.getText();
						}
						vo.setTeacher_gen(gender);// 성별
						vo.setTeacher_career(experienceTextArea.getText()); // 경력사항
						filePath = vo.getTeacher_img();
						// 수업종류 숫자로 나타냄
						String type = "1";
						if (radioButton1.isSelected()) {
							type = "1";
						} else if (radioButton2.isSelected()) {
							type = "2";
						} else if (radioButton3.isSelected()) {
							type = "3";
						} else if (radioButton4.isSelected()) {
							type = "4";
						}
						vo.setTeacher_type(type);
								
								

						
	
						
						p.setVo(vo);
						p.setCmd(1317);
						main.out.writeObject(p);
						main.out.flush();
						main.cardlayout.show(main.pg1, "coMg1"); 				
					} catch (Exception e2) {
					}

				} else if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
					vo.setTeacher_name(nameField.getText().trim());// 이름
					vo.setTeacher_phone(phoneField.getText().trim());// 전화번호
					vo.setTeacher_addr(addressField.getText().trim());// 주소
					vo.setTeacher_career(experienceTextArea.getText()); // 경력사항
				}
			}

		});

	}
	

	// 수정할 강사 내용 불러오기
	public void fix() {
		try {
			vo = main.vo;

			filePath = vo.getTeacher_img();
			String imagepath = vo.getTeacher_img();
			ImageIcon imageIcon = new ImageIcon(imagepath);
			Image image = imageIcon.getImage();
			Image scaledImage = image.getScaledInstance(200, 300, Image.SCALE_SMOOTH); // 원하는 크기로 조정
			ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
			imageLabel.setIcon(scaledImageIcon);

			nameField.setText(vo.getTeacher_name());
			phoneField.setText(vo.getTeacher_phone());
			addressField.setText(vo.getTeacher_addr());
			experienceTextArea.setText(vo.getTeacher_career());
		
			String gen = vo.getTeacher_gen();
			String type = vo.getTeacher_type();
			if (gen.equals("남")) {
				maleRadioButton.setSelected(true);
			} else if (gen.equals("여")) {
				femaleRadioButton.setSelected(true);
			}

			switch (type) {
			case "1":
				radioButton1.setSelected(true);
				break;
			case "2":
				radioButton2.setSelected(true);
				break;
			case "3":
				radioButton3.setSelected(true);
				break;
			case "4":
				radioButton4.setSelected(true);
				break;
			}

			addButton.setEnabled(false);
			editButton.setEnabled(true);
			main.cardlayout.show(main.pg1, "coMg3");

		} catch (Exception e) {
		}

	}
	
	
	// 강사 등록화면 리프레쉬 및 화면 이
	public void refresh() {
		try {
			vo = new VO();
			
			filePath = "";
			ImageIcon ii = new ImageIcon("src/images/photo.jpg");
			JLabel label = new JLabel(ii);
			imagePanel.add(label);
	    
			nameField.setText("");
			phoneField.setText("");
			addressField.setText("");
			experienceTextArea.setText("");
			
			genderGroup.clearSelection();
			courseGroup.clearSelection();
				
			addButton.setEnabled(true);
			editButton.setEnabled(false);

			main.cardlayout.show(main.pg1, "coMg3");

		} catch (Exception e) {
		}
		
	}

	
}
