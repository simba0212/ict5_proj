package com.ict5.admin.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

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

import com.ict5.admin.Admin_main;
import com.ict5.db.Protocol;
import com.ict5.db.VO;

public class Member_view extends JPanel {
	Admin_main main;
	DefaultTableModel model;

	public Member_view(Admin_main main) {

		this.main = main;

		JPanel jp1 = new JPanel(new BorderLayout());
		JPanel jp1_1 = new JPanel(new BorderLayout());

		JLabel jl1 = new JLabel("     회원관리");
		jl1.setFont(jl1.getFont().deriveFont(Font.BOLD, 17f));
		JLabel jl2 = new JLabel("회원검색");
		jl2.setFont(jl2.getFont().deriveFont(Font.BOLD, 17f));
		JLabel jl3 = new JLabel("     포인트관리    ");
		jl3.setFont(jl3.getFont().deriveFont(Font.BOLD, 17f));
		jp1.add(jl1, BorderLayout.WEST);
		jp1_1.add(jl2, BorderLayout.WEST);
		jp1_1.add(jl3, BorderLayout.EAST);
		jp1_1.setBackground(Color.lightGray);
		jp1.setBackground(Color.lightGray);
		jp1.add(jp1_1, BorderLayout.AFTER_LINE_ENDS);

		JTextField jtfMember = new JTextField(" 회원검색", 20);
		jtfMember.setEditable(true);
		jtfMember.setPreferredSize(new Dimension(80, 40));
		JButton bt_search = new JButton(new ImageIcon("src/images/search.png"));
		bt_search.setPreferredSize(new Dimension(80, 40));
		JPanel jp2 = new JPanel(new BorderLayout());
		JPanel jp2_1 = new JPanel(new BorderLayout());

		jp2_1.add(jtfMember, BorderLayout.WEST);
		jp2_1.add(new JLabel("  "), BorderLayout.CENTER);
		jp2_1.add(bt_search, BorderLayout.EAST);
		jp2.add(jp2_1, BorderLayout.WEST);
		jp1.setPreferredSize(new Dimension(1280, 60));
		jp2.setPreferredSize(new Dimension(1200, 40));

		// 열 제목 지정
		String[] columnNames = { "회원번호", "이름", "ID", "전화번호", "이메일", "성별", "생년월일", "주소", "보유 포인트", "등록 날짜", "최근 출석일",
				"이번달출석", "누적사용포인트", "누적충전포인트" };

		// 테이블 모델 생성
		model = new DefaultTableModel(columnNames, 0);

		// 테이블 생성 및 모델 설정
		JTable table = new JTable(model);
		table.getTableHeader().setReorderingAllowed(false);

		// 테이블 각 열 너비지정
		TableColumnModel columnModel = table.getColumnModel();
		int[] columnWidths = { 55, 60, 100, 100, 150, 30, 100, 200, 100, 100, 100, 100, 100, 100};
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

		// 프레임 생성 및 테이블 스크롤 패널 추가
		add(jp1);
		add(jp2);
		add(scrollPane);

		jtfMember.addFocusListener(new FocusListener() { // 회원검색 필드 클릭시
			@Override
			public void focusLost(FocusEvent e) {
			}

			@Override
			public void focusGained(FocusEvent e) {
				jtfMember.setText("");
			}
		});

		// 데이터 클릭 이벤트 리스너 등록
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Protocol p = new Protocol();
					VO vo = new VO();
					p.setCmd(1203);
					int row = table.getSelectedRow();
					Object value = table.getValueAt(row, 0); // 클릭된 데이터의 회원번호
					String member_num = value.toString();
					vo.setMember_num(member_num);
					p.setVo(vo);
					main.out.writeObject(p);
					main.out.flush();
					main.cardlayout.show(main.pg1, "member2");
				} catch (Exception e2) {
				}
			}
		});

		jtfMember.addActionListener(new ActionListener() { // 검색창 엔터

			@Override
			public void actionPerformed(ActionEvent e) {
				if (jtfMember.getText().trim().isEmpty()) { // 빈칸 검색시
					try {
						Protocol p = new Protocol();
						p.setCmd(1201);
						main.out.writeObject(p);
						main.out.flush();
						main.cardlayout.show(main.pg1, "member");
					} catch (Exception e2) {
					}

				} else {
					try {
						String name = jtfMember.getText().trim();
						Protocol p = new Protocol();
						VO vo = new VO();
						vo.setMember_name(name);
						p.setCmd(1202);
						p.setVo(vo);
						main.out.writeObject(p);
						main.out.flush();
					} catch (Exception e2) {

					}
				}

			}

		});

		bt_search.addActionListener(new ActionListener() { // 검색버튼

			@Override
			public void actionPerformed(ActionEvent e) {
				if (jtfMember.getText().trim().isEmpty()) { // 빈칸 검색시
					try {
						Protocol p = new Protocol();
						p.setCmd(1201);
						main.out.writeObject(p);
						main.out.flush();
						main.cardlayout.show(main.pg1, "member");
					} catch (Exception e2) {
						// TODO: handle exception
					}

				} else {
					try {
						String name = jtfMember.getText().trim();
						Protocol p = new Protocol();
						VO vo = new VO();
						vo.setMember_name(name);
						p.setCmd(1202);
						p.setVo(vo);
						main.out.writeObject(p);
						main.out.flush();
					} catch (Exception e2) {

					}
				}

			}
		});

		jl1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				jl1.setForeground(Color.red);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				jl1.setForeground(Color.black);
			}
		});

		jl3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				jl2.setForeground(Color.red);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				jl3.setForeground(Color.black);
			}
		});

		jl3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.cardlayout.show(main.pg1, "checkagain"); // "member" 페이지로 이동
			}
		});

	}

	public class CenterTableCellRenderer implements TableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
			renderer.setHorizontalAlignment(SwingConstants.CENTER);
			return renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		}
	}

	public void refresh() { // 전체회원 가져오기
		model.setRowCount(0);
		List<VO> list = main.list;
		for (VO k : list) {

			Vector<Object> rowData = new Vector<>();
			rowData.add(k.getMember_num());
			rowData.add(k.getMember_name());
			rowData.add(k.getMember_id());
			rowData.add(k.getMember_phone());
			rowData.add(k.getMember_mail());
			rowData.add(k.getMember_gen());
			if (k.getMember_birth() != null) {
				rowData.add(k.getMember_birth().substring(0, 10));
			} else
				rowData.add(k.getMember_birth());
			rowData.add(k.getMember_addr());
			rowData.add(k.getMember_point());
			if (k.getMember_signup_date() != null) {
				rowData.add(k.getMember_signup_date().substring(0, 10));
			} else
				rowData.add(k.getMember_signup_date());
			if (k.getAttendent_date() != null) {
				rowData.add(k.getAttendent_date().substring(0, 10));
			} else
				rowData.add(k.getAttendent_date());
			rowData.add(k.getAttendent_month());
			rowData.add(k.getMember_usep());
			rowData.add(k.getMember_chargep());
			rowData.add(k.getMember_totaluse());
			rowData.add(k.getMember_totalcharge());

			model.addRow(rowData);
		}
	}

	public void search() { // 검색
		model.setRowCount(0);
		List<VO> list = main.list;
		for (VO k : list) {

			Vector<Object> rowData = new Vector<>();
			rowData.add(k.getMember_num());
			rowData.add(k.getMember_name());
			rowData.add(k.getMember_id());
			rowData.add(k.getMember_phone());
			rowData.add(k.getMember_mail());
			rowData.add(k.getMember_gen());
			if (k.getMember_birth() != null) {
				rowData.add(k.getMember_birth().substring(0, 10));
			} else
				rowData.add(k.getMember_birth());
			rowData.add(k.getMember_addr());
			rowData.add(k.getMember_point());
			if (k.getMember_signup_date() != null) {
				rowData.add(k.getMember_signup_date().substring(0, 10));
			} else
				rowData.add(k.getMember_signup_date());
			if (k.getAttendent_date() != null) {
				rowData.add(k.getAttendent_date().substring(0, 10));
			} else
				rowData.add(k.getAttendent_date());
			rowData.add(k.getAttendent_month());
			rowData.add(k.getMember_usep());
			rowData.add(k.getMember_chargep());
			rowData.add(k.getMember_totaluse());
			rowData.add(k.getMember_totalcharge());

			model.addRow(rowData);
		}
	}
}
