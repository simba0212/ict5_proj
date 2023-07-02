package com.ict5.client;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.ict5.db.VO;

public class Client_main extends JFrame implements Runnable {
	Socket s;
	ObjectOutputStream out;
	ObjectInputStream in;

	Client_Login login;
	Client_CreateId createId;
	Client_Home home;
	TabPage tab;
	CreateId_2 createId2;
	Notice noti;
	Mypage myPg;
	Mypoint myPo;
	PassChange pwChan;
	ChargeP2 chargeP2;
	ChargeP3 chargeP3;
	Client_ChargeP chargeP;
	VO vo;

	CardLayout cardlayout;
	JPanel pg1;
	String membername;

	public Client_main() {
		super("거구로 거듭나자 거구장센터");
		connected();
		cardlayout = new CardLayout();
		pg1 = new JPanel(cardlayout);
		vo = new VO();

//		클래스명 변수명 = new 클래스명(this);  이 클래스들은 각각의 페이지(카드)를 의미합니다.
		login = new Client_Login(this);
		createId = new Client_CreateId(this);
		home = new Client_Home(this);
		tab = new TabPage(this);
		createId2 = new CreateId_2(this);
		chargeP = new Client_ChargeP(this);
		chargeP2 = new ChargeP2(this);
		chargeP3 = new ChargeP3(this);
		noti = new Notice(this);
		myPg = new Mypage(this);
		myPo = new Mypoint(this);
		pwChan = new PassChange(this);

//		클래스명 변수명 = new 클래스명(this);
//		클래스명 변수명 = new 클래스명(this);
//		클래스명 변수명 = new 클래스명(this);

//		pg1.add("페이지명",객체이름);	각 페이지들의 이름을 지정해주고, 각 객체들로 해당 페이지로 이동합니다.
		pg1.add("login", login); // 로그인 페이지
		pg1.add("createId", createId); // 회원가입
		pg1.add("home", home); // 메인페이지 - 가장가까운수업 클릭 안되어있음
		pg1.add("chargeP", chargeP); // 포인트충전
		pg1.add("tab", tab); // 탭페이지
		pg1.add("createId2", createId2); // 회원가입완료
		pg1.add("chargeP2", chargeP2); // 포인트충전2p
		pg1.add("chargeP3", chargeP3); // 입금완료
		pg1.add("notice", noti); // 공지사항
		pg1.add("myPg", myPg); // 마이페이지 -작업중
		pg1.add("myPo", myPo); // 포인트이력
		pg1.add("pwChan", pwChan); // 비밀번호 변경

		cardlayout.show(pg1, "home");
//		pg1.add("페이지명",객체이름);

		add(pg1);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 800);
		setResizable(false);
		setLocationRelativeTo(null);

		// 여기부터 서버연동 및 기능구현
		// 접속
		

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
					vo = p.getVo();
					
					switch (p.getCmd()) {
					case 0:
						break esc;
					case 1:
						if (p.getResult() == 1) {
							cardlayout.show(pg1, "home");
							home.home.refresh();
							home.usertop.refresh();
							
						} else {
							System.out.println("실패");
						}
						break;
					}
				}
			} catch (Exception e) {
			}
		}
		closed();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
					Client_main frame = new Client_main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
