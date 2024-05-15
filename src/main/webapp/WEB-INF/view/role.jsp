<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Select Your Role</title>
    <link rel="stylesheet" href="styles.css">
    <style>
        body, html {
            height: 100%;
            margin: 0;
        }

        .background {
            /* Add funky background images or animations here */
            background-image: url('https://source.unsplash.com/random'); /* Example random image from Unsplash */
            background-size: cover;
            height: 100%;
            position: fixed;
            width: 100%;
        }

        .overlay {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: rgba(0, 0, 0, 0.5); /* Semi-transparent black overlay */
            padding: 20px; /* Adjust padding as needed */
            border-radius: 10px; /* Adjust border radius as needed */
            color: #fff;
            text-align: center;
        }

        .container {
            align-items: center;
            display: flex;
            flex-direction: column;
            height: 100%;
            justify-content: center;
            position: absolute;
            width: 100%;
        }

        .title {
            font-size: 2rem;
            margin-bottom: 20px; /* Add margin to separate from buttons */
        }

        .options {
            display: flex;
            margin-top: 20px; /* Add margin to separate from title */
        }

        .option {
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            color: #fff;
            cursor: pointer;
            font-size: 1rem;
            margin: 0 10px;
            padding: 10px 20px;
            transition: background-color 0.3s;
        }

        .option:hover {
            background-color: #0056b3;
        }

    </style>
</head>
<body>
    <div class="background">
        <div class="overlay">
            <h1 class="title">Let's understand your role</h1>
            <div class="options">
                <a href="selectRole?role=user" class="option">Job seeker</a>
                <a href="selectRole?role=employer" class="option">Employer</a>
            </div>
        </div>
    </div>

</body>
</html>
