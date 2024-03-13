package com.FKFabian.medicalclinic.remote.schoolclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "schoolClient", url = "localhost:8081")
public interface SchoolClient {

    @GetMapping("/school/{id}/parent/{parentId}/billing")
    ParentBillingReportDto getReportFromGivenSchool(@PathVariable("id") String schoolId, @PathVariable("parentId") String parentId);

    @GetMapping("/school/{id}/billing")
    SchoolBillingReportDto getBillingFromGivenSchool(@PathVariable("id") String schoolId);
}
