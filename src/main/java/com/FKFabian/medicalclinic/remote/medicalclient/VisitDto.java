package com.FKFabian.medicalclinic.remote.medicalclient;

import lombok.Data;

import java.time.LocalDateTime;

@Data

public class VisitDto {
    private final LocalDateTime startingVisitDate;
    private final LocalDateTime endingVisitDate;
    private final Long patientId;
    private final Long doctorId;
}
