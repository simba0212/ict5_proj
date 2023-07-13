package com.ict5.admin.panel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.util.EventObject;
import java.util.List;

import javax.swing.AbstractCellEditor;
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
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.ict5.admin.Admin_main;
import com.ict5.db.Protocol;
import com.ict5.db.VO;

public class TimeTable2 extends JPanel {
	private JTable table;
	Admin_main main;
	CardLayout cardLayout;
	JTextField date;
	String date2;
	LocalDate currentDate;
	String dateOnly, voDate, teacherName, classType, classTime;

	public void refreshData() {
		clearTableData();
		Date();
	}

	public TimeTable2(Admin_main main) {
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());

		this.main = main;
		this.cardLayout = main.cardlayout;

		JPanel north = new JPanel(new BorderLayout());
		north.setBackground(Color.WHITE);
		JPanel north1 = new JPanel(new BorderLayout());
		north1.setBackground(Color.WHITE);
		JPanel north2 = new JPanel();
		north2.setBackground(Color.WHITE);
		north.setPreferredSize(new Dimension(10, 140));

		JLabel lblNewLabel = new JLabel("오늘의 강의목록");
		lblNewLabel.setFont(lblNewLabel.getFont().deriveFont(Font.BOLD, 17f));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		north1.add(lblNewLabel, BorderLayout.WEST);

		JButton btnNewButton = new JButton("강의추가");
		btnNewButton.setFont(btnNewButton.getFont().deriveFont(17f));
		btnNewButton.setPreferredSize(new Dimension(100, 50));

		north1.add(btnNewButton, BorderLayout.EAST);

		JButton btnNewButton_1_1 = new JButton("<<");
		btnNewButton_1_1.setPreferredSize(new Dimension(50, 30));
		north2.add(btnNewButton_1_1);

		JLabel lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setIcon(new ImageIcon("src/images/calender.png"));
		north2.add(lblNewLabel_2);

		LocalDate today = LocalDate.now();
		date2 = today.toString();
		date = new JTextField();
		date.setColumns(20);
		date.setPreferredSize(new Dimension(100, 50));
		date.setText(date2);
		north2.add(date);

		currentDate = LocalDate.now();
		date2 = currentDate.toString();

		JButton btnNewButton_1 = new JButton(">>");
		btnNewButton_1.setPreferredSize(new Dimension(50, 30));
		north2.add(btnNewButton_1);
		north.add(north1, BorderLayout.NORTH);
		north.add(north2, BorderLayout.SOUTH);

		add(north, BorderLayout.NORTH);

		Object[][] data = { { "09:00", "", "", "", "" }, { "10:00", "", "", "", "" }, { "11:00", "", "", "", "" },
				{ "12:00", "", "", "", "" }, { "13:00", "", "", "", "" }, { "14:00", "", "", "", "" },
				{ "15:00", "", "", "", "" }, { "16:00", "", "", "", "" }, { "17:00", "", "", "", "" },
				{ "18:00", "", "", "", "" }, { "19:00", "", "", "", "" }, { "20:00", "", "", "", "" },
				{ "21:00", "", "", "", "" } };
		String[] columnNames = { date2, "헬스", "요가", "수영", "필라테스" };

		DefaultTableModel model = new DefaultTableModel(data, columnNames);

		// 버튼을 렌더링하는 TableCellRenderer 구현
		class ButtonRenderer extends JButton implements TableCellRenderer {
			public ButtonRenderer() {
				setOpaque(true);
			}

			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				if (value.toString().equals("")) {
					return new JLabel();
				} else {
					setText(value.toString());
					return this;
				}
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
				if (value.toString().equals("")) {
					return new JLabel();
				} else {
					return button;
				}
				
			}

			public Object getCellEditorValue() {
				return button.getText();
			}
		}

		table = new JTable(model);
		table.setRowHeight(40);
		table.setShowVerticalLines(false);
		table.getTableHeader().setReorderingAllowed(false);
		
		table.getColumnModel().getColumn(0).setCellEditor(new NonEditableCellEditor());

		// 승인 여부 열에 버튼 렌더러와 에디터 설정
		TableColumn approveColumn1 = table.getColumnModel().getColumn(1);
		TableColumn approveColumn2= table.getColumnModel().getColumn(2);
		TableColumn approveColumn3 = table.getColumnModel().getColumn(3);
		TableColumn approveColumn4 = table.getColumnModel().getColumn(4);
		approveColumn1.setCellRenderer(new ButtonRenderer());
		approveColumn1.setCellEditor(new ButtonEditor());
		approveColumn2.setCellRenderer(new ButtonRenderer());
		approveColumn2.setCellEditor(new ButtonEditor());
		approveColumn3.setCellRenderer(new ButtonRenderer());
		approveColumn3.setCellEditor(new ButtonEditor());
		approveColumn4.setCellRenderer(new ButtonRenderer());
		approveColumn4.setCellEditor(new ButtonEditor());

		JScrollPane jsp = new JScrollPane(table);
		jsp.setPreferredSize(new Dimension(600, 544));
		add(jsp, BorderLayout.SOUTH);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(main.pg1, "classEdit");
			}
		});

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					if(table.getSelectedColumn()>0) {
						
						String row = (table.getSelectedRow()+1) + "";
						String col = (table.getSelectedColumn()) + "";
						String tmp = (String) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
						if(tmp.equals("")) {
							return;
						}
						VO vo = new VO();
						date2 = date2.replaceAll("-", "");
						vo.setClass_date(date2);
						vo.setClass_type(col);
						vo.setClass_time(row);
						Protocol p = new Protocol();
						p.setVo(vo);
						p.setCmd(1106);
						main.out.writeObject(p);
						main.out.flush();
					}
				} catch (Exception e2) {

				}

			}
		});

		// 날짜 다음 날
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentDate = currentDate.plusDays(1);
				date2 = currentDate.toString();
				date.setText(date2);
				try {
					Protocol p = new Protocol();
					p.setCmd(1002);
					main.out.writeObject(p);
					main.out.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				clearTableData();
				Date();
			}
		});

		// 날짜 전 날
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentDate = currentDate.minusDays(1);
				date2 = currentDate.toString();
				date.setText(date2);

				try {
					Protocol p = new Protocol();
					p.setCmd(1002);
					main.out.writeObject(p);
					main.out.flush();
					System.out.println();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				// 이전 데이터를 지우기 위해 테이블 초기화
				clearTableData();
				Date();
			}
		});

	}

	public void Date() {
		try {
			// 응답 받은 후 list를 확인
			List<VO> list = main.list;
			if (list != null) {
				for (VO k : list) {

					voDate = k.getClass_date().substring(0, 10);

					// vo의 날짜 정보가 date2와 일치하는 경우에만 처리
					if (voDate.equals(date2)) {
						teacherName = k.getTeacher_name();
						classType = k.getClass_type();

						switch (k.getClass_type()) {
						case "1":
							switch (k.getClass_time()) {
							case "1":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 0,
										1);
								break;
							case "2":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 1,
										1);
								break;
							case "3":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 2,
										1);
								break;
							case "4":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 3,
										1);
								break;
							case "5":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 4,
										1);
								break;
							case "6":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 5,
										1);
								break;
							case "7":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 6,
										1);
								break;
							case "8":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 7,
										1);
								break;
							case "9":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 8,
										1);
								break;
							case "10":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 9,
										1);
								break;
							case "11":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 10,
										1);
								break;
							case "12":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 11,
										1);
								break;

							default:
								teacherName = "알 수 없는 타입";
								break;
							}
							break;

						case "2":
							switch (k.getClass_time()) {
							case "1":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 0,
										2);
								break;
							case "2":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 1,
										2);
								break;
							case "3":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 2,
										2);
								break;
							case "4":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 3,
										2);
								break;
							case "5":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 4,
										2);
								break;
							case "6":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 5,
										2);
								break;
							case "7":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 6,
										2);
								break;
							case "8":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 7,
										2);
								break;
							case "9":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 8,
										2);
								break;
							case "10":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 9,
										2);
								break;
							case "11":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 10,
										2);
								break;
							case "12":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 11,
										2);
								break;

							default:
								teacherName = "알 수 없는 타입";
								break;
							}
							break;

						case "3":
							switch (k.getClass_time()) {
							case "1":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 0,
										3);
								break;
							case "2":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 1,
										3);
								break;
							case "3":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 2,
										3);
								break;
							case "4":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 3,
										3);
								break;
							case "5":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 4,
										3);
								break;
							case "6":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 5,
										3);
								break;
							case "7":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 6,
										3);
								break;
							case "8":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 7,
										3);
								break;
							case "9":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 8,
										3);
								break;
							case "10":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 9,
										3);
								break;
							case "11":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 10,
										3);
								break;
							case "12":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 11,
										3);
								break;

							default:
								teacherName = "알 수 없는 타입";
								break;
							}
							break;
						case "4":
							switch (k.getClass_time()) {
							case "1":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 0,
										4);
								break;
							case "2":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 1,
										4);
								break;
							case "3":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 2,
										4);
								break;
							case "4":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 3,
										4);
								break;
							case "5":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 4,
										4);
								break;
							case "6":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 5,
										4);
								break;
							case "7":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 6,
										4);
								break;
							case "8":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 7,
										4);
								break;
							case "9":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 8,
										4);
								break;
							case "10":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 9,
										4);
								break;
							case "11":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 10,
										4);
								break;
							case "12":
								table.setValueAt(
										"<html><div style='text-align: center;'>" + teacherName + "</div></html>", 11,
										4);
								break;

							default:
								teacherName = "알 수 없는 타입";
								break;
							}
							break;

						default:
							teacherName = "알 수 없는 타입";
							break;
						}
					}
				}
			} else {
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 테이블 데이터 초기화 메서드
	public void clearTableData() {
		for (int row = 0; row < table.getRowCount(); row++) {
			for (int column = 1; column < table.getColumnCount(); column++) {
				table.setValueAt("", row, column);
			}
		}
	}

}
class NonEditableCellEditor implements TableCellEditor {
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        // Create a JLabel to display the cell value
        JLabel label = new JLabel(value.toString());
        label.setOpaque(true);
        label.setBackground(table.getSelectionBackground());
        return label;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        return false; // Make the cell not editable
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return false;
    }

    @Override
    public boolean stopCellEditing() {
        return true;
    }

    @Override
    public void cancelCellEditing() {
    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {
    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {
    }
}