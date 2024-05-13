<%-- 
    Document   : job_details
    Created on : Apr 2, 2024, 4:15:24 AM
    Author     : kaviprakashramalingam
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Job Details</title>
            <!-- Bootstrap CSS -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
            <!-- Font Awesome CSS -->
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
            <style>
                /* Custom CSS */
                .job-details-container {
                    padding: 20px;
                    border: 1px solid #ccc;
                    border-radius: 5px;
                    background-color: #f9f9f9;
                }

                .job-details-title {
                    font-size: 20px;
                    font-weight: bold;
                    margin-bottom: 10px;
                }

                .job-details-block {
                    max-height: 300px;
                    /* Set the max height of the first block */
                    overflow-y: auto;
                    /* Make the block scrollable if content exceeds max height */
                    margin-bottom: 20px;
                    /* Add some space between blocks */
                }

                .job-details-label {
                    font-weight: bold;
                }

                .job-details-value {
                    margin-left: 10px;
                }

                .apply-now-btn {
                    border: none;
                    border-radius: 20px;
                    background-color: #007bff;
                    /* Blue background color */
                    color: #fff;
                    /* White text color */
                    padding: 10px 20px;
                    cursor: pointer;
                    transition: background-color 0.3s;
                    margin-top: 14px;
                    margin-bottom: 15px;
                }

                .apply-now-btn:hover {
                    background-color: #0056b3;
                    /* Darker blue on hover */
                }
            </style>
        </head>

        <body>
            <div class="container">
                <div class="job-details-container">
                    <div class="job-details-title">Job Details</div>
                    <div class="job-details-block">
                        <div class="job-details-item">
                            <span class="job-details-label">Title:</span>
                            <span class="job-details-value">${job.title}</span>
                            <br/>
                        </div>
                        <div class="job-details-item">
                            <span class="job-details-label">Company:</span>
                            <span class="job-details-value">${job.company}</span>
                        </div>
                        <div class="job-details-item">
                            <span class="job-details-label">Location:</span>
                            <span class="job-details-value">${job.place}</span>
                        </div>
                        <div class="job-details-item">
                            <form action="apply_now" method="GET">
                                <input type="hidden" name="jobId" value="${job.id}">
                                <button type="submit" class="apply-now-btn">Apply Now</button>
                            </form>    
                        </div>
                    </div>
                    <div class="job-details-block"> <!-- Second block with scrollable content -->
                        <div class="job-details-item">
                            <span class="job-details-label">Pay:</span>
                            <span class="job-details-value">${job.pay}</span>
                        </div>
                        <div class="job-details-item">
                            <span class="job-details-label">Job Type:</span>
                            <span class="job-details-value">${job.mode}</span>
                        </div>
                        <div class="job-details-item">
                            <span class="job-details-label">Education:</span>
                            <span class="job-details-value">${job.education}</span>
                        </div>
                        <div class="job-details-item">
                            <span class="job-details-label">Full Job Description:</span>
                            <span class="job-details-value">${job.fullDesc}</span>
                        </div>
                        <div class="job-details-item">
                            <span class="job-details-label">Skills Required:</span>
                            <span class="job-details-value">${job.skills}</span>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Bootstrap Bundle with Popper -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
        </body>

        </html>