package com.example.train_schedule.controller;

import com.example.train_schedule.entities.Train;
import com.example.train_schedule.repository.TrainRepository;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/update")
public class UpdateTrainServlet extends HttpServlet {
    @Inject
    private TrainRepository trainRepository;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String stationDeparture = request.getParameter("stationDeparture");
        String stationArrival = request.getParameter("stationArrival");
        String dateDeparture = request.getParameter("dateDeparture");
        String dateArrival = request.getParameter("dateArrival");

        Optional<Train> trainOpt = trainRepository.findById(id);
        if (trainOpt.isPresent()) {
            Train train = trainOpt.get();
            train.setName(name);
            train.setStationDeparture(stationDeparture);
            train.setStationArrival(stationArrival);
            train.setDateDeparture(dateDeparture);
            train.setDateArrival(dateArrival);
            trainRepository.update(train);
        }
        response.sendRedirect(request.getContextPath() + "/train");
    }
}