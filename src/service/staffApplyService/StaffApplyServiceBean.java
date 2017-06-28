package service.staffApplyService;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import service.StaffApplyService;
import service.StaffService;
import service.StaffServiceBean.StaffServiceBean;

import com.alibaba.fastjson.JSONObject;

import dao.BaseDao;
import dao.applyDao.Staffapply;
import dao.applyDao.StaffapplyDAO;
import database.HibernateSessionFactory;

public class StaffApplyServiceBean implements StaffApplyService {
	private StaffapplyDAO staffapplyDAO = new StaffapplyDAO();
	
	private StaffService staffService = new StaffServiceBean();
	
	@Override
	public Staffapply saveAStaffApply(JSONObject jsonObject) {
		//定义申请变量和参数获取
		Staffapply staffapply = new Staffapply();
		staffapply.setApplicant(jsonObject.getString("staffName"));
		staffapply.setApplystate("审批中");
		staffapply.setApplytime(new Date());
		System.out.println((new Date()).toString());
		staffapply.setStafemail(jsonObject.getString("email"));
		staffapply.setStaffphone(jsonObject.getString("phone"));
		staffapply.setUsername(jsonObject.getString("phone"));
		staffapply.setTrainname(jsonObject.getString("trainName"));
		String password = jsonObject.getString("password");
		password = DigestUtils.md5Hex(password);
		staffapply.setPassword(password);
		int id = BaseDao.generateEntityId("staffApply", "staffApplyId");		
		staffapply.setStaffapplyid(id);
		//完成申请提交
		try {
			this.staffapplyDAO.save(staffapply);
			return staffapply;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	//审核处理
	@Override
	public Staffapply dealStaffApply(JSONObject jsonObject) {
		String staffApplyId = jsonObject.getString("staffApplyId");
		String comment = jsonObject.getString("comment");
		Staffapply staffApply = this.staffapplyDAO.findById(Integer.parseInt(staffApplyId));
		
		//同意之后新建人
		if("同意".equals(comment)){
			boolean value = this.staffService.registStaff(staffApply);
			staffApply.setApplystate("已通过");
			this.staffapplyDAO.save(staffApply);
			if(value){
				return staffApply;
			}
		}else{//不同意就更新记录，设置状态
			staffApply.setApplystate("不同意");
			this.staffapplyDAO.save(staffApply);
		}
		return null;
	}

	@Override
	public Staffapply findOneStaffApplyById(String staffapplyId) {
		try {
			Staffapply staffapply = this.staffapplyDAO.findById(Integer.parseInt(staffapplyId));
			return staffapply;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
