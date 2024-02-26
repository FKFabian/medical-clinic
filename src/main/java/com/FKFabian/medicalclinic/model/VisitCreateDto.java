package com.FKFabian.medicalclinic.model;

import lombok.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@Builder
@ToString
@EqualsAndHashCode
public class VisitCreateDto {
    private final LocalDateTime startingVisitDate;
    private final LocalDateTime endingVisitDate;
}
