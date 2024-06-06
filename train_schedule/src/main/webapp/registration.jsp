<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <style>
        header {
            background-color: #ff6a00;
            color: #fff;
            padding: 1em;
            display: flex;
            justify-content: space-between;
            align-items: center;
            font-weight: 600;
            margin-bottom: 7px;
        }
        body {
            background: linear-gradient(135deg, rgba(0, 123, 255, 0.8), rgba(255, 106, 0, 0.8));
            background-repeat: no-repeat;
            background-attachment: fixed;
        }
        .page-container {
            display: flex;
            justify-content: center;
            align-items: center;
            max-height: 100vh;
        }

        .form-container {
            background-color: #fff;
            padding: 2em;
            border-radius: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
        }

        .form-container h2 {
            margin-bottom: 1em;
            color: #333;
        }

        .form-container label {
            display: block;
            margin-bottom: 0.5em;
            color: #333;
        }

        .form-container input {
            width: calc(100% - 1em);
            padding: 0.5em;
            margin-bottom: 1em;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 1em;
        }

        .form-container button {
            background-color: #ff6a00;
            color: #fff;
            border: none;
            padding: 0.75em 1.5em;
            cursor: pointer;
            border-radius: 20px;
            font-size: 1em;
            transition: background-color 0.3s, transform 0.2s;
        }

        .form-container button:hover {
            background-color: #ff8600;
            transform: scale(1.05);
        }
        .error-message {
            background-color: #ffdddd;
            color: #d8000c;
            border: 1px solid #d8000c;
            border-radius: 5px;
            padding: 0.5em;
            margin-top: 1em;
        }

        .ok-message {
            background-color: #daffb9;
            color: #1dbb01;
            border: 1px solid #61d800;
            border-radius: 5px;
            padding: 0.5em;
            margin-top: 1em;
        }

        .link-container {
            margin-top: 1em;
            padding: 0.5em;
            border: 1px solid #ccc;
            border-radius: 10px;
            background-color: #f9f9f9;
            text-align: center;
        }

        .link-container p {
            margin: 0.5em 0;
        }

        .login-button {
            display: inline-block;
            background-color: #007bff;
            color: #fff;
            padding: 0.5em 1em;
            border-radius: 20px;
            text-decoration: none;
            transition: background-color 0.3s, transform 0.2s;
        }

        .login-button:hover {
            background-color: #0056b3;
            transform: scale(1.05);
        }
    </style>
</head>
<body>
<main>
    <div class="page-container">
        <div class="form-container">
            <h2>Register</h2>
            <form action="${pageContext.request.contextPath}/register" method="post">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required><br><br>

                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required><br><br>

                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required><br><br>

                <label for="adminCode">Admin Code:</label>
                <input type="password" id="adminCode" name="adminCode" required><br><br>

                <button type="submit">Register</button>
            </form>
            <c:choose>
                <c:when test="${not empty errorMessage}">
                    <p class="error-message">${errorMessage}</p>
                </c:when>
                <c:when test="${not empty successMessage}">
                    <p class="ok-message">${successMessage}</p>
                </c:when>
            </c:choose>
            <div class="link-container">
                <p>Already have an account?</p>
                <a href="${pageContext.request.contextPath}/login.jsp" class="login-button">Login</a>
            </div>
        </div>
    </div>
</main>
</body>
</html>