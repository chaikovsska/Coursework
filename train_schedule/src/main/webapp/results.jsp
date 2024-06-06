<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Results</title>
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

        .main-container h2 {
            color: #ff6a00;
            margin-bottom: 1em;
        }

        .results-container {
            margin-bottom: 2em;
            overflow-y: auto;
            max-height: 500px;
            position: relative;
        }

        .result {
            background-color: #f9f9f9;
            border: 2px solid #ccc;
            border-radius: 20px;
            padding: 1em;
            margin-bottom: 1em;
            text-align: left;
        }

        .result:hover {
            background-color: #f0f0f0;
        }

        .result p {
            margin: 0;
            padding: 0;
        }
    </style>
</head>
<body>
<div class="page-container">
    <div class="main-container">
        <h2>Search Results</h2>
        <c:if test="${not empty trains}">
            <div class="results-container">
                <c:forEach var="train" items="${trains}">
                    <div class="result">
                        <p>
                            <strong>Train:</strong> ${train.name}<br>
                            <strong>From:</strong> ${train.stationDeparture} - <strong>To:</strong> ${train.stationArrival}<br>
                            <strong>Departure:</strong> ${train.dateDeparture} - <strong>Arrival:</strong> ${train.dateArrival}
                        </p>
                    </div>
                </c:forEach>
            </div>
        </c:if>
        <c:if test="${empty trains}">
            <p>No trains found.</p>
        </c:if>
    </div>
</div>
</body>
</html>