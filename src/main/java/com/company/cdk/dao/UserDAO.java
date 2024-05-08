package com.company.cdk.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import com.company.cdk.model.User;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import jakarta.persistence.criteria.Root;

@Repository
@Transactional
public class UserDAO {

	public void saveUser(User user) {
		Session session = DAO.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.persist(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public User loginUser(String em, String pwd) {
		Session session = DAO.getSessionFactory().openSession();
		try {
			Query<User> query = session.createQuery("FROM User WHERE email = :email",
					User.class);
			query.setParameter("email", em);
			User user = query.uniqueResult();
//			query.setParameter("password", pwd);
//			return query.uniqueResult();
			if (user != null && BCrypt.checkpw(pwd, user.getPassword())) {
	            return user;
	        } else {
	            return null;
	        }
		} finally {
			session.close();
		}
	}

	public boolean userCheck(String em) {
		Session session = DAO.getSessionFactory().openSession();
		try {
//			Query query = session.createQuery("SELECT COUNT(*) FROM User WHERE email = :email");
//			query.setParameter("email", em);
//			Long count = (Long) query.uniqueResult();
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(criteriaBuilder.count(root));
            criteriaQuery.where(criteriaBuilder.equal(root.get("email"), em));
            Query<Long> query = session.createQuery(criteriaQuery);
            Long count = query.uniqueResult();
			return count > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}

	public User getUserByEmail(String email) {
		Session session = DAO.getSessionFactory().openSession();
		try {
			Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
			query.setParameter("email", email);
			return query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void updateUser(User user) {
	    Session session = DAO.getSessionFactory().openSession();
	    try {
	        session.beginTransaction();
	        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaUpdate<User> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(User.class);
            Root<User> root = criteriaUpdate.from(User.class);
            criteriaUpdate.set("fullname", user.getFullname());
            criteriaUpdate.set("address", user.getAddress());
            criteriaUpdate.set("password", user.getPassword());
            criteriaUpdate.set("userType", user.getUserType());
            criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), user.getId()));
            session.createQuery(criteriaUpdate).executeUpdate();
	        session.getTransaction().commit();
	    } catch (Exception e) {
	        if (session.getTransaction() != null) {
	            session.getTransaction().rollback();
	        }
	        e.printStackTrace(); // Or handle the exception appropriately
	    } finally {
	        session.close();
	    }
	}

}
