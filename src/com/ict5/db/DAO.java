package com.ict5.db;

import java.util.ArrayList;
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
			return vo;
		} else {
			return null;
		}
	}

	public static VO getLoginChk_Admin(VO vo) {
		vo = getSession().selectOne("loginChk_Admin", vo);

		if (vo != null) {
			return vo;
		} else {
			return null;
		}
	}

	public static String getNotice() {
		List<VO> list = null;
		list = getSession().selectList("getNotice");
		VO vo = list.get(0);
		String str = vo.getNotice_text();
		return str;
	}

	public static VO mostclose(VO vo) {
		vo = getSession().selectOne("mostclose", vo);
		return vo;
	}

	public static List<VO> getToday() {
		List<VO> list = null;
		list = getSession().selectList("todayclass");

		return list;
	}

	public static List<VO> getNewMember() {
		List<VO> list = null;
		list = getSession().selectList("NweMember");

		return list;
	}

	public static List<VO> getPointApprove() {
		List<VO> list = null;
		list = getSession().selectList("PointApprove");

		return list;
	}

	public static int getTeacherInsert(VO vo) {
		int result = getSession().insert("teacherIns", vo);
		ss.commit();
		return result;
	}

	public static VO setNotice(VO vo) {
		int res = getSession().insert("inNotice", vo);
		ss.commit();
		return vo;
	}

	public static List<VO> t_bookclass(VO vo) {
		List<VO> list = null;
		list = getSession().selectList("t_bookclass");
		return list;
	}

	public static List<VO> sel_date_class(VO vo) {
		List<VO> list = null;
		list = getSession().selectList("sel_date_class", vo);
		return list;
	}

	public static List<VO> sel_book_class(VO vo) {
		List<VO> list = null;
		list = getSession().selectList("sel_book_class", vo);
		return list;
	}

	public static VO setInsertJoinFields(VO vo) {
		int res = getSession().insert("insertMember", vo);
		ss.commit();
		return vo;
	}

	public static List<VO> getCoachList() {
		List<VO> list = null;
		list = getSession().selectList("getCoachList");
		return list;
	}

	public static int getInsert_book(VO vo) {
		getSession().insert("getInsert_book", vo);
		ss.commit();
		return 0;
	}

	public static int getInsert_attenedent(VO vo) {
		getSession().insert("getInsert_attenedent", vo);
		ss.commit();
		return 0;
	}

	public static List<VO> getMemberList() {
		List<VO> list = null;
		list = getSession().selectList("getMemberList");
		return list;
	}

	public static List<VO> getPoint() {
		List<VO> list = null;
		list = getSession().selectList("getPoint");
		return list;
	}

	public static List<VO> getMemberSearch(VO vo) {
		List<VO> list = null;
		list = getSession().selectList("getMemberSearch", vo);
		return list;
	}

	public static VO getMemberOne(VO vo) {
		vo = getSession().selectOne("getMemberOne", vo);

		return vo;
	}

	public static List<VO> getBookedClass(VO vo) {
		List<VO> list = getSession().selectList("getBookedClass", vo);
		return list;
	}

	public static List<VO> getPointList(VO vo) {
		List<VO> list = getSession().selectList("getPointList", vo);
		return list;
	}

	public static List<VO> getApproveList() { // 포인트 승인내역 테이블
		List<VO> list = getSession().selectList("getApproveList");
		return list;
	}

	public static int setApprove(VO vo) {
		int result = getSession().update("setApprove", vo);
		return result;
	}

	public static List<VO> sel_class_noice(VO vo) { // 알림표시할 클래스 정보 불러오기
		List<VO> list = getSession().selectList("sel_class_noice",vo);
		return list;
	}
	public static int update_goal(VO vo) {
		int result =getSession().update("update_goal",vo);
		ss.commit();
		return result;
	}
	public static List<VO> sel_already_book(VO vo) { // 알림표시할 클래스 정보 불러오기
		List<VO> list = getSession().selectList("sel_already_book",vo);
		return list;
	}

	public static VO getTeacherOne(VO vo){
		vo = getSession().selectOne("getTeacherOne", vo);
		return vo;
	}
	
	public static List<VO> searchTeacherName(VO vo) {
		List<VO> list = null;
		list = getSession().selectList("searchTeacherName", vo);
		return list;
	}
	
	public static List<VO> getTeacherClass(VO vo) {
		List<VO> list = getSession().selectList("getTeacherClass",vo);
		return list;
	}
	public static int getDeleteTeacher(VO vo) {
//		String resultStr = "success";
//		try {
//			int result = getSession().update("getDeleteTeacher",vo);
//			System.out.println("삭제 건수 : " + result);
//			
//			
//		} catch(Exception e) {
//			e.printStackTrace();
//			resultStr = e.getMessage();
//			
//		}
//		return resultStr;
		getSession().update("getDeleteTeacher",vo);
		ss.commit();
		return 0;
	}
	
	public static int getTeacherEdit(VO vo) {
		getSession().update("getTeacherEdit",vo);
		ss.commit();
		return 1;
	}

	public static List<VO> getMemberPw(VO vo) {//비밀번호 가져오기
		List<VO> list = null;
		list = getSession().selectList("checkMemberPw", vo);
		return list;
	}

	public static VO setMemberPw(VO vo) {// 비밀번호 변경
		int res = getSession().update("updatePassword", vo);
		ss.commit();
		return vo;
	}

	public static VO setApplyPoints(VO vo) { // 포인트 신청
		int res = getSession().insert("applyPoints", vo);
		ss.commit();
		return vo;
	
	}

	public static VO setPointChargeDate(VO vo) {
        int res = getSession().update("updateChargeDate", vo);
        ss.commit();
        return vo;
    }
	
	public static List<VO> getAllApprovePoints(VO vo) { //마이포인트 불러오기 
		List<VO> list = getSession().selectList("getAllApprovePoints",vo);
		return list;
	}



	public static int setApprove(VO vo) {
		int result = getSession().update("setApprove",vo);
		ss.commit();
		return result;
	}
}

	public static List<VO> getOneClass(VO vo) { // 클릭한 수업 상세정보
		List<VO> list = getSession().selectList("classCheck", vo);
		
		return list;
	}

	public static VO getOneClass_2(VO vo) { // 예약 아무도없는 수업일때
		vo = getSession().selectOne("classCheck_null",vo);
		return vo;
	}

	public static int delMember(VO vo) { // book에서 해당수업 멤버 지우기
		int res = getSession().delete("delMember",vo);
		ss.commit();
		return res;
	}

	public static int refundPoint(VO vo) { // 예약취소시 포인트 환급
		int res = getSession().insert("refundPoint",vo);
		ss.commit();
		return 0;
	}

	public static List<VO> getBookedMember(VO vo) { // 해당수업 예약한 회원리스트
		List<VO> list = getSession().selectList("bookedMember",vo);
		return list;
	}

	public static void deleteClass(VO vo) {
		System.out.println("클래스 번호 : "+vo.getClass_num());
		getSession().delete("deleteClass", vo);
		System.out.println("DAO끝");
		
	}

}
