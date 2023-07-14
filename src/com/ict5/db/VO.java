package com.ict5.db;

import java.io.Serializable;

// Member 테이블의 컬럼명과 일치 시키자. 
public class VO implements Serializable {

	private String member_num, member_id, member_name, member_gen, member_birth, member_signup_date, member_addr, member_goal,
	member_pw, member_mail, member_point, member_chargep, member_usep, member_phone, attendent_date,
	attendent_month, member_totalcharge, member_totaluse;
	private String notice_num, notice_text;
	private String class_num, class_time, class_date, class_res, class_max, class_point, class_room, class_type;
	private String attendent_num, attendent_time, point_num, point, point_type, point_change_date,point_name,point_money;
	private String point_signup_date,charge_num,point_approve,point_charge_date;
	private String admin_num, admin_id, admin_pw;
	private String teacher_num, teacher_name, teacher_phone, teacher_addr, teacher_gen, teacher_career, teacher_img,
			teacher_type;
	private String book_date, book_type;
	private String old_pw, new_pw; //비밀번호 변경용
	
	
	public String getMember_goal() {
		return member_goal;
	}
	public void setMember_goal(String member_goal) {
		this.member_goal = member_goal;
	}
	public String getMember_num() {
		return member_num;
	}
	public void setMember_num(String member_num) {
		this.member_num = member_num;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_gen() {
		return member_gen;
	}
	public void setMember_gen(String member_gen) {
		this.member_gen = member_gen;
	}
	public String getMember_birth() {
		return member_birth;
	}
	public void setMember_birth(String member_birth) {
		this.member_birth = member_birth;
	}
	public String getMember_signup_date() {
		return member_signup_date;
	}
	public void setMember_signup_date(String member_signup_date) {
		this.member_signup_date = member_signup_date;
	}
	public String getMember_addr() {
		return member_addr;
	}
	public void setMember_addr(String member_addr) {
		this.member_addr = member_addr;
	}
	public String getMember_pw() {
		return member_pw;
	}
	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}
	public String getMember_mail() {
		return member_mail;
	}
	public void setMember_mail(String member_mail) {
		this.member_mail = member_mail;
	}
	public String getMember_point() {
		return member_point;
	}
	public void setMember_point(String member_point) {
		this.member_point = member_point;
	}
	public String getMember_chargep() {
		return member_chargep;
	}
	public void setMember_chargep(String member_chargep) {
		this.member_chargep = member_chargep;
	}
	public String getMember_usep() {
		return member_usep;
	}
	public void setMember_usep(String member_usep) {
		this.member_usep = member_usep;
	}
	public String getMember_phone() {
		return member_phone;
	}
	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}
	public String getAttendent_date() {
		return attendent_date;
	}
	public void setAttendent_date(String attendent_date) {
		this.attendent_date = attendent_date;
	}
	public String getAttendent_month() {
		return attendent_month;
	}
	public void setAttendent_month(String attendent_month) {
		this.attendent_month = attendent_month;
	}
	public String getMember_totalcharge() {
		return member_totalcharge;
	}
	public void setMember_totalcharge(String member_totalcharge) {
		this.member_totalcharge = member_totalcharge;
	}
	public String getMember_totaluse() {
		return member_totaluse;
	}
	public void setMember_totaluse(String member_totaluse) {
		this.member_totaluse = member_totaluse;
	}
	public String getNotice_num() {
		return notice_num;
	}
	public void setNotice_num(String notice_num) {
		this.notice_num = notice_num;
	}
	public String getNotice_text() {
		return notice_text;
	}
	public void setNotice_text(String notice_text) {
		this.notice_text = notice_text;
	}
	public String getClass_num() {
		return class_num;
	}
	public void setClass_num(String class_num) {
		this.class_num = class_num;
	}
	public String getClass_time() {
		return class_time;
	}
	public void setClass_time(String class_time) {
		this.class_time = class_time;
	}
	public String getClass_date() {
		return class_date;
	}
	public void setClass_date(String class_date) {
		this.class_date = class_date;
	}
	public String getClass_res() {
		return class_res;
	}
	public void setClass_res(String class_res) {
		this.class_res = class_res;
	}
	public String getClass_max() {
		return class_max;
	}
	public void setClass_max(String class_max) {
		this.class_max = class_max;
	}
	public String getClass_point() {
		return class_point;
	}
	public void setClass_point(String class_point) {
		this.class_point = class_point;
	}
	public String getClass_room() {
		return class_room;
	}
	public void setClass_room(String class_room) {
		this.class_room = class_room;
	}
	public String getClass_type() {
		return class_type;
	}
	public void setClass_type(String class_type) {
		this.class_type = class_type;
	}
	public String getAttendent_num() {
		return attendent_num;
	}
	public void setAttendent_num(String attendent_num) {
		this.attendent_num = attendent_num;
	}
	public String getAttendent_time() {
		return attendent_time;
	}
	public void setAttendent_time(String attendent_time) {
		this.attendent_time = attendent_time;
	}
	public String getPoint_num() {
		return point_num;
	}
	public void setPoint_num(String point_num) {
		this.point_num = point_num;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getPoint_type() {
		return point_type;
	}
	public void setPoint_type(String point_type) {
		this.point_type = point_type;
	}
	public String getPoint_change_date() {
		return point_change_date;
	}
	public void setPoint_change_date(String point_change_date) {
		this.point_change_date = point_change_date;
	}
	public String getAdmin_num() {
		return admin_num;
	}
	public void setAdmin_num(String admin_num) {
		this.admin_num = admin_num;
	}
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_pw() {
		return admin_pw;
	}
	public void setAdmin_pw(String admin_pw) {
		this.admin_pw = admin_pw;
	}
	public String getTeacher_num() {
		return teacher_num;
	}
	public void setTeacher_num(String teacher_num) {
		this.teacher_num = teacher_num;
	}
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	public String getTeacher_phone() {
		return teacher_phone;
	}
	public void setTeacher_phone(String teacher_phone) {
		this.teacher_phone = teacher_phone;
	}
	public String getTeacher_addr() {
		return teacher_addr;
	}
	public void setTeacher_addr(String teacher_addr) {
		this.teacher_addr = teacher_addr;
	}
	public String getTeacher_gen() {
		return teacher_gen;
	}
	public void setTeacher_gen(String teacher_gen) {
		this.teacher_gen = teacher_gen;
	}
	public String getTeacher_career() {
		return teacher_career;
	}
	public void setTeacher_career(String teacher_career) {
		this.teacher_career = teacher_career;
	}
	public String getTeacher_img() {
		return teacher_img;
	}
	public void setTeacher_img(String teacher_img) {
		this.teacher_img = teacher_img;
	}
	public String getTeacher_type() {
		return teacher_type;
	}
	public void setTeacher_type(String teacher_type) {
		this.teacher_type = teacher_type;
	}
	public String getBook_date() {
		return book_date;
	}
	public void setBook_date(String book_date) {
		this.book_date = book_date;
	}
	public String getBook_type() {
		return book_type;
	}
	public void setBook_type(String book_type) {
		this.book_type = book_type;
	}
	public String getPoint_name() {
		return point_name;
	}
	public void setPoint_name(String point_name) {
		this.point_name = point_name;
	}
	public String getPoint_money() {
		return point_money;
	}
	public void setPoint_money(String point_money) {
		this.point_money = point_money;
	}
	public String getPoint_signup_date() {
		return point_signup_date;
	}
	public void setPoint_signup_date(String point_signup_date) {
		this.point_signup_date = point_signup_date;
	}
	public String getCharge_num() {
		return charge_num;
	}
	public void setCharge_num(String charge_num) {
		this.charge_num = charge_num;
	}
	public String getPoint_approve() {
		return point_approve;
	}
	public void setPoint_approve(String point_approve) {
		this.point_approve = point_approve;
	}
	public String getPoint_charge_date() {
		return point_charge_date;
	}
	public void setPoint_charge_date(String point_charge_date) {
		this.point_charge_date = point_charge_date;
	}
	public String getOld_pw() {
		return old_pw;
	}
	public void setOld_pw(String old_pw) {
		this.old_pw = old_pw;
	}
	public String getNew_pw() {
		return new_pw;
	}
	public void setNew_pw(String new_pw) {
		this.new_pw = new_pw;
	}
	
	
	
	
	
	

}