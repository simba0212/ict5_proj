package com.ict5.admin.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.ict5.admin.Admin_main;
import com.ict5.db.Protocol;
import com.ict5.db.VO;

public class ClassCheck2 extends JPanel {
	Admin_main main;
	JPanel centerPanel;
	JTable table;
	DefaultTableModel model;
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

//		add(northPanel, BorderLayout.NORTH);

		// Create the 6x6 table in the center
		centerPanel = new JPanel(new BorderLayout());
		createTable();

		add(centerPanel, BorderLayout.CENTER);

		// Create the buttons panel in the south
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Right-aligned FlowLayout

		JButton deleteButton = new JButton("인원삭제");

		buttonsPanel.add(deleteButton);

		add(buttonsPanel, BorderLayout.SOUTH);

		deleteButton.addActionListener(new ActionListener() { // 인원삭제

			@Override
			public void actionPerformed(ActionEvent e) {
				List<VO> list = new ArrayList<>();
				Protocol p = new Protocol();
				String class_num = main.classcheck.classCheck.label20.getText();
				String class_point = main.classcheck.classCheck.label19.getText();
				int column = 0;  // 체크박스 컬럼
				
				for (int row = 0; row < model.getRowCount(); row++) { 
					Boolean checked = (Boolean) model.getValueAt(row, column);
					if (checked != null && checked) {
						// 체크박스 선택시
						VO vo = new VO();
						String membernum = (String)model.getValueAt(row, 4); // 회원넘버
						vo.setMember_num(membernum); // vo에 세팅
						vo.setClass_num(class_num);
						vo.setClass_point(class_point);
						list.add(vo); // list에 담아두기
					} else {
					    // 체크박스 선택안되면 아무것도 안함
					}
				} // list에 전부 담았다.
				// list크기를 확인해서 0이면 아무것도 안하고, 1이면 vo로 보내고,
				// 1보다 크면, list를 보내서 CP에서 하나씩뽑아서 DAO보낸다.
				if(list.size()==0) {
					// 아무것도 안함. 선택된게 없음
					JOptionPane.showMessageDialog(null, "삭제할 인원을 선택하세요");
					return;
				}else if(list.size()==1) {
					// 한놈 선택시
					p.setVo(list.get(0)); // 한놈밖에없어서 이놈이 끝
					p.setResult(0); // 한놈인걸 0으로 보내고
				}else {
					p.setList(list); // 여러명 => 리스트에 담아서 보내야함
					p.setResult(1); // 여러명인걸 1이라고 해서 보내자
				}
				try {
					p.setCmd(1108);
					main.out.writeObject(p);
					main.out.flush();
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				
			}
		});

	}

	public void createTable() {
		String[] columnNames = { "선택", "이름", "성별", "잔여 포인트", "회원번호" };
		Object[][] rowData = { { false, "1", "남성", "3456", "1" }, { false, "1", "남성", "3456", "1", "대기" },
				{ false, "1", "남성", "3456", "1" }, { false, "1", "남성", "3456", "1" },
				{ false, "1", "남성", "3456", "1" }, { false, "1", "남성", "3456", "1" } };

		// 체크박스 넣기

		model = new DefaultTableModel(rowData, columnNames) {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == 0) {
					return Boolean.class; // Column 0 (선택) should contain checkboxes
				}
				return super.getColumnClass(columnIndex);
				
				
			}
			public boolean isCellEditable(int row, int column) {
		        return column == 0;
		    }
		};

		table = new JTable(model);
		table.getTableHeader().setReorderingAllowed(false);
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
	public void refresh(List<VO> list) {
		model.setRowCount(0);
		for (VO k : list) {
			Object[] rowData = {false,k.getMember_name(),k.getMember_gen(),k.getMember_point(),k.getMember_num()};
			model.addRow(rowData);
		}
		
	}

}