package com.oscuro.nasa.apod.job.utils;

import com.oscuro.nasa.apod.job.clients.NasaApodResponse;
import com.oscuro.nasa.apod.job.entities.NasaApodEntity;

public class NasaApodMapper {

    public static NasaApodEntity convertToNasaApodEntity(NasaApodResponse nasaApodResponse) {
        NasaApodEntity nasaApodEntity = new NasaApodEntity();
        nasaApodEntity.setTitle( nasaApodResponse.title );
        nasaApodEntity.setUrl( nasaApodResponse.url );
        nasaApodEntity.setExplanation( nasaApodResponse.explanation );
        nasaApodEntity.setHdurl( nasaApodResponse.hdurl );
        nasaApodEntity.setMediaType( nasaApodResponse.media_type );
        nasaApodEntity.setServiceVersion( nasaApodResponse.service_version );
        nasaApodEntity.setDate( nasaApodResponse.date );
        nasaApodEntity.setCopyright( nasaApodResponse.copyright );

        return nasaApodEntity;
    }
}
