package com.oscuro.nasa.apod.job.clients;

import io.smallrye.common.constraint.NotNull;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import com.oscuro.nasa.apod.job.config.NasaConstants;

import java.util.List;

@Path( NasaConstants.Nasa_Apod_Endpoint )
@RegisterRestClient(configKey = "nasa-api-base-url")
public interface NasaApodClient {

    //Client to make the request to Nasa
    @GET
    NasaApodResponse getApod(@QueryParam("api_key") String apiKey);

    @GET
    List<NasaApodResponse> getApodByDateAsync(
            @QueryParam("api_key") @NotNull String apiKey, @QueryParam("start_date") String startDate,
            @QueryParam("end_date") String endDate);
}
