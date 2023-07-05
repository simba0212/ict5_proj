package com.ict5.db;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
					switch (p.getCmd()) {
					case 0:
						out.writeObject(p);
						out.flush();
						break;
					case 1: // 로그인
						VO vo = new VO();
						vo = p.getVo(); // id, pw를 가지고 왔어요 어디서? 로그인창에있는 jtf에 있는 text를 get으로 가져온상태.
						vo = DAO.getLoginChk(vo); // DB를 다녀온 vo를 업데이트 해주는것

						p.setVo(vo); // 보낼 프로토콜p의 vo에 현재 vo정보 저장
						if (vo != null) {
							// 로그인 성공
							System.out.println("로그인성공!");
							p.setResult(1);
						} else {
							System.out.println("로그인실패");
						}
						System.out.print("카피 vo.num : ");
						System.out.println(p.vo.getMember_num());
						out.writeObject(p);
						out.flush();
						break;
					case 1001: // 관리자 로그인

						break;
					case 2301:
						List<VO> list = null;
						vo = p.getVo();
						list = DAO.t_bookclass(vo);
						p.setList(list);
						out.writeObject(p);
						out.flush();
						

						break;
					case 2302:
						System.out.println("카피클라0");
						list = null;
						vo = p.getVo();
						System.out.println("카피클라1");
						list = DAO.sel_date_class(vo);
						
						System.out.println(list.get(0).getClass_room()+"카피클라3");
						p.setList(list);
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
