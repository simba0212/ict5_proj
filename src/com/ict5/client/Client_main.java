package com.ict5.client;

import java.awt.CardLayout;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Client_main extends JFrame {
	CardLayout cardlayout;
	JPanel pg1;
	Client_ChargeP chargeP;
	Client_ChargeP2 chargeP2;

	public Client_main() {
		super("거구로 거듭나자 거구장센터");
		cardlayout = new CardLayout();
		pg1 = new JPanel(cardlayout);

//		클래스명 변수명 = new 클래스명(this);  이 클래스들은 각각의 페이지(카드)를 의미합니다.
		Client_Login login = new Client_Login(this);
		Client_CreateId createId = new Client_CreateId(this);
		Client_Home home = new Client_Home(this); 
		chargeP =new Client_ChargeP(this);
		TabPage tab = new TabPage(this);
		CreateId_2 createId2 = new CreateId_2(this);
		chargeP2 = new Client_ChargeP2(this);
		Client_ChargeP3 chargeP3 = new Client_ChargeP3(this);
		Notice noti = new Notice(this);
		Mypage myPg = new Mypage(this);
		Client_MyPoint myPo = new Client_MyPoint(this);
		Client_PassChange pwChan = new Client_PassChange(this);
		
//		클래스명 변수명 = new 클래스명(this);
//		클래스명 변수명 = new 클래스명(this);
//		클래스명 변수명 = new 클래스명(this);

//		pg1.add("페이지명",객체이름);	각 페이지들의 이름을 지정해주고, 각 객체들로 해당 페이지로 이동합니다.
		pg1.add("login",login); // 로그인 페이지
		pg1.add("createId",createId);	// 회원가입
		pg1.add("home",home); // 메인페이지 - 가장가까운수업 클릭 안되어있음
		pg1.add("chargeP",chargeP); // 포인트충전
		pg1.add("tab",tab); // 탭페이지
		pg1.add("createId2",createId2);	// 회원가입완료
		pg1.add("chargeP2",chargeP2); // 포인트충전2p
		pg1.add("chargeP3",chargeP3); // 입금완료
		pg1.add("notice", noti);	// 공지사항
		pg1.add("myPg",myPg);	// 마이페이지
		pg1.add("myPo",myPo);	// 포인트이력
		pg1.add("pwChan",pwChan); // 비밀번호 변경
		 
		 

		cardlayout.show(pg1, "login"); 
//		pg1.add("페이지명",객체이름);
		


		add(pg1);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 800);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
			new Client_main();
		} catch (Exception e) {
		}
	}
}
