package com.ict5.admin.panel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.ict5.admin.Admin_main;
import com.ict5.db.Protocol;
import com.ict5.db.VO;

public class ClassEdit1 extends JPanel {
    Admin_main main;
    CardLayout cardLayout;
    JComboBox<String> comboBox_1;

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

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setPreferredSize(new Dimension(100, 40));
        comboBox.setModel(new DefaultComboBoxModel<>(new String[] { "101호", "102호", "103호", "104호", "201호", "202호",
                "203호", "204호", "301호", "302호", "303호", "304호" }));
        panel_2.add(comboBox);

        JPanel panel_3 = new JPanel();
        panel_3.setMaximumSize(new Dimension(400, 50));
        panel_3.setBackground(Color.WHITE);
        west.add(panel_3);

        JLabel lblNewLabel_2 = new JLabel("강사");
        lblNewLabel_2.setPreferredSize(new Dimension(100, 40));
        panel_3.add(lblNewLabel_2);

        // comboBox_1 초기화 및 설정
        comboBox_1 = new JComboBox<>();
        comboBox_1.setPreferredSize(new Dimension(100, 40));
        panel_3.add(comboBox_1);

        JPanel panel_4 = new JPanel();
        panel_4.setMaximumSize(new Dimension(400, 50));
        panel_4.setBackground(Color.WHITE);
        west.add(panel_4);

        JLabel lblNewLabel_3 = new JLabel("정원");
        lblNewLabel_3.setPreferredSize(new Dimension(100, 40));
        panel_4.add(lblNewLabel_3);

        JComboBox<String> comboBox_2 = new JComboBox<>();
        comboBox_2.setPreferredSize(new Dimension(100, 40));
        comboBox_2.setModel(new DefaultComboBoxModel<>(new String[] { "1명", "2명", "3명", "4명", "5명", "6명", "7명", "8명", "9명", "10명" }));
        panel_4.add(comboBox_2);

        JPanel panel_5 = new JPanel();
        panel_5.setMaximumSize(new Dimension(400, 50));
        panel_5.setBackground(Color.WHITE);
        west.add(panel_5);

        JLabel lblNewLabel_4 = new JLabel("강의시간");
        lblNewLabel_4.setPreferredSize(new Dimension(100, 40));
        panel_5.add(lblNewLabel_4);

        JComboBox<String> comboBox_3 = new JComboBox<>();
        comboBox_3.setPreferredSize(new Dimension(120, 40));
        comboBox_3.setModel(new DefaultComboBoxModel<>(new String[] { "09:00 ~ 09:50", "10:00 ~ 10:50", "11:00 ~ 11:50",
                "12:00 ~ 12:50", "13:00 ~ 13:50", "14:00 ~ 14:50", "15:00 ~ 15:50", "16:00 ~ 16:50", "17:00 ~ 17:50",
                "18:00 ~ 18:50", "19:00 ~ 19:50", "20:00 ~ 20:50", "21:00 ~ 21:50" }));
        panel_5.add(comboBox_3);

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

        JComboBox<String> comboBox_4 = new JComboBox<>();
        comboBox_4.setPreferredSize(new Dimension(120, 40));

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate currentDate = LocalDate.now();
        LocalDate endDate = currentDate.plusDays(30);

        while (currentDate.isBefore(endDate) || currentDate.isEqual(endDate)) {
            String formattedDate = currentDate.format(formatter);
            model.addElement(formattedDate);
            currentDate = currentDate.plusDays(1);
        }

        comboBox_4.setModel(model);
        panel_7.add(comboBox_4);

        JPanel panel_8 = new JPanel();
        panel_8.setMaximumSize(new Dimension(400, 50));
        panel_8.setBackground(Color.WHITE);
        west.add(panel_8);

        JLabel lblNewLabel_11 = new JLabel("금액");
        lblNewLabel_11.setPreferredSize(new Dimension(100, 40));
        panel_8.add(lblNewLabel_11);

        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(100, 40));
        panel_8.add(textField);

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
        bt_add.setPreferredSize(new Dimension(150, 50));
        south.add(bt_add);

        JButton bt_update = new JButton("수정");
        bt_update.setPreferredSize(new Dimension(150, 50));
        south.add(bt_update);

        JButton bt_cancel = new JButton("취소");
        bt_cancel.setPreferredSize(new Dimension(150, 50));
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

        bt_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // JComboBox에서 선택한 데이터 가져오기
                String CLASS_TYPE = "";
                if (rdbtnNewRadioButton.isSelected()) {
                	CLASS_TYPE = rdbtnNewRadioButton.getText();
                } else if (rdbtnNewRadioButton_1.isSelected()) {
                	CLASS_TYPE = rdbtnNewRadioButton_1.getText();
                } else if (rdbtnNewRadioButton_2.isSelected()) {
                	CLASS_TYPE = rdbtnNewRadioButton_2.getText();
                } else if (rdbtnNewRadioButton_3.isSelected()) {
                	CLASS_TYPE = rdbtnNewRadioButton_3.getText();
                }
                
                String CLASS_ROOM = comboBox.getSelectedItem().toString();
                String TEACHER_NAME = comboBox_1.getSelectedItem().toString();
                String CLASS_MAX = comboBox_2.getSelectedItem().toString();
                String CLASS_TIME = comboBox_3.getSelectedItem().toString();
                String CLASS_DATE = comboBox_4.getSelectedItem().toString();
                String CLASS_POINT = textField.getText();
                
                try {
                    Protocol p = new Protocol();
    		    	VO vo = new VO();
    		    	vo.setTeacher_name(TEACHER_NAME);
    		    	p.setCmd(1007);
    		    	p.setVo(vo);
    				main.out.writeObject(p);
    				main.out.flush();
    				} catch (IOException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
            }
        });
        
    }
    
    public void TeacherName() {
        List<VO> list = main.list;
        
        comboBox_1.removeAllItems(); // Clear existing items in the comboBox_1
        
        for (VO vo : list) {
            String T_name = vo.getTeacher_name();
            String T_num = vo.getTeacher_num();
            comboBox_1.addItem(T_name); // Add each teacher name to the comboBox_1
        }
    }

    
    
}
