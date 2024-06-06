package com.example.train_schedule.controller;

import com.example.train_schedule.service.AuthenticationService;
import com.example.train_schedule.dto.UserCredentials;
import com.example.train_schedule.entities.User;
import com.example.train_schedule.repository.AdminRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.lang.invoke.MethodHandles;
import java.util.logging.Logger;

@Path("auth")
@RequestScoped
public class Authentication {
    private final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
    @Inject
    private AdminRepository adminRepository;
    @Inject
    private AuthenticationService authenticationService;

    @POST
    @Path("register")
    @Consumes("application/json")
    @Produces("application/json")
    public Response register(User user) {
        logger.info("registered User with name " + user.getUserName());
        try{
            return Response.status(Response.Status.CREATED).entity(adminRepository.register(user)).build();
        }catch (PersistenceException ex){
            logger.info("Error creating user " + user.getUserName());
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }

    @POST
    @Path("login")
    @Consumes("application/json")
    @Produces("application/json")
    public Response login(UserCredentials credentials) {
        logger.info("LogIn User with name " + credentials.getUsername());

        User user = adminRepository.findUserByUsername(credentials.getUsername());
        if (user == null) {
            logger.info("User not found: " + credentials.getUsername());
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }

        if (!user.getUserPassword().equals(credentials.getPassword())) {
            logger.info("Incorrect password for user: " + credentials.getUsername());
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }

        String authToken = authenticationService.generateAuthToken(user);
        return Response.ok().entity(authToken).build();
    }
}
