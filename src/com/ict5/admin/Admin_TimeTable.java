package com.ict5.admin;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.AbstractCellEditor;
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

public class Admin_TimeTable extends JPanel {
	String[] label;
	String[][] data;

	private DefaultTableModel dtm;
	private JTable jtable;
	private JScrollPane jsp;

	public Admin_TimeTable() {
		setBackground(Color.white);

		label = new String[] { "6월 10일", "P.T", "요가", "수영", "필라테스" };
		data = new String[][] { { "09:00", "", "", "", "" }, { "10:00", "", "수업1", "", "" },
				{ "11:00", "", "", "수업2", "" }, { "12:00", "", "", "", "" }, { "13:00", "", "", "", "" },
				{ "14:00", "", "", "", "" }, { "15:00", "", "", "", "" }, { "16:00", "", "", "", "" },
				{ "17:00", "", "", "", "" }, { "18:00", "", "", "", "" }, { "19:00", "", "", "", "" },
				{ "20:00", "", "", "", "" }, { "21:00", "", "", "", "" } };

		dtm = new DefaultTableModel(data, label);
		jtable = new JTable(dtm);
		jtable.setShowVerticalLines(false);
		jtable.setPreferredSize(new Dimension(400, 600));
		jtable.setRowHeight(45);

		jsp = new JScrollPane(jtable);
		jsp.setPreferredSize(new Dimension(400, 650));


		jtable.getColumnModel().getColumn(1).setCellRenderer(new TableCell());
		jtable.getColumnModel().getColumn(2).setCellRenderer(new TableCell());
		jtable.getColumnModel().getColumn(3).setCellRenderer(new TableCell());
		jtable.getColumnModel().getColumn(4).setCellRenderer(new TableCell());
		jtable.getColumnModel().getColumn(1).setCellEditor(new TableCell());
		jtable.getColumnModel().getColumn(2).setCellEditor(new TableCell());
		jtable.getColumnModel().getColumn(3).setCellEditor(new TableCell());
		jtable.getColumnModel().getColumn(4).setCellEditor(new TableCell());

		add(jsp);

	}

	class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {

		JButton jb;

		public TableCell() {
//			// TODO Auto-generated constructor stub
//			setOpaque(true);
			jb = new JButton();
//
//			jb.addActionListener(e -> {
//				// 버튼클릭시 행동
//				JButton jb1 = (JButton) e.getSource();
//				System.out.println("클릭");
//			});

		}

		@Override
		public Object getCellEditorValue() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			String buttonText = data[row][column];
			jb.setText(buttonText);
			if (data[row][column].equals("") || data[row][column].isEmpty()) {
				return null;
			}
			return jb;
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			// TODO Auto-generated method stub
			String buttonText = data[row][column];
			jb.setText(buttonText);
			return jb;
		}

	}
}

