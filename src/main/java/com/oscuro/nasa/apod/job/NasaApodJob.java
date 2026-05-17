package com.oscuro.nasa.apod.job;
import com.oscuro.nasa.apod.job.services.NasaApodSyncService;
import io.quarkus.logging.Log;
import io.quarkus.runtime.Quarkus;
import jakarta.inject.Inject;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class NasaApodJob implements QuarkusApplication {

    @Inject
    NasaApodSyncService nasaApodSyncService;

    @Override
    public int run(String... args) {

        try {
            Log.info("Starting NASA APOD synchronization job...");
            nasaApodSyncService.runSync();
            Log.info("NASA APOD synchronization completed successfully");
            return 0; // Exit with 0 to signal success to Cloud Run Jobs
        } catch (Exception e) {
            Log.error("NASA APOD synchronization failed", e);
            return 1;
        }
    }
}
