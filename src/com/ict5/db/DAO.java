package com.ict5.db;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

// DB처리하는 메서드들을 가지고 있는 클래스
public class DAO {
	// 실제 사용하는 클래스 : SqlSession
	private static SqlSession ss;

	// 싱글턴 패턴 (동기화처리) : 프로그램이 종료될 때 까지 한번 만들어진 객체를 재 사용한다.
	private synchronized static SqlSession getSession() {
		if (ss == null) {
			ss = DBService.getFactory().openSession();
		}
		return ss;
	}

	// DB처리하는 메서드들
	// customer 테이블 전체보기
	// select , 결과는 여러개 , 파라미터가 없음
	public static List<VO> getList() {
		List<VO> list = null;
		// selectList() : 결과가 하나이상일때
		// selectOne() : 반드시 결과가 하나일때
		// 파라미터가 있는 메서드와 파라미터가 없는메서드로 나눈다.
		// 파라미터가 있는 메서드 : selectList("mepper의 id",파라미터);
		// 파라미터가 없는 메서드 : selectList("mepper의 id")
		list = getSession().selectList("memberlist");
		for (VO vo : list) {
			System.out.println(vo.getMember_id());
			System.out.println(vo.getMember_pw());
		}

		return list;
	}
	// select, 결과는 하나, 파라미터 있음(String)
	// insert, delete, update 결과 int, 파라미터있음
	// 반드시 commit를 해야 된다.

	public static VO getLoginChk(VO vo) {

		vo = getSession().selectOne("loginChk", vo);
		if (vo != null) {
			System.out.println("검색결과 있음");
			return vo;
		} else {
			System.out.println("검색결과 없음");
			return null;
		}
	}

	public static String getNotice() {
		List<VO> list = null;
		list = getSession().selectList("getNotice");
		VO vo = list.get(0);
		String str = vo.getNotice_content();

		return str;
	}

	public static VO getNearClasstime(VO vo) {
		vo = getSession().selectOne("mostclose", vo);
		return vo;
	}

	public static List<VO> t_bookclass(VO vo){
		List<VO> list = null;
		list = getSession().selectList("t_bookclass");
		
		
		return list;
	}
	public static List<VO> sel_date_class(VO vo){
		List<VO> list = null;
		list = getSession().selectList("sel_date_class",vo);
		return list;
	}

	public static VO setInsertJoinFields(VO vo) {
	    try {
	    	System.out.println(vo.getMember_birth());
	        System.out.println(" 여기는 온다");
	        int res = getSession().insert("insertMember", vo);
	        if (res > 0) {
	        	ss.commit();
	            System.out.println("insert 성공");
	        } else {
	            System.out.println("insert 실패");
	        }
	    } catch (Exception e) {
	        System.out.println("오류 발생: " + e.getMessage());
	        e.printStackTrace();
	        // 오류 처리를 위한 추가 작업 수행
	    }
	    return vo;
	}
//	public static String near_type() {
//		List<VO> list = null;
//		list = getSession().selectList("mostclose");
//		VO vo = list.get(0);
//		String str = vo.getClass_type();
//		return str;
//	}
//	public static String t_name_select() {
//		List<VO> list = null;
//		list = getSession().selectList("t_name_select");
//		VO vo = list.get(0);
//		String str = vo.getTeacher_name();
//		return str;
//	}
//	public static String near_classtime() {
//		List<VO> list = null;
//		list = getSession().selectList("mostclose");
//		VO vo = list.get(0);
//		String str = vo.getClass_time();
//		return str;
//	}
//	public static String near_classdate() {
//		List<VO> list = null;
//		list = getSession().selectList("mostclose");
//		VO vo = list.get(0);
//		String str = vo.getClass_date();
//		return str;
//	}
//	public static String near_membername() {
//		List<VO> list = null;
//		list = getSession().selectList("mostclose");//변경필요
//		VO vo = list.get(0);
//		String str = vo.getMember_name();
//		return str;
//	}
//	


	public static List<VO> getCoachList() {
		List<VO> list = null;
		list = getSession().selectList("getCoachList");
		return list;
	}
	public static int getInsert(VO vo) {
		getSession().insert("insert_book", vo);
		ss.commit();
	
		return 0;
	}
}
