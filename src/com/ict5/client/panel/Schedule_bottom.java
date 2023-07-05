package com.ict5.client.panel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.ict5.client.Client_main;
import com.ict5.db.DAO;
import com.ict5.db.Protocol;
import com.ict5.db.VO;

public class Schedule_bottom extends JPanel {
	Client_main main;
	CardLayout cardlayout;
	JScrollPane jsp;
	VO vo;
	List<VO> list;
	public Schedule_bottom(Client_main main) {
		this.main = main;
		this.cardlayout = main.cardlayout;
		setLayout(new BorderLayout());
		add(new JLabel("<html><h3>수업 </h2></html>"), BorderLayout.NORTH);

	}

	private static JPanel createPanel() {

		JPanel panel = new JPanel(new GridLayout(3, 3));

		panel.setPreferredSize(new Dimension(300, 80));
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.add(new JLabel("수업종류", JLabel.LEFT));
		panel.add(new JLabel());
		panel.add(new JLabel("강의실", JLabel.RIGHT));
		panel.add(new JLabel("강사이름", JLabel.LEFT));
		panel.add(new JLabel());
		panel.add(new JButton("예약"));
		panel.add(new JLabel("수업시간", JLabel.LEFT));
		panel.add(new JLabel());
		panel.add(new JLabel("정원", JLabel.RIGHT));

		return panel;
	}
	public void refresh() {
		JPanel bt = new JPanel();
		bt.setLayout(new BoxLayout(bt, BoxLayout.Y_AXIS)); // 박스
		JPanel[] panels = new JPanel[10];
		for (int i = 0; i < panels.length; i++) {
			panels[i] = createPanel(); // 패널 생성 및 배열에 할당
			bt.add(panels[i]); // 프레임에 패널 추가
		}
		
		
		jsp = new JScrollPane(bt, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setPreferredSize(new Dimension(480, 350));
		add(jsp,BorderLayout.SOUTH);
//		if(main.list!=null) {
//			list = main.list;
//			System.out.println("스케줄하단 리플레쉬");
//			System.out.println(list.get(0).getAdmin_num()+"스케줄하단 에서 리스트 불러오고있습니다");
//			}
//			JPanel bt = new JPanel();
//			// 패널 배열 생성
//			JPanel[] panels = new JPanel[list.size()];
//			for (int i = 0; i < list.size(); i++) {
//				panels[i] = createPanel(); // 패널 생성 및 배열에 할당
//				bt.add(panels[i]); // 프레임에 패널 추가
//		}
//		
//		jsp = new JScrollPane(bt, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
//				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		add(jsp);
//		try {
//			
//			Protocol p = new Protocol();
//			p.setCmd(2301);
//			this.vo = main.vo;
//			p.setVo(vo);
//			main.out.writeObject(p);
//			main.out.flush();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
}
