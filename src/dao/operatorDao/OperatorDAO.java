package dao.operatorDao;

import database.BaseHibernateDAO;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Operator entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see dao.operatorDao.Operator
 * @author MyEclipse Persistence Tools
 */
public class OperatorDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(OperatorDAO.class);
	// property constants
	public static final String LOGINNAME = "loginname";
	public static final String PASSWORD = "password";
	public static final String EMPID = "empid";

	public void save(Operator transientInstance) {
		log.debug("saving Operator instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Operator persistentInstance) {
		log.debug("deleting Operator instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Operator findById(java.lang.Integer id) {
		log.debug("getting Operator instance with id: " + id);
		try {
			Operator instance = (Operator) getSession().get(
					"dao.operatorDao.Operator", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Operator instance) {
		log.debug("finding Operator instance by example");
		try {
			List results = getSession()
					.createCriteria("dao.operatorDao.Operator")
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
		log.debug("finding Operator instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Operator as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByLoginname(Object loginname) {
		return findByProperty(LOGINNAME, loginname);
	}

	public List findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List findByEmpid(Object empid) {
		return findByProperty(EMPID, empid);
	}

	public List findAll() {
		log.debug("finding all Operator instances");
		try {
			String queryString = "from Operator";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Operator merge(Operator detachedInstance) {
		log.debug("merging Operator instance");
		try {
			Operator result = (Operator) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Operator instance) {
		log.debug("attaching dirty Operator instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Operator instance) {
		log.debug("attaching clean Operator instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List validatLoginUser(String userName,String password) {
		try {
			String queryString = "select * from operator  where loginname = ? and password = ?";
			Query queryObject = getSession().createSQLQuery(queryString).addEntity(Operator.class);
			queryObject.setParameter(0, userName);
			queryObject.setParameter(1, password);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
}