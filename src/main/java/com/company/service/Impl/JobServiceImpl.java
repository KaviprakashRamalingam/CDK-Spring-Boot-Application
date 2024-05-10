package com.company.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.cdk.dao.JobDAO;
import com.company.service.JobService;
import com.company.cdk.model.Job;

@Service
public class JobServiceImpl implements JobService{
	@Autowired
    private JobDAO jobDAO;

    @Override
    public List<Job> getAllJobs() {
        return jobDAO.getAllJobs();
    }

    @Override
    public List<Job> getJobsByPage(int page, int pageSize) {
        return jobDAO.getJobsByPage(page, pageSize);
    }

    @Override
    public void saveJob(Job job) {
        jobDAO.saveJob(job);
    }

    @Override
    public Job getJobById(int jobId) {
        return jobDAO.getJobById(jobId);
    }

    @Override
    public void deleteJob(int jobId) {
        jobDAO.deleteJob(jobId);
    }

    @Override
    public void updateJob(Job job) {
        jobDAO.updateJob(job);
    }

    @Override
    public List<Job> searchJobs(String searchText) {
        return jobDAO.searchJobs(searchText);
    }

    @Override
    public int getTotalJobs() {
        return jobDAO.getTotalJobs();
    }

    @Override
    public List<Job> getJobPostsByUser(String email) {
        return jobDAO.getJobPostsByUser(email);
    }
}
