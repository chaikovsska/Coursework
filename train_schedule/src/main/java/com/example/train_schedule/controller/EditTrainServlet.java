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

@WebServlet("/edit")
public class EditTrainServlet extends HttpServlet {
    @Inject
    private TrainRepository trainRepository;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        Optional<Train> trainOpt = trainRepository.findById(id);
        if (trainOpt.isPresent()) {
            request.setAttribute("selectedTrain", trainOpt.get());
            request.getRequestDispatcher("/editTrain.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/train");
        }
    }
}