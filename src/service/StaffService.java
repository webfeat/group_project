package service;

import java.util.List;
import java.util.Map;

import dao.applyDao.Staffapply;
import dao.staffDao.Staff;

public interface StaffService {
	//由机构管理员向系统中添加人员
	public boolean registStaff(Map<String,Object> map);
	//在表格里面显示所有学生
	public List<?> findAllStudentLimit(Map<String,Object> params);
	//一个学生的离园操作
	public int deleteStaff(String staffId);
	//人员在也注册页面注册
	public boolean registStaff(Staffapply staffapply);
	//根据一个人员Id查找数据
	public Staff findAStaff(String staffId);
	//更新一条数据
	public Staff updateStaff(Staff staff);
	
}
