package com.webprague.StudentSign.DAO;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webprague.StudentSign.pojo.SignIn;

/**
 * A data access object (DAO) providing persistence and search support for
 * SignIn entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.webprague.StudentSign.DAO.SignIn
 * @author MyEclipse Persistence Tools
 */
public class SignInDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(SignInDAO.class);
	// property constants
	public static final String USERNAME = "username";
	public static final String NAME = "name";
	public static final String REMARK = "remark";
	public static final String TEACHER_USERNAME = "teacherUsername";
	public static final String STATUS = "status";

	public void save(SignIn transientInstance) {
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

	public void delete(SignIn persistentInstance) {
		//getSession().clear();
		//getSession().flush();
		//Transaction transaction = getSession().beginTransaction();
		log.debug("deleting SignIn instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		//transaction.commit();
		//getSession().flush();
		//getSession().close();
	}

	public SignIn findById(Integer id) {
		//getSession().clear();
		//getSession().flush();
		log.debug("getting SignIn instance with id: " + id);
		try {
			SignIn instance = (SignIn) getSession().get(
					"com.webprague.StudentSign.pojo.SignIn", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SignIn instance) {
//		getSession().clear();
//		getSession().flush();
		log.debug("finding SignIn instance by example");
		try {
			List results = getSession()
					.createCriteria("com.webprague.StudentSign.pojo.SignIn")
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
//		getSession().clear();
//		getSession().flush();
		log.debug("finding SignIn instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SignIn as model where model."
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

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findByTeacherUsername(Object teacherUsername) {
		return findByProperty(TEACHER_USERNAME, teacherUsername);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findAll() {
		log.debug("finding all SignIn instances");
		//getSession().clear();
		//getSession().flush();
		try {
			String queryString = "from SignIn";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SignIn merge(SignIn detachedInstance) {
		log.debug("merging SignIn instance");
		try {
			SignIn result = (SignIn) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SignIn instance) {
		log.debug("attaching dirty SignIn instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SignIn instance) {
		log.debug("attaching clean SignIn instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
}