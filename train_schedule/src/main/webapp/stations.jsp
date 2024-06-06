<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Stations</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .page-container {
            display: flex;
            flex-direction: column;
            justify-content: flex-start;
            align-items: center;
            min-height: 100vh;
            background-color: #f8f9fa;
            padding-top: 2em;
            padding-bottom: 2em;
            position: relative;
        }

        .main-container {
            background-color: #ffffff;
            border-radius: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 2em;
            max-width: 600px;
            width: 100%;
            text-align: center;
            position: relative;
        }

        h1 {
            color: #ff6a00;
            margin-bottom: 1em;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        form {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"] {
            width: calc(100% - 10px);
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #0056b3;
        }

        p {
            margin-bottom: 10px;
        }

        .form-container {
            margin-top: 2em;
        }

        .form-container button, .link-container button, .link-container .button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 0.75em 1.5em;
            cursor: pointer;
            border-radius: 20px;
            font-size: 1em;
            transition: background-color 0.3s, transform 0.2s;
        }

        .form-container button:hover, .link-container button:hover, .link-container .button:hover {
            background-color: #0056b3;
            transform: scale(1.05);
        }

        .link-container {
            margin-top: 1em;
            text-align: center;
        }

        .button {
            background-color: #007bff;
            color: #fff;
            border: none;
            cursor: pointer;
            border-radius: 10px;
            font-size: 1em;
            transition: background-color 0.3s, transform 0.2s;
        }

        .button:hover {
            background-color: #0056b3;
            transform: scale(1.05);
        }

        .orange-button {
            background-color: #ff6a00 !important;
            color: #fff !important;
            text-decoration: none !important;
        }

        .orange-button:hover {
            background-color: #ff8600 !important;
        }

        .table-container {
            margin-bottom: 2em;
            overflow-y: auto;
            max-height: 500px;
            position: relative;
        }

        .table-container table {
            width: 100%;
            border-collapse: collapse;
            border-radius: 20px;
            overflow: hidden;
        }

        .table-container th, .table-container td {
            padding: 10px;
            border: 2px solid #ccc;
        }

        .table-container th {
            background-color: #f2f2f2;
            border-bottom: 1px solid #ccc;
            position: sticky;
            top: 0;
            z-index: 10;
        }

        .table-container tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .table-container tr:hover {
            background-color: #f0f0f0;
        }
    </style>
</head>
<body>
<main class="page-container">
    <div class="main-container">
        <h1>All Stations</h1>

        <c:if test="${not empty stations}">
            <div class="table-container">
                <table>
                    <thead>
                    <tr>
                        <th>Station Name</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="station" items="${stations}">
                        <tr>
                            <td>${station.name}</td>
                            <td>
                                <form action="${pageContext.request.contextPath}/editStation" method="get">
                                    <input type="hidden" name="id" value="${station.id}">
                                    <button type="submit" class="button">Edit</button>
                                </form>
                            </td>
                            <td>
                                <form action="${pageContext.request.contextPath}/deleteStation" method="post">
                                    <input type="hidden" name="id" value="${station.id}">
                                    <input type="hidden" name="redirectToTrain" value="false">
                                    <button type="submit" class="button">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>

        <c:if test="${empty stations}">
            <p>No stations found.</p>
        </c:if>

        <h2>Create New Station</h2>
        <form action="${pageContext.request.contextPath}/createStation" method="post" class="form-container">
            <label for="name">Station Name:</label>
            <input type="text" id="name" name="name" required><br><br>
            <button type="submit" class="button">Create New Station</button>
        </form>

        <c:if test="${stationCreated}">
            <p>Station "${stationName}" created successfully. Do you want to create a new train?</p>
            <form action="${pageContext.request.contextPath}/createTrainAfterStation" method="post">
                <button type="submit">Yes</button>
            </form>
            <form action="${pageContext.request.contextPath}/stations" method="get">
                <button type="submit">No</button>
            </form>
        </c:if>

        <div class="link-container">
            <a href="${pageContext.request.contextPath}/train" class="button orange-button">Trains</a>
        </div>
    </div>
</main>
</body>
</html>