package com.ict5.admin.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
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
import com.ict5.db.Protocol;
import com.ict5.db.VO;

public class Member_view2 extends JPanel {
	Admin_main main;
	UnmodifiableTableModel model;
	UnmodifiableTableModel model2;
	UnmodifiableTableModel model3;

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
		String[] columnNames = { "항목", "내용" };

		String[][] data = { { "회원번호", null }, { "이름", null }, { "ID", null }, { "전화번호", null }, { "이메일", null },
				{ "성별", null }, { "생년월일", null }, { "주소", null }, { "보유포인트", null }, { "등록 날짜", null },
				{ "최근 출석일", null }, { "출석일수", null }, { "누적사용포인트", null }, { "누적충전포인트", null } };
		// 테이블 모델 생성
		model = new UnmodifiableTableModel(data, columnNames);
		model.isCellEditable(0, 0);
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

		table.setRowHeight(30); // 각 셀의 높이를 30으로 설정
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

		model2 = new UnmodifiableTableModel(columnNames2, 0);

		JTable table2 = new JTable(model2);
		table2.getTableHeader().setReorderingAllowed(false);

		JScrollPane scrollPane2 = new JScrollPane(table2);
		scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane2.setPreferredSize(new Dimension(640, 235));
		classlabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
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

		String[] columnNames3 = { "포인트변동번호", "변동내용", "변동날짜", "사용내역", "변동포인트", "출석여부" };
		// 테이블 모델 생성
		model3 = new UnmodifiableTableModel(columnNames3, 0);
		// 데이터 추가

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

		jp.add(jp1, BorderLayout.NORTH);
		jp.add(jp2, BorderLayout.WEST);
		jp.add(jp3, BorderLayout.EAST);
		add(jp);

	}

	public void refresh1() { // 한명정보 불러오기
		// 왼쪽테이블 (회원상세정보)
		VO vo = main.vo;
		// 추가할 데이터
		model.setValueAt(vo.getMember_num(), 0, 1);
		model.setValueAt(vo.getMember_name(), 1, 1);
		model.setValueAt(vo.getMember_id(), 2, 1);
		model.setValueAt(vo.getMember_phone(), 3, 1);
		model.setValueAt(vo.getMember_mail(), 4, 1);
		model.setValueAt(vo.getMember_gen(), 5, 1);
		model.setValueAt(vo.getMember_birth(), 6, 1);
		model.setValueAt(vo.getMember_addr(), 7, 1);
		model.setValueAt(vo.getMember_point(), 8, 1);
		model.setValueAt(vo.getMember_signup_date(), 9, 1);
		model.setValueAt(vo.getAttendent_date(), 10, 1);
		model.setValueAt(vo.getAttendent_month(), 11, 1);
		model.setValueAt(vo.getMember_usep(), 12, 1);
		model.setValueAt(vo.getMember_chargep(), 13, 1);

		// 오른쪽 테이블 불러오기
		try {
			Protocol p = new Protocol();
			p.setVo(vo);
			p.setCmd(1204);
			main.out.writeObject(p);
			main.out.flush();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void refresh2() { // 오른쪽 테이블 예약목록
		model2.setRowCount(0);
		List<VO> list = main.list;
		for (VO k : list) {
			switch (k.getClass_type()) {
			case "1":
				k.setClass_time("헬스");
				break;
			case "2":
				k.setClass_time("요가");
				break;
			case "3":
				k.setClass_time("수영");
				break;
			case "4":
				k.setClass_time("필라테스");
				break;

			default:
				break;
			}
			if (k.getAttendent_time() != null) {
				k.setAttendent_time("출석완료");
			} else {
				k.setAttendent_time("출석전");
			}
			if (k.getBook_date() != null) {
				k.setBook_date(k.getBook_date().substring(0, 10));
			}
			if (k.getClass_date() != null) {
				k.setClass_date(k.getClass_date().substring(0, 10));
			}
			Object[] rowData = { k.getClass_num(), k.getClass_time(), k.getBook_date(), k.getClass_date(),
					k.getTeacher_name(), k.getAttendent_time() };
			model2.addRow(rowData);
		}

		// 포인트충전 / 사용내역 불러오기
		try {
			Protocol p = new Protocol();
			VO vo = main.vo;
			p.setVo(vo);
			p.setCmd(1205);
			main.out.writeObject(p);
			main.out.flush();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void refresh3() {
		model3.setRowCount(0);
		List<VO> list = main.list;
		
		for (VO k : list) {
			if(k.getPoint_change_date()!=null) {
				k.setPoint_change_date(k.getPoint_change_date().substring(0,10));
			}
			if(k.getPoint_type().equals("사용")) {
				k.setPoint("<html><p style=\"color: red;\">-"+k.getPoint()+"</p>");
			}else {
				k.setPoint("<html><p style=\"color: blue;\">+"+k.getPoint()+"</p>");
			}
			Object[] rowData = { k.getPoint_num(), k.getPoint_type(), k.getPoint_change_date(), k.getPoint_name(), k.getPoint() };
			model3.addRow(rowData);

		}

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

	@SuppressWarnings("serial")
	public class UnmodifiableTableModel extends DefaultTableModel {
		public UnmodifiableTableModel(String[][] data, String[] columnNames) {
			super(data, columnNames);
		}

		public UnmodifiableTableModel(String[] columnNames2, int i) {
			super(columnNames2, i);
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}

}
