package com.example.train_schedule.controller;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.example.train_schedule.entities.Train;
import com.example.train_schedule.repository.TrainRepository;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/train")
public class TrainServlet extends HttpServlet {
    @Inject
    private TrainRepository trainRepository;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Train> trains = trainRepository.findAll();
        request.setAttribute("trains", trains);

        String idStr = request.getParameter("id");
        if (idStr != null) {
            long id = Long.parseLong(idStr);
            Optional<Train> selectedTrain = trainRepository.findById(id);
            selectedTrain.ifPresent(train -> request.setAttribute("selectedTrain", train));
        }

        request.getRequestDispatcher("trains.jsp").forward(request, response);
    }
}