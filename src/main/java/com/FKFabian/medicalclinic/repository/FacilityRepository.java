package com.FKFabian.medicalclinic.repository;

import com.FKFabian.medicalclinic.model.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;
import java.util.Optional;

public interface FacilityRepository extends JpaRepository<Facility, Long> {

}
