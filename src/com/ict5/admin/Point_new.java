package com.ict5.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class Point_new extends JPanel {
	private JTable table;

	public Point_new() {
		setLayout(new BorderLayout());
		JLabel titleLabel = new JLabel("최근 포인트 신청");
		Font labelFont = titleLabel.getFont().deriveFont(Font.BOLD,20f);
		titleLabel.setFont(labelFont);
		add(titleLabel, BorderLayout.NORTH);
		String[] columnNames = { "이름", "신청포인트", "입금상태", "지급상태", "지급하기", "신청날짜" };
		Object[][] data = { { "고길동", 50000, "입금완료", "지급대기", "지급", "2023-06-10" },
				{ "둘리", 10000, "입금대기", "지급대기", "", "2023-06-10" }, { "도우너", 20000, "입금대기", "지급대기", "", "2023-06-10" },
				{ "마이콜", 50000, "입금완료", "지금완료", "", "2023-06-10" }, { "또치", 18000, "입금대기", "지급대기", "", "2023-06-10" },
				{ "심바", 10000000, "입금완료", "지급완료", "", "2023-06-10" },
				{ "희동", 5000, "입금완료", "지급대기", "지급", "2023-06-10" },
				{ "공실이", 70000, "입금완료", "지급완료", "", "2023-06-10" } };

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return (!(data[row][column].toString().equals("")) && column == 4);
			}
		};

		TableCellRenderer buttonRenderer = new ButtonRenderer();
		TableCellEditor buttonEditor = new ButtonEditor();

		table = new JTable(model);
		table.setRowHeight(30);
		table.getColumn("신청날짜").setPreferredWidth(200);
		table.getColumn("신청포인트").setPreferredWidth(100);
//		table.setShowVerticalLines(false);
		table.setDefaultRenderer(Object.class, buttonRenderer);
		table.setDefaultEditor(Object.class, buttonEditor);

		// Add the table to a scroll pane and display it
		JScrollPane jsp = new JScrollPane(table);
		jsp.setPreferredSize(new Dimension(600, 300));
		add(jsp, BorderLayout.SOUTH);
	}

	class ButtonRenderer extends JButton implements TableCellRenderer {
		public ButtonRenderer() {
			setOpaque(true);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			// Render the specific cells in the third row as buttons
			if ((!(value.toString().equals("")) && (column == 4))) {
				setText(value.toString());
				setBackground(Color.white);
				return this;
			} else if (column == 0) {
				return new JLabel(value.toString(), JLabel.CENTER);
			}

			else {

				// Render other cells normally
				return new JLabel(value.toString(), JLabel.CENTER);
			}
		}
	}

	class ButtonEditor extends DefaultCellEditor {
		private JButton button;

		public ButtonEditor() {
			super(new JTextField());
			button = new JButton("Button");
			button.addActionListener(e -> {
				// Button action
				JOptionPane.showMessageDialog(button, "Button clicked!");
				fireEditingStopped();
			});
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			// Edit the specific cells in the third row using the button editor
			if (!(value.toString().equals("") || column == 0)) {
				button.setText(value.toString());
				return button;
			} else {
				// Edit other cells normally
				return super.getTableCellEditorComponent(table, value, isSelected, row, column);
			}
		}

		@Override
		public Object getCellEditorValue() {
			return button.getText();
		}
	}
}
