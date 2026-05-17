package com.oscuro.nasa.apod.job.services;

import com.oscuro.nasa.apod.job.clients.NasaApodClient;
import com.oscuro.nasa.apod.job.clients.NasaApodResponse;
import com.oscuro.nasa.apod.job.config.NasaConfig;
import com.oscuro.nasa.apod.job.repositories.NasaApodRepository;
import com.oscuro.nasa.apod.job.repositories.NasaApodSyncRepository;
import com.oscuro.nasa.apod.job.utils.NasaApodMapper;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 * Service responsible for synchronizing NASA Astronomy Picture of the Day (APOD) data.
 * This service fetches APOD data from the NASA API in chunks and persists it to the database,
 * ensuring the sync date is updated after each successful batch.
 */
@ApplicationScoped
public class NasaApodSyncService {
    
    
    @Inject
    @RestClient
    NasaApodClient nasaApodClient;

    @Inject
    NasaApodRepository nasaApodRepository;

    @Inject
    NasaConfig nasaConfig;

    @Inject
    NasaApodSyncRepository nasaApodSyncRepository;

    /**
     * Runs the synchronization process for NASA APOD data.
     * Retrieves the last synced date from the database, then fetches and persists APOD data
     * in chunks from the last synced date to today, updating the sync date after each chunk.
     * If the data is already up to date, no sync is performed.
     */
    @ActivateRequestContext
    public void runSync() {
        LocalDate lastSynced = nasaApodSyncRepository.getLastSyncedDate();
        LocalDate start = lastSynced.plusDays(1);
        LocalDate today = LocalDate.now();

        // If start is tomorrow, we are already finished for today
        if (start.isAfter(today)) {
            Log.info("Database is already caught up through today.");
            return;
        }

        LocalDate currentStart  = start;
        while( currentStart.isBefore(today.plusDays(1)) ) {
            LocalDate currentEnd = currentStart.plusDays(13);

            if (currentEnd.isAfter(today)) {
                currentEnd = today;
            }

            try {
                Log.infof("Syncing APOD data from %s to %s", currentStart, currentEnd);
                List<NasaApodResponse> apods = nasaApodClient.getApodByDateAsync(
                        nasaConfig.getApiKey(), currentStart.toString(), currentEnd.toString()
                );

                persistApods(apods, currentEnd);
            }
            catch (Exception e) {
                Log.errorf("Failed to sync APOD data from %s to %s: %s", currentStart, currentEnd, e.getMessage());
                // Optionally implement retry logic here or break the loop to prevent further attempts
                break;
            }
            currentStart = currentEnd.plusDays(1);
        }
    }

    /**
     * Persists a list of NASA APOD responses to the database and updates the last synced date.
     * This method is transactional to ensure data consistency.
     *
     * @param apods the list of APOD responses to persist
     * @param chunckEnd the end date of the current chunk, used to update the sync date
     */
    @Transactional
    public void persistApods(List<NasaApodResponse> apods, LocalDate chunckEnd) {
        if( apods== null || apods.isEmpty() ) {
            Log.warnf("No APOD data to persist for chunk ending on %s", chunckEnd);
            return;
        }
        apods.forEach(apod -> {
            nasaApodRepository.save(NasaApodMapper.convertToNasaApodEntity(apod));
        });
        nasaApodSyncRepository.updateLastSyncedDate(chunckEnd);
    }

}
