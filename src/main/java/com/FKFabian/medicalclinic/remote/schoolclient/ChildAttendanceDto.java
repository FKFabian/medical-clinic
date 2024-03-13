package com.FKFabian.medicalclinic.remote.schoolclient;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ChildAttendanceDto {
    private OffsetDateTime entryDate;
    private OffsetDateTime exitDate;
}
