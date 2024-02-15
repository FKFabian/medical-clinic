package com.FKFabian.medicalclinic.mapper;

import com.FKFabian.medicalclinic.model.Facility;
import com.FKFabian.medicalclinic.model.FacilityCreateDto;
import com.FKFabian.medicalclinic.model.FacilityDTO;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FacilityMapperTest {
    FacilityMapper facilityMapper = Mappers.getMapper(FacilityMapper.class);

    @ParameterizedTest
    @MethodSource("dataToFacilityDtoTest")
    void toFacilityDtoTest(Facility facility, FacilityDTO expectedFacility) {
        FacilityDTO result = facilityMapper.toFacilityDto(facility);
        assertEquals(expectedFacility, result);
    }

    private static Stream<Arguments> dataToFacilityDtoTest() {
        return Stream.of(
                Arguments.of(createFacility(), createFacilityDto()),
                Arguments.of(new Facility(), createFacilityDtoNull())
        );
    }

    @ParameterizedTest
    @MethodSource("dataToFacilityTest")
    void toFacilityTest(FacilityCreateDto facilityCreateDto, Facility expectedFacility) {
        Facility result = facilityMapper.toFacility(facilityCreateDto);
        assertEquals(expectedFacility.getName(), result.getName());
    }

    private static Stream<Arguments> dataToFacilityTest() {
        return Stream.of(
                Arguments.of(createFacilityCreateDto(), createFacility()),
                Arguments.of(createFacilityCreateDtoNull(), new Facility())
        );
    }

    private static FacilityCreateDto createFacilityCreateDto() {
        return new FacilityCreateDto("111", "111", "111"
                , "111", "111");
    }

    private static FacilityCreateDto createFacilityCreateDtoNull() {
        return new FacilityCreateDto(null, null, null
                , null, null);
    }

    private static Facility createFacility() {
        return new Facility(null, "111"
                , "111", "111", "111"
                , "111", new ArrayList<>());
    }

    private static FacilityDTO createFacilityDto() {
        return new FacilityDTO("111", "111"
                , "111", "111"
                , "111", new ArrayList<>());
    }

    private static FacilityDTO createFacilityDtoNull() {
        return new FacilityDTO(null, null
                , null, null
                , null, new ArrayList<>());
    }
}
