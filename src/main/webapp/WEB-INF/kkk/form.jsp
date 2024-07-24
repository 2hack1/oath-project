<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Stylish Form</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #e0f7fa;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .form-container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
            box-sizing: border-box;
        }
        .form-container h2 {
            margin-top: 0;
            text-align: center;
            color: #00796b;
            font-size: 28px;
            font-weight: 600;
        }
        .form-container label {
            display: block;
            margin-bottom: 8px;
            font-weight: 500;
            color: #00796b;
        }
        .form-container input[type="text"],
        .form-container input[type="email"] {
            width: 100%;
            padding: 12px;
            margin-bottom: 20px;
            border: 1px solid #b2dfdb;
            border-radius: 6px;
            box-sizing: border-box;
            font-size: 16px;
            color: #004d40;
            background-color: #e0f2f1;
            transition: border-color 0.3s, background-color 0.3s;
        }
        .form-container input[type="text"]:hover,
        .form-container input[type="email"]:hover {
            background-color: #ffffff;
        }
        .form-container input[type="text"]:focus,
        .form-container input[type="email"]:focus {
            border-color: #00796b;
            background-color: #ffffff;
        }
        .gender-group {
            margin-bottom: 20px;
        }
        .gender-group label {
            font-weight: normal;
            color: #004d40;
        }
        .gender-option {
            display: inline-block;
            margin-right: 15px;
        }
        .gender-option input[type="radio"] {
            display: none;
        }
        .gender-option label {
            display: flex;
            align-items: center;
            cursor: pointer;
            position: relative;
            padding-left: 25px;
        }
        .gender-option label:before {
            content: '';
            display: inline-block;
            width: 20px;
            height: 20px;
            border: 2px solid #00796b;
            border-radius: 50%;
            position: absolute;
            left: 0;
            top: 50%;
            transform: translateY(-50%);
            transition: background-color 0.3s, border-color 0.3s;
        }
        .gender-option input[type="radio"]:checked + label:before {
            background-color: #00796b;
            border-color: #00796b;
        }
        .gender-option label:after {
            content: '';
            display: block;
            width: 10px;
            height: 10px;
            border-radius: 50%;
            background: #ffffff;
            position: absolute;
            left: 5px;
            top: 50%;
            transform: translateY(-50%);
            opacity: 0;
            transition: opacity 0.3s;
        }
        .gender-option input[type="radio"]:checked + label:after {
            opacity: 1;
        }
        .form-container input[type="submit"] {
            background-color: #00796b;
            color: #ffffff;
            border: none;
            padding: 12px 20px;
            text-align: center;
            display: inline-block;
            font-size: 16px;
            border-radius: 6px;
            cursor: pointer;
            width: 100%;
            transition: background-color 0.3s;
        }
        .form-container input[type="submit"]:hover {
            background-color: #004d40;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Stylish Form</h2>
        <form action="aa" method="get">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>

            <label for="city">City:</label>
            <input type="text" id="city" name="city" required>

            <div class="gender-group">
                <div class="gender-option">
                    <input type="radio" id="male" name="gender" value="male">
                    <label for="male">Male</label>
                </div>
                <div class="gender-option">
                    <input type="radio" id="female" name="gender" value="female">
                    <label for="female">Female</label>
                </div>
                <div class="gender-option">
                    <input type="radio" id="other" name="gender" value="other">
                    <label for="other">Other</label>
                </div>
            </div>

            <input type="submit" value="Submit">
        </form>
    </div>
</body>
</html>
