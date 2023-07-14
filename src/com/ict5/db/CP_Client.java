package com.ict5.db;

import java.io.ObjectInputStream;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.ict5.admin.Admin_main;
import com.ict5.admin.panel.TimeTable;

import java.util.ArrayList;
import java.util.List;


public class CP_Client extends Thread {
	Socket s;
	DB_Server server;
	ObjectInputStream in;
	ObjectOutputStream out;
	
	

	public CP_Client(Socket s, DB_Server server) {
		this.s = s;
		this.server = server;
		try {
			in = new ObjectInputStream(s.getInputStream());
			out = new ObjectOutputStream(s.getOutputStream());
		} catch (Exception e) {

		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				Object obj = in.readObject();
				if (obj != null) {
					Protocol p = (Protocol) obj;
					VO vo = new VO();
					vo = p.getVo();
					List<VO> list = new ArrayList<>();
					list = p.getList();

					switch (p.getCmd()) {
					
					case 0:
						out.writeObject(p);
						out.flush();
						break;

					case 1001: // 관리자 로그인
						vo = p.getVo();
						vo = DAO.getLoginChk_Admin(vo);
						p.setVo(vo);
						if (vo != null) {
							// 로그인 성공
							p.setResult(1);
							System.out.println("로그인성공!");
							
						} else {
							p.setResult(0);
							System.out.println("로그인실패");
							
						}
						out.writeObject(p);
						out.flush();
						break;

					case 1002:
						list = DAO.getToday();
					    p.setList(list);
					    
					     if (p.getList() != null) {
					        p.setResult(1);
					        System.out.println("테이블 성공");
					      
					    } else {
					    	p.setResult(0);
					        System.out.println("테이블 실패");
					    }
					    out.writeObject(p);
					    out.flush();
					    
					    break;
					    
					case 1003:
						list = DAO.getNewMember();
						p.setList(list);
						
						out.writeObject(p);
						out.flush();
						    
						break;
						
					case 1004:
						list = DAO.getPointApprove();
						p.setList(list);
						
						out.writeObject(p);
						out.flush();
						
						break;

					case 1201: // 회원목록 불러오기
						list = DAO.getMemberList();
						p.setList(list);
						out.writeObject(p);
						out.flush();
						break;
					case 1202: // 이름으로 회원검색/중복이름가능
						list = DAO.getMemberSearch(vo);
						p.setList(list);
						out.writeObject(p);
						out.flush();
						break;

					case 1203: // 회원상세정보 보기
						vo = DAO.getMemberOne(vo);
						p.setVo(vo);
						out.writeObject(p);
						out.flush();
						break;
					case 1204: // 회원상세정보 =>수업예약내역
						list = DAO.getBookedClass(vo);
						p.setList(list);
						out.writeObject(p);
						out.flush();
						break;

					case 1205: // 회원상세정보 =>포인트 이력
						list = DAO.getPointList(vo);
						p.setList(list);
						out.writeObject(p);
						out.flush();
						break;
						
//					case 1206: // 포인트관리 가기전 PW체크
//						list = DAO.getPointList(vo);
//						p.setList(list);
//						out.writeObject(p);
//						out.flush();
//						break;
					case 1207:
						list = DAO.getApproveList();
						p.setList(list);
						out.writeObject(p);
						out.flush();

					case 1301: // 강사목록 불러오기
						list = DAO.getCoachList();
						p.setList(list);
						out.writeObject(p);
						out.flush();
						break;

					case 1318:
						vo = p.getVo();
						if (vo != null) {
							System.out.println("cp옴");
							DAO.getTeacherInsert(vo);
							System.out.println("dao통해서 디비에 넣어줌");
						} else {
							System.out.print("cp실패");
						}
						p.setVo(vo);
						out.writeObject(p);
						out.flush();
						break;

					case 1320:
						vo = p.getVo();
						DAO.setNotice(vo);
						p.setVo(vo);
						p.setResult(1);
						out.writeObject(p);
						out.flush();
						break;

					case 2001: // 클라이언트 로그인
						vo = DAO.getLoginChk(vo); // DB를 다녀온 vo를 업데이트 해주는것
						if (vo != null) {
							// 로그인 성공
							System.out.println("로그인성공!");
							vo.setNotice_text(DAO.getNotice());// 로그인하면 알림 생성
							p.setVo(vo); // 보낼 프로토콜p의 vo에 현재 vo정보 저장
							p.setResult(1);
						} else {
							System.out.println("로그인실패");
						}
						out.writeObject(p);
						out.flush();
						break;

				

					case 2101: // 가입
						vo = p.getVo();
						if (vo != null) {

							DAO.setInsertJoinFields(vo);
							//System.out.println("정보가져옴");

						} else {
							System.out.println("정보못가져옴");
						}
						p.setVo(vo);
						p.setResult(1);
						out.writeObject(p);
						out.flush();
						break;		
						
					case 2102: // 포인트 구매
						vo = p.getVo();
						if (vo != null) {
							//System.out.println("못넘어가는중");
							DAO.setApplyPoints(vo);
							System.out.println("정보가져옴");
						} else {
							System.out.println("정보 못 가져옴");
						}
						p.setVo(vo);
						p.setResult(1);
						out.writeObject(p);
						out.flush();
						break;
						
					case 2103: // 비번 번경
						//vo = new VO();
						vo = p.getVo();
						if (vo != null) {
							DAO.setMemberPw(vo);
							//System.out.println("정보가져옴");
						} else {
							System.out.println("정보 못 가져옴");
						}
						p.setVo(vo);
						p.setResult(1);
						out.writeObject(p);
						out.flush();
						break;

					case 2301:
						list = DAO.t_bookclass(vo);
						p.setList(list);
						out.writeObject(p);
						out.flush();
						break;

					case 2302:
						list = DAO.sel_date_class(vo);
						p.setList(list);
						out.writeObject(p);
						out.flush();
						break;
					case 2303:
						int result = DAO.getInsert_book(vo);
						out.writeObject(p);
						out.flush();
						break;
					case 2304:
						list = DAO.sel_book_class(vo);
						p.setList(list);
						out.writeObject(p);
						out.flush();
						break;
					case 2305:
						list = DAO.sel_class_noice(vo);
						p.setList(list);
						out.writeObject(p);
						out.flush();
						break;	
					case 2306:
						list = DAO.sel_already_book(vo);
						p.setList(list);
						out.writeObject(p);
						out.flush();
						break;		
					case 2307:
						vo = DAO.mostclose(vo);
						p.setVo(vo);
						out.writeObject(p);
						out.flush();
						break;		
					case 2308:
						DAO.deletebook(vo);
						p.setVo(vo);
						out.writeObject(p);
						out.flush();
						break;			
					case 2501:	// 목표 작성 후 member_goal 칼럼 업데이트하기 위한 구문
						int result2 = DAO.update_goal(vo);
						out.writeObject(p);
						out.flush();
						break;	
						
					case 2701:
					
						int i = DAO.book_point(vo);
						p.setVo(vo);
						out.writeObject(p);
						out.flush();
						break;		
					case 2702:
						int i2 = DAO.book_can_point(vo);
						p.setVo(vo);
						out.writeObject(p);
						out.flush();
						break;	
						
						
					case 2901:
						result = DAO.getInsert_attenedent(vo);
						
						out.writeObject(p);
						out.flush();
						break;



						

					}

				}
			} catch (Exception e) {

			}
		}

	}

}