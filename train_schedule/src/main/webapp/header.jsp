<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trainly</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
    <style>
        header {
            background-color: #ff6a00;
            color: #fff;
            padding: 1em;
            display: flex;
            justify-content: space-between;
            align-items: center;
            font-weight: 600;
        }

        header h1 a {
            color: #fff;
            text-decoration: none;
            font-weight: 600;
        }

        header .login-info {
            display: flex;
            align-items: center;
        }

        header .login-info .welcome-message {
            margin-right: 1em;
            font-size: 1.2em;
        }

        header .login-info .logout-button {
            background-color: #35dc64;
            color: #fff;
            border: none;
            cursor: pointer;
            padding: 0.75em 1.5em;
            border-radius: 25px;
            font-size: 1em;
            text-decoration: none;
            transition: background-color 0.3s, transform 0.2s;
        }

        header .login-info .logout-button:hover {
            background-color: #23c844;
            transform: scale(1.05);
        }

        header .login-info .button-link button, header .login-info .logout-button, header .login-info .nav-link {
            background-color: #333;
            color: #fff;
            border: none;
            cursor: pointer;
            padding: 0.75em 1.5em;
            border-radius: 25px;
            font-size: 1em;
            text-decoration: none;
            transition: background-color 0.3s, transform 0.2s;
        }

        header .login-info .button-link button:hover, header .login-info .logout-button:hover, header .login-info .nav-link:hover {
            background-color: #555;
            transform: scale(1.05);
        }

        header .login-info .nav-link {
            margin-left: 1em;
        }

        header .login-info .nav-link:last-child {
            margin-right: 1em;
        }
        .logout-container {
            display: flex;
            align-items: center;
        }

        .logout-container .nav-link {
            margin-right: 20px;
        }
    </style>
</head>
<body>
<header>
    <h1 class="logo"><a href="${pageContext.request.contextPath}/index">Trainly</a></h1>
    <div class="login-info">
        <c:choose>
            <c:when test="${empty authToken}">
                <section>
                    <a href="${pageContext.request.contextPath}/login.jsp" class="button-link">
                        <button type="button">Login as Admin</button>
                    </a>
                </section>
            </c:when>
            <c:otherwise>
                <div class="logout-container">
                    <a href="${pageContext.request.contextPath}/stations" class="nav-link">Stations</a>
                    <a href="${pageContext.request.contextPath}/train" class="nav-link">Trains</a>
                    <div class="welcome-message">Welcome, ${sessionScope.username}!</div>
                    <a href="${pageContext.request.contextPath}/logout" class="logout-button">Logout</a>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</header>
</body>
</html>
