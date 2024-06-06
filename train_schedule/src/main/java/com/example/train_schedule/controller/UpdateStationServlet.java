package com.example.train_schedule.controller;
import com.example.train_schedule.entities.Station;
import com.example.train_schedule.repository.StationRepository;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;

@WebServlet("/updateStation")
public class UpdateStationServlet extends HttpServlet {
    @Inject
    private StationRepository stationRepository;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");

        Station station = stationRepository.findById(id);

        if (station != null) {
            String oldName = station.getName();
            station.setName(name);

            stationRepository.updateStationName(oldName, name);

            response.sendRedirect(request.getContextPath() + "/train");
        } else {
            response.sendRedirect(request.getContextPath() + "/stations");
        }
    }
}