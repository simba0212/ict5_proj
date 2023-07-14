package com.ict5.client.panel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ict5.client.Client_main;
import com.ict5.db.Protocol;
import com.ict5.db.VO;

public class Mypoint extends JPanel {
    Client_main main;
    CardLayout cardlayout;
    JLabel point;
    JPanel plist;
    JPanel jp1;
    JPanel mp;
    List<VO> list;
    public Mypoint(Client_main main) {
        this.main = main;
        this.cardlayout = main.cardlayout;
        jp1 = new JPanel();
        mp = new JPanel();

        Font font = new Font("돋움", Font.PLAIN, 30);

        jp1.setLayout(new BorderLayout());
        mp.setLayout(new BorderLayout());
        JPanel mp1, mp2;
        mp1 = new JPanel();
        mp2 = new JPanel();
        int point_num = 5000; // 데이터베이스에서 포인트 가져오기
         point = new JLabel(); // 포인트 가져오기
        JLabel mypoint = new JLabel("내 포인트"); // 포인트 가져오기
        point.setText(String.valueOf(point_num)); // 데이터 형식 String으로 변환
        point.setFont(font);
        mypoint.setFont(font);
        mp1.add(mypoint);
        mp2.add(point);

        mp.add(mp1, BorderLayout.NORTH);
        mp.add(mp2, BorderLayout.EAST);
        mp.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // 아래쪽 테두리 설정

        plist = new JPanel();
        JLabel jl1 = new JLabel("포인트 이력");
        Font font2 = new Font("돋움", Font.PLAIN, 20);
        jl1.setFont(font2);
        plist.add(jl1);
        plist.setLayout(new GridLayout(0, 1));
//        int ii = 4; // 제공될 알림의 수 지정하는 변수
//        JPanel[] panels = new JPanel[ii];
//        for (int i = 0; i < panels.length; i++) {
//            panels[i] = createPanel(); // 패널 생성 및 배열에 할당
//            plist.add(panels[i]); // 프레임에 패널 추가
//        }
//        jp1.add(mp, BorderLayout.CENTER);
//        jp1.add(plist, BorderLayout.SOUTH);
//        add(jp1);

    }

    private JPanel createPanel(List<VO> list,int i) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(400, 100));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.setPreferredSize(new Dimension(400, 30));
        JPanel panel2 = new JPanel(new BorderLayout());
        JPanel panel3 = new JPanel(new BorderLayout());
        panel1.setBackground(Color.WHITE);
        panel2.setBackground(Color.WHITE);
        panel3.setBackground(Color.WHITE);

        JLabel label = new JLabel(list.get(i).getPoint_money());
        Font labelFont = label.getFont();
        label.setFont(labelFont.deriveFont(20f));
        JButton button = new JButton("입금완료");
        JLabel label2 = new JLabel("무통장입금");
        String pointlabel = list.get(i).getPoint_signup_date().substring(0,16); 
        JLabel label3 = new JLabel(pointlabel, JLabel.RIGHT);
        if(list.get(i).getPoint_approve()!=null) {
        	button.setText("승인됨");
        	button.setEnabled(false);
        }
        else if(list.get(i).getPoint_charge_date()!=null) {
        	 button.setText("승인대기");
        	 button.setEnabled(false);
        }
        
        panel1.add(label, BorderLayout.WEST);
        panel1.add(button, BorderLayout.EAST);
        panel2.add(label2, BorderLayout.WEST);
        panel3.add(label3, BorderLayout.EAST);

        panel.add(panel1, BorderLayout.NORTH);
        panel.add(panel2, BorderLayout.CENTER);
        panel.add(panel3, BorderLayout.SOUTH);

        
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	button.setText("승인대기");
            	button.setEnabled(false);
            	try {
    				Protocol p = new Protocol();
    				VO vo = main.vo;
    				vo.setCharge_num(list.get(i).getCharge_num());
    				p.setCmd(2703);
    				p.setVo(vo);
    				main.out.writeObject(p);
    				main.out.flush();
    			} catch (IOException e1) {
    				
    			}

            }
        });

        return panel;
    }

    public void refresh() {
    	point.setText(main.userpoint);
    	list=main.list;
         JPanel[] panels = new JPanel[list.size()];
         for (int i = 0; i < list.size(); i++) {
             panels[i] = createPanel(list,i); // 패널 생성 및 배열에 할당
             plist.add(panels[i]); // 프레임에 패널 추가
         }
         jp1.add(mp, BorderLayout.CENTER);
         jp1.add(plist, BorderLayout.SOUTH);
         add(jp1);
    }
}
