package org.job_application_tracker.Service;

import org.job_application_tracker.DTO.ApplicationRequest;
import org.job_application_tracker.DTO.ApplicationResponse;
import org.job_application_tracker.Entity.ApplicationEntity;
import org.job_application_tracker.Repository.ApplicationRepository;
import org.job_application_tracker.Utility.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public ApplicationResponse saveApplication(ApplicationRequest applicationDTO) {

//        log.info("Converting DTO to Entity: {}", applicationDTO.toString());
        ApplicationEntity applicationEntity = new ApplicationEntity(
                applicationDTO.getCompany(),
                applicationDTO.getApplicationDate(),
                applicationDTO.getTime(),
                applicationDTO.getLocation(),
                applicationDTO.getPostedDate(),
                applicationDTO.getUrl()
        );
        ApplicationEntity savedApplicationEntity = applicationRepository.save(applicationEntity);
//        log.info("Saved Entity: {}", savedApplicationEntity);
        return Mapper.toResponse(savedApplicationEntity);
    }

    public List<ApplicationResponse> getAllApplications() {
        List<ApplicationResponse> applicationDTOList = applicationRepository.findAll().stream()
                .map(Mapper::toResponse)
                .collect(Collectors.toList());

        return applicationDTOList;
    }

    public ApplicationResponse getApplication(Long id) {
        ApplicationEntity applicationEntity = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unable to retrieve Applicaion with ID: " + id));

        return Mapper.toResponse(applicationEntity);
    }

    public ApplicationResponse updateApplication(Long id, ApplicationRequest dto) {
        ApplicationEntity applicationEntity = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unable to retrieve Applicaion with ID: " + id));

        applicationEntity.setCompany(dto.getCompany());
        applicationEntity.setApplicationDate(dto.getApplicationDate());
        applicationEntity.setTime(dto.getTime());
        applicationEntity.setLocation(dto.getLocation());
        applicationEntity.setPostedDate(dto.getPostedDate());
        applicationEntity.setUrl(dto.getUrl());

        ApplicationEntity savedApplicationEntity = applicationRepository.save(applicationEntity);
        return Mapper.toResponse(savedApplicationEntity);
    }

    public void deleteApplication(Long id) {

        applicationRepository.deleteById(id);
    }


}
