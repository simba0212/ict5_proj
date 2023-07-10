package com.ict5.db;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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

					case 1301: // 강사목록 불러오기
						list = DAO.getCoachList();
						p.setList(list);
						out.writeObject(p);
						out.flush();
						break;
						
					case 2001: // 클라이언트 로그인
						vo = DAO.getLoginChk(vo); // DB를 다녀온 vo를 업데이트 해주는것
						if (vo != null) {
							// 로그인 성공
							System.out.println("로그인성공!");
							vo.setNotice_text(DAO.getNotice());
							p.setVo(vo); // 보낼 프로토콜p의 vo에 현재 vo정보 저장
							p.setResult(1);
						} else {
							System.out.println("로그인실패");
						}
						System.out.print("카피 vo.num : ");
						System.out.println(p.vo.getMember_num());
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
						int result = DAO.getInsert(vo);
						System.out.println("123");
						out.writeObject(p);
						out.flush();
						break;
						
					case 2101: // 가입
						vo = new VO();
						vo = p.getVo(); // 가입창의 정보를 가져옴
						if (vo != null) {
							System.out.println("cp옴");
							DAO.setInsertJoinFields(vo); // 가입 정보를 DB에 삽입
							System.out.println("정보가져옴");
						} else {
							System.out.println("정보못가져옴");
						}
						p.setVo(vo); // 업데이트된 VO를 프로토콜 객체에 설정
						p.setResult(1);
						System.out.println("정보넘김");
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
