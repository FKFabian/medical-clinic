package com.FKFabian.medicalclinic.repository;

import com.FKFabian.medicalclinic.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Long> {
}
