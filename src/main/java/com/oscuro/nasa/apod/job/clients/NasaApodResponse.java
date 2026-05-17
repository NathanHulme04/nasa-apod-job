package com.oscuro.nasa.apod.job.clients;

import java.time.LocalDate;

public class NasaApodResponse {
    public String title;
    public String url;
    public String explanation;
    public String hdurl;
    public String media_type;
    public String service_version;
    public LocalDate date;
    public String copyright;
}
