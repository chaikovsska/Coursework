package com.example.train_schedule.controller;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.example.train_schedule.repository.AdminRepository;
import com.example.train_schedule.entities.User;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Inject
    private AdminRepository adminRepository;

    private static final Set<String> VALID_ADMIN_CODES = new HashSet<>(Arrays.asList("CODE1", "CODE2", "CODE3"));

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String adminCode = request.getParameter("adminCode");

        if (!VALID_ADMIN_CODES.contains(adminCode)) {
            request.setAttribute("errorMessage", "Invalid admin code. Please try again.");
            request.getRequestDispatcher("registration.jsp").forward(request, response);
            return;
        }

        User existingUser = adminRepository.findUserByUsername(username);
        if (existingUser != null) {
            request.setAttribute("errorMessage", "Username already exists. Please login.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        User user = new User();
        user.setUserName(username);
        user.setUserEmail(email);
        user.setUserPassword(password);

        try {
            adminRepository.register(user);
            request.getSession().setAttribute("successMessage", "Registration successful! You can now login.");
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } catch (PersistenceException ex) {
            request.setAttribute("errorMessage", "Registration failed. Please try again.");
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        }
    }
}