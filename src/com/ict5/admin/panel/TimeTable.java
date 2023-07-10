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
		String[] columnNames = { date2, "수영", "헬스", "요가", "필라테스" };

		DefaultTableModel model = new DefaultTableModel(data, columnNames);

		TableCellRenderer buttonRenderer = new ButtonRenderer();
		TableCellEditor buttonEditor = new ButtonEditor();

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
				cardLayout.show(main.pg1, "classEdit");
			}
		});

		// 날짜 다음 날
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDate currentDate = LocalDate.parse(date2);
				LocalDate nextDate = currentDate.plusDays(1);
				date2 = nextDate.toString();
				date.setText(date2);
				
			}
		});

		// 날짜 전 날
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDate currentDate = LocalDate.parse(date2);
				LocalDate previousDate = currentDate.minusDays(1);
				date2 = previousDate.toString();
				date.setText(date2);
			}
		});

	}

	public void Date() {
		try {
			Protocol p = new Protocol();
			p.setCmd(1002);
			p.setVo(vo);
			main.out.writeObject(p);
			main.out.flush();

			// 응답 받은 후 list를 확인
			Protocol response = (Protocol) main.in.readObject();
			List<VO> list = response.getList();
			if (list != null) {
				for (VO vo : list) {
					String classType = "";
					String teacherName = vo.getTeacher_name();

					switch (vo.getClass_type()) {
					case "1":
						switch (vo.getClass_time()) {
						case "1":
							classType = "수영 1";
							table.setValueAt(classType, 1, 1);
							break;
						case "2":
							classType = "수영 2";
							table.setValueAt(classType, 2, 1);
							break;
						case "3":
							classType = "수영 3";
							table.setValueAt("<html><div style='text-align: center;'>" + classType + "<br>"
									+ teacherName + "</div></html>", 4, 1);
							break;
						case "4":
							classType = "수영 4";
							table.setValueAt(classType, 5, 1);
							break;
						case "5":
							classType = "수영 5";
							table.setValueAt(classType, 6, 1);
							break;
						case "6":
							classType = "수영 6";
							table.setValueAt(classType, 7, 1);
							break;
						case "7":
							classType = "수영 7";
							table.setValueAt(classType, 8, 1);
							break;
						case "8":
							classType = "수영 8";
							table.setValueAt(classType, 9, 1);
							break;
						case "9":
							classType = "수영 9";
							table.setValueAt(classType, 10, 1);
							break;
						case "10":
							classType = "수영 10";
							table.setValueAt(classType, 11, 1);
							break;
						case "11":
							classType = "수영 11";
							table.setValueAt(classType, 12, 1);
							break;
						case "12":
							classType = "수영 12";
							table.setValueAt(classType, 13, 1);
							break;

						default:
							classType = "알 수 없는 타입";
							break;
						}
						break;

					case "2":
						switch (vo.getClass_time()) {
						case "1":
							classType = "헬스 1";
							table.setValueAt(classType, 1, 2);
							break;
						case "2":
							classType = "헬스 2";
							table.setValueAt(classType, 2, 2);
							break;
						case "3":
							classType = "헬스 3";
							table.setValueAt(classType, 4, 2);
							break;
						case "4":
							classType = "헬스 4";
							table.setValueAt(classType, 5, 2);
							break;
						case "5":
							classType = "헬스 5";
							table.setValueAt(classType, 6, 2);
							break;
						case "6":
							classType = "헬스 6";
							table.setValueAt("<html><div style='text-align: center;'>" + classType + "<br>"
									+ teacherName + "</div></html>", 7, 2);
							break;
						case "7":
							classType = "헬스 7";
							table.setValueAt(classType, 8, 2);
							break;
						case "8":
							classType = "헬스 8";
							table.setValueAt(classType, 9, 2);
							break;
						case "9":
							classType = "헬스 9";
							table.setValueAt(classType, 10, 2);
							break;
						case "10":
							classType = "헬스 20";
							table.setValueAt(classType, 11, 2);
							break;
						case "11":
							classType = "헬스 11";
							table.setValueAt(classType, 12, 2);
							break;
						case "12":
							classType = "헬스 12";
							table.setValueAt(classType, 13, 2);
							break;

						default:
							classType = "알 수 없는 타입";
							break;
						}
						break;

					case "3":
						switch (vo.getClass_time()) {
						case "1":
							classType = "요가 1";
							table.setValueAt(classType, 1, 3);
							break;
						case "2":
							classType = "요가 2";
							table.setValueAt(classType, 2, 3);
							break;
						case "3":
							classType = "요가 3";
							table.setValueAt(classType, 4, 3);
							break;
						case "4":
							classType = "요가 4";
							table.setValueAt(classType, 5, 3);
							break;
						case "5":
							classType = "요가 5";
							table.setValueAt(classType, 6, 3);
							break;
						case "6":
							classType = "요가 6";
							table.setValueAt(classType, 7, 3);
							break;
						case "7":
							classType = "요가 7";
							table.setValueAt(classType, 8, 3);
							break;
						case "8":
							classType = "요가 8";
							table.setValueAt(classType, 9, 3);
							break;
						case "9":
							classType = "요가 9";
							table.setValueAt(classType, 10, 3);
							break;
						case "10":
							classType = "요가 10";
							table.setValueAt(classType, 11, 3);
							break;
						case "11":
							classType = "요가 11";
							table.setValueAt(classType, 12, 3);
							break;
						case "12":
							classType = "요가 12";
							table.setValueAt(classType, 13, 3);
							break;

						default:
							classType = "알 수 없는 타입";
							break;
						}
						break;
					case "4":
						switch (vo.getClass_time()) {
						case "1":
							classType = "필라테스 1";
							table.setValueAt(classType, 1, 4);
							break;
						case "2":
							classType = "필라테스 2";
							table.setValueAt(classType, 2, 4);
							break;
						case "3":
							classType = "필라테스 3";
							table.setValueAt(classType, 4, 4);
							break;
						case "4":
							classType = "필라테스 4";
							table.setValueAt(classType, 5, 4);
							break;
						case "5":
							classType = "필라테스 5";
							table.setValueAt(classType, 6, 4);
							break;
						case "6":
							classType = "필라테스 6";
							table.setValueAt(classType, 7, 4);
							break;
						case "7":
							classType = "필라테스 7";
							table.setValueAt(classType, 8, 4);
							break;
						case "8":
							classType = "필라테스 8";
							table.setValueAt(classType, 9, 4);
							break;
						case "9":
							classType = "필라테스 9";
							table.setValueAt(classType, 10, 4);
							break;
						case "10":
							classType = "필라테스 10";
							table.setValueAt(classType, 11, 4);
							break;
						case "11":
							classType = "필라테스 11";
							table.setValueAt(classType, 12, 4);
							break;
						case "12":
							classType = "필라테스 12";
							table.setValueAt(classType, 13, 4);
							break;

						default:
							classType = "알 수 없는 타입";
							break;
						}
						break;

					default:
						classType = "알 수 없는 타입";
						break;
					}

					System.out.println(vo.getClass_type() + vo.getClass_time());
				}
			} else {
				System.out.println("테이블 실패");
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}

class ButtonRenderer extends DefaultTableCellRenderer {
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		// 특정 열과 조건을 만족하는 경우에만 버튼 생성
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

	public ButtonEditor() {
		button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 버튼 클릭 시 동작을 수행할 수 있도록 구현
				System.out.println("Button clicked");
				JOptionPane.showMessageDialog(button, "눌러");
				fireEditingStopped();
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
		return super.stopCellEditing();
	}

}