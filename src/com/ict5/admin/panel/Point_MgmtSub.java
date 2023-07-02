package com.ict5.admin;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class Point_MgmtSub extends JPanel {
	Admin_main main;
	CardLayout cardLayout;
	
	public Point_MgmtSub(Admin_main main) {
		this.main = main;

		JPanel jp1 = new JPanel(new BorderLayout());
		JPanel jp1_1 = new JPanel(new BorderLayout());
		JPanel jp2 = new JPanel(new BorderLayout());
		JPanel jp2_1 = new JPanel(new BorderLayout());

		JLabel jl1 = new JLabel("회원관리");
		jl1.setFont(jl1.getFont().deriveFont(Font.BOLD,17f));
		jl1.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

		jp1_1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		// "포인트관리" 버튼 생성
		JLabel pointManagementLabel = new JLabel("회원검색");
		pointManagementLabel.setFont(pointManagementLabel.getFont().deriveFont(Font.BOLD,17f));
		pointManagementLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		jp1_1.add(pointManagementLabel);
		
		// Create the "회원검색" button
		JLabel memberSearchLabel = new JLabel("포인트관리");
		memberSearchLabel.setFont(memberSearchLabel.getFont().deriveFont(Font.BOLD,17f));
		memberSearchLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		jp1_1.add(memberSearchLabel);

		// JPanel에 "회원검색" 버튼 추가
		jp1_1.add(pointManagementLabel, BorderLayout.EAST);
		jp1_1.add(memberSearchLabel, BorderLayout.EAST);

		jp1.add(jl1, BorderLayout.WEST);

		jp1_1.setBackground(Color.lightGray);
		jp1.setBackground(Color.lightGray);
		jp1.add(jp1_1, BorderLayout.AFTER_LINE_ENDS);

		jp2_1.add(new JLabel("  "), BorderLayout.CENTER);
		jp2.add(jp2_1, BorderLayout.WEST);
		jp1.setPreferredSize(new Dimension(1280, 60));
		jp2.setPreferredSize(new Dimension(1200, 40));

		// 가변 데이터 수를 결정할 변수
		int rowCount = 25; // 예시로 5개의 데이터 행을 가정합니다.

		// 열 제목 지정
		String[] columnNames = { "회원번호", "이름", "ID", "전화번호", "보유 포인트", "신청 포인트", "신청 날짜", "입금상태", "승인 여부" };

		// 테이블 모델 생성
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);

		// 데이터 추가
		for (int i = 1; i <= rowCount; i++) {
			Object[] rowData = { i, "노종문씨", "idsmsrlftneh", "010-1234-1234", "12345", "45678", "1999-12-34", "입금",
					"승인" };

			model.addRow(rowData);
		}

		// 버튼을 렌더링하는 TableCellRenderer 구현
		class ButtonRenderer extends JButton implements TableCellRenderer {
			public ButtonRenderer() {
				setOpaque(true);
			}

			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				setText(value.toString());
				return this;
			}
		}

		// 버튼을 편집하는 TableCellEditor 구현
		class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
			private JButton button;

			public ButtonEditor() {
				button = new JButton();
				button.addActionListener(e -> {
					// 버튼 클릭 시 동작을 수행할 수 있도록 구현
					System.out.println("Button clicked");
					fireEditingStopped();
				});
			}

			public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
					int column) {
				button.setText(value.toString());
				return button;
			}

			public Object getCellEditorValue() {
				return button.getText();
			}
		}

		// 테이블 생성 및 모델 설정
		JTable table = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// "승인 여부" 열은 수정할 수 없도록 설정
				return column == 8;
			}
		};
		table.getTableHeader().setReorderingAllowed(false);

		// 승인 여부 열에 버튼 렌더러와 에디터 설정
		TableColumn approveColumn = table.getColumnModel().getColumn(8);
		approveColumn.setCellRenderer(new ButtonRenderer());
		approveColumn.setCellEditor(new ButtonEditor());

		// 테이블 각 열 너비지정
		TableColumnModel columnModel = table.getColumnModel();
		int[] columnWidths = { 55, 60, 100, 100, 100, 100, 80, 80, 80 };
		for (int i = 0; i < columnNames.length; i++) {
			TableColumn column = columnModel.getColumn(i);
			column.setPreferredWidth(columnWidths[i]);
		}

		// 테이블을 담을 스크롤 패널 생성
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(table.getPreferredSize().width, table.getPreferredSize().height));

		// 프레임 생성 및 테이블 스크롤 패널 추가
		add(jp1);
		add(jp2);
		add(scrollPane);

		// 데이터 클릭 이벤트 리스너 등록
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int column = table.getSelectedColumn();
				if (column == 8) {
					// "승인 여부" 열의 버튼 클릭 동작 수행
					System.out.println("승인 여부 버튼 클릭됨");
					// 추가 동작 수행
				}
			}
		});
		
		memberSearchLabel.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        memberSearchLabel.setForeground(Color.red);
		    }
		    
		    @Override
		    public void mouseExited(MouseEvent e) {
		        memberSearchLabel.setForeground(Color.black);
		    }
		});
		
		
		
		jl1.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseEntered(MouseEvent e) {
		    	jl1.setForeground(Color.red);
		    }
		    
		    @Override
		    public void mouseExited(MouseEvent e) {
		    	jl1.setForeground(Color.black);
		    }
		});
		
		jl1.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        main.cardlayout.show(main.pg1, "member"); // "member" 페이지로 이동
		    }
		});
		
		
	}
}
