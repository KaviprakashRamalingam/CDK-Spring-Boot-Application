package com.company.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.cdk.dao.ApplicationDAO;
import com.company.cdk.model.Application;
import com.company.service.AppService;

@Service
public class AppServiceImpl implements AppService {

    @Autowired
    private ApplicationDAO applicationDAO;

    @Override
    public void saveApplication(Application application) {
        applicationDAO.saveApplication(application);
    }

    @Override
    public void deleteApplication(int appId) {
        applicationDAO.deleteApplication(appId);
    }

    @Override
    public boolean isApplied(String email, int jobId) {
        return applicationDAO.isApplied(email, jobId);
    }
}
