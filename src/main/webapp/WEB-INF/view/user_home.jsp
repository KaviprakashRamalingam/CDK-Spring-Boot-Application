<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>User Home</title>
            <!-- Bootstrap CSS -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
            <!-- Font Awesome CSS -->
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
            <style>
                /* Custom CSS */
                .job-list,
                .single-job {
                    margin-top: 20px;
                    padding: 10px;
                    border: 1px solid #ccc;
                    border-radius: 5px;
                    background-color: #f9f9f9;
                }

                .job-list .job-box {
                    margin-bottom: 20px;
                }

                .job-list .job-box .job-title {
                    font-size: 18px;
                    font-weight: bold;
                    margin-bottom: 5px;
                }

                .job-list .job-box .job-details {
                    font-size: 14px;
                    margin-bottom: 5px;
                    word-wrap: break-word;
                }

                .pay-mode-box {
                    background-color: #f2f2f2;
                    padding: 5px;
                    display: inline-block;
                    margin-top: 5px;
                }

                .job-list .job-box .job-pay,
                .job-list .job-box .job-mode {
                    background-color: #f0f0f0;
                    padding: 5px 10px;
                    border-radius: 5px;
                    margin-right: 10px;
                }

                .job-list .job-box .job-skills {
                    font-style: italic;
                }

                .single-job {
                    padding: 20px;
                    border: 1px solid #ccc;
                    border-radius: 5px;
                    background-color: #f9f9f9;
                }

                .search-container {
                    text-align: center;
                    margin-bottom: 20px;
                    margin-top: 25px;
                }

                .search-box-container {
                    border: 1px solid #ccc;
                    border-radius: 5px;
                    display: inline-flex;
                    align-items: center;
                }

                .search-box {
                    width: 200px;
                    padding: 8px;
                    border: none;
                    border-radius: 5px;
                    margin-right: 10px;
                    text-align: left;
                }

                .search-button {
                    padding: 8px 15px;
                    background-color: #007bff;
                    color: white;
                    border: none;
                    border-radius: 5px;
                    cursor: pointer;
                    margin: 5px 5px 5px 0px;
                }

                .search-icon {
                    margin: 0 10px;
                    color: #888;
                }

                /* Disappearing box styles */
                .error-box {
                    position: fixed;
                    top: 20px;
                    left: 50%;
                    transform: translateX(-50%);
                    padding: 10px 20px;
                    background-color: #f44336;
                    /* Red color for error */
                    color: white;
                    border-radius: 5px;
                    z-index: 9999;
                    display: none;
                    /* Initially hidden */
                    width: fit-content;
                }

                .job-list {
                    cursor: pointer;
                }

                .job-list:hover .job-title {
                    text-decoration: underline;
                }

                .pagination {
                    justify-content: center;
                }

                .success-box {
                    position: fixed;
                    top: 20px;
                    left: 50%;
                    transform: translateX(-50%);
                    padding: 10px 20px;
                    background-color: #4CAF50;
                    color: white;
                    border-radius: 5px;
                    z-index: 9999;
                    display: none;
                    width: fit-content;
                }
            </style>
        </head>

        <body>
            <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
                <div class="container-fluid">
                    <a class="navbar-brand" href="#">Career Development Kit</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <!-- Add your navigation links here -->
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page"></a>
                            </li>
                            <!-- Add additional navigation links here -->
                        </ul>
                        <form class="d-flex">
                            <!-- Add a search form or other content here if needed -->
                        </form>
                        <div class="d-flex">
                            <a href="mydetails.htm" class="btn btn-outline-light">My Details</a>
                            <a href="logout.htm" class="btn btn-outline-light me-2">Logout</a>
                        </div>
                    </div>
                </div>
            </nav>
            <div id="errorBox" class="error-box">
                <c:out value="${emp_msg}" />
            </div>
            <div id="successBox" class="success-box">
                <c:out value="${job_msg}" />
            </div>

            <div class="container">
                <div class="search-container">
                    <form action="searchJobs.htm" method="post" class="search-box-container">
                        <i class="fas fa-search search-icon"></i>
                        <input type="text" name="search" class="search-box" placeholder="Search...">
                        <button type="submit" class="search-button">Search</button>
                    </form>
                </div>
                <div class="search-container"><a href="createjob.htm">Employers: post a job</a>-your next hire is here
                </div>
                <div class="row">
                    <div class="col-lg-6">
                        <c:forEach var="job" items="${jobs}">
                            <div class="job-list" data-job-id="${job.id}">
                                <div class="job-box">
                                    <div class="job-title">${job.title}</div>
                                    <div class="job-details">${job.company}</div>
                                    <div class="pay-mode-box">${job.pay}</div>
                                    <div class="pay-mode-box">${job.mode}</div>
                                    <div class="job-details">Description: ${job.description}</div>
                                    <div class="job-skills">Skills: ${job.skills}</div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="col-lg-6">
                        <div class="single-job">
                            <p>Click on a job to view its details</p>
                        </div>
                    </div>
                </div>
                <!-- Pagination -->
                <c:if test="${totalPages > 1}">
                    <nav aria-label="Page navigation">
                        <ul class="pagination justify-content-center">
                            <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                <a class="page-link" href="?page=1" tabindex="-1" aria-disabled="true">First</a>
                            </li>
                            <c:forEach var="pageNumber" begin="1" end="${totalPages}">
                                <li class="page-item ${pageNumber == currentPage ? 'active' : ''}">
                                    <a class="page-link" href="?page=${pageNumber}">${pageNumber}</a>
                                </li>
                            </c:forEach>
                            <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                                <a class="page-link" href="?page=${totalPages}">Last</a>
                            </li>
                        </ul>
                    </nav>
                </c:if>
            </div>

            <!-- Bootstrap Bundle with Popper -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

            <script>
                document.addEventListener("DOMContentLoaded", function () {
                    var errorBox = document.getElementById("errorBox");
                    if (errorBox && errorBox.textContent.trim().length > 0) {
                        errorBox.style.display = "block";
                        setTimeout(function () {
                            errorBox.style.display = "none"; 
                        }, 2000); // 2 seconds
                    }
                });
            </script>
            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <script>
                $(document).ready(function () {
                    $('.job-list').click(function () {
                        var jobId = $(this).data('job-id');
                        $.ajax({
                            type: 'GET',
                            url: 'getJobDetails.htm',
                            data: { jobId: jobId },
                            success: function (response) {
                                $('.single-job').html(response);
                            }
                        });
                    });
                });
            </script>
            <script>
                document.addEventListener("DOMContentLoaded", function () {
                    var successBox = document.getElementById("successBox");
                    if (successBox && successBox.textContent.trim().length > 0) {
                        successBox.style.display = "block";
                        setTimeout(function () {
                            successBox.style.display = "none";
                        }, 2000); // 2 seconds
                    }
                });
            </script>
        </body>

        </html>