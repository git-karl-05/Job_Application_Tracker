package org.job_application_tracker.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationResponse {

    private Long id;
    private String company;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate applicationDate;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;
    private String location;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate postedDate;

    private String url;

    public ApplicationResponse() {
    }

    public ApplicationResponse(Long id, String company, LocalDate applicationDate, LocalTime time, String location, LocalDate postedDate, String url) {
        this.id = id;
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

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(LocalDate postedDate) {
        this.postedDate = postedDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
