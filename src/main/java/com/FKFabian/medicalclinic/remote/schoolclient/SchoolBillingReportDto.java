package com.FKFabian.medicalclinic.remote.schoolclient;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SchoolBillingReportDto {
    private BigDecimal totalFees;
    private ParentBillingReportDto parentBillingReports;
}
