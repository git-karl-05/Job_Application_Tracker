package org.job_application_tracker.Repository;

import org.job_application_tracker.Entity.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long> {
}
