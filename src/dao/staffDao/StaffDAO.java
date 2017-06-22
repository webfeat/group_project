package dao.staffDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

import util.ValidateUtil;
import database.BaseHibernateDAO;

public class StaffDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(StaffDAO.class);
	// property constants
	public static final String STAFFNAME = "staffname";
	public static final String EMAIL = "email";
	public static final String PHONE = "phone";
	public static final String TRAINID = "trainid";

	public void save(Staff transientInstance) {
		log.debug("saving Staff instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Staff persistentInstance) {
		log.debug("deleting Staff instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Staff findById(java.lang.Integer id) {
		log.debug("getting Staff instance with id: " + id);
		try {
			Staff instance = (Staff) getSession().get("dao.staffDao.Staff", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Staff instance) {
		log.debug("finding Staff instance by example");
		try {
			List results = getSession().createCriteria("dao.staffDao.Staff")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Staff instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Staff as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByStaffname(Object staffname) {
		return findByProperty(STAFFNAME, staffname);
	}

	public List findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}

	public List findByTrainid(Object trainid) {
		return findByProperty(TRAINID, trainid);
	}

	public List findAll() {
		log.debug("finding all Staff instances");
		try {
			String queryString = "from Staff";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Staff merge(Staff detachedInstance) {
		log.debug("merging Staff instance");
		try {
			Staff result = (Staff) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Staff instance) {
		log.debug("attaching dirty Staff instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Staff instance) {
		log.debug("attaching clean Staff instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	//查询学员并且分页
	public List findAllStudentLimit(Map<String,Object> params ){
		try {
			//获取参数
			Integer curentPage = Integer.parseInt(params.get("curentPage").toString()) ;
			Integer pageNumbers = Integer.parseInt(params.get("pageNumbers").toString());
			String searchValue = (String) params.get("searchValue");
			String searchKey = (String) params.get("searchKey");
			String jumpPage = (String)params.get("jumpPage");
			
			String searchCondition = "";
			int limitCondition = 0;
			
			if(jumpPage != null && ValidateUtil.isNumeric(jumpPage)){
				limitCondition = Integer.parseInt("jumpPage") * pageNumbers;
			}else{
				limitCondition = curentPage * pageNumbers;
			}
			 
			searchCondition = " and" + searchKey + " = '" + searchValue + "'";
			String sql = " select * from student_view where 1=1 and trainName is not null ";
			String limitStr = "" ;

			if(limitCondition > 0){
				limitStr += "and rid > " + (limitCondition - pageNumbers)  + " and rid <= "+ limitCondition;
			}
			
			if(searchKey != null && !"".equals(searchKey.trim())){
				sql += searchCondition;
			}
			
			if(limitStr != null && !"".equals(limitStr)){
				sql += limitStr;
			}
			List list = getSession().createSQLQuery(sql).list();
			Map<String,Object> map = new HashMap<String, Object>();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Staff updateStaff(Staff staff){
		try {
			Session session = getSession();
			Transaction transaction = session.beginTransaction();
			session.update(staff);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return staff;
	}
	
	//向数据库添加人
	public Staff regitstStaff(Staff staff){
		try {
			getSession().save(staff);
			return staff;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}