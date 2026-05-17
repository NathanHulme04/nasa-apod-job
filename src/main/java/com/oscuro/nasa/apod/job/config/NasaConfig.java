package com.oscuro.nasa.apod.job.config;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class NasaConfig {

    @ConfigProperty(name = "nasa.api.key")
    private String apiKey;

    @ConfigProperty(name = "NASA_BASE_URL")
    private String nasaBaseURL;

    public String getApiKey() {
        return apiKey;
    }

    public String getBaseURL() {
        return nasaBaseURL;
    }
}