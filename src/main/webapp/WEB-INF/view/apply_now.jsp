<%-- Document : apply_now 
     Created on : Mar 23, 2024, 6:55:09 PM 
     Author : kaviprakashramalingam 
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored="false" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <title>Apply Now</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
            crossorigin="anonymous">
        <link rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <style>
            .navbar-brand {
                font-weight: bold;
            }

            .navbar-nav {
                margin-left: auto;
            }

            .nav-item {
                margin-right: 10px;
            }

            .line-container {
                display: flex;
                align-items: center;
                justify-content: center;
            }

            .line {
                border-top: 1px solid #ccc;
                width: 100%;
                margin-top: 20px;
                margin-bottom: 20px;
            }

            .form-container {
                margin-top: 20px;
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 5px;
                background-color: #f9f9f9;
            }

            .form-group {
                margin-bottom: 20px;
            }

            .form-group label {
                font-weight: bold;
            }

            .form-group input[type="text"],
            .form-group input[type="email"],
            .form-group input[type="tel"] {
                width: 100%;
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }

            .education-group,
            .work-experience-group {
                border: 1px solid #ccc;
                padding: 10px;
                border-radius: 5px;
                position: relative;
                margin-bottom: 24px;
                margin-top: 24px;
            }

            .add-button {
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 50%;
                padding: 8px;
                cursor: pointer;
                position: relative;
                top: 10px;
                margin-bottom: 24px;
            }

            .delete-button {
                position: absolute;
                top: 5px;
                right: 5px;
                color: red;
                cursor: pointer;
            }

            .submit-button {
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 5px;
                padding: 10px 20px;
                cursor: pointer;
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
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <div class="container-fluid">
                <a class="navbar-brand" href="index.htm">Career Development Kit</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mb-2 mb-lg-0">
                        <li class="nav-item"><a class="nav-link" href="logout">Logout</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container">
            <div class="line-container">
                <div class="line"></div>
            </div>
            <a href="javascript:history.back()" style="position: absolute; top: 56px; left: 10px;"><i class="fas fa-arrow-left"></i> Back</a>
            <div class="form-container">
                <h3>Apply Now</h3>
                <form:form action="submit_application" method="POST" enctype="multipart/form-data"
                    modelAttribute="application">
                    <input type="hidden" name="jobId" value="${param.jobId}">
                    <div class="form-group">
                        <label for="resume">Upload Resume:<span class="required-field"></span></label>
                        <input type="file" id="resume" name="resumeFile" />
                        <form:errors path="resumeFile" cssClass="error" />
                    </div>
                    <div class="form-group">
                        <label for="firstname">First Name:<span class="required-field"></span></label>
                        <form:input path="firstName" id="firstName" />
                        <form:errors path="firstName" cssClass="error" />
                    </div>
                    <div class="form-group">
                        <label for="lastname">Last Name:<span class="required-field"></span></label>
                        <form:input path="lastName" id="lastName" />
                        <form:errors path="lastName" cssClass="error" />
                    </div>
                    <div class="form-group">
                        <label for="email">Email:<span class="required-field"></span></label>
                        <form:input path="email" id="email" />
                        <form:errors path="email" cssClass="error" />
                    </div>
                    <div class="form-group">
                        <label for="phone">Phone:<span class="required-field"></span></label>
                        <form:input path="phone" id="phone" />
                        <form:errors path="phone" cssClass="error" />
                    </div>

                    <div class="form-group">
                        <h5>Education:</h5>
                        <div id="education-container">
                            <div class="education-group">
                                <div class="form-group">
                                    <label for="collegeName">College Name:</label>
                                    <input type="text" id="collegeName" name="collegeName[]">
                                </div>
                                <div class="form-group">
                                    <label for="startDate">Start Date:</label>
                                    <input type="date" id="startDate" name="startDate[]">
                                </div>
                                <div class="form-group">
                                    <label for="endDate">End Date:</label>
                                    <input type="date" id="endDate" name="endDate[]">
                                </div>
                            </div>
                        </div>
                        <button type="button" id="add-education" class="add-button">+</button>
                    </div>

                    <div class="form-group">
                        <h5>Work Experience:</h5>
                        <div id="work-experience-container">
                            <div class="work-experience-group">
                                <div class="form-group">
                                    <label for="companyName">Company Name:</label>
                                    <input type="text" id="companyName" name="companyName[]">
                                </div>
                                <div class="form-group">
                                    <label for="workStartDate">Start Date:</label>
                                    <input type="date" id="workStartDate" name="workStartDate[]">
                                </div>
                                <div class="form-group">
                                    <label for="workEndDate">End Date:</label>
                                    <input type="date" id="workEndDate" name="workEndDate[]">
                                </div>
                            </div>
                        </div>
                        <button type="button" id="add-work-experience" class="add-button">+</button>
                    </div>
                    <div class="form-group">
                        <label>Are you legally authorized to work in the job posting country?<span class="required-field"></span></label><br>
                        <input type="radio" name="applicationDetails.authorization" value="Yes"> Yes
                        <input type="radio" name="applicationDetails.authorization" value="No"> No
                        <form:errors path="applicationDetails.authorization" cssClass="error" />
                    </div>
                    <!-- Add Sponsorship Question -->
                    <div class="form-group">
                        <label>Will you now, or in the future, require sponsorship for employment to work in the job posting country?<span class="required-field"></span></label><br>
                        <input type="radio" name="applicationDetails.sponsorshipQuestion" value="Yes"> Yes
                        <input type="radio" name="applicationDetails.sponsorshipQuestion" value="No"> No
                        <form:errors path="applicationDetails.sponsorshipQuestion" cssClass="error" />
                    </div>
                    <!-- Add Gender Selection Dropdown -->
                    <div class="form-group">
                        <label>Please select your gender<span class="required-field"></span></label><br>
                        <select name="applicationDetails.gender">
                            <option value="">No selection</option>
                            <option value="Male">Male</option>
                            <option value="Female">Female</option>
                            <option value="NA">Prefer not to say</option>
                        </select>
                        <form:errors path="applicationDetails.gender" cssClass="error" />
                    </div>
                    <!-- Add Hispanic or Latino Question -->
                    <div class="form-group">
                        <label>Are you Hispanic or Latino?<span class="required-field"></span></label><br>
                        <input type="radio" name="applicationDetails.hispanic" value="Yes"> Yes
                        <input type="radio" name="applicationDetails.hispanic" value="No"> No
                        <form:errors path="applicationDetails.hispanic" cssClass="error" />
                    </div>
                    <!-- Add Race Selection Dropdown -->
                    <div class="form-group">
                        <label>Please select your race<span class="required-field"></span></label><br>
                        <select name="applicationDetails.race">
                            <option value="US">American Indian/Alaska Native (United States of America)</option>
                            <option value="Asian">Asian</option>
                            <option value="African">Black/African American</option>
                            <option value="Hispanic">Hispanic/Latino</option>
                            <option value="NA">I choose not to disclose</option>
                            <option value="Pac">Native Hawaiian/Oth Pac Island</option>
                            <option value="Many">Two or More Races</option>
                            <option value="White">White</option>
                        </select>
                        <form:errors path="applicationDetails.race" cssClass="error" />
                    </div>
                    <!-- Add U.S. Military Veteran Question -->
                    <div class="form-group">
                        <label>Are you a protected U.S. Military Veteran?<span class="required-field"></span></label><br>
                        <select name="applicationDetails.veteran">
                            <option value="Yes">I IDENTIFY AS ONE OR MORE OF THE CLASSIFICATIONS OF PROTECTED VETERANS LISTED ABOVE</option>
                            <option value="Only Veteran">I IDENTIFY AS A VETERAN, JUST NOT A PROTECTED VETERAN</option>
                            <option value="No">I AM NOT A VETERAN</option>
                        </select>
                        <form:errors path="applicationDetails.veteran" cssClass="error" />
                    </div>
                    <div>
                        <h4>Terms and Conditions<span class="required-field"></span></h4>
                        <p>Integrity at CDK: </br>CDK's standards of business conduct, called Integrity at CDK, embody the fundamental principles that govern our ethical and legal obligations to CDK. They pertain not only to our conduct within the company but also to conduct involving our customers, channel partners, suppliers and competitors. Read more about how we win the right way.</p>
                        <p>Equal Opportunity Employer (EEO):</br> CDK provides equal employment opportunity without regard to race, color, religion, sex, national origin, ancestry, citizenship, sexual orientation, age, disability, or status as a disabled veteran, recently separated veteran, Armed Forces Service Medal veteran, or other protected veteran, marital status, familial status, age, handicap or disability, genetic predisposition or carrier status, uniformed service status or any other characteristic protected by applicable law. If you’d like more information about your EEO rights as an applicant under the law, please click here: Equal Employment Opportunity is the Law Equal Employment Opportunity is the Law - Supplement</p>
                        <p>Pay Transparency Nondiscrimination Provision:</br>The contractor will not discharge or in any other manner discriminate against employees or applicants because they have inquired about, discussed, or disclosed their own pay or the pay of another employee or applicant. However, employees who have access to the compensation information of other employees or applicants as a part of their essential job functions cannot disclose the pay of other employees or applicants to individuals who do not otherwise have access to compensation information, unless the disclosure is (a) in response to a formal complaint or charge, (b) in furtherance of an investigation, proceeding, hearing, or action, including an investigation conducted by the employer, or (c) consistent with the contractor's legal duty to furnish information.</p>
                        <p>Accessibility </br>CDK is committed to working with and providing reasonable accommodation to qualified individuals with physical and mental disabilities. If you need assistance in filling out the employment application or require a reasonable accommodation while seeking employment, please e-mail cdk.pvt.ltd@gmail.com. </br>Note: This option is reserved for applicants needing a reasonable accommodation related to a disability.</p>
                    </div>
                    <div class="form-group">
                        <input type="checkbox" id="terms" name="applicationDetails.terms">
                        <label for="terms">Yes, I have read and consent to the terms and conditions<span class="required-field"></span></label>
                        <form:errors path="applicationDetails.terms" cssClass="error" />
                    </div>
                    <button type="submit" class="submit-button">Submit Application</button>
                </form:form>
                <!-- <a href="${flowExecutionUrl}&_eventId=next">Next</a> -->
            </div>
        </div>

        <script>
            function addDeleteButton(container) {
                var deleteButton = document.createElement('i');
                deleteButton.className = 'fas fa-trash delete-button';
                deleteButton.style.display = 'block';
                deleteButton.addEventListener('click', function () {
                    container.remove();
                });
                container.appendChild(deleteButton);
            }

            document.getElementById('add-education').addEventListener('click', function () {
                var educationContainer = document.getElementById('education-container');
                var newEducationGroup = educationContainer.children[0].cloneNode(true);
                educationContainer.appendChild(newEducationGroup);
                addDeleteButton(newEducationGroup);
            });

            document.getElementById('add-work-experience').addEventListener('click', function () {
                var workExperienceContainer = document.getElementById('work-experience-container');
                var newWorkExperienceGroup = workExperienceContainer.children[0].cloneNode(true);
                workExperienceContainer.appendChild(newWorkExperienceGroup);
                addDeleteButton(newWorkExperienceGroup);
            });

            $(document).ready(function () {
                $('#resume').change(function () {
                    var formData = new FormData();
                    formData.append('resume', $(this)[0].files[0]);

                    // Send AJAX request
                    $.ajax({
                        url: '/fill_data', // Your controller URL
                        type: 'POST',
                        data: formData,
                        cache: false,
                        contentType: false,
                        processData: false,
                        success: function (response) {
                            response = JSON.parse(response);
                            $('#firstName').val(response.firstName);
                            $('#lastName').val(response.lastName);
                            $('#phone').val(response.phoneNumber);
                            $('#email').val(response.email);
                        },
                        error: function (xhr, status, error) {
                            console.log('Error:', error);
                        }
                    });
                });
            });
        </script>
    </body>

    </html>