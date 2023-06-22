package com.ict5.client;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Huge_main extends JFrame {
	CardLayout cardlayout;
	JPanel pg1;

	public Huge_main() {
		super("거구로 거듭나자 거구장센터");
		cardlayout = new CardLayout();
		pg1 = new JPanel(cardlayout);

		CreateAccount ca = new CreateAccount(this);

		Notice nt= new Notice(this);
		Myp myp = new Myp(this);
		WelCome wc = new WelCome(this);
		Complete cp = new Complete(this);

//		클래스명 변수명 = new 클래스명(this);  이 클래스들은 각각의 페이지(카드)를 의미합니다.
//		클래스명 변수명 = new 클래스명(this);
//		클래스명 변수명 = new 클래스명(this);
//		클래스명 변수명 = new 클래스명(this);


		pg1.add("nt", nt);
//		pg1.add("페이지명",객체이름);	각 페이지들의 이름을 지정해주고, 각 객체들로 해당 페이지로 이동합니다.

		pg1.add("ca",ca);
		pg1.add("myp",myp);
		pg1.add("wc",wc);
		pg1.add("cp",cp);

//		pg1.add("페이지명",객체이름);
//		pg1.add("페이지명",객체이름);

		cardlayout.next(pg1);

		
		cardlayout.show(pg1, "cp");

		add(pg1);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 800);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
			new Huge_main();
		} catch (Exception e) {
		}
	}
}
