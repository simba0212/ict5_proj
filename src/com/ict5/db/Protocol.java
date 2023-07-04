package com.ict5.db;

import java.io.Serializable;
import java.util.List;


public class Protocol implements Serializable{
	// 1.로그인
	public int cmd;
	public int result ;
	public List<VO> list;
	public VO vo ;
	
	public int getCmd() {
		return cmd;
	}
	public void setCmd(int cmd) {
		this.cmd = cmd;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public List<VO> getList() {
		return list;
	}
	public void setList(List<VO> list) {
		this.list = list;
	}
	public VO getVo() {
		return vo;
	}
	public void setVo(VO vo) {
		this.vo = vo;
	}
}
