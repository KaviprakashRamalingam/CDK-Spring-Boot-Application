<%-- Document : createjob Created on : Mar 27, 2024, 4:02:14 AM Author : kaviprakashramalingam --%>
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                <%@page isELIgnored="false" %>
                    <!DOCTYPE html>
                    <html>

                    <head>
                        <meta charset="ISO-8859-1">
                        <title>Job Posting</title>
                        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
                            rel="stylesheet"
                            integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
                            crossorigin="anonymous">
                        <!-- Font Awesome CDN link -->
                        <link rel="stylesheet"
                            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
                        <style>
                            body {
                                background-color: #F3F2F1;
                            }

                            .job-container {
                                width: 570px;
                                margin: 100px auto;
                                padding: 20px;
                                background-color: #ffffff;
                                border-radius: 20px;
                                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
                                position: relative;
                                /* Added */
                            }

                            .brand-heading {
                                color: #0056b3;
                                /* Darker blue color */
                                text-align: center;
                                margin-bottom: 20px;
                                position: absolute;
                                /* Added */
                                top: 30px;
                                /* Adjusted */
                                left: 50%;
                                /* Center horizontally */
                                transform: translateX(-50%);
                                /* Center horizontally */
                            }

                            .google-button {
                                background-color: #ffffff;
                                color: #000000;
                                font-weight: bold;
                                text-align: center;
                                border: 2px solid #0056b3;
                                /* Darker blue border color */
                                outline: none;
                                width: 100%;
                                padding: 10px;
                                border-radius: 5px;
                                display: flex;
                                align-items: center;
                                justify-content: center;
                            }

                            .google-icon {
                                margin-right: 10px;
                                width: 24px;
                                height: 24px;
                            }

                            .required-field::after {
                                content: '*';
                                color: red;
                                margin-left: 5px;
                            }

                            .error {
                                color: red;
                            }
                        </style>
                    </head>

                    <body>
                        <!-- Brand Heading -->
                        <h1 class="brand-heading"><a href="index.htm"
                                style="text-decoration: none; color: inherit;">Career Development Kit</a></h1>
                        <a href="javascript:history.back()" style="position: absolute; top: 20px; left: 20px;"><i class="fas fa-arrow-left"></i> Back</a>
                        <div class="job-container">
                            <div class="card">
                                <div class="card-header text-center">
                                    <h3>Job Posting</h3>
                                    <!-- Add any success message here if needed -->
                                </div>
                                <div class="card-body">
                                    <form:form action="postJob.htm" method="post" modelAttribute="job">
                                        <div class="mb-3">
                                            <label>Title <span class="required-field"></span></label>
                                            <form:input type="text" path="title" class="form-control" />
                                            <form:errors path="title" cssClass="error" />
                                        </div>
                                        <div class="mb-3">
                                            <label>Company<span class="required-field"></span></label>
                                            <form:input type="text" path="company" class="form-control" />
                                            <form:errors path="company" cssClass="error" />
                                        </div>
                                        <div class="mb-3">
                                            <label>Place<span class="required-field"></span></label>
                                            <form:input type="text" path="place" class="form-control" />
                                            <form:errors path="place" cssClass="error" />
                                        </div>
                                        <div class="mb-3">
                                            <label>Pay</label>
                                            <form:input type="text" path="pay" class="form-control" />
                                        </div>
                                        <div class="mb-3">
                                            <label>Mode</label>
                                            <select class="form-select" name="mode">
                                                <option value="FullTime">FullTime</option>
                                                <option value="Hybrid">Hybrid</option>
                                                <option value="Remote">Remote</option>
                                                <option value="Negotiable">Negotiable</option>
                                            </select>
                                        </div>
                                        <div class="mb-3">
                                            <label>Description<span class="required-field"></span></label>
                                            <form:textarea id="description" path="description" class="form-control"></form:textarea>
                                            <div id="description-counter" class="text-muted">0/255 characters</div>
                                            <form:errors path="description" cssClass="error" />
                                        </div>
                                        <div class="mb-3">
                                            <label>Skills<span class="required-field"></span></label>
                                            <br/>
                                            <form:errors path="skills" cssClass="error" />
                                            <div class="row">
                                                <div class="col-md-4">
                                                    <label>
                                                        <form:checkbox path="skills" value="Java" /> Java
                                                    </label>
                                                </div>
                                                <div class="col-md-4">
                                                    <label>
                                                        <form:checkbox path="skills" value="Python" /> Python
                                                    </label>
                                                </div>
                                                <div class="col-md-4">
                                                    <label>
                                                        <form:checkbox path="skills" value="REACT" /> REACT
                                                    </label>
                                                </div>
                                                <div class="col-md-4">
                                                    <label>
                                                        <form:checkbox path="skills" value="SAP" /> SAP
                                                    </label>
                                                </div>
                                                <div class="col-md-4">
                                                    <label>
                                                        <form:checkbox path="skills" value="ERP" /> ERP
                                                    </label>
                                                </div>
                                                <div class="col-md-4">
                                                    <label>
                                                        <form:checkbox path="skills" value="GoLang" /> GoLang
                                                    </label>
                                                </div>
                                                <div class="col-md-4">
                                                    <label>
                                                        <form:checkbox path="skills" value="Cloud engineer" /> Cloud
                                                        engineer
                                                    </label>
                                                </div>
                                                <div class="col-md-4">
                                                    <label>
                                                        <form:checkbox path="skills" value="Network Engineer" /> Network
                                                        Engineer
                                                    </label>
                                                </div>
                                                <div class="col-md-4">
                                                    <label>
                                                        <form:checkbox path="skills" value="Hardware" /> Hardware
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="mb-3">
                                            <label>Education</label>
                                            <form:input type="text" path="education" class="form-control" />
                                        </div>
                                        <div class="mb-3">
                                            <label>Full Address</label>
                                            <form:input type="text" path="fullAddress" class="form-control" />
                                        </div>
                                        <div class="mb-3">
                                            <label>Full Description<span class="required-field"></span></label>
                                            <form:textarea id="fullDesc" path="fullDesc" class="form-control"></form:textarea>
                                            <div id="fullDesc-counter" class="text-muted">0/1200 characters</div>
                                            <form:errors path="fullDesc" cssClass="error" />
                                        </div>
                                        <button class="btn btn-primary col-md-12 mb-3">Post Job</button><br />
                                    </form:form>
                                </div>
                            </div>
                        </div>

                        <script>
                            const descriptionInput = document.getElementById('description');
                            const descriptionCounter = document.getElementById('description-counter');
                            descriptionInput.addEventListener('input', function () {
                            const count = this.value.length;
                            descriptionCounter.textContent = count + '/255 characters';
                            });
                            const fullDescInput = document.getElementById('fullDesc');
                            const fullDescCounter = document.getElementById('fullDesc-counter');
                            fullDescInput.addEventListener('input', function () {
                            const count = this.value.length;
                            fullDescCounter.textContent = count + '/1200 characters';
                            });
                            </script>
                    </body>

                    </html>