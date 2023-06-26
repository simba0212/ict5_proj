package com.ict5.admin;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;

public class Admin_TimeTable extends JPanel {
	private JTable table;
	Admin_main main;
	CardLayout cardLayout;

	public Admin_TimeTable(Admin_main main) {
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

		JLabel lblNewLabel = new JLabel("<html><h1>오늘의 강의목록</h1></html>");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		north1.add(lblNewLabel, BorderLayout.WEST);

		JButton btnNewButton = new JButton("강의추가");
		btnNewButton.setPreferredSize(new Dimension(100, 50));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		north1.add(btnNewButton, BorderLayout.EAST);

		JButton btnNewButton_1_1 = new JButton("<<");
		btnNewButton_1_1.setPreferredSize(new Dimension(50, 30));
		north2.add(btnNewButton_1_1);

		JLabel lblNewLabel_2 = new JLabel(new ImageIcon("src/images/calender.png"));
		north2.add(lblNewLabel_2);

		JTextField date = new JTextField();
		date.setColumns(20);
		date.setPreferredSize(new Dimension(100, 50));
		north2.add(date);

		JButton btnNewButton_1 = new JButton(">>");
		btnNewButton_1.setPreferredSize(new Dimension(50, 30));
		north2.add(btnNewButton_1);
		north.add(north1, BorderLayout.NORTH);
		north.add(north2, BorderLayout.SOUTH);

		add(north, BorderLayout.NORTH);

		Object[][] data = { { "09:00", "", "", "", "" }, { "10:00", "", "수업1", "", "" }, { "11:00", "", "", "수업2", "" },
				{ "12:00", "", "", "", "" }, { "13:00", "", "", "", "" }, { "14:00", "", "", "", "" },
				{ "15:00", "", "", "", "" }, { "16:00", "", "", "", "" }, { "17:00", "", "", "", "" },
				{ "18:00", "", "", "", "" }, { "19:00", "", "", "", "" }, { "20:00", "", "", "", "" },
				{ "21:00", "", "", "", "" } };
		String[] columnNames = { "6월 10일", "P.T", "요가", "수영", "필라테스" };

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return !(data[row][column].toString().equals("") || column == 0);
			}
		};

		TableCellRenderer buttonRenderer = new ButtonRenderer();
		TableCellEditor buttonEditor = new ButtonEditor(main);

		table = new JTable(model);
		table.setRowHeight(40);
		table.setShowVerticalLines(false);
		table.setDefaultRenderer(Object.class, buttonRenderer);
		table.setDefaultEditor(Object.class, buttonEditor);

		// Add the table to a scroll pane and display it
		JScrollPane jsp = new JScrollPane(table);
		jsp.setPreferredSize(new Dimension(600, 544));
		add(jsp, BorderLayout.SOUTH);
		
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
		} else if (column == 0) {
			return new JLabel(value.toString(), JLabel.CENTER);
		}

		else {
			// Render other cells normally
			return new JLabel(value.toString());
		}
	}
}
class ButtonEditor extends DefaultCellEditor {
	private JButton button;
	
	public ButtonEditor(Admin_main main) {
		super(new JTextField());
		button = new JButton("Button");
		button.addActionListener(e -> {
			// Button action
			JButton jb = (JButton)e.getSource();
				
			main.cardlayout.show(main.pg1, "home");
			JOptionPane.showMessageDialog(button, "눌러");
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
