package com.ict5.db;

import java.io.Serializable;

// Member 테이블의 컬럼명과 일치 시키자. 
public class VO implements Serializable {
	private String member_num, member_id, member_name, member_gen, member_birth, member_signup_date, member_addr,
			member_pw, member_mail;
	private String notice_num, notice_content;
	private String class_num, class_time, class_date, class_res, class_max, class_point, class_room, class_type,
			teacher_num, teacher_name;
	private String admin_num, admin_id, admin_pw; // 
	
	
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

	public String getTeacher_name() {
		return teacher_name;
	}

	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
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

	public String getNotice_num() {
		return notice_num;
	}

	public void setNotice_num(String notice_num) {
		this.notice_num = notice_num;
	}

	public String getNotice_content() {
		return notice_content;
	}

	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
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

	public String getTeacher_num() {
		return teacher_num;
	}

	public void setTeacher_num(String teacher_num) {
		this.teacher_num = teacher_num;
	}

}
