package com.oscuro.nasa.apod.job.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "nasa_apod_sync")
public class NasaApodSyncEntity {
    @Id
    public Long id;

    @Column(name = "last_synced_date")
    public LocalDate lastSyncedDate;

    public Long getId() {
        return id;
    }

    public LocalDate getLastSyncedDate() {
        return lastSyncedDate;
    }

    public void setLastSyncedDate(LocalDate lastSyncedDate) {
        this.lastSyncedDate = lastSyncedDate;
    }
}
