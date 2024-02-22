package com.FKFabian.medicalclinic.repository;

import com.FKFabian.medicalclinic.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    @Query("SELECT v FROM Visit v WHERE v.doctor.id = :doctorId " +
            "AND v.startingVisitDate >= :startingVisitDate " +
            "AND v.endingVisitDate <= :endingVisitDate")
    List<Visit> findAllDoctorsVisitsWithGivenTimeRange(
            @Param("startingVisitDate") LocalDateTime startingVisitDate,
            @Param("endingVisitDate") LocalDateTime endingVisitDate,
            @Param("doctorId") Long doctorId
    );
}
