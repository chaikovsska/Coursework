package com.example.train_schedule.controller;

import com.example.train_schedule.entities.Station;
import com.example.train_schedule.repository.StationRepository;
import com.example.train_schedule.entities.Train;
import com.example.train_schedule.repository.TrainRepository;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import java.util.List;

import java.io.IOException;


@WebServlet("/create")
public class CreateTrainServlet extends HttpServlet {
    @Inject
    private TrainRepository trainRepository;
    @Inject
    private StationRepository stationRepository;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Station> stations = stationRepository.findAll();
        request.setAttribute("stations", stations);
        request.getRequestDispatcher("/createTrain.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String stationDeparture = request.getParameter("stationDeparture");
        String stationArrival = request.getParameter("stationArrival");
        String dateDeparture = request.getParameter("dateDeparture");
        String dateArrival = request.getParameter("dateArrival");

        Train newTrain = new Train();
        newTrain.setName(name);
        newTrain.setStationDeparture(stationDeparture);
        newTrain.setStationArrival(stationArrival);
        newTrain.setDateDeparture(dateDeparture);
        newTrain.setDateArrival(dateArrival);

        trainRepository.create(newTrain);

        response.sendRedirect(request.getContextPath() + "/train");
    }
}