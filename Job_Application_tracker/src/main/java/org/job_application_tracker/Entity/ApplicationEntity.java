package org.job_application_tracker.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class ApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String company;
    private String applicationDate;
    private String time;
    private String location;
    private String postedDate;
    private String url;

    public ApplicationEntity() {
    }

    public ApplicationEntity(String company, String applicationDate, String time, String location, String postedDate, String url) {
        this.company = company;
        this.applicationDate = applicationDate;
        this.time = time;
        this.location = location;
        this.postedDate = postedDate;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ApplicationEntity{" +
                "company='" + company + '\'' +
                ", applicationDate='" + applicationDate + '\'' +
                ", time='" + time + '\'' +
                ", location='" + location + '\'' +
                ", postedDate='" + postedDate + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
