package com.ict5.client.panel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.ict5.client.Client_main;
import com.ict5.db.Protocol;
import com.ict5.db.VO;

public class Reservation_bottom extends JPanel {
	Client_main main;
	CardLayout cardlayout;
	JScrollPane jsp;
	VO vo;
	List<VO> list;
	JLabel jl1;
	String str="";
	
	public Reservation_bottom(Client_main main) {
		this.main = main;
		this.cardlayout = main.cardlayout;
		setLayout(new BorderLayout());
	}
	private JPanel createPanel(List<VO> list,int i) {
		JPanel panel = new JPanel(new GridLayout(3,3));
		String str2="";
    	switch (list.get(i).getClass_type()) {
		case "1":str2="수영";	break;
		case "2":str2="PT";break;
		case "3":str2="요가";break;
		case "4":str2="필라테스";break;
	
		}
		panel.setPreferredSize(new Dimension(300, 80));
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.add(new JLabel("수업종류", JLabel.LEFT));
		panel.add(new JLabel(str2));
		panel.add(new JLabel(list.get(i).getClass_room(), JLabel.RIGHT));
		panel.add(new JLabel("강사이름", JLabel.LEFT));
		panel.add(new JLabel(list.get(i).getTeacher_name()));
		JButton jb = new JButton("출석");
		panel.add(jb);
		if(list.get(i).getAttendent_time()!=null) {
			jb.setEnabled(false);
			jb.setText("출석함");
		}
		panel.add(new JLabel("수업시간", JLabel.LEFT));
		switch (list.get(i).getClass_time()) {
			case "1":str="09:00~09:50";	break;
			case "2":str="10:00~10:50";break;
			case "3":str="11:00~11:50";break;
			case "4":str="12:00~12:50";break;
			case "5":str="13:00~13:50";	break;
			case "6":str="14:00~14:50";break;
			case "7":str="15:00~15:50";break;
			case "8":str="16:00~16:50";	break;
			case "9":str="17:00~17:50";break;
			case "10":str="18:00~18:50";break;
			case "11":str="19:00~19:50";break;
			case "12":str="20:00~20:50";break;
		}
		panel.add(new JLabel(str));
		panel.add(new JLabel("정원"+list.get(i).getClass_res()+"/"+list.get(i).getClass_max(), JLabel.RIGHT));
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				switch (list.get(i).getClass_time()) {
	    		case "1":str="09:00~09:50";	break;
	    		case "2":str="10:00~10:50";break;
	    		case "3":str="11:00~11:50";break;
	    		case "4":str="12:00~12:50";break;
	    		case "5":str="13:00~13:50";	break;
	    		case "6":str="14:00~14:50";break;
	    		case "7":str="15:00~15:50";break;
	    		case "8":str="16:00~16:50";	break;
	    		case "9":str="17:00~17:50";break;
	    		case "10":str="18:00~18:50";break;
	    		case "11":str="19:00~19:50";break;
	    		case "12":str="20:00~20:50";break;
	    		}
	        	Object ob = str+"\n"+list.get(i).getTeacher_name()+"\n"+"입장하시겠습니까?"
	        			;
	        	
	        	String str2="";
	        	switch (list.get(i).getClass_type()) {
	    		case "1":str2="수영";	break;
	    		case "2":str2="PT";break;
	    		case "3":str2="요가";break;
	    		case "4":str2="필라테스";break;
	    	
	    		}
	        	
	            int option = JOptionPane.showConfirmDialog(null,ob, str2, JOptionPane.YES_NO_OPTION);
	            if (option == JOptionPane.YES_OPTION) {
				    // 사용자가 확인 버튼을 클릭한 경우에 대한 처리
					System.out.println("확인버튼 눌럿을때");
					try {
						Protocol p = new Protocol();
						vo = main.vo;
						vo.setClass_num(list.get(i).getClass_num());
						vo.setMember_num(main.usernum);
						p.setCmd(2901);
						p.setVo(vo);
						main.out.writeObject(p);
						main.out.flush();
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
				} else if (option == JOptionPane.NO_OPTION) {
				    // 사용자가 취소 버튼을 클릭한 경우에 대한 처리
				}
			}
		});
		return panel;
	}
	
	public void refresh() {
		list = main.list;
		if (list.size() == 0) {
			removeAll();
			jsp=new JScrollPane();
			jsp.setPreferredSize(new Dimension(480, 350));
			add(new JLabel("<html><h3>수업 "+main.tab.reservation.mon+"월"+ main.tab.reservation.day_i+"일 </h2></html>"), BorderLayout.NORTH);
			add(jsp);
		}
		else {
			removeAll();
			JPanel bt = new JPanel();
			bt.setLayout(new BoxLayout(bt, BoxLayout.Y_AXIS)); // 박스
			// 패널 배열 생성
			JPanel[] panels = new JPanel[list.size()];
			for (int i = 0; i < list.size(); i++) {
				panels[i] = createPanel(list,i); // 패널 생성 및 배열에 할당
				JButton button = new JButton("Button " + i);
				bt.add(panels[i]); // 프레임에 패널 추가
			}
			jsp = new JScrollPane(bt, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
						ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				jsp.setPreferredSize(new Dimension(480, 350));
				add(new JLabel("<html><h3>수업 "+main.tab.reservation.mon+"월"+ main.tab.reservation.day_i+"일 </h2></html>"), BorderLayout.NORTH);
				add(jsp);
		}
	}

}
