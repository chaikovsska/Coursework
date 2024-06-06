<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Train Schedule</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
    <style>
        .search-container {
            display: flex;
            justify-content: center;
            align-items: center;
            background-image: url('trains.jpg');
            background-size: cover;
            background-position: center;
            padding: 60px;
            border-radius: 30px;
            margin: 50px auto;
            width: 100%;
            max-width: 1000px;
            height: auto;
            background-color: #e5e5e5;
        }

        .search-form {
            display: flex;
            justify-content: center;
            align-items: center;
            width: 100%;
        }

        .search-form label,
        .search-form input,
        .search-form button {
            flex: 1;
            margin: 10px;
            padding: 15px;
            border: 1px solid #ccc;
            border-radius: 20px;
            font-size: 1.4em;
            font-weight: 500;
        }

        .search-form input[type="date"] {
            padding: 14px;
        }

        .search-form button {
            background-color: #ff6a00;
            color: #fff;
            border: none;
            cursor: pointer;
            transition: background-color 0.3s;
            margin-right: 0;
        }

        .search-form button:hover {
            background-color: #ff8600;
        }

        .schedule-section {
            text-align: center;
            margin-top: 40px;
        }

        .schedule-container {
            max-width: 900px;
            margin: 0 auto;
        }

        .schedule-heading {
            border: 1px solid white;
            border-radius: 30px;
            padding: 20px 150px;
            margin-bottom: 20px;
            background-color: #ff6a00;
            display: inline-block;
        }

        .schedule-heading h2,
        .schedule-heading .date {
            margin: 0;
            font-weight: 500;
        }

        .schedule-heading h2 {
            font-size: 1.4em;
            font-weight: 500;
        }

        .date-container {
            background-color: #ffffff;
            border-radius: 20px;
            margin-top: 10px;
            padding: 5px;
        }

        .schedule-table-container {
            width: 100%;
            border-radius: 20px;
            overflow: hidden;
            background-color: #ffffff;
            border: none;
            position: relative;
            margin-bottom: 50px;
        }

        .schedule-table {
            width: 100%;
            border-collapse: separate;
            border-spacing: 0;
        }

        .schedule-table th,
        .schedule-table td {
            border: 1px solid #ccc;
            padding: 10px;
        }

        .schedule-table th {
            background-color: #f0f0f0;
            border-bottom: 1px solid #ccc;
        }

        .schedule-table tbody tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .schedule-table tbody tr:hover {
            background-color: #f0f0f0;
        }

        .schedule-table-container:before,
        .schedule-table-container:after {
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
    </style>
</head>
<body>
<main>
    <div class="search-container">
        <form class="search-form" action="${pageContext.request.contextPath}/search" method="get">
            <div class="search-input">
                <input type="text" id="fromStation" name="fromStation" placeholder="From station">
                <input type="text" id="toStation" name="toStation" placeholder="To station">
                <input type="date" id="departureDate" name="departureDate">
                <button type="submit">Search</button>
            </div>
        </form>
    </div>
    <section class="schedule-section">
        <div class="schedule-container">
            <div class="schedule-heading">
                <h2>Today's Schedule</h2>
                <div class="date-container">
                    <p class="date">${currentDate}</p>
                </div>
            </div>
            <div class="schedule-table-container">
                <table class="schedule-table">
                    <thead>
                    <tr>
                        <th>Train</th>
                        <th>From</th>
                        <th>To</th>
                        <th>Departure</th>
                        <th>Arrival</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="train" items="${todayTrains}">
                        <tr>
                            <td>${train.name}</td>
                            <td>${train.stationDeparture}</td>
                            <td>${train.stationArrival}</td>
                            <td>${train.dateDeparture}</td>
                            <td>${train.dateArrival}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
</main>
</body>
</html>