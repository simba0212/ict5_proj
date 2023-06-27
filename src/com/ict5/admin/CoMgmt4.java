package com.ict5.admin;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CoMgmt4 extends JPanel {
	CardLayout cardlayout;
	JPanel pg1;
	
	Admin_main main;
	
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
		label.setFont(label.getFont().deriveFont(24f)); // 폰트 크기를 24로 설정
		teacherManagementPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 10));
		teacherManagementPanel.setBackground(Color.lightGray); // 배경색을 회색으로 설정
		topPanel.add(teacherManagementPanel, BorderLayout.WEST);

		// "강사 등록" 버튼과 "강사 목록" 버튼을 포함하는 패널을 생성하여 오른쪽에 추가
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton registerButton = new JButton("강사 등록");
		registerButton.setFont(registerButton.getFont().deriveFont(18f)); // 폰트 크기를 24로 설정
		buttonsPanel.add(registerButton);
		JButton listButton = new JButton("강사 목록");
		listButton.setFont(listButton.getFont().deriveFont(18f)); // 폰트 크기를 24로 설정
		buttonsPanel.add(listButton);
		buttonsPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 20));
		buttonsPanel.setBackground(Color.lightGray); // 배경색을 회색으로 설정
		topPanel.add(buttonsPanel, BorderLayout.EAST);

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
		courseLabel.setFont(courseLabel.getFont().deriveFont(18f)); // 폰트 크기를 18로 설정
		coursePanel.add(courseLabel);

		// 라디오 버튼 그룹 생성
		ButtonGroup courseGroup = new ButtonGroup();
		JRadioButton radioButton1 = new JRadioButton("헬스");
		radioButton1.setFont(radioButton1.getFont().deriveFont(18f)); // 폰트 크기를 18로 설정
		JRadioButton radioButton2 = new JRadioButton("요가");
		radioButton2.setFont(radioButton2.getFont().deriveFont(18f)); // 폰트 크기를 18로 설정
		JRadioButton radioButton3 = new JRadioButton("수영");
		radioButton3.setFont(radioButton3.getFont().deriveFont(18f)); // 폰트 크기를 18로 설정
		JRadioButton radioButton4 = new JRadioButton("필라테스");
		radioButton4.setFont(radioButton4.getFont().deriveFont(18f)); // 폰트 크기를 18로 설정
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
		JTextField nameField = new JTextField(20);
		nameLabel.setFont(nameLabel.getFont().deriveFont(18f)); // 폰트 크기를 18로 설정
		namePanel.add(nameLabel);
		namePanel.add(nameField);

		centerPanel.add(namePanel);

		// "전화번호" 텍스트 필드를 포함한 패널 생성
		JPanel phonePanel = new JPanel();
		phonePanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // 가운데 정렬로 변경
		JLabel phoneLabel = new JLabel("전화번호: ");
		JTextField phoneField = new JTextField(20);
		phoneLabel.setFont(phoneLabel.getFont().deriveFont(18f)); // 폰트 크기를 18로 설정
		phonePanel.add(phoneLabel);
		phonePanel.add(phoneField);

		centerPanel.add(phonePanel);

		// "주소" 텍스트 필드를 포함한 패널 생성
		JPanel addressPanel = new JPanel();
		addressPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // 가운데 정렬로 변경
		JLabel addressLabel = new JLabel("주소: ");
		JTextField addressField = new JTextField(20);
		addressLabel.setFont(addressLabel.getFont().deriveFont(18f)); // 폰트 크기를 18로 설정
		addressPanel.add(addressLabel);
		addressPanel.add(addressField);

		centerPanel.add(addressPanel);

		// "성별" 라디오 버튼을 포함한 패널 생성
		JPanel genderPanel = new JPanel();
		genderPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // 가운데 정렬로 변경
		JLabel genderLabel = new JLabel("성별: ");
		genderLabel.setFont(genderLabel.getFont().deriveFont(18f)); // 폰트 크기를 18로 설정
		JRadioButton maleRadioButton = new JRadioButton("남");
		maleRadioButton.setFont(maleRadioButton.getFont().deriveFont(18f)); // 폰트 크기를 18로 설정
		JRadioButton femaleRadioButton = new JRadioButton("여");
		femaleRadioButton.setFont(femaleRadioButton.getFont().deriveFont(18f)); // 폰트 크기를 18로 설정
		ButtonGroup genderGroup = new ButtonGroup();
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
		experienceLabel.setFont(experienceLabel.getFont().deriveFont(18f)); // 폰트 크기를 18로 설정

		// "경력사항" 텍스트 영역 생성
		JTextArea experienceTextArea = new JTextArea(5, 20);
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
		String imagePath = "src/images/complete.png";

		// 이미지 레이블 생성
		JLabel imageLabel = new JLabel();
		ImageIcon imageIcon = new ImageIcon(imagePath);
		Image image = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);  // 원하는 크기로 조정
		imageLabel.setIcon(new ImageIcon(image));
		

		// 이미지 패널 생성
		JPanel imagePanel = new JPanel();
		imagePanel.setLayout(new BorderLayout());
		
		imagePanel.add(imageLabel, BorderLayout.CENTER);
		add(imagePanel, BorderLayout.WEST);
		imagePanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 10));
		
		// "사진 첨부" 버튼 생성
		JButton attachButton = new JButton("사진 첨부");
		attachButton.setFont(attachButton.getFont().deriveFont(15f)); // 폰트 크기를 18로 설정
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
		JButton addButton = new JButton("등록");
		addButton.setFont(addButton.getFont().deriveFont(18f)); // 폰트 크기를 24로 설정

		// "수정" 버튼 생성
		JButton editButton = new JButton("수정");
		editButton.setFont(editButton.getFont().deriveFont(18f)); // 폰트 크기를 24로 설정

		// 버튼들을 하단 패널에 추가

		bottomPanel.add(addButton);
		bottomPanel.add(editButton);
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 40, 10));

		// 하단 패널을 BorderLayout의 SOUTH 위치에 배치
		add(bottomPanel, BorderLayout.SOUTH);

	}

	

	
}