package com.FKFabian.medicalclinic.repository;

import com.FKFabian.medicalclinic.model.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
}
