package com.FKFabian.medicalclinic.common;

import com.FKFabian.medicalclinic.model.VisitCreateDto;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class VisitValidatorTest {
    @ParameterizedTest
    @MethodSource("")
    void checkIfAnyVisitsValueIsNull(VisitCreateDto visitCreateDto){

    }

    private static Stream<Arguments> provideNullForVisits(){

    }
}
