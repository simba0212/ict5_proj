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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import com.ict5.admin.Admin_main;
import com.ict5.db.VO;

public class Point_new extends JPanel {
    JTable table;
    Admin_main main;
    DefaultTableModel model;
    String[] columnNames;
    Object[][] data;
    JButton button; // 버튼 객체를 멤버 변수로 선언

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
                Object value = getValueAt(row, column);
                return value != null && column == 4 && value.toString().equals("지급");
            }
        };

        ButtonRenderer renderer = new ButtonRenderer();
        ButtonEditor editor = new ButtonEditor();
        button = new JButton("지급"); // 버튼 객체 초기화

        table = new JTable(model);
        table.setRowHeight(30);
        table.getColumn("신청날짜").setPreferredWidth(200);
        table.getColumn("신청포인트").setPreferredWidth(100);
        table.getColumn("지급하기").setCellRenderer(renderer);
        table.getColumn("지급하기").setCellEditor(editor);
        table.getTableHeader().setReorderingAllowed(false);

        JScrollPane jsp = new JScrollPane(table);
        jsp.setPreferredSize(new Dimension(600, 300));
        add(jsp, BorderLayout.SOUTH);
    }

    public void PointApprove() {
        List<VO> member = main.list;

        if (member == null) {
            return;
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        int columnCount = model.getColumnCount();

        if (member.size() > rowCount) {
            for (int i = rowCount; i < member.size(); i++) {
                model.addRow(new Object[columnCount]);
            }
        }

        for (int i = 0; i < member.size(); i++) {
            VO vo = member.get(i);
            model.setValueAt(vo.getMember_name(), i, 0);
            model.setValueAt(vo.getPoint_money(), i, 1);
            model.setValueAt(vo.getPoint_signup_date(), i, 5);

            String charge;
            if (vo.getPoint_charge_date() != null) {
                charge = "입금완료";
                model.setValueAt(charge, i, 2);
            } else {
                charge = "입금대기";
                model.setValueAt(charge, i, 2);
            }

            if (charge.equals("입금완료")) {
                charge = "지급";
                model.setValueAt(charge, i, 4);
                table.setValueAt(charge, i, 4); // 테이블에서도 값을 변경합니다.
            }
        }
    }

    class ButtonRenderer extends DefaultTableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            String text = (value != null) ? value.toString() : "";
            if (text.equals("지급")) {
                button.setEnabled(true);
                button.setBackground(table.getSelectionBackground());
                button.setForeground(table.getSelectionForeground());
            } else {
                button.setEnabled(false);
                button.setBackground(table.getBackground());
                button.setForeground(table.getForeground());
            }
            return button;
        }
    }

    class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            JButton button = new JButton("지급"); // 각 버튼을 독립적으로 생성
            button.addActionListener(new ActionListener() {
                private boolean clicked = false;

                public void actionPerformed(ActionEvent e) {
                    if (!clicked) {
                        clicked = true;
                        JOptionPane.showMessageDialog(null, "포인트 지급");
                        // 여기서 원하는 동작을 수행합니다.
                        System.out.println("버튼 클릭");
                    }
                    button.setEnabled(false); // 버튼을 비활성화
                    fireEditingStopped();
                }
            });

            String text = (value != null) ? value.toString() : "";
            button.setText(text); // 버튼의 텍스트 설정

            if (isSelected) {
                button.setBackground(table.getSelectionBackground());
                button.setForeground(table.getSelectionForeground());
            } else {
                button.setBackground(table.getBackground());
                button.setForeground(table.getForeground());
            }

            return button;
        }

        public Object getCellEditorValue() {
            return null;
        }
    }

}
