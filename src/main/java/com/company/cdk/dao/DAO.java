package com.company.cdk.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import com.company.cdk.model.Application;
import com.company.cdk.model.ApplicationDetails;
import com.company.cdk.model.Education;
import com.company.cdk.model.Job;
import com.company.cdk.model.User;
import com.company.cdk.model.WorkExperience;

public class DAO {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySetting(Environment.DRIVER, "com.mysql.cj.jdbc.Driver")
                        .applySetting(Environment.URL, "jdbc:mysql://localhost:3306/CDK")
                        .applySetting(Environment.USER, "root")
                        .applySetting(Environment.PASS, "Password")
                        .applySetting(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect")
                        .applySetting(Environment.SHOW_SQL, "true")
                        .applySetting(Environment.HBM2DDL_AUTO, "update")
                        //modify the ddl pattern from create to update to avoid dropping the table
                        .build();

                MetadataSources metadataSources = new MetadataSources(serviceRegistry);
                metadataSources.addAnnotatedClasses(User.class, 
                									Job.class, 
                									Application.class, 
                									Education.class, 
                									WorkExperience.class,
                									ApplicationDetails.class);
                

                Metadata metadata = metadataSources.getMetadataBuilder().build();

                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return sessionFactory;
    }

}

