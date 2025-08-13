package org.job_application_tracker.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ApplicationDTO {

    @NotBlank
    private String company;
    @NotNull
    private LocalDate applicationDate;
    @NotNull
    private LocalTime time;
    @NotBlank
    private String location;
    @NotBlank
    private LocalDate postedDate;
    private String url;

    public ApplicationDTO() {
    }

    public ApplicationDTO(String company, String applicationDate, String time, String location, String postedDate, String url) {
        this.company = company;
        this.applicationDate mvFapplicationDate;
        this.time = time;
        this.location = location;
        this.postedDate = postedDate;
        this.url = url;
    }

    public @NotBlank String getCompany() {
        return company;
    }

    public void setCompany(@NotBlank String company) {
        this.company = company;
    }

    public @NotNull String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(@NotNull String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public @NotNull String getTime() {
        return time;
    }

    public void setTime(@NotNull String time) {
        this.time = time;
    }

    public @NotBlank String getLocation() {
        return location;
    }

    public void setLocation(@NotBlank String location) {
        this.location = location;
    }

    public @NotBlank String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(@NotBlank String postedDate) {
        this.postedDate = postedDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
