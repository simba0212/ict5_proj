package com.ict5.client.panel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.ict5.client.Client_main;
import com.ict5.db.Protocol;
import com.ict5.db.VO;

public class Notice extends JPanel {
	Client_main main;
	CardLayout cardlayout;
	JScrollPane jsp;
	List<VO> list;
	VO vo;
	public Notice(Client_main main) {
		this.main = main;
		this.cardlayout = main.cardlayout;
		
		
	}

	private static JPanel createPanel(int i,List<VO> list) {
		String str= "";
		LocalDate currentDate = LocalDate.now();
		String str3="";
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2,10,5));
		panel.setMaximumSize(new Dimension(500,100));
		panel.setPreferredSize(new Dimension(100, 100));
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		switch (list.get(i).getClass_time()) {
    		case "1":str="09:00";	break;
    		case "2":str="10:00";break;
    		case "3":str="11:00";break;
    		case "4":str="12:00";break;
    		case "5":str="13:00";	break;
    		case "6":str="14:00";break;
    		case "7":str="15:00";break;
    		case "8":str="16:00";	break;
    		case "9":str="17:00";break;
    		case "10":str="18:00";break;
    		case "11":str="19:00";break;
    		case "12":str="20:00";break;
    		}
        if(currentDate.toString().equals(list.get(i).getClass_date().substring(0,10))) {
//        	str 에 수업 남은시간 표시LocalDateTime currentDateTime = LocalDateTime.now();
	        LocalTime currentTime = LocalTime.now();
	        String str2 = String.valueOf(currentTime).substring(0,5);
	        
	        // 시간 문자열을 LocalTime으로 파싱
	        LocalTime localTime1 = LocalTime.parse(str2);
	        LocalTime localTime2 = LocalTime.parse(str);
	        
	        // 시간 차이 계산
	        Duration duration = Duration.between(localTime1, localTime2);
	        
	        // 결과 출력
	        long hours = duration.toHours();
	        long minutes = duration.toMinutes() % 60;
	        
	        if(hours<0) {
	        	panel.add(new JLabel("시간이 지난 수업입니다",JLabel.LEFT));
	        	
	        }
	        else {
	        	 panel.add(new JLabel(hours + "시간 " + minutes + "분"+" 남았습니다.",JLabel.LEFT));
	        }
	       
        }else {
//        	몇일 남앗는지 비교
        	LocalDate compareDate = LocalDate.parse(list.get(i).getClass_date().substring(0,10));
        	long daysDifference = ChronoUnit.DAYS.between(currentDate, compareDate);
        	
        	str3= String.valueOf(daysDifference);
        	panel.add(new JLabel(str3+"일 후 수업입니다",JLabel.LEFT));
        }
		
		panel.add(new JLabel("",JLabel.RIGHT));
		panel.add(new JLabel(list.get(i).getTeacher_name()+"선생님",JLabel.LEFT));
		panel.add(new JLabel());
		panel.add(new JLabel(list.get(i).getClass_room()));
		panel.add(new JLabel(list.get(i).getClass_date().substring(0,10)+"     "+str+"시 수업" ,JLabel.RIGHT));
		
		return panel;
	}
	public void refresh(int ii) {
			
		if(ii==0) {
			try {
				Protocol p = new Protocol();
				VO vo= main.vo;
				vo.setMember_num(main.usernum);
				p.setCmd(2305);
				p.setVo(vo);
				
				main.out.writeObject(p);
				main.out.flush();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		
		}else if(ii==1) {
		list = main.list;
		JPanel noti = new JPanel();
		noti.setLayout(new BoxLayout(noti, BoxLayout.Y_AXIS)); // 박스
		// 패널 배열 생성
		JPanel[] panels = new JPanel[list.size()];
		for (int i = 0; i < list.size(); i++) {
			panels[i] = createPanel(i,list); // 패널 생성 및 배열에 할당
			noti.add(panels[i]); // 프레임에 패널 추가
		}
		
		jsp = new JScrollPane(noti, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setPreferredSize(new Dimension(480, 700));
		add(jsp);
		}
	}
}
