package com.example.train_schedule.repository;

import com.example.train_schedule.entities.Train;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Stateless
public class TrainRepository {
    private static final Logger logger = Logger.getLogger(TrainRepository.class.getName());

    @PersistenceContext
    private EntityManager em;

    public Train create(Train train) {
        logger.info("Creating train " + train.getName());
        em.persist(train);
        return train;
    }
    public List<Train> findTodayTrains() {
        String todayDate = LocalDate.now().toString();
        logger.info("Finding today's trains with date: " + todayDate);
        List<Train> result = em.createQuery("SELECT c FROM Train c WHERE c.dateDeparture LIKE :todayDate", Train.class)
                .setParameter("todayDate", todayDate + "%")
                .getResultList();
        logger.info("Found " + result.size() + " trains for today's date: " + todayDate);
        return result;
    }


    public List<Train> findAll() {
        logger.info("Getting all trains ");
        return em.createQuery("SELECT c FROM Train c", Train.class).getResultList();
    }


    public List<Train> findByDepartureStation(String departureStation) {
        logger.info("Finding trains by departure station: " + departureStation);
        return em.createQuery("SELECT c FROM Train c WHERE c.stationDeparture = :departureStation", Train.class)
                .setParameter("departureStation", departureStation)
                .getResultList();
    }

    public List<Train> findByArrivalStation(String arrivalStation) {
        logger.info("Finding trains by arrival station: " + arrivalStation);
        return em.createQuery("SELECT c FROM Train c WHERE c.stationArrival = :arrivalStation", Train.class)
                .setParameter("arrivalStation", arrivalStation)
                .getResultList();
    }

    public List<Train> findByDepartureAndArrival(String departureStation, String arrivalStation) {
        logger.info("Finding trains by departure station: " + departureStation + " and arrival station: " + arrivalStation);
        return em.createQuery("SELECT c FROM Train c WHERE c.stationDeparture = :departureStation AND c.stationArrival = :arrivalStation", Train.class)
                .setParameter("departureStation", departureStation)
                .setParameter("arrivalStation", arrivalStation)
                .getResultList();
    }

    public List<Train> findByDate(String date) {
        logger.info("Finding trains by date: " + date);
        return em.createQuery("SELECT c FROM Train c WHERE c.dateDeparture LIKE :date", Train.class)
                .setParameter("date", date + "%")
                .getResultList();
    }

    public List<Train> findByDepartureAndDate(String departureStation, String date) {
        logger.info("Finding trains by departure station: " + departureStation + " and date: " + date);
        return em.createQuery("SELECT c FROM Train c WHERE c.stationDeparture = :departureStation AND c.dateDeparture LIKE :date", Train.class)
                .setParameter("departureStation", departureStation)
                .setParameter("date", date + "%")
                .getResultList();
    }

    public List<Train> findByArrivalAndDate(String arrivalStation, String date) {
        logger.info("Finding trains by arrival station: " + arrivalStation + " and date: " + date);
        return em.createQuery("SELECT c FROM Train c WHERE c.stationArrival = :arrivalStation AND c.dateDeparture LIKE :date", Train.class)
                .setParameter("arrivalStation", arrivalStation)
                .setParameter("date", date + "%")
                .getResultList();
    }

    public Optional<Train> findById(long id) {
        logger.info("Getting train by id " + id);
        return Optional.ofNullable(em.find(Train.class, id));
    }

    public void delete(long id) {
        logger.info("Deleting train by id " + id);
        var train = findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid train Id:" + id));
        em.remove(train);
    }

    public Train update(Train train) {
        logger.info("Updating train " + train.getName());
        return em.merge(train);
    }
}