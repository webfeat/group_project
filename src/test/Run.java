package test;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.moduleDao.ModuleDAO;
import dao.staffDao.Staff;
import dao.trainDao.Train;
import dao.trainDao.TrainDAO;
import database.HibernateSessionFactory;

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ModuleDAO m = new ModuleDAO();
		Set list = m.findMenuModule("1");
	}

	public static void test(String trainid) {

		// Session session = HibernateSessionFactory.getSession();
		// Transaction transaction = session.beginTransaction();
		//
		// RoleDAO roleDao = new RoleDAO();
		// Role role = roleDao.findById(roleid);
		//
		// OperatorDAO operatorDao= new OperatorDAO();
		// Operator operator = operatorDao.findById(operatorid);
		//
		// Set<Role> roles = new HashSet<Role>();
		// roles.add(role);
		// operator.setRoles(roles);
		//
		// Set<Operator> operators = new HashSet<Operator>();
		// operators.add(operator);
		// role.setOperators(operators);
		//
		// session.save(operator);
		// session.save(role);
		// transaction.commit();
		// session.close();//���ڲ��Բ���Ա�ͽ�ɫ֮��Ĺ�ϵ

		// Session session = HibernateSessionFactory.getSession();
		// Transaction transaction = session.beginTransaction();
		//
		// RoleDAO roleDao = new RoleDAO();
		// Role role = roleDao.findById(roleid);
		//
		// PermissionDAO permissiondao = new PermissionDAO();
		// Permission permission = permissiondao.findById(permissionid);
		//
		// Set<Role> roles = new HashSet<Role>();
		// roles.add(role);
		// permission.setRoles(roles);
		//
		// Set<Permission> permissions = new HashSet<Permission>();
		// permissions.add(permission);
		// role.setPermissions(permissions);
		//
		// session.save(role);
		// session.save(permission);
		// transaction.commit();
		// session.close();//���ڲ���Ȩ�޺ͽ�ɫ֮��Ĺ�ϵ

//		Session session = HibernateSessionFactory.getSession();
//		Transaction transaction = session.beginTransaction();
//		ModuleDAO moduleDao = new ModuleDAO();
//		Module module = moduleDao.findById(moduleid);
//
//		PermissionDAO permissiondao = new PermissionDAO();
//		Permission permission = permissiondao.findById(permissionid);
//
//		Set<Module> modules = new HashSet<Module>();
//		modules.add(module);
//		permission.setModules(modules);
//
//		Set<Permission> permissions = new HashSet<Permission>();
//		permissions.add(permission);
//		module.setPermissions(permissions);
//
//		session.save(module);
//		session.save(permission);
//		transaction.commit();
//		session.close();//���ڲ���Ȩ�޺�ģ��֮��Ĺ�ϵ��
		
		
//		Session session = HibernateSessionFactory.getSession();
//		Transaction transaction = session.beginTransaction();
        //�û�  
//		Staff staff = new Staff();
//		staff.setStaffid(3);
//		staff.setStaffname("Mary");
//		staff.setEmail("@163.com");
//		staff.setPhone("1254660");
//		staff.setTrainid("15");
//		staff.setDepartureTime(new Date());
		
//		Staffapply staffapply = new Staffapply();
//		
//		staffapply.setApplytime(new Date());
//		staffapply.setApplicant("wym");
//		staffapply.setApplystate("0000000000");
//		staffapply.setStafemail("@360.com");
//		staffapply.setStaffphone("1111");
//		staffapply.setTrainname("LOL");
//		staffapply.setStaffapplyid(1234);
//		session.save(staffapply);
//		session.getTransaction().commit();  
//        session.close(); //��Ա�ύ����?�ȴ���� 

//		Session session = HibernateSessionFactory.getSession();
//		Transaction transaction = session.beginTransaction();
//		Staff staff = new Staff();
//		staff.setStaffid(3);
//		staff.setStaffname("Mary");
//		staff.setEmail("@163.com");
//		staff.setPhone("1254660");
//		staff.setTrainid("15");
//		staff.setDepartureTime(new Date());
//
//		Operator operator = new Operator();
//		operator.setLoginname("cccc");
//		operator.setPassword("11111111");
//		operator.setOperatorid(222);
//		operator.setStaff(staff);
//		
//		session.save(operator);
//		session.getTransaction().commit();  
//		session.close();
//		//������Ա�Ͳ���Ա֮��Ĺ�ϵ
		
//		Session session = HibernateSessionFactory.getSession();
//		Transaction transaction = session.beginTransaction();
//		
//		
//		
//		Train train = new Train();
//		train.setTrainid(23);
//		train.setTrainname("�����?��ѧ");
//		train.setCheckInTime(new Date());
//		train.setDepartureTime(new Date());
//		train.setManageid(7);
//		train.setManagename("����");
//		train.setManagephone("123456");
//		
//		ParkDAO parkdao = new ParkDAO();
//		
//		Park park = parkdao.findById(parkid);
//		train.setPark(park);
//	
//		session.save(train);
//		transaction.commit();  
//		session.close();//����԰�����ѵ��Ĺ�ϵ
		
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		
		TrainDAO traindao = new TrainDAO();
		Train train = traindao.findById(Integer.parseInt(trainid));
		Staff staff1 = new Staff();
		staff1.setStaffid(5);
		staff1.setStaffname("Michael");
		staff1.setEmail("@qq.com");
		staff1.setPhone("01254660");
		staff1.setDepartureTime(new Date());
		staff1.setTrainid(trainid);
		
		session.save(staff1);
		transaction.commit();
		session.close();
	}
}
