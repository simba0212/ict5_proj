package com.ict5.db;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import com.ict5.admin.Admin_main;
import com.ict5.admin.panel.TimeTable;


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
						vo = DAO.getLoginChk_Admin(vo);
						p.setVo(vo);
						if (vo != null) {
							// 로그인 성공
							p.setResult(1);
						} else {
							p.setResult(0);
						}
						out.writeObject(p);
						out.flush();
						break;

					case 1002: // 홈페이지로 이동
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
						if (p.getList() != null) {
							p.setResult(1);
						}else {
							p.setResult(0);
						}
						out.writeObject(p);
						out.flush();
						break;
              
          case 1005: // 포인트 승인하기
						p.setResult(DAO.setApprove(vo));
						out.writeObject(p);
						out.flush();
						break;
						
					case 1105: // 수업확인 눌렀을때
						list = DAO.getToday(); // 일단 테이블 가져오기
						p.setList(list);
						out.writeObject(p);
						out.flush();
						break;
						
					case 1106: // 수업확인에서 수업한개 클릭
						list = DAO.getOneClass(vo);
						if(list.isEmpty()) { // 예약아무도없음
							vo = DAO.getOneClass_2(vo);
							p.setResult(0);
							p.setVo(vo);
							out.writeObject(p);
							out.flush();
						}else {				// 예약있음
							p.setList(list);
							p.setResult(1);
							out.writeObject(p);
							out.flush();
						}
						break;
					case 1107: // 수업확인 우측하단 테이블
						list = DAO.getBookedMember(vo);
						p.setList(list);
						out.writeObject(p);
						out.flush();
						break;
					case 1108: // 예약회원 삭제
						if(p.getResult()==0) { // 한명 보냈을때
							int res = DAO.delMember(vo); // book에서 삭제
							res = DAO.refundPoint(vo); // 
						}else if(p.getResult()==1){ // 여러명 보냈을때
							for (VO k : list) {
								int res = DAO.delMember(k);
								res = DAO.refundPoint(k);
							}
						}
						out.writeObject(p);
						out.flush();
						break;
					case 1109:
						DAO.deleteClass(vo);
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

					case 1206: // 포인트관리 가기전 PW체크
						vo = DAO.getLoginChk_Admin(vo);
						if(vo!=null) {
							p.setResult(1);
						}else {
							p.setResult(0);
						}
						out.writeObject(p);
						out.flush();
						break;
					case 1207: // 포인트승인화면 가기
						list = DAO.getApproveList();
						p.setList(list);
						out.writeObject(p);
						out.flush();
						break;
						
					case 1208: // 포인트 승인하기
						p.setResult(DAO.setApprove(vo));
						out.writeObject(p);
						out.flush();
						break;
						
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
						vo = p.getVo();
						if (vo != null) {
							DAO.setMemberPw(vo);
							
						} else {
							System.out.println("정보 못 가져옴");
						}
						p.setVo(vo);
						p.setResult(1);
						out.writeObject(p);
						out.flush();
						break;
						
					case 2104: // 마이포인트
						list = DAO.getAllApprovePoints(vo);
						p.setList(list);
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
					case 2501:	// 목표 작성 후 member_goal 칼럼 업데이트하기 위한 구문
						int result2 = DAO.update_goal(vo);
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