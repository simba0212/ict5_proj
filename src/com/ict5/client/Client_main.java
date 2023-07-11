package com.ict5.client;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.ict5.client.panel.ChargeP2;
import com.ict5.client.panel.ChargeP3;
import com.ict5.client.panel.CreateId_2;
import com.ict5.client.panel.Mypage;
import com.ict5.client.panel.Mypoint;
import com.ict5.client.panel.Notice;
import com.ict5.client.panel.PassChange;
import com.ict5.client.panel.TabPage;
import com.ict5.db.DAO;
import com.ict5.db.Protocol;
import com.ict5.db.VO;

public class Client_main extends JFrame implements Runnable {
	public Socket s;
	public ObjectOutputStream out;
	public ObjectInputStream in;
	public VO vo;
	public List<VO> list;
	
	public Client_Login login;
	public Client_CreateId createId;
	public Client_Home home;
	public TabPage tab;
	public CreateId_2 createId2;
	public Notice noti;
	public Mypage myPg;
	public Mypoint myPo;
	public PassChange pwChan;
	public ChargeP2 cp2;
	public ChargeP3 cp3;
	public Client_ChargeP chargeP;
	public Client_ChargeP2 chargeP2;
	public Client_ChargeP3 chargeP3;
	public CardLayout cardlayout;
	public JPanel pg1;
	
	public String usernum;

	public Client_main() {
		super("거구로 거듭나자 거구장센터");
		connected();
		cardlayout = new CardLayout();
		pg1 = new JPanel(cardlayout);
		vo = new VO();
		list = new ArrayList<>();

//		클래스명 변수명 = new 클래스명(this);  이 클래스들은 각각의 페이지(카드)를 의미합니다.
		login = new Client_Login(this);
		createId = new Client_CreateId(this);
		home = new Client_Home(this);
		tab = new TabPage(this);
		createId2 = new CreateId_2(this);
		chargeP = new Client_ChargeP(this);
		chargeP2 = new Client_ChargeP2(this);
		chargeP3 = new Client_ChargeP3(this);
		cp2 = new ChargeP2(this);
		cp3 = new ChargeP3(this);
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

		cardlayout.show(pg1, "login");
//		pg1.add("페이지명",객체이름);
		
		add(pg1);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 800);
		setResizable(false);
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
			System.out.println(e);
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
					list = null;
					
					switch (p.getCmd()) {
					case 0:
						break esc;
					case 2001:
						if (p.getResult() == 1) {
							cardlayout.show(pg1, "home");
							refreshAll();
							usernum=vo.getMember_num();
							System.out.println("메인 usernum"+usernum);
						} else {
							System.out.println("실패");
						}
						break;
						
//					case 2301:
//						 list = p.getList();
//						// 초기화 메서드
//						 tab.schedule.sb.refresh();
//						break;
					case 2302: // 스케쥴을 클릭해서 해당 날짜 가져오는 프로토콜
						 list = p.getList();
						 tab.schedule.sb.refresh();
						break;
					case 2303:
						// 예약완료됨을 알리기 위한 메소드를 스schedule_bottom에서 작성하고 실행
						break;
					case 2304: // Reservation의 달력을 클릭해서 해당 날짜에 예약된 수업을 가져오는 프로토콜
						 list = p.getList();
						 tab.reservation.rb.refresh();
						break;
						
					case 2101:
						if (p.getResult() == 1) {
							System.out.println("회원가입 완료");
						} else {
							System.out.println("실패");
						}
						break;
					case 2102:
						if (p.getResult() == 1) {
							cardlayout.show(pg1, "chargeP");
							chargeP.usertop.refresh();
							chargeP.cp.refresh();
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
	
	
	
	public void refreshAll() {
		home.usertop.refresh();
		tab.usertop.refresh();
		home.home.refresh();
		tab.noti.refresh();
	}
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
					Client_main frame = new Client_main();
					frame.setVisible(true);
					frame.requestFocusInWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
