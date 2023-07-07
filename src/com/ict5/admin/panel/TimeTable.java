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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
import com.ict5.db.CP_Client;
import com.ict5.db.DAO;
import com.ict5.db.DB_Server;
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

		Object[][] data = { { "09:00", "", "", "", "" }, { "10:00", "", "수업1", "수업1", "수업1" },
				{ "11:00", "", "", "수업2", "" }, { "12:00", "", "", "", "" }, { "13:00", "", "", "수업1", "" },
				{ "14:00", "", "", "", "" }, { "15:00", "", "", "", "" }, { "16:00", "", "", "", "" },
				{ "17:00", "", "", "", "" }, { "18:00", "", "", "", "" }, { "19:00", "", "", "", "" },
				{ "20:00", "", "", "", "" }, { "21:00", "", "", "수업1", "" } };
		String[] columnNames = { date2, "P.T", "요가", "수영", "필라테스" };

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
		
		

	}
	
	


	public void Date() {
		try {
			Protocol p = new Protocol();
			p.setCmd(1002);
			p.setVo(vo);
			main.out.writeObject(p);
			main.out.flush();

		} catch (IOException e) {
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
