package com.ict5.admin.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Member_new extends JPanel {
	private JTable table;

	public Member_new() {
		setLayout(new BorderLayout());
		JLabel titleLabel = new JLabel("신규 회원 등록");
        setBackground(Color.lightGray);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
		Font labelFont = titleLabel.getFont().deriveFont(Font.BOLD,20f);
		titleLabel.setFont(labelFont);
		add(titleLabel, BorderLayout.NORTH);

		String[] columnNames = { "이름", "전화번호", "성별", "생년월일", "등록날짜", "주소" };
		Object[][] data = { { "고길동", "000-1111-2222", "남성", "1990-00-00", "2023-06-10", "인천광역시 서구" },
							{ "둘리", "000-1111-2222", "남성", "1990-00-00", "2023-06-10", "인천광역시 서구" },
							{ "도우너", "000-1111-2222", "여성", "1990-00-00", "2023-06-10", "인천광역시 서구" },
							{ "마이콜", "000-1111-2222", "남성", "1990-00-00", "2023-06-10", "인천광역시 서구" }, 
							{ "또치", "000-1111-2222", "여성", "1990-00-00", "2023-06-10", "인천광역시 서구" },
							{ "심바", "000-1111-2222", "남성", "1990-00-00", "2023-06-10", "인천광역시 서구" },
							{ "희동", "000-1111-2222", "남성", "1990-00-00", "2023-06-10", "인천광역시 서구" },
							{ "공실이", "000-1111-2222", "여성", "1990-00-00", "2023-06-10", "인천광역시 서구" } };

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
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
		table.getColumn("전화번호").setCellRenderer(celAlignCenter);
		table.getColumn("성별").setCellRenderer(celAlignCenter);
		table.getColumn("생년월일").setCellRenderer(celAlignCenter);
		table.getColumn("등록날짜").setCellRenderer(celAlignCenter);
		table.getColumn("주소").setCellRenderer(celAlignCenter);
		table.getColumn("전화번호").setPreferredWidth(100);
		table.getColumn("주소").setPreferredWidth(200);
		table.getTableHeader().setReorderingAllowed(false);

		// Add the table to a scroll pane and display it
		JScrollPane jsp = new JScrollPane(table);
		jsp.setPreferredSize(new Dimension(600, 300));
		add(jsp, BorderLayout.SOUTH);
	}

	
}
