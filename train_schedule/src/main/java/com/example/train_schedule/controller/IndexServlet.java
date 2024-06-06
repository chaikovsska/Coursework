package com.example.train_schedule.controller;

import java.io.IOException;
import java.util.List;

import com.example.train_schedule.entities.Train;
import com.example.train_schedule.repository.TrainRepository;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.logging.Logger;



@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(IndexServlet.class.getName());

    @Inject
    private TrainRepository trainRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("IndexServlet doGet method called");

        LocalDate currentDate = LocalDate.now();

        List<Train> todayTrains = trainRepository.findTodayTrains();

        request.setAttribute("todayTrains", todayTrains);
        request.setAttribute("currentDate", currentDate);

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
