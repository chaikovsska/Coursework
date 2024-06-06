<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Train</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
    <style>
        main {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .form-container {
            background-color: #fff;
            padding: 1.5em;
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
            text-align: left;
        }

        .form-container input[type="text"],
        .form-container input[type="password"],
        .form-container select {
            width: calc(100% - 2em);
            padding: 0.75em;
            margin-bottom: 1em;
            border: 1px solid #ccc;
            border-radius: 20px;
            font-size: 1em;
        }

        .form-container input[type="datetime-local"] {
            width: calc(100% - 2em);
            padding: 0.75em;
            margin-bottom: 1em;
            border: 1px solid #ccc;
            border-radius: 20px;
            font-size: 1em;
        }

        .form-container button {
            width: 100%;
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
            transition: background-color 0.3s, transform 0.2s;
            padding: 0.75em;
            border-radius: 20px;
        }

        .form-container button:hover {
            background-color: #0056b3;
            transform: scale(1.05);
        }
    </style>
</head>
<body>
<main>
    <div class="form-container">
        <h2>Create New Train</h2>
        <form action="${pageContext.request.contextPath}/create" method="post">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required><br>

            <label for="stationDeparture">Departure Station:</label>
            <select id="stationDeparture" name="stationDeparture" required>
                <c:forEach var="station" items="${stations}">
                    <option value="${station.name}">${station.name}</option>
                </c:forEach>
            </select><br>

            <label for="stationArrival">Arrival Station:</label>
            <select id="stationArrival" name="stationArrival" required>
                <c:forEach var="station" items="${stations}">
                    <option value="${station.name}">${station.name}</option>
                </c:forEach>
            </select><br>

            <label for="dateDeparture">Departure Date:</label>
            <input type="datetime-local" id="dateDeparture" name="dateDeparture" required><br>

            <label for="dateArrival">Arrival Date:</label>
            <input type="datetime-local" id="dateArrival" name="dateArrival" required><br>

            <button type="submit">Create Train</button>
        </form>
    </div>
</main>
</body>
</html>