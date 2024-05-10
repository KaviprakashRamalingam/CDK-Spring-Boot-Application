package com.company.service;

import com.company.cdk.model.Application;

public interface AppService {
	void saveApplication(Application application);
    void deleteApplication(int appId);
    boolean isApplied(String email, int jobId);
}
