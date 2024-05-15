<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        
    </style>
</head>
<body>
<div class="container mt-4">
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <a href="javascript:history.back()" style="position: absolute; top: 56px; left: 10px;"><i class="fas fa-arrow-left"></i> Back</a>
            <h2>Update Profile</h2>
            <form action="updateUser.htm" method="post" modelAttribute="user">
                <input type="hidden" name="id" value="${user.id}" />
                <div class="mb-3">
                    <label>Full Name:</label>
                    <input type="text" name="fullname" class="form-control" value="${user.fullname}" required>
                </div>
                <div class="mb-3">
                    <label>Address:</label>
                    <input type="text" name="address" class="form-control" value="${user.address}">
                </div>
                <div class="mb-3">
                    <label>Email:</label>
                    <input type="email" name="email" class="form-control" value="${user.email}" disabled>
                </div>
                <div class="mb-3">
                    <label>New Password:</label>
                    <input type="password" name="password" class="form-control">
                </div>
                <input type="hidden" name="userType" value="${user.userType}" class="form-control">
                <button type="submit" class="btn btn-primary">Update</button>
            </form>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</body>
</html>
