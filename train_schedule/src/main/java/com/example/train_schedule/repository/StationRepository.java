package com.example.train_schedule.repository;
import com.example.train_schedule.entities.Station;
import com.example.train_schedule.entities.Train;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class StationRepository {
    @PersistenceContext
    private EntityManager em;

    public void create(Station station) {
        em.persist(station);
    }

    public List<Station> findAll() {
        return em.createQuery("SELECT c FROM Station c", Station.class).getResultList();
    }

    public Station findById(long id) {
        return em.find(Station.class, id);
    }

    public void updateStationName(String oldName, String newName) {
        Station station = em.createQuery("SELECT c FROM Station c WHERE c.name = :oldName", Station.class)
                .setParameter("oldName", oldName)
                .getSingleResult();
        if (station != null) {
            station.setName(newName);
            em.merge(station);

            List<Train> trainsWithStation = em.createQuery("SELECT c FROM Train c WHERE c.stationDeparture = :oldName OR c.stationArrival = :oldName", Train.class)
                    .setParameter("oldName", oldName)
                    .getResultList();
            for (Train train : trainsWithStation) {
                if (train.getStationDeparture().equals(oldName)) {
                    train.setStationDeparture(newName);
                }
                if (train.getStationArrival().equals(oldName)) {
                    train.setStationArrival(newName);
                }
                em.merge(train);
            }
        }
    }
    public void deleteStation(long id) {
        Station station = em.find(Station.class, id);
        if (station != null) {
            String stationName = station.getName();

            List<Train> trains = em.createQuery("SELECT c FROM Train c WHERE c.stationDeparture = :stationName OR c.stationArrival = :stationName", Train.class)
                    .setParameter("stationName", stationName)
                    .getResultList();
            for (Train train : trains) {
                em.remove(train);
            }

            em.remove(station);
        }
    }
}
