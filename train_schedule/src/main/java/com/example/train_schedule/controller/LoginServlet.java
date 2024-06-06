package com.example.train_schedule.controller;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.example.train_schedule.repository.AdminRepository;
import com.example.train_schedule.service.AuthenticationService;
import com.example.train_schedule.entities.User;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Inject
    private AdminRepository adminRepository;
    @Inject
    private AuthenticationService authenticationService;

    private static final Set<String> VALID_ADMIN_CODES = new HashSet<>(Arrays.asList("CODE1", "CODE2", "CODE3"));

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String adminCode = request.getParameter("adminCode");

        if (!VALID_ADMIN_CODES.contains(adminCode)) {
            request.setAttribute("errorMessage", "Invalid admin code. Please try again.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        User user = adminRepository.findUserByUsername(username);
        if (user == null || !user.getUserPassword().equals(password)) {
            request.setAttribute("errorMessage", "Login failed. Please try again.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        String authToken = authenticationService.generateAuthToken(user);
        request.getSession().setAttribute("authToken", authToken);
        request.setAttribute("successMessage", "Login successful!");
        request.getSession().setAttribute("authToken", authToken);
        request.getSession().setAttribute("username", user.getUserName());
        response.sendRedirect(request.getContextPath() + "/train");
    }
}