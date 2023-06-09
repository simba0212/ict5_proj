package com.ict5.admin.panel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.ict5.admin.Admin_main;
import com.ict5.db.Protocol;
import com.ict5.db.VO;

public class Point_MgmtSub extends JPanel {
	Admin_main main;
	CardLayout cardLayout;
	DefaultTableModel model;

	public Point_MgmtSub(Admin_main main) {
		this.main = main;

		JPanel jp1 = new JPanel(new BorderLayout());
		JPanel jp1_1 = new JPanel(new BorderLayout());
		JPanel jp2 = new JPanel(new BorderLayout());
		JPanel jp2_1 = new JPanel(new BorderLayout());

		JLabel jl1 = new JLabel("회원관리");
		jl1.setFont(jl1.getFont().deriveFont(Font.BOLD, 17f));
		jl1.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

		jp1_1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		// "포인트관리" 버튼 생성
		JLabel memberSearchLabel = new JLabel("회원검색");
		memberSearchLabel.setFont(memberSearchLabel.getFont().deriveFont(Font.BOLD, 17f));
		memberSearchLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		jp1_1.add(memberSearchLabel);

		// Create the "회원검색" button
		JLabel pointManagementLabel = new JLabel("포인트관리");
		pointManagementLabel.setFont(pointManagementLabel.getFont().deriveFont(Font.BOLD, 17f));
		pointManagementLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		jp1_1.add(pointManagementLabel);

		// JPanel에 "회원검색" 버튼 추가
		jp1_1.add(memberSearchLabel, BorderLayout.EAST);
		jp1_1.add(pointManagementLabel, BorderLayout.EAST);

		jp1.add(jl1, BorderLayout.WEST);

		jp1_1.setBackground(Color.lightGray);
		jp1.setBackground(Color.lightGray);
		jp1.add(jp1_1, BorderLayout.AFTER_LINE_ENDS);

		jp2_1.add(new JLabel("  "), BorderLayout.CENTER);
		jp2.add(jp2_1, BorderLayout.WEST);
		jp1.setPreferredSize(new Dimension(1280, 60));
		jp2.setPreferredSize(new Dimension(1200, 40));

		// 열 제목 지정
		String[] columnNames = { "신청번호", "이름", "ID", "전화번호", "보유 포인트", "신청 포인트", "신청 날짜", "입금상태", "승인 여부" };
		// 테이블 모델 생성
		model = new DefaultTableModel(columnNames, 0);
		// 버튼을 렌더링하는 TableCellRenderer 구현
		class ButtonRenderer extends JButton implements TableCellRenderer {
			public ButtonRenderer() {
				setOpaque(true);
			}

			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				if (value.toString().equals("승인불가")) {

					return new JLabel("<html><p style=\"color: rgb(255, 0, 0);\"> 승인불가 </p></html>", JLabel.CENTER);
				} else if (value.toString().equals("승인완료")) {
					return new JLabel("<html><p style=\"color: rgb(0, 0, 255);\"> 승인완료 </p></html>", JLabel.CENTER);
				}
				setText(value.toString());
				return this;
			}
		}

		// 버튼을 편집하는 TableCellEditor 구현
		class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
			private JButton button;

			public ButtonEditor() {
				button = new JButton();
				button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						fireEditingStopped();
					}
				});
			}

			public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
					int column) {
				button.setText(value.toString());
				if (value.toString().equals("승인불가")) {
					return new JLabel("<html><p style=\"color: rgb(255, 0, 0);\"> 승인불가 </p></html>", JLabel.CENTER);
				} else if (value.toString().equals("승인완료")) {
					return new JLabel("<html><p style=\"color: rgb(0, 0, 255);\"> 승인완료 </p></html>", JLabel.CENTER);
				}
				return button;
			}

			public Object getCellEditorValue() {
				return button.getText();
			}
		}

		// 테이블 생성 및 모델 설정
		JTable table = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// "승인 여부" 열은 수정할 수 없도록 설정
				return column == 8;
			}
		};
		table.getTableHeader().setReorderingAllowed(false);

		// 승인 여부 열에 버튼 렌더러와 에디터 설정
		TableColumn approveColumn = table.getColumnModel().getColumn(8);
		approveColumn.setCellRenderer(new ButtonRenderer());
		approveColumn.setCellEditor(new ButtonEditor());

		// 테이블 각 열 너비지정
		TableColumnModel columnModel = table.getColumnModel();
		int[] columnWidths = { 55, 60, 100, 100, 100, 100, 80, 80, 80 };
		for (int i = 0; i < columnNames.length; i++) {
			TableColumn column = columnModel.getColumn(i);
			column.setPreferredWidth(columnWidths[i]);
		}

		// 테이블을 담을 스크롤 패널 생성
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(700, 500));

		// 프레임 생성 및 테이블 스크롤 패널 추가
		add(jp1);
		add(jp2);
		add(scrollPane);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row = table.getSelectedRow();
					int col = table.getSelectedColumn();
					Object obj = table.getValueAt(row, col);
					if (obj.toString().equals("승인")) {
						int res = JOptionPane.showConfirmDialog(null, "정말 승인하시겠습니까?", "승인확인",
								JOptionPane.YES_NO_OPTION);
						if (res == 0) {
							obj = table.getValueAt(row, 0);
							String charge_num = obj.toString();

							Protocol p = new Protocol();
							VO vo = new VO();

							vo.setCharge_num(charge_num);
							p.setVo(vo);
							p.setCmd(1208);
							main.out.writeObject(p);
							main.out.flush();
						}

					}

				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});

		pointManagementLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pointManagementLabel.setForeground(Color.red);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				pointManagementLabel.setForeground(Color.black);
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

		jl1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.cardlayout.show(main.pg1, "member"); // "member" 페이지로 이동
			}
		});
	}

	public void refresh() {
		// 데이터 추가
		model.setRowCount(0);
		List<VO> list = main.list;
		main.cardlayout.show(main.pg1, "point_Mgmt");
		String charge_date =""; // 가변으로 넣어줄 입금날짜
		String approve = ""; // 가변으로 넣어줄 승인/승인불가/승인완료
		for (VO k : list) {
			if (k.getPoint_charge_date() != null) { // 입금완료일때
				charge_date =k.getPoint_charge_date().substring(0,10); // 입금날짜
				if (k.getPoint_approve() != null) { // 승인완료일때
					approve = "승인완료";
				} else { // 승인대기중일때
					approve = "승인";
				}
			} else { // 입금대기중
				charge_date = "입금대기";
				approve = "승인불가";
			}
			k.setPoint_signup_date(k.getPoint_signup_date().substring(0, 10));
			Object[] rowData = { k.getCharge_num(), k.getMember_name(), k.getMember_id(), k.getMember_phone(),
					k.getMember_point(), k.getPoint_money(), k.getPoint_signup_date(), charge_date,
					approve };
			model.addRow(rowData);

		}

	}
}
