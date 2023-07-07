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
				
						vo = p.getVo(); //set으로 보낸거 get으로 받아옴
						int result = DAO.getInsert(vo); //받아온 vo를 dao를 통해서 디비에 넣어주기
					
						//성공 실패를 알려줘야 하니까
						if(result>0) { //db에서 추가되면 1열 추가니까 데이터가 추가되면 무조건 1이상
							System.out.println("성공");
						
						}else {
							System.out.print("실패");
						}
						out.writeObject(p);
						out.flush();
						break;
					case 1001: { // 관리자 로그인

					}
						break;
					}
				}
			} catch (Exception e) {
			}
		}

	}
}
