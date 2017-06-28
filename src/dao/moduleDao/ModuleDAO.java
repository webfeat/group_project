package dao.moduleDao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Example;

import dao.operatorDao.Operator;
import dao.operatorDao.OperatorDAO;
import dao.permissionDao.Permission;
import dao.permissionDao.PermissionDAO;
import dao.roleDao.Role;
import dao.roleDao.RoleDAO;
import database.BaseHibernateDAO;

public class ModuleDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ModuleDAO.class);
	// property constants
	public static final String MODULENAME = "modulename";
	public static final String SUPMODULEID = "supmoduleid";
	public static final String SUBMODELID = "submodelid";
	public static final String METHODNAME = "methodname";
	private OperatorDAO operatorDAO = new OperatorDAO();
	private RoleDAO roleDAO = new RoleDAO();
	private PermissionDAO permissionDAO = new PermissionDAO();
	
	public void save(Module transientInstance) {
		log.debug("saving Module instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Module persistentInstance) {
		log.debug("deleting Module instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Module findById(java.lang.Integer id) {
		log.debug("getting Module instance with id: " + id);
		try {
			Module instance = (Module) getSession().get("dao.moduleDao.Module",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Module instance) {
		log.debug("finding Module instance by example");
		try {
			List results = getSession().createCriteria("dao.moduleDao.Module")
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
		log.debug("finding Module instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Module as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByModulename(Object modulename) {
		return findByProperty(MODULENAME, modulename);
	}

	public List findBySupmoduleid(Object supmoduleid) {
		return findByProperty(SUPMODULEID, supmoduleid);
	}

	public List findBySubmodelid(Object submodelid) {
		return findByProperty(SUBMODELID, submodelid);
	}

	public List findByMethodname(Object methodname) {
		return findByProperty(METHODNAME, methodname);
	}

	public List findAll() {
		log.debug("finding all Module instances");
		try {
			String queryString = "from Module";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Module merge(Module detachedInstance) {
		log.debug("merging Module instance");
		try {
			Module result = (Module) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Module instance) {
		log.debug("attaching dirty Module instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Module instance) {
		log.debug("attaching clean Module instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	//根据人员查询人员模块
	public List findMenuModule(String operatorId){
		log.debug("findMenuModule");
		try {
			String sql = "select moduleName from module_view where operatorId = ?";
			SQLQuery sqlQuery = getSession().createSQLQuery(sql);
			sqlQuery.setParameter(0, operatorId);
			return sqlQuery.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}