package com.ict5.admin;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.ict5.db.CP_Client;
import com.ict5.db.DAO;
import com.ict5.db.DB_Server;
import com.ict5.db.Protocol;
import com.ict5.db.VO;

public class Admin_main extends JFrame implements Runnable {
	public Socket s;
	public ObjectOutputStream out;
	public ObjectInputStream in;
	public VO vo;

	public List<VO> list;
	public String admin_id;


	public CardLayout cardlayout;
	public JPanel pg1;
	public Admin_Home home;
	public Admin_Login login;
	public Admin_CheckAgain checkagain;
	public Admin_ClassEdit classEdit;
	public Admin_memberview member;
	public Admin_memberview2 member2;
	public Admin_CoMgmt1 coMg1;
	public Admin_CoMgmt2 coMg2;
	public Admin_PointMgmt point_Mgmt;
	public Admin_CoMgmt3 coMg3;
	public Admin_UserAppMgmt userMg;
	public Admin_ClassCheck classcheck;

	public Admin_main() {
		super("거구로 거듭나자 거구장센터");
		connected();
		cardlayout = new CardLayout();
		pg1 = new JPanel();
		pg1.setLayout(cardlayout);

		// 클래스명 변수명 = new 클래스명(this); 이 클래스들은 각각의 페이지(카드)를 의미합니다.
		home = new Admin_Home(this);
		login = new Admin_Login(this);
		checkagain = new Admin_CheckAgain(this);
		classEdit = new Admin_ClassEdit(this);
		member = new Admin_memberview(this);
		member2 = new Admin_memberview2(this);
		coMg1 = new Admin_CoMgmt1(this);
		coMg2 = new Admin_CoMgmt2(this);
		point_Mgmt = new Admin_PointMgmt(this);
		coMg3 = new Admin_CoMgmt3(this);
		userMg = new Admin_UserAppMgmt(this);
		classcheck = new Admin_ClassCheck(this);

		// 클래스명 변수명 = new 클래스명(this);
		// 클래스명 변수명 = new 클래스명(this);
		// 클래스명 변수명 = new 클래스명(this);

		// pg1.add("페이지명",객체이름); 각 페이지들의 이름을 지정해주고, 각 객체들로 해당 페이지로 이동합니다.

		pg1.add("home", home); // 홈
		pg1.add("login", login); // 로그인
		pg1.add("classEdit", classEdit); // 수업 수정
		pg1.add("member", member); // 회원 목록
		pg1.add("member2", member2); // 회원 세부사항
		pg1.add("checkagain", checkagain); // 보안 로그인
		pg1.add("coMg1", coMg1); // 강사관리-강사목록
		pg1.add("coMg2", coMg2); // 강사관리-해당강사의 수업
		pg1.add("coMg3", coMg3); // 강사 등록
		pg1.add("userMg", userMg); // 유저앱관리
		pg1.add("point_Mgmt", point_Mgmt); // 포인트 관리
		pg1.add("classcheck", classcheck); // 수업 확인

		// pg1.add("페이지명",객체이름);

		add(pg1);


		cardlayout.show(pg1, "home");

		setResizable(false);
		getContentPane().setBackground(Color.white);
		setSize(1280, 840);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}

	// 접속
	public void connected() {
		try {
			s = new Socket("localhost", 7780);
			out = new ObjectOutputStream(s.getOutputStream());
			in = new ObjectInputStream(s.getInputStream());
			new Thread(this).start();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// 끝내기
	public void closed() {
		try {
			out.close();
			in.close();
			s.close();
			System.exit(0);
		} catch (Exception e) {
		}
	}

	@Override
	public void run() {
		esc: while (true) {
			try {
				Object obj = in.readObject();
				if (obj != null) {
					Protocol p = (Protocol) obj;
					list = p.getList();
					vo = p.getVo();

					switch (p.getCmd()) {
					case 0:
						break esc;

					case 1001:
						if (p.getResult() == 1) {
							
							p.setCmd(1002);
							out.writeObject(p);
							out.flush();
						} else {
							System.out.println("실패");
							break ;
						}

					case 1002:
						if (p.getResult() == 1) {
							home.timetable.Date();
							System.out.println("테이블 성공 111");
						} else {
							System.out.println("테이블 실패 222");
							break;
						}


					case 1201: // 회원목록 불러오기
						member.memberv.refresh();
						break;
					case 1202: // 한명 검색하기
						member.memberv.search();
						break;
					case 1203: // 회원 세부정보 보기
						member2.memberv2.refresh1();// 왼쪽테이블
						break;
					case 1204: // 회원 세부정보 => 수업예약내역
						member2.memberv2.refresh2();// 예약내역
						break;
					case 1205: // 회원 세부정보 => 포인트이력
						member2.memberv2.refresh3();// 포인트 이력
						break;
//					case 1206: // 포인트관리 이동전 체크
//						member2.memberv2.refresh3();
//						break;
					case 1207: // 포인트 승인페이지이동
						point_Mgmt.sub.refresh(); // 포인트불러오기
						break;
					case 1301: // 강사목록 불러오기
						coMg1.coTable1.refresh();
						break;
					case 1302: // 한명 검색하기
						coMg1.coTable1.search();
						break;
					case 1303: // 강사 세부정보 보기
						coMg2.coTable2.refresh1();// 왼쪽테이블
						break;
					case 1304: // 회원 세부정보 => 수업예약내역
						coMg2.coTable2.refresh2();// 예약내역
						break;
					case 1309:
						System.out.println("픽스메서드전까지옴");
						coMg3.coMgmt4.fix(); // 수정내용
						break;
					case 1310: //삭제 
						p.setCmd(1301);
						out.writeObject(p);
						out.flush();
						
//						System.out.println("작업하고옴  " + p.getResult());
//
//						if (p.getResult() == 1) {
//							//리스트 재조회
//							coMg1.coTable1.refresh();
//						}
						break;
					case 1317: // 강사 수정후 재등록
						p.setCmd(1301);
						out.writeObject(p);
						out.flush();
						break;
					case 1318: // 강사 등록하기
						p.setCmd(1301);
						out.writeObject(p);
						out.flush();
					case 1320: // 공지사항 등록하기
						if (p.getResult() == 1) {
							System.out.println("공지등록완료");
						} else {
							System.out.println("공지등록실패");
						}
						break;

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		closed();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
					Admin_main frame = new Admin_main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}