package com.company.cdk.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.company.cdk.model.Application;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import jakarta.persistence.criteria.CriteriaBuilder;

@Repository
@Transactional
public class ApplicationDAO {
	
	public void saveApplication(Application application) {
        Session session = DAO.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.persist(application);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace(); // Or log the exception
        } finally {
            session.close();
        }
    }
	
	public List<Application> getApplicationByEmail(String useremail) {
		Session session = DAO.getSessionFactory().openSession();
		try {
			Query<Application> query = session.createQuery("FROM Application WHERE useremail = :userEmail", Application.class);
			query.setParameter("userEmail", useremail);
			return query.list();
		} finally {
			session.close();
		}
	}
	
	public void deleteApplication(int appId) {
		Session session = DAO.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Application application = session.get(Application.class, appId);
			if (application != null) {
				session.remove(application);
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public boolean isApplied(String em, int jobid) {
		Session session = DAO.getSessionFactory().openSession();
		try {
	        CriteriaBuilder builder = session.getCriteriaBuilder();
	        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
	        Root<Application> root = criteriaQuery.from(Application.class);

	        criteriaQuery.select(builder.count(root));
	        criteriaQuery.where(builder.equal(root.get("useremail"), em), builder.equal(root.get("jobid"), jobid));

	        Long count = session.createQuery(criteriaQuery).uniqueResult();
			return count > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}
}
