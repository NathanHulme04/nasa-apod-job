package com.oscuro.nasa.apod.job.repositories;

import com.oscuro.nasa.apod.job.entities.NasaApodEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class NasaApodRepository {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void save(NasaApodEntity nasaApodEntity) {
        em.persist(nasaApodEntity);
    }
}
