<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Trains</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
    <style>
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
            max-width: 900px;
            width: 100%;
            text-align: center;
            position: relative;
        }

        .main-container h1 {
            color: #ff6a00;
            margin-bottom: 1em;
        }

        .table-container {
            margin-bottom: 2em;
            overflow-x: auto;
            max-width: 100%;
        }

        .table-container:before,
        .table-container:after {
            content: '';
            position: absolute;
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
            border: 2px solid #ccc;
            border-radius: 20px;
            pointer-events: none;
            box-sizing: border-box;
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
            background-color: #f0f0f0;
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

        .form-container {
            margin-top: 2em;
        }

        .form-container button, .link-container button, .link-container .button {
            background-color: #ff6a00;
            color: #fff;
            border: none;
            padding: 0.75em 1.5em;
            cursor: pointer;
            border-radius: 20px;
            font-size: 1em;
            transition: background-color 0.3s, transform 0.2s;
        }

        .form-container button:hover, .link-container button:hover, .link-container .button:hover {
            background-color: #ff8600;
            transform: scale(1.05);
        }
        .orange-button {
            background-color: #ff6a00;
            color: #fff;
            text-decoration: none;
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
            padding: 7px;
            margin: 2px;
        }

        .button:hover {
            background-color: #0056b3;
            transform: scale(1.05);
        }
    </style>
</head>
<body>
<div class="page-container">
    <div class="main-container">
        <h1>All Trains</h1>
        <c:if test="${not empty trains}">
            <div class="table-container">
                <table>
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Departure Station</th>
                        <th>Arrival Station</th>
                        <th>Departure Date</th>
                        <th>Arrival Date</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="train" items="${trains}" varStatus="status">
                        <tr id="row${status.index}">
                            <td>${train.name}</td>
                            <td>${train.stationDeparture}</td>
                            <td>${train.stationArrival}</td>
                            <td>${train.dateDeparture}</td>
                            <td>${train.dateArrival}</td>
                            <td>
                                <form action="${pageContext.request.contextPath}/edit" method="get" style="display:inline;">
                                    <input type="hidden" name="id" value="${train.id}">
                                    <button type="submit" class="button">Edit</button>
                                </form>
                                <form action="${pageContext.request.contextPath}/deleteTrain" method="post" style="display:inline;">
                                    <input type="hidden" name="id" value="${train.id}">
                                    <button type="submit" class="button">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
        <c:if test="${empty trains}">
            <p>No trains found.</p>
        </c:if>

        <c:if test="${not empty selectedTrain}">
            <div class="form-container">
                <h2>Edit Train</h2>
                <form action="${pageContext.request.contextPath}/update" method="post">
                    <input type="hidden" name="id" value="${selectedTrain.id}">
                    <label for="editName">Name:</label>
                    <input type="text" id="editName" name="name" value="${selectedTrain.name}" required><br><br>

                    <label for="editStationDeparture">Departure Station:</label>
                    <input type="text" id="editStationDeparture" name="stationDeparture" value="${selectedTrain.stationDeparture}" required><br><br>

                    <label for="editStationArrival">Arrival Station:</label>
                    <input type="text" id="editStationArrival" name="stationArrival" value="${selectedTrain.stationArrival}" required><br><br>

                    <label for="editDateDeparture">Departure Date:</label>
                    <input type="datetime-local" id="editDateDeparture" name="dateDeparture" value="${selectedTrain.dateDeparture}" required><br><br>

                    <label for="editDateArrival">Arrival Date:</label>
                    <input type="datetime-local" id="editDateArrival" name="dateArrival" value="${selectedTrain.dateArrival}" required><br><br>

                    <button type="submit" class="button">Save Changes</button>
                </form>
            </div>
        </c:if>

        <form action="${pageContext.request.contextPath}/create" method="get">
            <button type="submit" class="button" style="padding: 0.75em 2em; border-radius: 20px;">Create New Train</button>
        </form>
        <div class="link-container">
            <a href="${pageContext.request.contextPath}/stations" class="button orange-button">Stations</a>
        </div>
    </div>
</div>
</body>
</html>