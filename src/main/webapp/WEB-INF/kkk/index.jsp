<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Google</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Product+Sans:ital,wght@0,400;1,400&display=swap');

        body {
            font-family: 'Product Sans', Arial, sans-serif;
            text-align: center;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            justify-content: center;
            height: 100vh;
            background: url('https://images.unsplash.com/photo-1534081333815-ae5019106622?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNjUyOXwwfDF8c2VhcmNofDd8fG5pZ2h0fGVufDB8fHx8MTY4MDA3Nzg4Nw&ixlib=rb-1.2.1&q=80&w=1080') no-repeat center center fixed;
            background-size: cover;
            color: white;
        }
        .logo {
            margin-bottom: 20px;
            font-size: 100px;
            font-weight: bold;
        }
        .logo .my {
            color: #EA4335;
        }
        .logo .google {
            color: #4285F4;
        }
        .logo .o {
            color: #FBBC05;
        }
        .logo .g {
            color: #34A853;
        }
        .search-box {
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .search-box input {
            width: 600px;
            height: 44px;
            padding: 0 15px;
            font-size: 18px;
            border: 1px solid #dfe1e5;
            border-radius: 24px 0 0 24px;
            outline: none;
            box-shadow: 0 1px 6px rgba(32, 33, 36, 0.28);
            border-right: none;
            background-color: rgba(255, 255, 255, 0.9);
        }
        .search-box button {
            height: 44px;
            width: 50px;
            background-color: rgba(255, 255, 255, 0.9);
            border: 1px solid #dfe1e5;
            border-left: none;
            border-radius: 0 24px 24px 0;
            cursor: pointer;
            outline: none;
            box-shadow: 0 1px 6px rgba(32, 33, 36, 0.28);
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .search-box button img {
            width: 20px;
            height: 20px;
        }
    </style>
</head>
<body>
    <div class="logo">
        <span class="my">My</span>
        <span class="google">G</span><span class="o">o</span><span class="google">o</span><span class="g">g</span><span class="google">l</span><span class="google">e</span>
    </div>
    <div class="search-box">
        <input type="text" placeholder="Search...">
        <button><img src="https://www.google.com/images/icons/material/system/1x/search_black_24dp.png" alt="Search"></button>
    </div>
</body>
</html>
