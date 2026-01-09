<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Đăng nhập</title>
    <style>
        * {
            box-sizing: border-box;
            font-family: "Segoe UI", Tahoma, sans-serif;
        }

        body {
            height: 100vh;
            margin: 0;
            background: linear-gradient(135deg, #1d2671, #c33764);
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .login-container {
            width: 380px;
            padding: 35px;
            border-radius: 16px;
            background: rgba(255, 255, 255, 0.15);
            backdrop-filter: blur(12px);
            box-shadow: 0 25px 50px rgba(0,0,0,0.3);
            color: #fff;
            animation: fadeIn 0.8s ease;
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        .login-container h2 {
            text-align: center;
            margin-bottom: 10px;
        }

        .login-container p {
            text-align: center;
            font-size: 14px;
            opacity: 0.8;
            margin-bottom: 25px;
        }

        .form-group {
            margin-bottom: 18px;
        }

        .form-group label {
            font-size: 13px;
            margin-bottom: 6px;
            display: block;
        }

        .form-group input {
            width: 100%;
            padding: 12px;
            border-radius: 8px;
            border: none;
            outline: none;
            font-size: 14px;
        }

        .form-group input:focus {
            box-shadow: 0 0 0 2px #ff9f9f;
        }

        .btn-login {
            width: 100%;
            padding: 12px;
            border-radius: 10px;
            border: none;
            font-size: 16px;
            font-weight: bold;
            background: linear-gradient(135deg, #ff758c, #ff7eb3);
            color: #fff;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .btn-login:hover {
            transform: translateY(-2px);
            box-shadow: 0 10px 25px rgba(255, 120, 150, 0.5);
        }

        .error {
            background: rgba(255, 80, 80, 0.25);
            color: #ffdede;
            padding: 10px;
            border-radius: 8px;
            text-align: center;
            margin-bottom: 15px;
            font-size: 14px;
        }

        .footer-text {
            text-align: center;
            margin-top: 20px;
            font-size: 12px;
            opacity: 0.7;
        }
    </style>
</head>
<body>

<div class="login-container">
    <h2>Welcome Back</h2>
    <p>Đăng nhập để tiếp tục</p>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <form method="post">
        <div class="form-group">
            <label>Username</label>
            <input type="text" name="username" placeholder="Nhập username" required>
        </div>

        <div class="form-group">
            <label>Password</label>
            <input type="password" name="password" placeholder="Nhập mật khẩu" required>
        </div>

        <button class="btn-login">Login</button>
    </form>

    <div class="footer-text">
        © 2025 Your Project | Java Web MVC
    </div>
</div>

</body>
</html>
