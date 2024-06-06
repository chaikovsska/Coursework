package com.example.train_schedule.controller;
import com.example.train_schedule.repository.TrainRepository;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/deleteTrain")
public class DeleteTrainServlet extends HttpServlet {
    @Inject
    private TrainRepository trainRepository;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String trainId = request.getParameter("id");

        if (trainId != null && !trainId.isEmpty()) {
            try {
                long id = Long.parseLong(trainId);
                trainRepository.delete(id);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect(request.getContextPath() + "/train");
    }
}