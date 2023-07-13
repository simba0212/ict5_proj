package com.ict5.admin.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.ict5.admin.Admin_main;
import com.ict5.db.VO;

public class Point_new extends JPanel {
	JTable table;
	Admin_main main;
	DefaultTableModel model;
	String[] columnNames;
	Object[][] data;

	public Point_new(Admin_main main) {
		this.main = main;

		setLayout(new BorderLayout());
		JLabel titleLabel = new JLabel("최근 포인트 신청");
		titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
		Font labelFont = titleLabel.getFont().deriveFont(Font.BOLD, 20f);
		titleLabel.setFont(labelFont);
		add(titleLabel, BorderLayout.NORTH);
		setBackground(Color.lightGray);

		columnNames = new String[] { "이름", "신청포인트", "입금상태", "지급상태", "지급하기", "신청날짜" };
		data = new Object[1][6];

		model = new DefaultTableModel(data, columnNames) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false; // 모든 칸을 편집 불가능하게 설정
		    }
		};



		TableCellRenderer buttonRenderer = new ButtonRenderer();
		TableCellEditor buttonEditor = new ButtonEditor();

		table = new JTable(model);
		table.setRowHeight(30);
		table.getColumn("신청날짜").setPreferredWidth(200);
		table.getColumn("신청포인트").setPreferredWidth(100);
		table.setDefaultRenderer(Object.class, buttonRenderer);
		table.setDefaultEditor(Object.class, buttonEditor);
		table.getTableHeader().setReorderingAllowed(false);

		JScrollPane jsp = new JScrollPane(table);
		jsp.setPreferredSize(new Dimension(600, 300));
		add(jsp, BorderLayout.SOUTH);
	}

	public void PointApprove() {
		List<VO> member = main.list;
		String charge;

		if (member == null) {
			// main.list가 null인 경우 처리를 중단하거나, 빈 리스트로 초기화할 수 있습니다.
			// member = new ArrayList<>();
			return;
		}

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int rowCount = model.getRowCount();
		int columnCount = model.getColumnCount();

		if (member.size() > rowCount) {
			// 만약 member 리스트의 크기가 테이블의 행 개수보다 크다면, 테이블의 행 개수를 늘려줍니다.
			for (int i = rowCount; i < member.size(); i++) {
				model.addRow(new Object[columnCount]);
			}
		}

		for (int i = 0; i < member.size(); i++) {
			VO vo = member.get(i);
			model.setValueAt(vo.getMember_name(), i, 0);
			model.setValueAt(vo.getPoint_money(), i, 1);
			model.setValueAt(vo.getPoint_signup_date(), i, 5);

			if (vo.getPoint_charge_date() != null) {
				charge = "입금완료";
				model.setValueAt(charge, i, 2);
			}else {
				charge = "입금대기";
				model.setValueAt(charge, i, 2);
			}
			
			if (charge.equals("입금완료")) {
				charge = "지급";
				model.setValueAt(charge, i, 4);
			}
		}
	}

	class ButtonRenderer extends DefaultTableCellRenderer {
	    private JButton button; // 버튼 객체 추가

	    public ButtonRenderer() {
	        button = new JButton();
	        button.setOpaque(true);
	        button.setBackground(null);

	        // 버튼 클릭 이벤트 리스너 추가
	        button.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                System.out.println("Button clicked");
	                JOptionPane.showMessageDialog(button, "눌러");
	            }
	        });
	    }

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
	            int row, int column) {
	        if (value != null && column == 4 && value.toString().equals("지급")) {
	            button.setText(value.toString());
	            return button;
	        } else {
	            // 빈 값인 경우 텍스트 필드를 표시
	            JTextField textField = new JTextField();
	            textField.setText(value != null ? value.toString() : "");
	            return textField;
	        }
	    }
	}


	class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
	    private JButton button;

	    public ButtonEditor() {
	        button = new JButton("지급");
	        button.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                System.out.println("Button clicked");
	                JOptionPane.showMessageDialog(button, "포인트 지급");
	                fireEditingStopped();
	            }
	        });
	    }

	    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
	            int column) {
	        // Edit the specific cells in the desired columns using the button editor
	        return button;
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
}