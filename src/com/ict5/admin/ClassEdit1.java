package com.ict5.admin;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class ClassEdit1 extends JPanel {
	Admin_main main;
	CardLayout cardLayout;
	
	public ClassEdit1(Admin_main main) {
		this.main = main;
		
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
		Font labelFont = titleLabel.getFont().deriveFont(Font.BOLD,20f);
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

		JPanel west = new JPanel();
		west.setBackground(Color.WHITE);
		add(west, BorderLayout.WEST);
		west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));

		JPanel panel_1 = new JPanel();
		panel_1.setMaximumSize(new Dimension(400, 50));
		panel_1.setBackground(Color.WHITE);
		west.add(panel_1);

		JLabel lblNewLabel = new JLabel("수업종류");
		panel_1.add(lblNewLabel);
		panel_1.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
		ButtonGroup bg1 = new ButtonGroup();

		JRadioButton rdbtnNewRadioButton = new JRadioButton("PT");
		rdbtnNewRadioButton.setBackground(Color.WHITE);
		panel_1.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("수영");
		rdbtnNewRadioButton_1.setBackground(Color.WHITE);
		panel_1.add(rdbtnNewRadioButton_1);

		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("필라테스");
		rdbtnNewRadioButton_2.setBackground(Color.WHITE);
		panel_1.add(rdbtnNewRadioButton_2);

		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("요가");
		rdbtnNewRadioButton_3.setBackground(Color.WHITE);
		panel_1.add(rdbtnNewRadioButton_3);
		bg1.add(rdbtnNewRadioButton);
		bg1.add(rdbtnNewRadioButton_1);
		bg1.add(rdbtnNewRadioButton_2);
		bg1.add(rdbtnNewRadioButton_3);

		JPanel panel_2 = new JPanel();
		panel_2.setMaximumSize(new Dimension(400, 50));
		panel_2.setBackground(Color.WHITE);
		west.add(panel_2);

		JLabel lblNewLabel_1 = new JLabel("수업장소");
		lblNewLabel_1.setPreferredSize(new Dimension(100, 40));
		panel_2.add(lblNewLabel_1);

		JComboBox comboBox = new JComboBox();
		comboBox.setPreferredSize(new Dimension(100, 40));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "101호", "102호", "103호", "104호", "201호", "202호",
				"203호", "204호", "301호", "302호", "303호", "304호" }));
		panel_2.add(comboBox);

		JPanel panel_3 = new JPanel();
		panel_3.setMaximumSize(new Dimension(400, 50));
		panel_3.setBackground(Color.WHITE);
		west.add(panel_3);

		JLabel lblNewLabel_2 = new JLabel("강사");
		lblNewLabel_2.setPreferredSize(new Dimension(100, 40));
		panel_3.add(lblNewLabel_2);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setPreferredSize(new Dimension(100, 40));
		comboBox_1.setModel(new DefaultComboBoxModel(
				new String[] { "PT강사1", "PT강사2", "요가강사1", "요가강사2", "필라1", "필라2", "수영1", "수영2" }));
		panel_3.add(comboBox_1);

		JPanel panel_4 = new JPanel();
		panel_4.setMaximumSize(new Dimension(400, 50));
		panel_4.setBackground(Color.WHITE);
		west.add(panel_4);

		JLabel lblNewLabel_3 = new JLabel("정원");
		lblNewLabel_3.setPreferredSize(new Dimension(100, 40));
		panel_4.add(lblNewLabel_3);

		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setPreferredSize(new Dimension(100, 40));
		comboBox_2.setModel(
				new DefaultComboBoxModel(new String[] { "1명", "2명", "3명", "4명", "5명", "6명", "7명", "8명", "9명", "10명" }));
		panel_4.add(comboBox_2);

		JPanel panel_5 = new JPanel();
		panel_5.setMaximumSize(new Dimension(400, 50));
		panel_5.setBackground(Color.WHITE);
		west.add(panel_5);

		JLabel lblNewLabel_4 = new JLabel("강의시간");
		lblNewLabel_4.setPreferredSize(new Dimension(100, 40));
		panel_5.add(lblNewLabel_4);

		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setPreferredSize(new Dimension(120, 40));
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] { "09:00 ~ 09:50", "10:00 ~ 10:50", "11:00 ~ 11:50",
				"12:00 ~ 12:50", "13:00 ~ 13:50", "14:00 ~ 14:50", "15:00 ~ 15:50", "16:00 ~ 16:50", "17:00 ~ 17:50",
				"18:00 ~ 18:50", "19:00 ~ 19:50", "20:00 ~ 20:50", "21:00 ~ 21:50" }));
		panel_5.add(comboBox_3);

		JPanel panel_6 = new JPanel();
		panel_6.setMaximumSize(new Dimension(400, 50));
		panel_6.setBackground(Color.WHITE);
		west.add(panel_6);

		JLabel lblNewLabel_5 = new JLabel("정규수업여부");
		lblNewLabel_5.setPreferredSize(new Dimension(100, 40));
		panel_6.add(lblNewLabel_5);
		
		ButtonGroup bg2= new ButtonGroup();
		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("YES");
		rdbtnNewRadioButton_4.setBackground(Color.WHITE);
		panel_6.add(rdbtnNewRadioButton_4);
		bg2.add(rdbtnNewRadioButton_4);
		JRadioButton rdbtnNewRadioButton_5 = new JRadioButton("NO");
		rdbtnNewRadioButton_5.setBackground(Color.WHITE);
		panel_6.add(rdbtnNewRadioButton_5);
		bg2.add(rdbtnNewRadioButton_5);
		
		JLabel lblNewLabel_10 = new JLabel("매주 같은 요일에 등록됩니다.");
		lblNewLabel_10.setForeground(new Color(178, 34, 34));
		panel_6.add(lblNewLabel_10);

		JPanel panel_7 = new JPanel();
		panel_7.setMaximumSize(new Dimension(400, 50));
		panel_7.setBackground(Color.WHITE);
		west.add(panel_7);

		JLabel lblNewLabel_6 = new JLabel("강의날짜");
		lblNewLabel_6.setPreferredSize(new Dimension(100, 40));
		panel_7.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon("D:\\khj\\javastudy\\ict5_proj\\src\\images\\calender.png"));
		panel_7.add(lblNewLabel_7);

		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setPreferredSize(new Dimension(120, 40));
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] { "2023 - 06 - 10", "2023 - 06 - 11",
				"2023 - 06 - 12", "2023 - 06 - 13", "2023 - 06 - 14", "2023 - 06 - 15", "2023 - 06 - 16" }));
		panel_7.add(comboBox_4);

		JPanel east = new JPanel();
		east.setBackground(Color.WHITE);
		add(east, BorderLayout.EAST);
		east.setLayout(new BorderLayout(0, 0));

		JLabel profile = new JLabel("");
		profile.setPreferredSize(new Dimension(200, 600));
		profile.setMaximumSize(new Dimension(200, 600));
		profile.setIcon(new ImageIcon("D:\\khj\\javastudy\\ict5_proj\\src\\images\\gym.png"));
		east.add(profile, BorderLayout.SOUTH);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		east.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_9 = new JLabel("이미지등록");
		panel.add(lblNewLabel_9, BorderLayout.WEST);

		JButton bt_fileopen = new JButton("파일열기");
		panel.add(bt_fileopen, BorderLayout.EAST);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JPanel south = new JPanel();
		south.setBackground(Color.WHITE);
		add(south, BorderLayout.SOUTH);

		JButton bt_add = new JButton("등록");
		bt_add.setPreferredSize(new Dimension(200, 50));
		south.add(bt_add);

		JButton bt_cancel = new JButton("취소");
		bt_cancel.setPreferredSize(new Dimension(200, 50));
		south.add(bt_cancel);
		
		label_1.addMouseListener(new MouseAdapter() {
			
			@Override
		    public void mouseEntered(MouseEvent e) {
				label_1.setForeground(Color.red);
		    }
		    
		    @Override
		    public void mouseExited(MouseEvent e) {
		    	label_1.setForeground(Color.black);
		    }
			
		});
		
		label_1.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        main.cardlayout.show(main.pg1, "classcheck"); // "member" 페이지로 이동
		    }
		});
	}

}
