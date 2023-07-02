package com.ict5.client.panel;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.ict5.client.Protocol;
import com.ict5.db.DAO;
import com.ict5.db.DB_Server;
import com.ict5.db.VO;

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
						vo = p.getVo();
						vo = DAO.getLoginChk(vo);
						
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
//					case 2 :
//						vo = p.getVo();
//						int result = DAO.getInsert(vo);
//						if(result>0) {
//							Protocol p2 = new Protocol();
//							List<VO> list2 = DAO.getList();
//							p2.setCmd(1);
//							p2.setList(list2);
//							out.writeObject(p2);
//							out.flush();
//						}
//						break;
					}
				}
			} catch (Exception e) {
			}
		}

	}
}
