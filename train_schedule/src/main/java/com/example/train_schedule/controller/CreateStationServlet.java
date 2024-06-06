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

@WebServlet("/createStation")
public class CreateStationServlet extends HttpServlet {
    @Inject
    private StationRepository stationRepository;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        Station station = new Station();
        station.setName(name);
        stationRepository.create(station);

        List<Station> stations = stationRepository.findAll();
        request.setAttribute("stations", stations);

        request.setAttribute("stationCreated", true);
        request.setAttribute("stationName", name);
        request.getRequestDispatcher("stations.jsp").forward(request, response);
    }
}