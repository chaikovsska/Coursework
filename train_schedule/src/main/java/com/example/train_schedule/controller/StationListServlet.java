package com.example.train_schedule.controller;

import com.example.train_schedule.entities.Station;
import com.example.train_schedule.repository.StationRepository;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import java.util.List;

import java.io.IOException;

@WebServlet("/stations")
public class StationListServlet extends HttpServlet {
    @Inject
    private StationRepository stationRepository;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Station> stations = stationRepository.findAll();
        request.setAttribute("stations", stations);
        request.getRequestDispatcher("/stations.jsp").forward(request, response);
    }
}