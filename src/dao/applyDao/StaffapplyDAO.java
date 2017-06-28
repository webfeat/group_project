package dao.applyDao;

import database.BaseHibernateDAO;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Staffapply entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see dao.applyDao.Staffapply
 * @author MyEclipse Persistence Tools
 */
public class StaffapplyDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(StaffapplyDAO.class);
	// property constants
	public static final String APPLICANT = "applicant";
	public static final String APPLYSTATE = "applystate";
	public static final String STAFEMAIL = "stafemail";
	public static final String STAFFPHONE = "staffphone";
	public static final String TRAINNAME = "trainname";
	public static final String STAFFID = "staffid";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";

	public void save(Staffapply transientInstance) {
		log.debug("saving Staffapply instance");
		try {
			Transaction transaction = getSession().beginTransaction();
			transaction.begin();
			getSession().saveOrUpdate(transientInstance);
			transaction.commit();
			getSession().close();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Staffapply persistentInstance) {
		log.debug("deleting Staffapply instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Staffapply findById(java.lang.Integer id) {
		log.debug("getting Staffapply instance with id: " + id);
		try {
			Staffapply instance = (Staffapply) getSession().get(
					"dao.applyDao.Staffapply", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public Integer generateEntityId(){
		try {
			String sql = "select max(staffapplyid) from Staffapply";
			SQLQuery sqlQuery = getSession().createSQLQuery(sql);
			List res = sqlQuery.list();
			int a = 0;
			for(int i=0;i < res.size();i++){
				a = Integer.parseInt(res.get(i).toString()) ;
			}
			return a+1;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
		
	}

	public List findByExample(Staffapply instance) {
		log.debug("finding Staffapply instance by example");
		try {
			List results = getSession()
					.createCriteria("dao.applyDao.Staffapply")
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
		log.debug("finding Staffapply instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Staffapply as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByApplicant(Object applicant) {
		return findByProperty(APPLICANT, applicant);
	}

	public List findByApplystate(Object applystate) {
		return findByProperty(APPLYSTATE, applystate);
	}

	public List findByStafemail(Object stafemail) {
		return findByProperty(STAFEMAIL, stafemail);
	}

	public List findByStaffphone(Object staffphone) {
		return findByProperty(STAFFPHONE, staffphone);
	}

	public List findByTrainname(Object trainname) {
		return findByProperty(TRAINNAME, trainname);
	}

	public List findByStaffid(Object staffid) {
		return findByProperty(STAFFID, staffid);
	}

	public List findByUsername(Object username) {
		return findByProperty(USERNAME, username);
	}

	public List findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List findAll() {
		log.debug("finding all Staffapply instances");
		try {
			String queryString = "from Staffapply";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Staffapply merge(Staffapply detachedInstance) {
		log.debug("merging Staffapply instance");
		try {
			Staffapply result = (Staffapply) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Staffapply instance) {
		log.debug("attaching dirty Staffapply instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Staffapply instance) {
		log.debug("attaching clean Staffapply instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}