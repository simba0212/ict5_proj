package com.ict5.admin.panel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.ict5.admin.Admin_main;
import com.ict5.db.Protocol;
import com.ict5.db.VO;

public class ClassCheck extends JPanel {
	Admin_main main;
	CardLayout cardLayout;
	public JLabel label11,label12,label13,label14,label15,label17,label18,label19,label20;	
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

		JLabel label1 = new JLabel("수업종류");
		center.add(label1);

		label11 = new JLabel("테이블에서 수업을 선택해주세요");
		center.add(label11);

		JLabel label2 = new JLabel("수업장소");
		center.add(label2);

		label12 = new JLabel("테이블에서 수업을 선택해주세요");
		center.add(label12);

		JLabel label3 = new JLabel("강사");
		center.add(label3);

		label13 = new JLabel("테이블에서 수업을 선택해주세요");
		center.add(label13);

		JLabel label4 = new JLabel("정원");
		center.add(label4);

		label14 = new JLabel("테이블에서 수업을 선택해주세요");
		center.add(label14);

		JLabel label5 = new JLabel("예약명단");
		center.add(label5);

		label15 = new JLabel("테이블에서 수업을 선택해주세요");
		center.add(label15);

		JLabel label7 = new JLabel("강의시간");
		center.add(label7);

		label17 = new JLabel("테이블에서 수업을 선택해주세요");
		center.add(label17);

		JLabel label8 = new JLabel("강의날짜");
		center.add(label8);

		label18 = new JLabel("테이블에서 수업을 선택해주세요");
		center.add(label18);

		JLabel label9 = new JLabel("강의료");
		center.add(label9);

		label19 = new JLabel("테이블에서 수업을 선택해주세요");
		center.add(label19);

		JLabel label10 = new JLabel("강의번호");
		center.add(label10);

		label20 = new JLabel("테이블에서 수업을 선택해주세요");
		center.add(label20);

		add(center, BorderLayout.CENTER); // center 패널을 추가
		center.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 10));

		// Add '수업삭제' button
		JButton deleteButton = new JButton("수업삭제");
		JPanel bottomRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomRightPanel.add(deleteButton);
		add(bottomRightPanel, BorderLayout.SOUTH);

		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(label15.getText().equals("예약한 회원이 없습니다.")) {
					int res = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?","확인",JOptionPane.YES_NO_OPTION);
					Protocol p = new Protocol();
					VO vo= new VO();
					vo.setClass_num(label20.getText());
					p.setCmd(1109);
					p.setVo(vo);
					try {
						main.out.writeObject(p);
						main.out.flush();
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "예약한 회원이 존재합니다.\n"
														+ "예약을 먼저 취소해주세요");
				}
			}
		});
		
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

	public void setLabel() {
		List<VO> list = main.list;
		StringBuilder sb = new StringBuilder();
		for (VO k : list) {
			sb.append(k.getMember_name()+",");
		}
		sb.deleteCharAt(sb.length()-1);
		String member = sb.toString();
		VO vo = list.get(0);
		label11.setText(vo.getClass_type());
		label12.setText(vo.getClass_room());
		label13.setText(vo.getTeacher_name());
		label14.setText(vo.getClass_res()+"/"+vo.getClass_max()+"명");
		label15.setText(member);
		label17.setText(vo.getClass_time());
		label18.setText(vo.getClass_date().substring(0,10));
		label19.setText(vo.getClass_point());
		label20.setText(vo.getClass_num());
		
	}

	public void setLabel2() {
		VO vo = main.vo;
		label11.setText(vo.getClass_type());
		label12.setText(vo.getClass_room());
		label13.setText(vo.getTeacher_name());
		label14.setText(vo.getClass_res()+"/"+vo.getClass_max()+"명");
		label15.setText("예약한 회원이 없습니다.");
		label17.setText(vo.getClass_time());
		label18.setText(vo.getClass_date().substring(0,10));
		label19.setText(vo.getClass_point());
		label20.setText(vo.getClass_num());
	}
	public void resetLabel() {
		label11.setText("테이블에서 수업을 선택해주세요");
		label12.setText("테이블에서 수업을 선택해주세요");
		label13.setText("테이블에서 수업을 선택해주세요");
		label14.setText("테이블에서 수업을 선택해주세요");
		label15.setText("테이블에서 수업을 선택해주세요");
		label17.setText("테이블에서 수업을 선택해주세요");
		label18.setText("테이블에서 수업을 선택해주세요");
		label19.setText("테이블에서 수업을 선택해주세요");
		label20.setText("테이블에서 수업을 선택해주세요");
	}

}
