package com.ict5.admin.panel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.ict5.admin.Admin_ClassCheck;
import com.ict5.admin.Admin_main;
import com.ict5.db.Protocol;
import com.ict5.db.VO;

public class TimeTable extends JPanel {
	private JTable table;
	Admin_main main;
	CardLayout cardLayout;
	VO vo;
	JTextField date;
	String date2;
	LocalDate currentDate;
	String dateOnly, voDate, teacherName, classType, classTime;

	public void refreshData() {
		clearTableData();
		Date();
		System.out.println("실행");
	}

	public TimeTable(Admin_main main) {
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());

		this.main = main;
		this.cardLayout = main.cardlayout;
		this.vo = main.vo;

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

		TableCellRenderer buttonRenderer = new ButtonRenderer();
		TableCellEditor buttonEditor = new ButtonEditor(main, this);

		table = new JTable(model);
		table.setRowHeight(40);
		table.setShowVerticalLines(false);
		table.setDefaultRenderer(Object.class, buttonRenderer);
		table.setDefaultEditor(Object.class, buttonEditor);
		table.getTableHeader().setReorderingAllowed(false);

		// Add the table to a scroll pane and display it
		JScrollPane jsp = new JScrollPane(table);
		jsp.setPreferredSize(new Dimension(600, 544));
		add(jsp, BorderLayout.SOUTH);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				clearTableData();

				try {
					Protocol p = new Protocol();
					p.setCmd(1002);
					main.out.writeObject(p);
					main.out.flush();
					System.out.println();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				cardLayout.show(main.pg1, "classEdit");
			}
		});

		// 날짜 다음 날
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentDate = currentDate.plusDays(1);
				date2 = currentDate.toString();
				date.setText(date2);
				// 이전 데이터를 지우기 위해 테이블 초기화
				clearTableData();

				try {
					Protocol p = new Protocol();
					p.setCmd(1002);
					main.out.writeObject(p);
					main.out.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

				// 이전 데이터를 지우기 위해 테이블 초기화
			}
		});

		// 날짜 전 날
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentDate = currentDate.minusDays(1);
				date2 = currentDate.toString();
				date.setText(date2);
				// 이전 데이터를 지우기 위해 테이블 초기화
				clearTableData();
				
				try {
					Protocol p = new Protocol();
					p.setCmd(1002);
					main.out.writeObject(p);
					main.out.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				// 이전 데이터를 지우기 위해 테이블 초기화
			}
		});

	}

	public void Date() {
		try {
			// 응답 받은 후 list를 확인
			List<VO> list = main.list;
			if (list != null) {
				for (VO vo : list) {

					voDate = vo.getClass_date().substring(0,10);

					// vo의 날짜 정보가 date2와 일치하는 경우에만 처리
					if (voDate.equals(date2)) {
						teacherName = vo.getTeacher_name();
						classType = vo.getClass_type();
						int classTime = Integer.parseInt(vo.getClass_time());

						switch (vo.getClass_type()) {
						case "1":
							switch (vo.getClass_time()) {
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
							switch (vo.getClass_time()) {
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
							switch (vo.getClass_time()) {
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
							switch (vo.getClass_time()) {
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

		try {
			Protocol p = new Protocol();
			p.setCmd(1003);
			main.out.writeObject(p);
			main.out.flush();
			System.out.println();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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

class ButtonRenderer extends DefaultTableCellRenderer {
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		if (column >= 1 && !value.toString().equals("")) {
			JButton button = new JButton(value.toString());
			button.setBackground(null);
			return button;
		} else {
			return component;
		}
	}
}

class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
	private JButton button;
	Admin_main main;
	CardLayout cardLayout;
	TimeTable timeTable;

	public ButtonEditor(Admin_main main, TimeTable timeTable) {
		this.main = main;
		this.cardLayout = main.cardlayout;
		this.timeTable = timeTable;

		button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 버튼 클릭 시 동작을 수행할 수 있도록 구현
				System.out.println("Button clicked");
				JOptionPane.showMessageDialog(button, "강의 페이지로 이동");
				
				timeTable.clearTableData();
				try {
					Protocol p = new Protocol();
					p.setCmd(1002);
					main.out.writeObject(p);
					main.out.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				main.cardlayout.show(main.pg1, "classcheck"); // "classcheck" 페이지로 이동
				
			}
		});
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		// Edit the specific cells in the desired columns using the button editor
		if (column >= 1 && !value.toString().equals("")) {
			button.setText(value.toString());
			return button;
		} else {
			// 해당 셀이 아닌 경우, 기본 편집 컴포넌트를 반환
			return null;
		}
	}

	public Object getCellEditorValue() {
		return button.getText();
	}

	public boolean stopCellEditing() {
		// 셀 편집이 중지될 때 호출되는 메서드
		// 필요한 경우 추가적인 작업을 수행할 수 있습니다.
		fireEditingStopped();
		return super.stopCellEditing();
	}
}
