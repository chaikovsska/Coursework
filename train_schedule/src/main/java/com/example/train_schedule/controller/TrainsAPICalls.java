package com.example.train_schedule.controller;

import com.example.train_schedule.service.AuthenticationService;
import com.example.train_schedule.entities.Train;
import com.example.train_schedule.repository.TrainRepository;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.logging.Logger;

@Path("trains")
public class TrainsAPICalls {
    private final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    @Inject
    private TrainRepository trainRepository;
    @Inject
    private AuthenticationService authenticationService;

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Train findTrain(@PathParam("id") long id) {
        logger.info("Getting train by id " + id);
        return trainRepository.findById(id)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }

    @GET
    @Produces("application/json")
    public List<Train> findAll() {
        logger.info("Getting all trains");
        return trainRepository.findAll();
    }

    @GET
    @Path("/index")
    @Produces("application/json")
    public List<Train> getTodayTrains() {
        return trainRepository.findTodayTrains();
    }

    @Path("/search")
    @Produces("application/json")
    @GET
    public List<Train> searchTrains(@QueryParam("from") String from, @QueryParam("to") String to, @QueryParam("date") String date) {
        if (from != null && to != null) {
            return trainRepository.findByDepartureAndArrival(from, to);
        } else if (from != null && date != null) {
            return trainRepository.findByDepartureAndDate(from, date);
        } else if (to != null && date != null) {
            return trainRepository.findByArrivalAndDate(to, date);
        } else if (from != null) {
            return trainRepository.findByDepartureStation(from);
        } else if (to != null) {
            return trainRepository.findByArrivalStation(to);
        } else if (date != null) {
            return trainRepository.findByDate(date);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(Train train, @HeaderParam(HttpHeaders.AUTHORIZATION) String auth) {
        if (!authenticationService.verifyAuthToken(auth)) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
        logger.info("Creating train " + train.getName());
        try {
            return Response.status(Response.Status.CREATED).entity(trainRepository.create(train)).build();
        } catch (PersistenceException ex){
            logger.info("Error creating train " + train.getName());
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") long id, @HeaderParam(HttpHeaders.AUTHORIZATION) String auth) {
        if (!authenticationService.verifyAuthToken(auth)) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
        logger.info("Deleting train by id " + id);
        try{
            trainRepository.delete(id);
        }catch (IllegalArgumentException e){
            logger.info("Error deleting train by id " + id);
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Train update(Train train, @HeaderParam(HttpHeaders.AUTHORIZATION) String auth) {
        if (!authenticationService.verifyAuthToken(auth)) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
        logger.info("Updating train " + train.getName());
        try{
            return trainRepository.update(train);
        }catch (PersistenceException ex){
            logger.info("Error updating train " + train.getName());
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }
}
