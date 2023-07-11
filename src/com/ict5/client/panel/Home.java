package com.ict5.client.panel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.ict5.client.Client_ChargeP;
import com.ict5.client.Client_main;
import com.ict5.db.DAO;
import com.ict5.db.VO;

public class Home extends JPanel {
	Client_main main;
	CardLayout cardlayout;
	JLabel label2, label3, label4, label5, label6;
	JTextArea notice;

	VO vo;

	public Home(Client_main main) {
		this.main = main;
		this.cardlayout = main.cardlayout;
		this.vo = main.vo; // vo 동기화

		// 화면단 패널을 공지사항, 가까운수업, 버튼 총3개로 나눔
		setLayout(new BorderLayout());

		// 공지사항 패널
		JPanel noticePanel = new JPanel(new BorderLayout());
		notice = new JTextArea(10, 40);
		notice.setBorder(BorderFactory.createEtchedBorder()); // JTextArea에 테두리 설정
		notice.setLineWrap(true); // 자동 줄 바꿈 jta 는 무조건 jscrollpane 과 짝궁
		notice.setEditable(false);
		JScrollPane jsp = new JScrollPane(notice, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		noticePanel.add(new JLabel(" 공  지  사  항 ", JLabel.CENTER), BorderLayout.NORTH);
		noticePanel.add(jsp, BorderLayout.SOUTH);

		// 가까운 수업 패널
		JPanel near_class = new JPanel(new GridLayout(0, 1)); // 중앙 기본베이스 패널
		near_class.setBorder(BorderFactory.createEtchedBorder()); // JPanel에 테두리 설정
		near_class.setBorder(new EmptyBorder(20, 20, 20, 20)); // 화면 양 사이드에 간격 주기

		JPanel second0 = new JPanel(new GridLayout(1, 0));
		JLabel label1 = new JLabel("가장가까운수업", JLabel.CENTER);
		second0.add(label1);

		JPanel second1 = new JPanel(new GridLayout(0, 2));
		second1.setBackground(Color.WHITE);
		label2 = new JLabel("운동종류");
		second1.add(label2);
		label3 = new JLabel("강의실", JLabel.RIGHT);
		second1.add(label3);

		JPanel second2 = new JPanel(new GridLayout(0, 2));
		second2.setBackground(Color.WHITE);
		label4 = new JLabel("강사이름");
		label5 = new JLabel("정원", JLabel.RIGHT);
		second2.add(label4);
		second2.add(label5);

		JPanel second3 = new JPanel(new GridLayout(0, 1));
		second3.setBackground(Color.WHITE);
		label6 = new JLabel("수업시간 ");
		second3.add(label6);

		near_class.add(second0);
		near_class.add(second1);
		near_class.add(second2);
		near_class.add(second3);

		// 버튼 패널
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 150, 20)); // 플로우레이아웃을 이용해서 버튼을 아래로 내리기
		btnPanel.setPreferredSize(new Dimension(50, 300)); // 패널 사이즈
		JButton point_bt = new JButton();
		point_bt = new JButton(" 포인트 충전 ");
		point_bt.setPreferredSize(new Dimension(300, 50));// 버튼 사이즈
		JButton book_bt = new JButton();
		book_bt = new JButton(" 수업 예약 ");
		book_bt.setPreferredSize(new Dimension(300, 50));
		JButton attend_bt = new JButton();
		attend_bt = new JButton(" 출결 체크 ");
		attend_bt.setPreferredSize(new Dimension(300, 50));

		btnPanel.add(point_bt, BorderLayout.NORTH);
		btnPanel.add(book_bt, BorderLayout.CENTER);
		btnPanel.add(attend_bt, BorderLayout.SOUTH);

		add(noticePanel, BorderLayout.NORTH);
		add(near_class, BorderLayout.CENTER);
		add(btnPanel, BorderLayout.SOUTH);

		// 포인트충전 버튼
		point_bt.addActionListener(e -> {
			Client_ChargeP chargeP = new Client_ChargeP(main);
			main.pg1.add("chargeP", chargeP);
			main.cardlayout.show(main.pg1, "chargeP");
			main.chargeP.usertop.refresh();
		});
		// 수업예약 클릭 이벤트
		book_bt.addActionListener(e -> {
			
			main.pg1.add("tab", main.tab);
			main.cardlayout.show(main.pg1, "tab");
			TabPage.tabbedPane.setSelectedIndex(0);
		});
		// 출결체크 클릭 이벤트
		attend_bt.addActionListener(e -> {
			main.pg1.add("tab", main.tab);
			main.cardlayout.show(main.pg1, "tab");
			TabPage.tabbedPane.setSelectedIndex(1);
		});
		// 패널 클릭
		near_class.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (vo == null) {
					JOptionPane.showMessageDialog(null, "예약된 클래스가 없습니다", "알림", JOptionPane.WARNING_MESSAGE);
				} else {
					main.cardlayout.show(main.pg1, "tab");
					TabPage.tabbedPane.setSelectedIndex(1);
					// 최근 강의시간으로 가야함
				}
			}
		});

	}

	public void refresh() {
		// 공지사항 최신화
		this.vo = main.vo; // 중요!
		notice.setText(vo.getNotice_text());

		// 가까운 수업
		vo = DAO.getNearClasstime(vo);
		if (vo == null) {
			label2.setText("");
			label3.setText("");
			label4.setText("No class");
			label5.setText("");
			label6.setText("");
		} else {

			label3.setText(vo.getClass_room());

			switch (vo.getClass_type()) {
			case "1":
				label2.setText("수영");
				break;
			case "2":
				label2.setText("헬스");
				break;
			case "3":
				label2.setText("요가");
				break;
			case "4":
				label2.setText("필라테스");
				break;
			default:
				break;
			}
			String t_name = vo.getTeacher_name();
			label4.setText(t_name);// 강사이름

			String room = "정원 : " + vo.getClass_res() + "/" + vo.getClass_max();
			label5.setText(room); // 정원정보

			String near_classdate = vo.getClass_date();
			String near_classtime = "";
			switch (vo.getClass_time()) {
			case "1":
				near_classtime = "09:00~";
				break;
			case "2":
				near_classtime = "10:00~";
				break;
			case "3":
				near_classtime = "11:00~";
				break;
			case "4":
				near_classtime = "12:00~";
				break;
			case "5":
				near_classtime = "13:00~";
				break;
			case "6":
				near_classtime = "14:00~";
				break;
			case "7":
				near_classtime = "15:00~";
				break;
			case "8":
				near_classtime = "16:00~";
				break;
			case "9":
				near_classtime = "17:00~";
				break;
			case "10":
				near_classtime = "18:00~";
				break;
			case "11":
				near_classtime = "19:00~";
				break;
			case "12":
				near_classtime = "20:00~";
				break;
			case "13":
				near_classtime = "21:00~";
				break;

			default:
				break;
			}

			String month = near_classdate.substring(5, 7);
			String day = near_classdate.substring(8, 10);
			String date = month + "-" + day + " / " + near_classtime;

			label6.setText(date);
		}

	}
}
