package com.company.cdk.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.company.cdk.model.Job;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public class JobDAO {

	public void saveJob(Job job) {
		Session session = DAO.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.persist(job);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace(); // Or log the exception
		} finally {
			session.close();
		}
	}

	public List<Job> getAllJobs() {
		Session session = DAO.getSessionFactory().openSession();
		try {
			Query<Job> query = session.createQuery("FROM Job", Job.class);
			query.setMaxResults(10);
			return query.list();
		} finally {
			session.close();
		}
	}

	public List<Job> searchJobs(String searchText) {
		Session session = DAO.getSessionFactory().openSession();
		try {
			Query<Job> query = session.createQuery(
					"FROM Job WHERE title LIKE :searchText OR company LIKE :searchText OR description LIKE :searchText OR skills LIKE :searchText",
					Job.class);
			query.setParameter("searchText", "%" + searchText + "%");
			return query.list();
		} finally {
			session.close();
		}
	}

	public int getTotalJobs() {
		Session session = DAO.getSessionFactory().openSession();
		try {
			Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Job", Long.class);
			Long result = query.uniqueResult();
			return result != null ? result.intValue() : 0;
		} finally {
			session.close();
		}
	}

	public List<Job> getJobsByPage(int page, int pageSize) {
		Session session = DAO.getSessionFactory().openSession();
		try {
			Query<Job> query = session.createQuery("FROM Job", Job.class);
			query.setFirstResult((page - 1) * pageSize);
			query.setMaxResults(pageSize);
			return query.list();
		} finally {
			session.close();
		}
	}

	public Job getJobById(int jobId) {
		Session session = DAO.getSessionFactory().openSession();
		try {
			Job job = session.get(Job.class, jobId);
			return job;
		} finally {
			session.close();
		}
	}

	public List<Job> getJobPostsByUser(String userEmail) {
		Session session = DAO.getSessionFactory().openSession();
		try {
			Query<Job> query = session.createQuery("FROM Job WHERE posted_by = :userEmail", Job.class);
			query.setParameter("userEmail", userEmail);
			return query.list();
		} finally {
			session.close();
		}
	}

	public void deleteJob(int jobId) {
		Session session = DAO.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Job job = session.get(Job.class, jobId);
			if (job != null) {
				session.remove(job);
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
	
	public void updateJob(Job updatedJob) {
	    Session session = DAO.getSessionFactory().openSession();
	    try {
	            session.beginTransaction();
	            session.merge(updatedJob);
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
