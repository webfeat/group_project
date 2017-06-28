package service.TrainServiceBean;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import service.TrainApplyService;
import dao.BaseDao;
import dao.applyDao.Trainapply;
import dao.applyDao.TrainapplyDAO;
import dao.operatorDao.Operator;
import dao.operatorDao.OperatorDAO;
import dao.parkDao.Park;
import dao.parkDao.ParkDAO;
import dao.roleDao.Role;
import dao.roleDao.RoleDAO;
import dao.staffDao.Staff;
import dao.staffDao.StaffDAO;
import dao.trainDao.Train;
import dao.trainDao.TrainDAO;
import database.HibernateSessionFactory;

public class TrainApplyServiceBean implements TrainApplyService{

	private TrainapplyDAO trainapplyDAO = new TrainapplyDAO();
	private ParkDAO parkDAO = new ParkDAO();
	private RoleDAO roleDao = new RoleDAO();
	private OperatorDAO operatorDAO = new OperatorDAO();
	private StaffDAO staffDAO = new StaffDAO();
	private TrainDAO trainDAO = new TrainDAO();
	
	@Override
	public boolean saveTrainApply(Map<String, Object> map) {
		//String managename = map.get("managename").toString();
		
		
		List trainapply = trainapplyDAO.findAll();
		
		for(int i=0;i<trainapply.size();i++){
			Trainapply temp=(Trainapply)trainapply.get(i);
			if(map.get("trainname").equals(temp.getTrainname())== true){
				return true;
			}
		}
		
		return false;
	}
	
	public List search(String managename){
		List trainapply = trainapplyDAO.findByManagename(managename);
		return trainapply;
	}
	
	public boolean update(Map<String, Object> map){
		List trainapply = trainapplyDAO.findAll();
		
		for(int i=0;i<trainapply.size();i++){
			Trainapply temp=(Trainapply)trainapply.get(i);
			if(map.get("trainname").equals(temp.getTrainname())== true){
				return true;
			}
		}
		
		return false;
		
	}

	@Override
	public String verify(Trainapply trainapply) {
		
		return null;
	}

	@Override
	public List<?> findAllTrainApplyLimit(Map<String, Object> params) {
		return BaseDao.findAllTrainLimit(params,"trainapply");
	}

	@Override
	public boolean dealTrainApplye(Trainapply trainapply) {
		Park park = this.parkDAO.findById(16);
		
		//建立机构
		Train train = new Train();
		train.setCheckInTime(new Date());
		train.setTrainname(trainapply.getTrainname());
		train.setManagename(trainapply.getManagename());
		train.setManagephone(trainapply.getManagephone());
		train.setPark(park);
		int trainId = BaseDao.generateEntityId("train", "trainId");
		train.setTrainid(trainId);
		
		Staff staff = new Staff();
		staff.setEmail(trainapply.getEmail());
		staff.setStaffname(trainapply.getManagename());
		staff.setPhone(trainapply.getManagephone());
		int staffId = BaseDao.generateEntityId("staff", "staffId");
		staff.setStaffid(staffId);
		
		Operator operator = new Operator();
		operator.setLoginname(trainapply.getManagephone());
		operator.setPassword(trainapply.getPassword());
		int operatrId = BaseDao.generateEntityId("operator", "operatorId");
		operator.setOperatorid(operatrId);
		
		Set<Role> roles = new HashSet<Role>();
		Role role = this.roleDao.findById(2);
		roles.add(role);
		operator.setRoles(roles);
		
		operator.setStaff(staff);
		train.setStaff(staff);
		train.setManageid(staffId);

		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		this.trainDAO.save(train);
		this.operatorDAO.save(operator);
		transaction.commit();
		session.close();
		
		return true;
	}

}
