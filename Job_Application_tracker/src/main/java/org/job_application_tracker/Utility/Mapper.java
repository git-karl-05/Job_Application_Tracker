package org.job_application_tracker.Utility;

import org.job_application_tracker.DTO.ApplicationRequest;
import org.job_application_tracker.DTO.ApplicationResponse;
import org.job_application_tracker.Entity.ApplicationEntity;
import org.springframework.stereotype.Component;


public class Mapper {



    public static ApplicationEntity toEntity(ApplicationRequest dto) {
        return new ApplicationEntity(
                dto.getCompany(),
                dto.getApplicationDate(),
                dto.getTime(),
                dto.getLocation(),
                dto.getPostedDate(),
                dto.getUrl(),
                dto.getDocumentUrl()
        );
    }

    public static ApplicationResponse toResponse(ApplicationEntity entity) {
        return new ApplicationResponse(
                entity.getId(),
                entity.getCompany(),
                entity.getApplicationDate(),
                entity.getTime(),
                entity.getLocation(),
                entity.getPostedDate(),
                entity.getUrl(),
                entity.getDocumentUrl()
        );
    }
}
