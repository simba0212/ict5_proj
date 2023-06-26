package com.ict5.admin;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Member_view2  extends JPanel {
	Admin_main main;
	
	public Member_view2 (Admin_main main) {
		this.main = main;
		
		JPanel jp = new JPanel(new BorderLayout());
		JPanel jp1 =new JPanel(new BorderLayout());
		JPanel jp1_1 =new JPanel(new BorderLayout());
		 Font font = new Font("돋움", Font.BOLD, 30);
		JLabel jl1 = new JLabel("     회원관리");
		JLabel jl2 = new JLabel("회원검색");
		JLabel jl3 = new JLabel("     포인트관리    ");
		jl1.setFont(font);
		jl2.setFont(font);
		jl3.setFont(font);
		jp1.add(jl1,BorderLayout.WEST);
		jp1_1.add(jl2,BorderLayout.WEST);
		jp1_1.add(jl3,BorderLayout.EAST);
		jp1_1.setBackground(Color.lightGray);
		jp1.setBackground(Color.lightGray);
		jp1.add(jp1_1,BorderLayout.AFTER_LINE_ENDS);
		jp1.setPreferredSize(new Dimension(1280, 60));
		
		
		  // 열 제목 지정
		String[][] data = {
				
			    {"이름", "변수2"},
			    {"ID", "변수3"},
			    {"전화번호", "변수4"},
			    {"이메일", "변수5"},
			    {"성별", "변수6"},
			    {"생년월일", "변수7"},
			    {"최근예약", "변수8"},
			    {"주소", "변수9"},
			    {"보유포인트", "변수10"},
			    {"등록 날짜", "변수11"},
			    {"최근 출석일", "변수12"},
			    {"출석일수", "변수13"},
			    {"사용포인트(달)", "변수14"},
			    {"충전포인트(달)", "변수15"},
			    {"누적사용포인트", "변수16"},
			    {"누적충전포인트", "변수17"},
			    // 추가할 데이터
			};
			String[] columnNames = {" 텍스트", "변수"};

        // 테이블 모델 생성
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // 변수로 받는 값을 테이블에 추가
       

        // 테이블 생성 및 모델 설정
        JTable table = new JTable(model);
		JPanel jp2 = new JPanel();
		jp2.setPreferredSize(new Dimension(480, 620));
		jp2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		jp2.add(table);
		
		JPanel jp3 = new JPanel(new GridLayout(0,1));
		
		JPanel jp3_1 = new JPanel(new BorderLayout());
		
		JLabel classlabel = new JLabel();
		String[] columnNames2 = {"수업 번호", "종류","예약날짜","수강날짜","강사이름","출석여부"};
        // 테이블 모델 생성
        DefaultTableModel model2 = new DefaultTableModel(columnNames2, 0 );
        int rowCount = 5;
        // 데이터 추가
        for (int i = 1; i <= rowCount; i++) {
            Object[] rowData = {"1","abc","2000-00-00","2000-00-00","노종문","출석완료"};
            
            model2.addRow(rowData);
        }
        JTable table2 = new JTable(model2);
		jp3_1.add(classlabel);
		jp3_1.add(table2);
		JPanel jp3_2 = new JPanel(new BorderLayout());
		
		JLabel pointlabel = new JLabel();
		
		jp3.add(jp3_1);
		jp3.add(jp3_2);
//		 String[] columnNames = {"회원번호", "이름", "ID","전화번호","이메일","성별","생년월일","최근예약","주소","보유 포인트","등록 날짜","최근 출석일","이번달출석","사용포인트","충전포인트(달)","누적사용포인트","누적충전포인트"};
	        
		jp. add(jp1,BorderLayout.NORTH);
		jp. add(jp2,BorderLayout.WEST);
		jp.add(jp3,BorderLayout.CENTER);
		add(jp);
		
	}
	
}
