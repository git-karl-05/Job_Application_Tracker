package org.job_application_tracker.Service;

import org.job_application_tracker.DTO.ApplicationDTO;
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

    public ApplicationDTO saveApplication(ApplicationDTO applicationDTO) {

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
        return Mapper.toDTO(savedApplicationEntity);
    }

    public List<ApplicationDTO> getAllApplications() {
        List<ApplicationDTO> applicationDTOList = applicationRepository.findAll().stream()
                .map(Mapper::toDTO)
                .collect(Collectors.toList());

        return applicationDTOList;
    }

    public ApplicationDTO getApplication(Long id) {
        ApplicationEntity applicationEntity = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unable to retrieve Applicaion with ID: " + id));

        return Mapper.toDTO(applicationEntity);
    }

    public ApplicationDTO updateApplication(Long id, ApplicationDTO dto) {
        ApplicationEntity applicationEntity = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unable to retrieve Applicaion with ID: " + id));

        applicationEntity.setCompany(dto.getCompany());
        applicationEntity.setApplicationDate(dto.getApplicationDate());
        applicationEntity.setTime(dto.getTime());
        applicationEntity.setLocation(dto.getLocation());
        applicationEntity.setPostedDate(dto.getPostedDate());
        applicationEntity.setUrl(dto.getUrl());

        ApplicationEntity savedApplicationEntity = applicationRepository.save(applicationEntity);
        return Mapper.toDTO(savedApplicationEntity);
    }

    public void deleteApplication(Long id) {
        applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unable to retrieve Applicaion with ID: " + id));
    }


}
