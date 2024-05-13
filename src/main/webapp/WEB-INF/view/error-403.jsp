<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Restricted Access</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
    <style>
        * {
            position: relative;
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Lato', sans-serif;
        }

        body {
            height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            background: linear-gradient(to bottom right, #EEE, #AAA);
        }

        h1 {
            margin: 40px 0 20px;
        }

        .lock {
            border-radius: 5px;
            width: 55px;
            height: 45px;
            background-color: #333;
            animation: dip 1s;
            animation-delay: calc(2s - .5s);

            &::before,
            &::after {
                content: '';
                position: absolute;
                border-left: 5px solid #333;
                height: 20px;
                width: 15px;
                left: calc(50% - 12.5px);
            }

            &::before {
                top: -30px;
                border: 5px solid #333;
                border-bottom-color: transparent;
                border-radius: 15px 15px 0 0;
                height: 30px;
                animation: lock 2s, spin 2s;
            }

            &::after {
                top: -10px;
                border-right: 5px solid transparent;
                animation: spin 2s;
            }
        }

        @keyframes lock {
            0% {
                top: -45px;
            }
            65% {
                top: -45px;
            }
            100% {
                top: -30px;
            }
        }

        @keyframes spin {
            0% {
                transform: scaleX(-1);
                left: calc(50% - 30px);
            }
            65% {
                transform: scaleX(1);
                left: calc(50% - 12.5px);
            }
        }

        @keyframes dip {
            0% {
                transform: translateY(0px);
            }
            50% {
                transform: translateY(10px);
            }
            100% {
                transform: translateY(0px);
            }
        }

        .message {
            text-align: center;
            margin-top: 20px;
        }

        .message h1 {
            font-size: 36px; /* Increased font size */
            font-weight: bold; /* Added font weight */
            margin-bottom: 10px; /* Adjusted margin */
        }

        .message h2 {
            font-size: 24px; /* Adjusted font size */
            margin-bottom: 20px; /* Adjusted margin */
        }

        .message p {
            font-size: 18px;
        }

        .link_403 {
			color: #fff !important;
			padding: 10px 20px;
			background: #7d7f7c;
			margin: 20px 0;
			display: inline-block;
		}
    </style>
</head>
<body>
    <div class="lock"></div>
    <div class="message">
        <h1>403</h1>
        <h2>Access to this page is restricted</h2><br/>
        <p>Please check with the site admin if you believe this is a mistake.</p>
        <a href="welcome_user.htm" class="link_403">Go to Home</a>
    </div>
</body>
</html>
