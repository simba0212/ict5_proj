package com.ict5.admin.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.ict5.admin.Admin_main;
import com.ict5.db.VO;

public class Member_view2 extends JPanel {
	Admin_main main;
	DefaultTableModel model;
	DefaultTableModel model2;
	DefaultTableModel model3;

	public Member_view2(Admin_main main) {
		this.main = main;
		setPreferredSize(new Dimension(1280, 600));
		JPanel jp = new JPanel(new BorderLayout());
		JPanel jp1 = new JPanel(new BorderLayout());
		JPanel jp1_1 = new JPanel(new BorderLayout());

		JLabel jl1 = new JLabel("회원관리");
		jl1.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 10));
		jl1.setFont(jl1.getFont().deriveFont(Font.BOLD, 17f));
		JLabel jl2 = new JLabel("회원검색");
		jl2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		jl2.setFont(jl2.getFont().deriveFont(Font.BOLD, 17f));
		JLabel jl3 = new JLabel("포인트관리");
		jl3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 40));
		jl3.setFont(jl3.getFont().deriveFont(Font.BOLD, 17f));

		jp1.add(jl1, BorderLayout.WEST);
		jp1_1.add(jl2, BorderLayout.WEST);
		jp1_1.add(jl3, BorderLayout.EAST);
		jp1_1.setBackground(Color.lightGray);
		jp1.setBackground(Color.lightGray);
		jp1.add(jp1_1, BorderLayout.AFTER_LINE_ENDS);
		jp1.setPreferredSize(new Dimension(1280, 60));

		// 열 제목 지정
		String[][] data = {

				{ "이름", "변수2" }, { "ID", "변수3" }, { "전화번호", "변수4" }, { "이메일", "변수5" }, { "성별", "변수6" },
				{ "생년월일", "변수7" }, { "최근예약", "변수8" }, { "주소", "변수9" }, { "보유포인트", "변수10" }, { "등록 날짜", "변수11" },
				{ "최근 출석일", "변수12" }, { "출석일수", "변수13" }, { "사용포인트(달)", "변수14" }, { "충전포인트(달)", "변수15" },
				{ "누적사용포인트", "변수16" }, { "누적충전포인트", "변수17" },
				// 추가할 데이터
		};
		String[] columnNames = { "항목", "내용" };

		// 테이블 모델 생성
		model = new DefaultTableModel(columnNames, 0);
		Vector<Object> rowData2 = new Vector<>();
		rowData2.add("이름");
		rowData2.add("홍길동");
		model.addRow(rowData2);

		// 테이블 생성 및 모델 설정
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(540, 580));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		table.getTableHeader().setReorderingAllowed(false);

		// 테이블 속 텍스트 가운데 정렬을 위한 코드 -> 하단에 클래스도 필요
		TableColumnModel columnModel = table.getColumnModel();
		TableColumn column = columnModel.getColumn(0);
		TableColumn column2 = columnModel.getColumn(1);
		column.setCellRenderer(new CenterTableCellRenderer());
		column2.setCellRenderer(new CenterTableCellRenderer());

		table.setFont(new Font("돋움", Font.BOLD, 25));
		table.setRowHeight(37); // 각 셀의 높이를 30으로 설정
		JPanel jp2 = new JPanel();
		jp2.add(scrollPane);

		jp2.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JPanel jp3 = new JPanel(new BorderLayout());

		// jp3_1 은 오른쪽 위 패널
		JPanel jp3_1 = new JPanel(new BorderLayout());
		JLabel classlabel = new JLabel("수업 예약 내역");
		classlabel.setFont(classlabel.getFont().deriveFont(Font.BOLD, 20f));
		String[] columnNames2 = { "수업 번호", "종류", "예약날짜", "수강날짜", "강사이름", "출석여부" };
		// 테이블 모델 생성
		DefaultTableModel model2 = new DefaultTableModel(columnNames2, 0);
		int rowCount = 25;
		// 데이터 추가
		for (int i = 1; i <= rowCount; i++) {
			Object[] rowData = { i, "abc", "2000-00-00", "2000-00-00", "노종문", "출석완료" };

			model2.addRow(rowData);
		}
		JTable table2 = new JTable(model2);
		table2.getTableHeader().setReorderingAllowed(false);

		JScrollPane scrollPane2 = new JScrollPane(table2);
		scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane2.setPreferredSize(new Dimension(640, 235));
		classlabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
//        scrollPane2.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 50));
		TableColumnModel columnModel3 = table2.getColumnModel();
		int columnCount = columnModel3.getColumnCount();
		for (int i = 0; i < columnCount; i++) {
			TableColumn column3 = columnModel3.getColumn(i);
			column3.setCellRenderer(new CenterTableCellRenderer());
		}
		jp3_1.add(classlabel, BorderLayout.NORTH);
		jp3_1.add(scrollPane2, BorderLayout.WEST);

		// jp3_2는 오른쪽 아래 패널
		JPanel jp3_2 = new JPanel(new BorderLayout());
		JLabel pointlabel = new JLabel("포인트 충전 / 사용 내역");
		pointlabel.setFont(pointlabel.getFont().deriveFont(Font.BOLD, 20f));
		pointlabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

		String[] columnNames3 = { "수업 번호", "종류", "예약날짜", "수강날짜", "강사이름", "출석여부" };
		// 테이블 모델 생성
		DefaultTableModel model3 = new DefaultTableModel(columnNames3, 0);
		int rowCount2 = 25;
		// 데이터 추가
		for (int i = 1; i <= rowCount2; i++) {
			Object[] rowData = { i, "abc", "2000-00-00", "2000-00-00", "노종문", "출석완료" };

			model3.addRow(rowData);
		}
		JTable table3 = new JTable(model3);
		table3.getTableHeader().setReorderingAllowed(false);

		JScrollPane scrollPane3 = new JScrollPane(table3);
		scrollPane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane3.setPreferredSize(new Dimension(640, 235));
		jp3_2.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 100));
		TableColumnModel columnModel4 = table3.getColumnModel();
		int columnCount2 = columnModel4.getColumnCount();
		for (int i = 0; i < columnCount2; i++) {
			TableColumn column4 = columnModel4.getColumn(i);
			column4.setCellRenderer(new CenterTableCellRenderer());
		}
		jp3_2.add(pointlabel, BorderLayout.NORTH);
		jp3_2.add(scrollPane3, BorderLayout.WEST);
		///////////////////////////////////////

		jp3.add(jp3_1, BorderLayout.NORTH);
		jp3.add(jp3_2, BorderLayout.WEST);
//		 String[] columnNames = {"회원번호", "이름", "ID","전화번호","이메일","성별","생년월일","최근예약","주소","보유 포인트","등록 날짜","최근 출석일","이번달출석","사용포인트","충전포인트(달)","누적사용포인트","누적충전포인트"};

		jp.add(jp1, BorderLayout.NORTH);
		jp.add(jp2, BorderLayout.WEST);
		jp.add(jp3, BorderLayout.EAST);
		add(jp);

	}

	public void refresh() { // 한명정보 불러오기

	}

	// 테이블 속 텍스트를 가운데정렬하기 위한 클래스
	public class CenterTableCellRenderer implements TableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
			renderer.setHorizontalAlignment(SwingConstants.CENTER);
			return renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		}
	}

}