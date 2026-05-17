package com.oscuro.nasa.apod.job.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import com.oscuro.nasa.apod.job.entities.NasaApodSyncEntity;
import jakarta.transaction.Transactional;

import java.time.LocalDate;

@ApplicationScoped
public class NasaApodSyncRepository {

    @PersistenceContext
    EntityManager em;

    public LocalDate getLastSyncedDate() {
        return em.createQuery("SELECT s.lastSyncedDate FROM NasaApodSyncEntity s WHERE s.id = 1", LocalDate.class)
                .getSingleResult();
    }

    @Transactional
    public void updateLastSyncedDate(LocalDate date) {
        NasaApodSyncEntity sync = em.find(NasaApodSyncEntity.class, 1L);
        sync.setLastSyncedDate(date);
    }

}
