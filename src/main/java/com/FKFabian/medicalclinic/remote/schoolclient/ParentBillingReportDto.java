package com.FKFabian.medicalclinic.remote.schoolclient;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ParentBillingReportDto {
    private Long parentId;
    private BigDecimal totalFees;
    private Long NumberOfPaidHours;
    private List<ChildReportDto> childrenReport;
}
