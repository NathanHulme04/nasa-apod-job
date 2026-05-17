package com.oscuro.nasa.apod.job.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "nasa_apod")
public class NasaApodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column(name = "title")
    public String title;

    @Column(name = "url")
    public String url;

    @Lob
    @Column(name = "explanation", columnDefinition = "TEXT")
    public String explanation;

    @Column(name = "hdurl")
    public String hdurl;

    @Column(name = "media_type")
    public String mediaType;

    @Column(name = "service_version")
    public String serviceVersion;

    @Column(name = "date")
    public LocalDate date;

    @Column(name = "copyright", columnDefinition = "TEXT")
    public String copyright;

    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getUrl(){
        return this.url;
    }
    public void setUrl(String url){
        this.url = url;
    }

    public String getExplanation(){
        return this.explanation;
    }
    public void setExplanation(String explanation){
        this.explanation = explanation;
    }

    public String getHdurl(){
        return this.hdurl;
    }
    public void setHdurl(String hdurl){
        this.hdurl = hdurl;
    }

    public String getMediaType(){
        return this.mediaType;
    }
    public void setMediaType(String mediaType){
        this.mediaType = mediaType;
    }

    public String getServiceVersion(){
        return this.serviceVersion;
    }
    public void setServiceVersion(String serviceVersion){
        this.serviceVersion = serviceVersion;
    }

    public LocalDate getDate(){
        return this.date;
    }
    public void setDate(LocalDate date){
        this.date = date;
    }

    public String getCopyright(){
        return this.copyright;
    }
    public void setCopyright(String copyRight){
        this.copyright = copyRight;
    }

    @Override
    public String toString() {
        return "NasaApodEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", explanation='" + (explanation != null ? explanation.substring(0, Math.min(100, explanation.length())) + "..." : null) + '\'' +
                ", date='" + date.toString() + '\'' +
                '}';
    }
}

