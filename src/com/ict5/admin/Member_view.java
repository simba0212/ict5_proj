package com.ict5.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.ict5.admin.Member_view2.CenterTableCellRenderer;

public class Member_view extends JPanel{
	Admin_main main;
	public Member_view(Admin_main main) {{
		this.main=main;
			 
			JPanel jp1 =new JPanel(new BorderLayout());
			JPanel jp1_1 =new JPanel(new BorderLayout());
			
			JLabel jl1 = new JLabel("     회원관리");
			jl1.setFont(jl1.getFont().deriveFont(Font.BOLD,17f));
			JLabel jl2 = new JLabel("회원검색");
			jl2.setFont(jl2.getFont().deriveFont(Font.BOLD,17f));
			JLabel jl3 = new JLabel("     포인트관리    ");
			jl3.setFont(jl3.getFont().deriveFont(Font.BOLD,17f));
			jp1.add(jl1,BorderLayout.WEST);
			jp1_1.add(jl2,BorderLayout.WEST);
			jp1_1.add(jl3,BorderLayout.EAST);
			jp1_1.setBackground(Color.lightGray);
			jp1.setBackground(Color.lightGray);
			jp1.add(jp1_1,BorderLayout.AFTER_LINE_ENDS);
			
			JTextField jtfMember = new JTextField(" 회원검색", 20);
			jtfMember.setEditable(false);
			jtfMember.setPreferredSize(new Dimension(80, 40));
			JButton bt_search = new JButton(new ImageIcon("src/images/search.png"));
			bt_search.setPreferredSize(new Dimension(80, 40));
			JPanel jp2 =new JPanel(new BorderLayout());
			JPanel jp2_1 =new JPanel(new BorderLayout());
			
			jp2_1.add(jtfMember,BorderLayout.WEST);
			jp2_1.add(new JLabel("  "),BorderLayout.CENTER);
			jp2_1.add(bt_search,BorderLayout.EAST);
			jp2.add(jp2_1,BorderLayout.WEST);
			jp1.setPreferredSize(new Dimension(1280, 60));
			jp2.setPreferredSize(new Dimension(1200, 40));

	        // 가변 데이터 수를 결정할 변수
	        int rowCount = 25; // 예시로 25개의 데이터 행을 가정합니다.

	        // 열 제목 지정
	        String[] columnNames = {"회원번호", "이름", "ID","전화번호","이메일","성별","생년월일","최근예약","주소","보유 포인트","등록 날짜","최근 출석일","이번달출석","사용포인트","충전포인트(달)","누적사용포인트","누적충전포인트"};
	        

	        // 테이블 모델 생성
	        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

	        // 데이터 추가
	        for (int i = 1; i <= rowCount; i++) {
	            Object[] rowData = {  i, "노종문",  "idsmsrlftneh","010-1234-1234","aqweqw@qasdfaf","남","1999-12-34","2023-06-26","서울마포구백범로213412소","123412354","2023-06-26","2023-06-26","30","사용포인트","412345453","13241543","123547456"};
	            
	            model.addRow(rowData);
	        }

	        // 테이블 생성 및 모델 설정
	        JTable table = new JTable(model);
	        
	        // 테이블 각 열 너비지정
	        TableColumnModel columnModel = table.getColumnModel();
	        int[] columnWidths = {55,60,100,100,120,30,80,80,200,70,80,80,80,80,80,80,80};
	        for (int i = 0; i < columnNames.length; i++) {
	            TableColumn column = columnModel.getColumn(i);
	            column.setPreferredWidth(columnWidths[i]);
	            column.setCellRenderer(new CenterTableCellRenderer());
	        }
	       
	        
	        // 테이블을 담을 스크롤 패널 생성
	        JScrollPane scrollPane = new JScrollPane(table);
	        
	        // 테이블 가로 스크롤 생성
	        
	        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	        scrollPane.setPreferredSize(new Dimension(1200, 450));
	        JScrollPane horizontalScrollPane = new JScrollPane(scrollPane, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

	        // 프레임 생성 및 테이블 스크롤 패널 추가
	        add(jp1);
	        add(jp2);
	       add(scrollPane);
	       // 데이터 클릭 이벤트 리스너 등록
	        table.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                int row = table.getSelectedRow();
	                Object value = table.getValueAt(row, 0); // 클릭된 데이터의 회원번호 
	                String selectedData = value.toString();
	                System.out.println("선택된 데이터: " +selectedData );
//	                Member_sel member_sel(main,selectedData); 
//	                cardlayout.show(pg1, "member_sel");  
	                
	                //
	            }
	        });
	     
	    }
	}
	public class CenterTableCellRenderer implements TableCellRenderer {
	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
	        renderer.setHorizontalAlignment(SwingConstants.CENTER);
	        return renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	    }
	}
}
