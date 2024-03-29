package com.ict5.admin;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.ict5.db.DAO;
import com.ict5.db.CP_Client;
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

		cardlayout.show(pg1, "login");

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
						System.out.println("종료");
						break esc;

					case 1001: // 로그인
						if (p.getResult() == 1) {
							admin_id = vo.getAdmin_id(); // 이 id는 쭉 가지고있음
							p.setCmd(1002);
							out.writeObject(p);
							out.flush();
							cardlayout.show(pg1, "home");
						} else {
							JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.");
							break;
						}
						break;

					case 1002:
						if (p.getResult() == 1) {
							home.timetable.clearTableData();
							home.timetable.Date();
							classcheck.timetable.Date();
							classEdit.timetable.Date();
						} else {
							
						}
						break;

					case 1003:
						home.member_new.Member();
						break;

					case 1004:
						home.point_new.PointApprove();
						break;
						
					case 1105: // 수업확인 눌렀을때
						classcheck.timetable.refreshData();
						
						break;
						
					case 1005: // 포인트 승인하기
						if (p.getResult() > 0) {
							System.out.println("확인");
							home.point_new.clearTableData();
							p.setCmd(1004); 
							out.writeObject(p);
							out.flush();
						}
						break;
					
					case 1006:
						classEdit.classEdit1.TeacherName();
						
						break;
						
					case 1007:
						if(p.getResult()>0) {
							JOptionPane.showMessageDialog(null, "수업을 생성했습니다.");
						}
						p.setCmd(1002);
						out.writeObject(p);
						out.flush();
						break;
						
					case 1106: // 수업 한개 클릭
						if(p.getResult()==1) {
							classcheck.classCheck.setLabel();
							vo.setClass_num(list.get(0).getClass_num());
						}else {
							classcheck.classCheck.setLabel2();
						}
						p.setVo(vo);
						p.setCmd(1107); // 예약회원 리스트 가지러
						out.writeObject(p);
						out.flush();
						cardlayout.show(pg1, "classcheck");
						break;
					case 1107: // 수업예약명단
						classcheck.classCheck2.refresh(list);
						p.setCmd(1105);
						out.writeObject(p);
						out.flush();
						break;
						
					case 1108: // 삭제한후 리프레쉬 하기
						vo.setClass_type(classcheck.classCheck.label11.getText());
						vo.setClass_date(classcheck.classCheck.label18.getText());
						vo.setClass_time(classcheck.classCheck.label17.getText());
						p.setVo(vo);
						p.setCmd(1106);
						out.writeObject(p);
						out.flush();
						break;
						
					case 1109: // 수업삭제후 다시 띄우기
						JOptionPane.showMessageDialog(null, "수업이 삭제되었습니다.");
						// 수업목록 다시 띄우기
						classcheck.classCheck.resetLabel();
						out.writeObject(p);
						out.flush();
						break;
					
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
					case 1206:
						if (p.getResult() == 1) { // 비밀번호 확인완
							p.setCmd(1207); // 포인트승인화면 가기
							out.writeObject(p);
							out.flush();
						} else {
							JOptionPane.showMessageDialog(checkagain, "비밀번호가 틀렸습니다.");
						}
						break;
					case 1207: // 포인트 승인페이지이동
						point_Mgmt.sub.refresh(); // 포인트불러오기
						break;

					case 1208: // 포인트 승인하기
						if (p.getResult() > 0) {
							p.setCmd(1207); // 포인트페이지 갱신
							out.writeObject(p);
							out.flush();
						}
						break;

					case 1301: // 강사목록 불러오기
						if(p.getResult()==0) {
							coMg1.coTable1.refresh();
						}else {
							coMg2.coTable2.refresh();
							
						}
						break;
					case 1302: // 한명 검색하기
						if(p.getResult()==0) {
							
							coMg1.coTable1.search();
						}else {
							coMg2.coTable2.search();
						}
						break;
					case 1303: // 강사 세부정보 보기
						coMg2.coTable2.refresh1();
						p.setCmd(1302);
						p.setResult(1);
						out.writeObject(p);
						out.flush();
						break;
					case 1304: // 회원 세부정보 => 수업예약내역
						coMg2.coTable2.refresh2();// 예약내역
						break;
					case 1308: //강사등록 클릭시 리프레쉬 기능
						coMg3.coMgmt4.refresh(); // 폼 비우기
					case 1309: //강사수정하기 정보 불러오기
						coMg3.coMgmt4.fix(); // 수정내용
						break;
					case 1310: //삭제완료후 강사목록다시부르기
						p.setCmd(1301);
						p.setResult(1);
						out.writeObject(p);
						out.flush();
						break;
					case 1317: // 강사 수정후 재등록
						p.setCmd(1301);
						out.writeObject(p);
						out.flush();
						break;
					case 1318: // 강사 등록하기
						try {
							if(p.getResult() == 1) {
								p.setCmd(1301);
								out.writeObject(p);
								out.flush();
							} else {
								JOptionPane.showMessageDialog(null, "등록 실패!!", "등록 실패", JOptionPane.ERROR_MESSAGE);
							}
						} catch(Exception e) {
							e.printStackTrace();
						}
						
						break;
					case 1320: // 공지사항 등록하기
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