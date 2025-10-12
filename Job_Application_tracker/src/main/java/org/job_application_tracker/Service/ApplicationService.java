package org.job_application_tracker.Service;

import org.job_application_tracker.DTO.ApplicationRequest;
import org.job_application_tracker.DTO.ApplicationResponse;
import org.job_application_tracker.Entity.ApplicationEntity;
import org.job_application_tracker.Repository.ApplicationRepository;
import org.job_application_tracker.Utility.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final Path uploadDir = Paths.get("/Users/kai/Documents/job-tracker-uploads");

    public ApplicationService(ApplicationRepository applicationRepository) throws IOException {
        this.applicationRepository = applicationRepository;
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
            System.out.println("Uploads folder created at: " + uploadDir.toAbsolutePath());
        } else {
            System.out.println("Uploads folder exists at: " + uploadDir.toAbsolutePath());
        }
    }


    public ApplicationResponse saveApplication(ApplicationRequest applicationRequest) {

//        log.info("Converting DTO to Entity: {}", applicationDTO.toString());
        ApplicationEntity applicationEntity = new ApplicationEntity(
                applicationRequest.getCompany(),
                applicationRequest.getApplicationDate(),
                applicationRequest.getTime(),
                applicationRequest.getLocation(),
                applicationRequest.getPostedDate(),
                applicationRequest.getUrl(),
                applicationRequest.getDocumentUrl()
        );
        ApplicationEntity savedApplicationEntity = applicationRepository.save(applicationEntity);
//        log.info("Saved Entity: {}", savedApplicationEntity);
        return Mapper.toResponse(savedApplicationEntity);
    }

    public ApplicationResponse saveApplicationForm(ApplicationRequest applicationRequest, MultipartFile document) {
        String documentUrl = null;

        if (document != null && !document.isEmpty()) {
            System.out.println("Received file: " + document.getOriginalFilename());
            System.out.println("Size: " + document.getSize() + " bytes");
            System.out.println("Type: " + document.getContentType());

            try {
                String safeName = document.getOriginalFilename().replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
                String fileName = System.currentTimeMillis() + "-" + safeName;
                Path filePath = uploadDir.resolve(fileName);


                document.transferTo(filePath.toFile());

                documentUrl = "/uploads/" + fileName;
            } catch (IOException e) {
                throw new RuntimeException("Could not save uploaded file: " + document.getOriginalFilename(), e);

            }
        }

        ApplicationEntity applicationEntity = new ApplicationEntity(
                applicationRequest.getCompany(),
                applicationRequest.getApplicationDate(),
                applicationRequest.getTime(),
                applicationRequest.getLocation(),
                applicationRequest.getPostedDate(),
                applicationRequest.getUrl(),
                documentUrl
        );

        ApplicationEntity savedEntity = applicationRepository.save(applicationEntity);
        return Mapper.toResponse(savedEntity);
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
