package com.example.train_schedule.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.train_schedule.entities.Train;
import com.example.train_schedule.repository.TrainRepository;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    @Inject
    private TrainRepository trainRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String from = request.getParameter("fromStation");
        String to = request.getParameter("toStation");
        String departureDate = request.getParameter("departureDate");

        List<Train> searchResults = new ArrayList<>();

        if (from != null && !from.isEmpty() && to != null && !to.isEmpty()) {
            searchResults = trainRepository.findByDepartureAndArrival(from, to);
        } else if (from != null && !from.isEmpty() && departureDate != null && !departureDate.isEmpty()) {
            searchResults = trainRepository.findByDepartureAndDate(from, departureDate);
        } else if (to != null && !to.isEmpty() && departureDate != null && !departureDate.isEmpty()) {
            searchResults = trainRepository.findByArrivalAndDate(to, departureDate);
        } else if (from != null && !from.isEmpty()) {
            searchResults = trainRepository.findByDepartureStation(from);
        } else if (to != null && !to.isEmpty()) {
            searchResults = trainRepository.findByArrivalStation(to);
        } else if (departureDate != null && !departureDate.isEmpty()) {
            searchResults = trainRepository.findByDate(departureDate);
        }

        request.setAttribute("trains", searchResults);
        request.getRequestDispatcher("results.jsp").forward(request, response);
    }
}