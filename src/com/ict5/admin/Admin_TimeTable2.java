package com.ict5.admin;

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

public class Admin_TimeTable2 extends JPanel {
	private JTable table;

	public Admin_TimeTable2() {
		// Create sample data for the table
		Object[][] data = { { "09:00", "", "", "", "" }, { "10:00", "", "수업1", "", "" }, { "11:00", "", "", "수업2", "" },
				{ "12:00", "", "", "", "" }, { "13:00", "", "", "", "" }, { "14:00", "", "", "", "" },
				{ "15:00", "", "", "", "" }, { "16:00", "", "", "", "" }, { "17:00", "", "", "", "" },
				{ "18:00", "", "", "", "" }, { "19:00", "", "", "", "" }, { "20:00", "", "", "", "" },
				{ "21:00", "", "", "", "" } };
		String[] columnNames = { "6월 10일", "P.T", "요가", "수영", "필라테스" };

		// Create a custom table model
		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// Make the specific cells in the third row editable
				return !(data[row][column].toString().equals("") || column == 0);
			}
		};

		// Create a custom cell renderer for the button cells
		TableCellRenderer buttonRenderer = new ButtonRenderer();

		// Create a custom cell editor for the button cells
		TableCellEditor buttonEditor = new ButtonEditor();

		// Create the table with the custom model and cell renderers/editors
		table = new JTable(model);
		table.setRowHeight(48);
		table.setShowVerticalLines(false);
		table.setDefaultRenderer(Object.class, buttonRenderer); // Apply renderer to all cells
		table.setDefaultEditor(Object.class, buttonEditor); // Apply editor to all cells

		// Add the table to a scroll pane and display it
		JScrollPane jsp = new JScrollPane(table);
		jsp.setPreferredSize(new Dimension(400, 650));
		add(jsp);

	}
}

class ButtonRenderer extends JButton implements TableCellRenderer {
	public ButtonRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// Render the specific cells in the third row as buttons
		if (!(value.toString().equals("") || column == 0)) {
			setText(value.toString());
			return this;
		} else if(column==0) {
			return new JLabel(value.toString(),JLabel.CENTER);
		}
		
		else {
			// Render other cells normally
			return new JLabel(value.toString());
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
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
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
