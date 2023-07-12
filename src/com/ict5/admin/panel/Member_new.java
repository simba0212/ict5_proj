package com.ict5.admin.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.ict5.admin.Admin_main;
import com.ict5.db.Protocol;
import com.ict5.db.VO;

public class Member_new extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    Admin_main main;
    String name, phone, gen, birth, date, addr;
    Object[][] data;
    String[] columnNames;

    public Member_new(Admin_main main) {
        this.main = main;
        
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("신규 회원 등록");
        setBackground(Color.lightGray);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
        Font labelFont = titleLabel.getFont().deriveFont(Font.BOLD,20f);
        titleLabel.setFont(labelFont);
        add(titleLabel, BorderLayout.NORTH);

        columnNames = new String[] { "이름", "전화번호", "성별", "생년월일", "등록날짜", "주소" };
        data = new Object[1][6];
        
        model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
        celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
        
        table = new JTable(model);
        table.setRowHeight(30);
        table.getColumn("이름").setCellRenderer(celAlignCenter);
        
        TableColumn phoneColumn = table.getColumn("전화번호");
        phoneColumn.setCellRenderer(celAlignCenter);
        phoneColumn.setPreferredWidth(100);
        
        table.getColumn("성별").setCellRenderer(celAlignCenter);
        
        TableColumn phoneColumn2 = table.getColumn("생년월일");
        phoneColumn2.setCellRenderer(celAlignCenter);
        phoneColumn2.setPreferredWidth(100);
        
        TableColumn phoneColumn3 = table.getColumn("등록날짜");
        phoneColumn3.setCellRenderer(celAlignCenter);
        phoneColumn3.setPreferredWidth(100);
        
        table.getColumn("주소").setCellRenderer(celAlignCenter);
        table.getColumn("전화번호").setPreferredWidth(100);
        table.getColumn("주소").setPreferredWidth(200);
        table.getTableHeader().setReorderingAllowed(false);

        JScrollPane jsp = new JScrollPane(table);
        jsp.setPreferredSize(new Dimension(600, 300));
        add(jsp, BorderLayout.SOUTH);
    }
    
    public void Member() {
        List<VO> member = main.list;
        int rowCount = member.size();
        
        model.setRowCount(rowCount); // 행의 개수 설정
        
        for (int i = 0; i < rowCount; i++) {
            VO vo = member.get(i);
            String name = vo.getMember_name();
            String phone = vo.getMember_phone();
            String gen = vo.getMember_gen();
            String birth = vo.getMember_birth();
            String signupDate = vo.getMember_signup_date();
            String addr = vo.getMember_addr();
            
            model.setValueAt(name, i, 0);
            model.setValueAt(phone, i, 1);
            model.setValueAt(gen, i, 2);
            model.setValueAt(extractDate(birth), i, 3);
            model.setValueAt(extractDate(signupDate), i, 4);
            model.setValueAt(addr, i, 5);
            
            
        }
        
        try {
			Protocol p = new Protocol();
			p.setCmd(1004);
			main.out.writeObject(p);
			main.out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    }

 // extractDate 메서드 정의
    private String extractDate(String fullDate) {
        if (fullDate != null && fullDate.length() >= 10) {
            String year = fullDate.substring(0, 4);
            String month = fullDate.substring(5, 7);
            String day = fullDate.substring(8, 10);
            return year + "-" + month + "-" + day;
        }
        return "";
        
        
    }
    

}
