package com.ict5.db;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

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
							p.setResult(1);	
						} else {
							p.setResult(0);
						}
						out.writeObject(p);
						out.flush();
						break;

					case 1002:
						list = DAO.getToday();
					    p.setList(list);					
					     if (p.getList() != null) {
					        p.setResult(1); 
					    } else {
					    	p.setResult(0);
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
						
					case 1302: // 이름으로 강사검색
						list = DAO.searchTeacherName(vo);
						p.setList(list);
						out.writeObject(p);
						out.flush();
						break;	
						
					case 1303: // 강사상세정보 보기
						vo = DAO.getTeacherOne(vo);
						p.setVo(vo);
						out.writeObject(p);
						out.flush();
						break;	
						
					case 1304: // 강사상세정보 =>강사수업내용
						list = DAO.getTeacherClass(vo);
						p.setList(list);
						out.writeObject(p);
						out.flush();
						break;
					case 1308: // 강사 등록화면 세팅
						p.setVo(new VO());
						out.writeObject(p);
						out.flush();
						break;
					case 1309: // 강사 수정하기
						vo = DAO.getTeacherOne(vo);
						p.setVo(vo);
						out.writeObject(p);
						out.flush();
						break;
					case 1310: // 강사 삭제
						int deleteTeacherResult = DAO.getDeleteTeacher(vo);
						p.setResult(deleteTeacherResult);
						out.writeObject(p);
						out.flush();
						break;
						
					case 1317: // 수정 후 재등록
						DAO.getTeacherEdit(vo);
						p.setVo(vo);
						out.writeObject(p);
						out.flush();
						break;
						
					case 1318: //강사 등록
						int teacherInsertResult = DAO.getTeacherInsert(vo);
						p.setResult(teacherInsertResult);
						out.writeObject(p);
						out.flush();
						break;

					case 1320:
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
							vo.setNotice_text(DAO.getNotice());
							p.setVo(vo); 
							p.setResult(1);
						} else {
						}
						System.out.print("카피 vo.num : ");
						System.out.println(p.vo.getMember_num());
						out.writeObject(p);
						out.flush();
						break;

				

					case 2101: // 가입
						vo = p.getVo();
						if (vo != null) {
							DAO.setInsertJoinFields(vo);
						} else {
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