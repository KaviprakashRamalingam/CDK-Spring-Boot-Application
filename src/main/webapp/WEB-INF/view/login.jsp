<%-- 
    Document   : login
    Created on : Mar 23, 2024, 6:55:09 PM
    Author     : kaviprakashramalingam
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>CDK-Login</title>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
            crossorigin="anonymous">
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
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <div class="container-fluid">
                <a class="navbar-brand" href="index.htm">Career Development Kit</a>
                <button class="navbar-toggler" type="button"
                        data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false"
                        aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mb-2 mb-lg-0">
                        <li class="nav-item"><a class="nav-link" href="register">Register</a></li>
                        <li class="nav-item"><a class="nav-link" href="login">Login</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="conatiner p-3">
            <div class="row">
                <div class="col-md-4 offset-md-4">
                    <div class="card">
                        <div class="card-header text-center">
                            <h3>Login Page</h3>
                            <c:if test="${not empty msg }">
                                <h5 class="text-success">${msg }</h5>
                                <c:remove var="msg" />
                            </c:if>
                        </div>
                        <div class="card-body">
                            <form action="/userlogin" method="post">

                                <div class="mb-3">
                                    <label>Enter Email</label> <input type="text" name="email" class="form-control">
                                </div>

                                <div class="mb-3">
                                    <label>Enter Password</label> <input type="password" name="password" class="form-control">
                                </div>

                                <button class="btn btn-primary col-md-12">Login</button>
                                <div class="line-container">
                                    <span class="line"></span>
                                    <span class="or-text">Or</span>
                                    <span class="line"></span>
                                </div>
                            </form>
                            <br/>
                            <a class="google-button mt-3"  href="/oauth2/authorization/google">
                                <img src="https://www.gstatic.com/firebasejs/ui/2.0.0/images/auth/google.svg" alt="Google Icon" class="google-icon"> Continue with Google
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function signInWithGoogle() {
                // Redirect the user to the Google sign-in URL
                window.location.href = "oauth2/authorization/google";
            }
        </script>
    </body>
</html>