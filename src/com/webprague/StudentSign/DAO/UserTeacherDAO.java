package com.webprague.StudentSign.DAO;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webprague.StudentSign.pojo.UserTeacher;

/**
 * A data access object (DAO) providing persistence and search support for
 * UserTeacher entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.webprague.StudentSign.DAO.UserTeacher
 * @author MyEclipse Persistence Tools
 */
public class UserTeacherDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(UserTeacherDAO.class);
	// property constants
	public static final String USERNAME = "username";
	public static final String NAME = "name";
	public static final String PASSWORD = "password";
	public static final String DEPARTMENT = "department";

	public void save(UserTeacher transientInstance) {
		log.debug("saving UserTeacher instance");
		Transaction transaction = getSession().beginTransaction();
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		transaction.commit();
		getSession().flush();
		getSession().close();
	}

	public void delete(UserTeacher persistentInstance) {
		getSession().clear();
		getSession().flush();
		Transaction transaction = getSession().beginTransaction();
		log.debug("deleting UserTeacher instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		transaction.commit();
		getSession().flush();
		getSession().close();
	}

	public UserTeacher findById(Integer id) {
		getSession().clear();
		getSession().flush();
		log.debug("getting UserTeacher instance with id: " + id);
		try {
			UserTeacher instance = (UserTeacher) getSession().get(
					"com.webprague.StudentSign.pojo.UserTeacher", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(UserTeacher instance) {
		getSession().clear();
		getSession().flush();
		log.debug("finding UserTeacher instance by example");
		try {
			List results = getSession()
					.createCriteria("com.webprague.StudentSign.pojo.UserTeacher")
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
		getSession().clear();
		getSession().flush();
		log.debug("finding UserTeacher instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from UserTeacher as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUsername(Object username) {
		return findByProperty(USERNAME, username);
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List findByDepartment(Object department) {
		return findByProperty(DEPARTMENT, department);
	}

	public List findAll() {
		log.debug("finding all UserTeacher instances");
		try {
			String queryString = "from UserTeacher";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public UserTeacher merge(UserTeacher detachedInstance) {
		log.debug("merging UserTeacher instance");
		try {
			UserTeacher result = (UserTeacher) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UserTeacher instance) {
		log.debug("attaching dirty UserTeacher instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserTeacher instance) {
		log.debug("attaching clean UserTeacher instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}