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
import java.util.List;

@WebServlet("/createTrainAfterStation")
public class CreateTrainAfterStationServlet extends HttpServlet {
    @Inject
    private StationRepository stationRepository;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Station> stations = stationRepository.findAll();
        request.setAttribute("stations", stations);
        request.getRequestDispatcher("/createTrain.jsp").forward(request, response);
    }
}