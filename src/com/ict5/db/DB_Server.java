package com.ict5.db;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class DB_Server implements Runnable{
	ServerSocket ss = null;
	Socket s = null;
	public DB_Server() {
		try {
			ss = new ServerSocket(7780);
			System.out.println("서버 대기 중 ...");
			new Thread(this).start();
		} catch (IOException e) {
			
		}
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				s = ss.accept();
				CP_Client cc = new CP_Client(s, this);
				System.out.println("누군가 접속");
				cc.start();
			} catch (Exception e) {
				
			}
		}
	}
	public static void main(String[] args) {
		new DB_Server();
	}
}
