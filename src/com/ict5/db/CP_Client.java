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
					List<VO> list = new ArrayList<>();
					
					switch (p.getCmd()) {
					case 0:
						out.writeObject(p);
						out.flush();
						break;
					case 1: // 로그인
						vo = p.getVo(); // id, pw를 가지고 왔어요 어디서? 로그인창에있는 jtf에 있는 text를 get으로 가져온상태.
						vo = DAO.getLoginChk(vo); // DB를 다녀온 vo를 업데이트 해주는것

						if (vo != null) {
							// 로그인 성공
							System.out.println("로그인성공!");
							vo.setNotice_content(DAO.getNotice());
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

						
					case 1318 :
						vo = new VO();
						vo = p.getVo(); //set으로 보낸거 get으로 받아옴
						if (vo != null) {
							System.out.println("cp옴");
						 DAO.getInsert(vo); //받아온 vo를 dao를 통해서 디비에 넣어주기
							System.out.println("dao통해서 디비에 넣어줌");
						}else {
							System.out.print("cp실패");
						}
						p.setVo(vo);
						out.writeObject(p);
						out.flush();
						break;


					case 1301:  // 강사목록 불러오기
						list = new ArrayList<>();
						list = DAO.getCoachList();
						p.setList(list);
						out.writeObject(p);
						out.flush();
						

					case 1320:
						vo = new VO();
						vo = p.getVo(); 
						DAO.setNotice(vo); 
						p.setVo(vo);
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
