package service;

import com.alibaba.fastjson.JSONObject;

import dao.applyDao.Staffapply;

public interface StaffApplyService {
	//注册时就是添加一条申请
	public Staffapply saveAStaffApply(JSONObject jsonObject);
	//处理一条申请
	public Staffapply dealStaffApply(JSONObject jsonObject);
	//查找一个申请
	public Staffapply findOneStaffApplyById(String staffapply);
	
}
