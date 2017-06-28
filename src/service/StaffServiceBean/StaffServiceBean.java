package service.StaffServiceBean;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Transaction;

import com.sun.mail.smtp.DigestMD5;

import service.StaffService;
import dao.BaseDao;
import dao.applyDao.Staffapply;
import dao.operatorDao.Operator;
import dao.operatorDao.OperatorDAO;
import dao.roleDao.Role;
import dao.roleDao.RoleDAO;
import dao.staffDao.Staff;
import dao.staffDao.StaffDAO;
import dao.trainDao.Train;
import dao.trainDao.TrainDAO;
import database.HibernateSessionFactory;

public class StaffServiceBean implements StaffService{

	private StaffDAO staffDAO = new StaffDAO();
	private TrainDAO trainDAO = new TrainDAO();
	private OperatorDAO operator = new OperatorDAO();
	private RoleDAO role = new RoleDAO();
	
	@Override
	public boolean registStaff(Staffapply staffapply) {
		//学员申请：当审批通过后开始添加人员
		Staff staff = new Staff();
		staff.setStaffapply(null);
		staff.setEmail(staffapply.getStafemail());
		staff.setPhone(staffapply.getStaffphone());
		staff.setStaffname(staffapply.getApplicant());
		List list = this.trainDAO.findByProperty("trainName", staffapply.getTrainname());
		if(list == null || list.size() == 0){
			return false;
		}
		Train train = (Train) list.get(0);
		staff.setTrain(train);
		Role role = this.role.findById(3);
		Set<Role> set = new HashSet<Role>();
		//新增操作员并且设置密码
		Operator operator = new Operator();
		operator.setStaff(staff);
		set.add(role);
		
		operator.setRoles(set);
		int staffId = BaseDao.generateEntityId("staff", "staffid");
		int operateId = BaseDao.generateEntityId("operator", "operatorId");
		staff.setTrainid(train.getTrainid().toString());
		staff.setStaffid(staffId);
		operator.setOperatorid(operateId);
		operator.setPassword(staffapply.getPassword());
		operator.setLoginname(staffapply.getUsername());
		try {
			Transaction transaction = HibernateSessionFactory.getSession().beginTransaction();
			transaction.begin();
			this.staffDAO.regitstStaff(staff);
			Staff saved = this.findAStaff(staff.getStaffid().toString());
			saved.setTrain(train);
			this.operator.save(operator);
			
			transaction.commit();
			HibernateSessionFactory.getSession().close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean registStaff(Map<String, Object> map) {
		return false;
	}
	
	@Override
	public List findAllStudentLimit(Map<String, Object> params) {
		return this.staffDAO.findAllStudentLimit(params);
	}

	@Override
	public int deleteStaff(String staffId) {
		Staff staff = this.staffDAO.findById(Integer.parseInt(staffId));
		staff.setDepartureTime(new Date());
		try {
			staffDAO.updateStaff(staff);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Staff findAStaff(String staffId) {
		try {
			Staff staff = this.staffDAO.findById(Integer.parseInt(staffId));
			return staff;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Staff updateStaff(Staff staff) {
		try {
			return this.staffDAO.updateStaff(staff);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
