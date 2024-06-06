package com.example.train_schedule.controller;

import com.example.train_schedule.repository.StationRepository;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/deleteStation")
public class DeleteStationServlet extends HttpServlet {
    @Inject
    private StationRepository stationRepository;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stationId = request.getParameter("id");
        String redirectToTrain = request.getParameter("redirectToTrain");

        if (stationId != null && !stationId.isEmpty()) {
            try {
                long id = Long.parseLong(stationId);
                stationRepository.deleteStation(id);
                System.out.println("Station with ID " + id + " deleted.");
            } catch (NumberFormatException e) {
                e.printStackTrace();
                System.err.println("Invalid station ID: " + stationId);
            }
        }

        System.out.println("Redirect to train: " + redirectToTrain);

        if ("true".equals(redirectToTrain)) {
            response.sendRedirect(request.getContextPath() + "/train");
        } else {
            response.sendRedirect(request.getContextPath() + "/stations");
        }
    }
}