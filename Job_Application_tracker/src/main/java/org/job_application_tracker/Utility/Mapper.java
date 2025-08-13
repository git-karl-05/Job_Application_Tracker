package org.job_application_tracker.Utility;

import org.job_application_tracker.DTO.ApplicationDTO;
import org.job_application_tracker.Entity.ApplicationEntity;

public class Mapper {

    //    private Long id;
    //    private String company;
    //    private LocalDateTime applicationDate;
    //    private LocalDateTime time;
    //    private String location;
    //    private String postedDate;
    //    private String url;

    public static ApplicationDTO toDTO(ApplicationEntity entity) {
        return new ApplicationDTO(
                entity.getCompany(),
                entity.getApplicationDate(),
                entity.getTime(),
                entity.getLocation(),
                entity.getPostedDate(),
                entity.getUrl()
        );
    }

    public static ApplicationEntity toEntity(ApplicationDTO dto) {
        return new ApplicationEntity(
                dto.getCompany(),
                dto.getApplicationDate(),
                dto.getTime(),
                dto.getLocation(),
                dto.getPostedDate(),
                dto.getUrl()
        );
    }
}
