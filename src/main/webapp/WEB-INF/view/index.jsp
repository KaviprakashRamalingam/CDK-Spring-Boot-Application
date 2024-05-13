<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CDK-Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
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

        /* Animation for image */
        .zoomIn {
            animation: zoomInAnimation ease 2s;
            animation-iteration-count: 1;
            animation-fill-mode: forwards;
        }

        @keyframes zoomInAnimation {
            0% {
                transform: scale(0.5);
            }
            100% {
                transform: scale(1);
            }
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
<div class="container mt-5">
    <div class="row">
        <div class="col-md-6">
            <h2>Welcome to Career Development Kit!</h2>
            <p>This platform offers various resources and tools to help you advance in your career.</p>
            <p>Whether you're looking for job opportunities, career advice, or skill development, we've got you covered.</p>
        </div>
        <div class="col-md-6">
            <img src="https://images.pexels.com/photos/9832700/pexels-photo-9832700.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2" alt="CDK Image" class="img-fluid zoomIn">
        </div>
    </div>
</div>
</body>
</html>
