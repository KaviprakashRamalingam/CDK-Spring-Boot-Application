<%-- 
    Document   : myDetails
    Created on : Apr 5, 2024, 2:24:25 AM
    Author     : kaviprakashramalingam
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Details</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <style>
            /* Custom CSS */
            /* Add your custom CSS here */
        </style>
    </head>
    <body>
        <!-- Navigation Bar -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <div class="container-fluid">
                <a class="navbar-brand" href="index.htm">Career Development Kit</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <!--<a class="nav-link active" aria-current="page" href="#">Home</a>-->
                        </li>
                    </ul>
                    <form class="d-flex">
                    </form>
                    <div class="d-flex">
                        <a href="logout.htm" class="btn btn-outline-light me-2">Logout</a>
                    </div>
                </div>
            </div>
        </nav>

        <!-- Page Content -->
        <div class="container mt-4">
            <!-- Content goes here -->
            <div class="row">
                <div class="col-md-8 offset-md-2">
                    <h2>User Details</h2>
                    <a href="javascript:history.back()" style="position: absolute; top: 56px; left: 10px;"><i class="fas fa-arrow-left"></i> Back</a>
                    <!-- Fetch user details here -->
                    <table class="table">
                        <tbody>
                            <tr>
                                <th>Email:</th>
                                <td>${user.email}</td>
                            </tr>
                            <!-- Add more user details here -->
                        </tbody>
                    </table>
                    <a href="updateProfile.htm" class="btn btn-primary">Update my profile</a>
                    <div id="userDetails"></div>
                    <hr>
                    <h2>My Applications</h2>
                    <div class="row">
                        <div class="col-md-8 offset-md-2">
                            <c:forEach var="application" items="${userApplications}">
                                <div class="job-box mb-3 p-3 border rounded">
                                    <div class="d-flex justify-content-between align-items-center">
                                        <h4>${application.jobtitle}</h4>
                                        <div class="d-flex">
                                            <div>
                                                <a href="deleteApplication.htm?app_Id=${application.id}" class="btn btn-primary">Withdraw</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <c:if test="${empty userApplications}">
                                <p>No active applications.</p>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
            function loadUserDetails() {
                $.ajax({
                    url: "getUserDetails.htm", 
                    type: "GET",
                    success: function(response) {
                        $('#userDetails').html(response);
                        $('#userDetails').show();
                    },
                    error: function(xhr, status, error) {
                        console.error("Error fetching user details: " + error);
                    }
                });
            }
        
            $('#updateProfileBtn').click(function(event) {
                event.preventDefault(); 
                loadUserDetails(); 
            });
        </script>
    </body>
</html>
