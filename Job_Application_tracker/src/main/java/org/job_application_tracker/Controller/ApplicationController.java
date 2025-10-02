package org.job_application_tracker.Controller;


import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.job_application_tracker.DTO.ApplicationRequest;
import org.job_application_tracker.DTO.ApplicationResponse;
import org.job_application_tracker.Service.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<ApplicationResponse> saveApplicationJson(
            @RequestBody ApplicationRequest applicationDTO ) {
        ApplicationResponse applicationResponse = applicationService.saveApplication(applicationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(applicationResponse);
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<ApplicationResponse> saveApplicationForm(
            @Valid @RequestBody ApplicationRequest applicationDTO,
            @RequestParam(value = "document", required = false)MultipartFile document
    ) {
        ApplicationResponse savedDTO = applicationService.saveApplication(applicationDTO, document);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponse> getApplicationById(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.getApplication(id));
    }

    @GetMapping()
    public ResponseEntity<List<ApplicationResponse>> getAllApplications() {
        return ResponseEntity.ok(applicationService.getAllApplications());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationResponse> updateApplication(@PathVariable Long id, @Valid @RequestBody ApplicationRequest applicationDTO) {
        ApplicationResponse updatedDTO = applicationService.updateApplication(id, applicationDTO);
        return ResponseEntity.ok(updatedDTO);
    }

}
