package com.ict5.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.table.TableCellRenderer;

public class Point_new extends JPanel {
    private JTable table;

    public Point_new() {
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("최근 포인트 신청");
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));        
        Font labelFont = titleLabel.getFont().deriveFont(Font.BOLD, 20f);
        titleLabel.setFont(labelFont);
        add(titleLabel, BorderLayout.NORTH);
        setBackground(Color.lightGray);
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
        table.setDefaultRenderer(Object.class, buttonRenderer);
        table.setDefaultEditor(Object.class, buttonEditor);
        table.getTableHeader().setReorderingAllowed(false);

        // Add the table to a scroll pane and display it
        JScrollPane jsp = new JScrollPane(table);
        jsp.setPreferredSize(new Dimension(600, 300));
        add(jsp, BorderLayout.SOUTH);
    }

    class ButtonRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // 특정 열과 조건을 만족하는 경우에만 버튼 생성
            if ((column == 4 && value.toString().equals("지급"))) {
                JButton button = new JButton(value.toString());
                button.setOpaque(true);
                button.setBackground(null);
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // 버튼 클릭 시 동작을 수행할 수 있도록 구현
                        System.out.println("Button clicked");
                        JOptionPane.showMessageDialog(button, "눌러");
                    }
                });
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
            if ((column == 4 && value.toString().equals("지급"))) {
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
}