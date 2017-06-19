package dao.trainDao;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import database.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for Train
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see dao.trainDao.Train
 * @author MyEclipse Persistence Tools
 */
public class TrainDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TrainDAO.class);
	// property constants
	public static final String TRAINNAME = "trainname";
	public static final String MANAGEID = "manageid";
	public static final String MANAGENAME = "managename";
	public static final String MANAGEPHONE = "managephone";
	public static final String PARKID = "parkid";

	public void save(Train transientInstance) {
		log.debug("saving Train instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Train persistentInstance) {
		log.debug("deleting Train instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Train findById(java.lang.Integer id) {
		log.debug("getting Train instance with id: " + id);
		try {
			Train instance = (Train) getSession().get("dao.trainDao.Train", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Train instance) {
		log.debug("finding Train instance by example");
		try {
			List results = getSession().createCriteria("dao.trainDao.Train")
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
		log.debug("finding Train instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Train as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTrainname(Object trainname) {
		return findByProperty(TRAINNAME, trainname);
	}

	public List findByManageid(Object manageid) {
		return findByProperty(MANAGEID, manageid);
	}

	public List findByManagename(Object managename) {
		return findByProperty(MANAGENAME, managename);
	}

	public List findByManagephone(Object managephone) {
		return findByProperty(MANAGEPHONE, managephone);
	}

	public List findByParkid(Object parkid) {
		return findByProperty(PARKID, parkid);
	}

	public List findAll() {
		log.debug("finding all Train instances");
		try {
			String queryString = "from Train";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Train merge(Train detachedInstance) {
		log.debug("merging Train instance");
		try {
			Train result = (Train) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Train instance) {
		log.debug("attaching dirty Train instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Train instance) {
		log.debug("attaching clean Train instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}