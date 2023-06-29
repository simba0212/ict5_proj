package com.ict5.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

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

public class ClassCheck2 extends JPanel {
	Admin_main main;
	JPanel centerPanel;

	public ClassCheck2(Admin_main main) {
		this.main = main;

		setLayout(new BorderLayout());

		// Create the "회원검색" panel in the north
		JPanel northPanel = new JPanel(new BorderLayout());
		northPanel.setBackground(Color.lightGray);

		JLabel searchLabel = new JLabel("회원검색");
		searchLabel.setFont(searchLabel.getFont().deriveFont(Font.BOLD, 17f));
		searchLabel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 10));

		JPanel searchPanel = new JPanel(new BorderLayout());
		JTextField searchField = new JTextField(" 회원검색", 20);
		searchField.setEditable(false);
		JButton searchButton = new JButton(new ImageIcon("src/images/search.png"));

		searchPanel.add(searchField, BorderLayout.WEST);
		searchPanel.add(searchButton, BorderLayout.EAST);

		northPanel.add(searchLabel, BorderLayout.WEST);
		northPanel.add(searchPanel, BorderLayout.EAST);

		add(northPanel, BorderLayout.NORTH);

		// Create the 6x6 table in the center
		centerPanel = new JPanel(new BorderLayout());
		createTable();

		add(centerPanel, BorderLayout.CENTER);

		// Create the buttons panel in the south
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Right-aligned FlowLayout

		JButton deleteButton = new JButton("인원삭제");
		JButton addButton = new JButton("인원추가");

		buttonsPanel.add(deleteButton);
		buttonsPanel.add(addButton);

		add(buttonsPanel, BorderLayout.SOUTH);
	}

	public void createTable() {
	    String[] columnNames = { "선택", "이름", "성별", "잔여 포인트", "회원번호", "대기상태" };
	    Object[][] rowData = {
	            { false, "1", "남성", "3456", "1", "대기" },
	            { false, "1", "남성", "3456", "1", "대기" },
	            { false, "1", "남성", "3456", "1", "대기" },
	            { false, "1", "남성", "3456", "1", "대기" },
	            { false, "1", "남성", "3456", "1", "대기" },
	            { false, "1", "남성", "3456", "1", "대기" }
	    };
	    
	    // 체크박스 넣기

	    DefaultTableModel model = new DefaultTableModel(rowData, columnNames) {
	        @Override
	        public Class<?> getColumnClass(int columnIndex) {
	            if (columnIndex == 0) {
	                return Boolean.class; // Column 0 (선택) should contain checkboxes
	            }
	            return super.getColumnClass(columnIndex);
	        }
	    };

	    JTable table = new JTable(model);
	    table.setRowHeight(30);

	    // Center align the cell contents
	    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
	    table.setDefaultRenderer(Object.class, centerRenderer);

	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setPreferredSize(new Dimension(500, 200)); // Set preferred size of scroll pane

	    centerPanel.setLayout(new BorderLayout()); // Change layout of centerPanel to BorderLayout
	    centerPanel.add(scrollPane, BorderLayout.CENTER); // Add the scroll pane with table to centerPanel
	}


}