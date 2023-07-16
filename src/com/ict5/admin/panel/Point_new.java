package com.ict5.admin.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.ict5.admin.Admin_main;
import com.ict5.db.Protocol;
import com.ict5.db.VO;

public class Point_new extends JPanel {
    JTable table;
    Admin_main main;
    DefaultTableModel model;
    String[] columnNames;
    Object[][] data;

    public Point_new(Admin_main main) {
        this.main = main;
        
        // 테이블을 초기화합니다.
        table = new JTable();

        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("최근 포인트 신청");
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
        Font labelFont = titleLabel.getFont().deriveFont(Font.BOLD, 20f);
        titleLabel.setFont(labelFont);
        add(titleLabel, BorderLayout.NORTH);
        setBackground(Color.lightGray);

        columnNames = new String[] {"신청번호", "이름", "신청포인트", "입금상태", "지급상태", "지급하기", "신청날짜" };
        data = new Object[1][7];

        model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // "승인 여부" 열은 수정할 수 없도록 설정
                return column == 5;
            }
        };

        // Create a custom cell renderer for the "지급하기" column
        TableCellRenderer buttonRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (column == 5 && value != null && value.toString().equals("승인")) {
                    JButton button = new JButton("지급");
                    return button;
                } else {
                    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                }
            }
        };

        table.setModel(model);
        table.setRowHeight(30);
        table.getColumnModel().getColumn(6).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setCellRenderer(buttonRenderer); // Set the custom cell renderer for the button column
        table.getTableHeader().setReorderingAllowed(false);

        JScrollPane jsp = new JScrollPane(table);
        jsp.setPreferredSize(new Dimension(600, 300));
        add(jsp, BorderLayout.SOUTH);
        
        // 테이블에 대한 TableRowSorter를 생성합니다.
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        
        // 정렬 순서를 정의합니다.
        sorter.setSortKeys(List.of(
            new RowSorter.SortKey(5, SortOrder.DESCENDING)   // "지급상태" 열의 인덱스 4, 내림차순 정렬
        ));

        // 테이블에 TableRowSorter를 설정합니다.
        table.setRowSorter(sorter);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    int row = table.getSelectedRow();
                    int col = table.getSelectedColumn();
                    Object obj = table.getValueAt(row, col);
                    if (obj.toString().equals("승인")) {
                        int res = JOptionPane.showConfirmDialog(null, "정말 승인하시겠습니까?", "승인확인",
                                JOptionPane.YES_NO_OPTION);
                        if (res == 0) {
                            obj = table.getValueAt(row, 0);
                            String charge_num = obj.toString();
                            Protocol p = new Protocol();
                            VO vo = new VO();
                            vo.setCharge_num(charge_num);
                            p.setVo(vo);
                            p.setCmd(1005);
                            main.out.writeObject(p);
                            main.out.flush();
                            
                        }
                    }
                } catch (Exception e2) {
                    // TODO: handle exception
                }

            }
        });
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
            
            model.setValueAt(vo.getCharge_num(), i, 0);
            model.setValueAt(vo.getMember_name(), i, 1);
            model.setValueAt(vo.getPoint_money(), i, 2);
            model.setValueAt(vo.getPoint_signup_date(), i, 6);

            String charge;
            if (vo.getPoint_charge_date() != null) {
                charge = "입금완료";
                model.setValueAt(charge, i, 3);
                charge = "지급대기";
                model.setValueAt(charge, i, 4);
                
                charge = "승인";
                model.setValueAt(charge, i, 5);
                
                if(vo.getPoint_approve() !=null) {
                    charge = "지급완료";
                    model.setValueAt(charge, i, 4);
                    
                    charge = "";
                    model.setValueAt(charge, i, 5);
                }
            } else {
                charge = "입금대기";
                model.setValueAt(charge, i, 3);
                charge = "";
                model.setValueAt(charge, i, 4);
            }

            if (charge.equals("입금완료")) {
                
            }

        }
        
    }
    
    // 테이블 데이터 초기화 메서드
    public void clearTableData() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
    }
}
