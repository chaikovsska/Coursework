<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Train</title>
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
        <h2>Edit Train</h2>
        <form action="${pageContext.request.contextPath}/update" method="post">
            <input type="hidden" name="id" value="${selectedTrain.id}">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="${selectedTrain.name}" required><br><br>

            <label for="stationDeparture">Departure Station:</label>
            <input type="text" id="stationDeparture" name="stationDeparture" value="${selectedTrain.stationDeparture}" required><br><br>

            <label for="stationArrival">Arrival Station:</label>
            <input type="text" id="stationArrival" name="stationArrival" value="${selectedTrain.stationArrival}" required><br><br>

            <label for="dateDeparture">Departure Date:</label>
            <input type="datetime-local" id="dateDeparture" name="dateDeparture" value="${selectedTrain.dateDeparture}" required><br><br>

            <label for="dateArrival">Arrival Date:</label>
            <input type="datetime-local" id="dateArrival" name="dateArrival" value="${selectedTrain.dateArrival}" required><br><br>

            <button type="submit">Save Changes</button>
        </form>
    </div>
</main>
</body>
</html>