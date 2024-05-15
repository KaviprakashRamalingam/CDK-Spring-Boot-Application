<%--
Document   : register
Created on : Mar 23, 2024, 8:08:35 PM
Author     : kaviprakashramalingam
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Register</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
              crossorigin="anonymous">
        <!-- Font Awesome CDN link -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <style>
            body {
                background-color: #F3F2F1;
            }
            .register-container {
                width: 500px;
                margin: 100px auto;
                padding: 20px;
                background-color: #ffffff;
                border-radius: 20px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
                position: relative; /* Added */
            }
            .brand-heading {
                color: #0056b3; /* Darker blue color */
                text-align: center;
                margin-bottom: 20px;
                position: absolute; /* Added */
                top: 30px; /* Adjusted */
                left: 50%; /* Center horizontally */
                transform: translateX(-50%); /* Center horizontally */
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
            .google-button {
                background-color: #ffffff;
                color: #000000;
                font-weight: bold;
                text-align: center;
                border: 2px solid #0056b3; /* Darker blue border color */
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
            .password-toggle-btn {
                position: absolute;
                top: 60%;
                right: 10px;
                transform: translateY(-50%);
                cursor: pointer;
            }
            .error {
                color: red;
            }
        </style>
    </head>
    <body>
        <!-- Brand Heading -->
        <h1 class="brand-heading"><a href="index.htm" style="text-decoration: none; color: inherit;">Career Development Kit</a></h1>
        <div class="register-container">
            <div class="card">
                <div class="card-header text-center">
                    <h3>Registration Page</h3>
                    <c:if test="${not empty msg }">
                        <h5 class="text-success">${msg }</h5>
                        <c:remove var="msg" />
                    </c:if>
                </div>
                <div class="card-body">
                    <form:form action="createUser.htm" method="post" modelAttribute="user">
                        <div class="mb-3">
                            <label>Enter Full Name <span class="required-field"></span></label>
                                <form:input type="text" path="fullname" class="form-control" />
                                <form:errors path="fullname" cssClass="error" />
                        </div>
                        <div class="mb-3">
                            <label>Enter Address</label>
                            <!--<input type="text" name="address" class="form-control">-->
                            <form:input type="text" path="address" class="form-control" />
                        </div>
                        <div class="mb-3">
                            <label>Enter Email <span class="required-field"></span></label>
                            <!--<input type="email" name="email" class="form-control">-->
                            <form:input type="text" path="email" class="form-control" />
                            <form:errors path="email" cssClass="error" />
                        </div>
                        <div class="mb-3" style="position: relative;">
                            <label>Enter Password <span class="required-field"></span></label>
                            <!--<input type="password" name="password" class="form-control" id="passwordField">-->
                            <form:input type="password" path="password" class="form-control" id="passwordField" />
                            <span class="password-toggle-btn" onclick="togglePasswordVisibility()">
                                <i class="fas fa-eye"></i>
                            </span>
                            <form:errors path="password" cssClass="error" />
                        </div>
                        <!--                        <div class="mb-3" style="position: relative;">
                                                    <label>Re-type Password <span class="required-field"></span></label>
                                                    <input type="password" name="password" class="form-control" id="passwordField">
                                                    <span class="password-toggle-btn" onclick="togglePasswordVisibility()">
                                                        <i class="fas fa-eye"></i>
                                                    </span>
                                                </div>-->
                        <div class="mb-3">
                            <label for="userType">Describe yourself as:</label>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="userType" id="jobSeeker" value="user">
                                <label class="form-check-label" for="jobSeeker">Job Seeker</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="userType" id="employer" value="employer">
                                <label class="form-check-label" for="employer">Employer</label>
                            </div>
                        </div>

                        <button class="btn btn-primary col-md-12 mb-3">Register</button><br/>
                    </form:form>
                </div>
            </div>
        </div>
        <script>
            function togglePasswordVisibility() {
                var passwordField = document.getElementById('passwordField');
                if (passwordField.type === "password") {
                    passwordField.type = "text";
                } else {
                    passwordField.type = "password";
                }
            }
        </script>
    </body>
</html>