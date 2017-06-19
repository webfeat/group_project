package dao.applyDao;

import database.BaseHibernateDAO;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Trainapply entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see dao.applyDao.Trainapply
 * @author MyEclipse Persistence Tools
 */
public class TrainapplyDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TrainapplyDAO.class);
	// property constants
	public static final String APPLICANT = "applicant";
	public static final String APPLYSTATE = "applystate";
	public static final String TRAINNAME = "trainname";
	public static final String MANAGENAME = "managename";
	public static final String MANAGEPHONE = "managephone";

	public void save(Trainapply transientInstance) {
		log.debug("saving Trainapply instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Trainapply persistentInstance) {
		log.debug("deleting Trainapply instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Trainapply findById(java.lang.Integer id) {
		log.debug("getting Trainapply instance with id: " + id);
		try {
			Trainapply instance = (Trainapply) getSession().get(
					"dao.applyDao.Trainapply", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Trainapply instance) {
		log.debug("finding Trainapply instance by example");
		try {
			List results = getSession()
					.createCriteria("dao.applyDao.Trainapply")
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
		log.debug("finding Trainapply instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Trainapply as model where model."
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

	public List findByTrainname(Object trainname) {
		return findByProperty(TRAINNAME, trainname);
	}

	public List findByManagename(Object managename) {
		return findByProperty(MANAGENAME, managename);
	}

	public List findByManagephone(Object managephone) {
		return findByProperty(MANAGEPHONE, managephone);
	}

	public List findAll() {
		log.debug("finding all Trainapply instances");
		try {
			String queryString = "from Trainapply";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Trainapply merge(Trainapply detachedInstance) {
		log.debug("merging Trainapply instance");
		try {
			Trainapply result = (Trainapply) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Trainapply instance) {
		log.debug("attaching dirty Trainapply instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Trainapply instance) {
		log.debug("attaching clean Trainapply instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}