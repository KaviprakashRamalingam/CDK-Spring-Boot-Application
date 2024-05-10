package com.company.service;

import java.util.List;

import com.company.cdk.model.Job;

public interface JobService {
	List<Job> getAllJobs();
    List<Job> getJobsByPage(int page, int pageSize);
    void saveJob(Job job);
    Job getJobById(int jobId);
    void deleteJob(int jobId);
    void updateJob(Job job);
    List<Job> searchJobs(String searchText);
    int getTotalJobs();
    List<Job> getJobPostsByUser(String email);
}
