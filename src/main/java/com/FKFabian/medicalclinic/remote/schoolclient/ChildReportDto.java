package com.FKFabian.medicalclinic.remote.schoolclient;

import lombok.Data;

import java.util.List;

@Data
public class ChildReportDto {
    private Long childId;
    private String firstName;
    private String lastName;
    private List<ChildAttendanceDto> attendances;
}
