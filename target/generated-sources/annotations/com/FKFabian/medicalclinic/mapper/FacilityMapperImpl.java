package com.FKFabian.medicalclinic.mapper;

import com.FKFabian.medicalclinic.model.Facility;
import com.FKFabian.medicalclinic.model.FacilityCreateDto;
import com.FKFabian.medicalclinic.model.FacilityDTO;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-13T08:53:38+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class FacilityMapperImpl implements FacilityMapper {

    @Override
    public FacilityDTO toFacilityDto(Facility facility) {
        if ( facility == null ) {
            return null;
        }

        List<Long> doctorsId = null;
        String name = null;
        String city = null;
        String zipCode = null;
        String street = null;
        String noBuilding = null;

        doctorsId = mapDoctors( facility.getDoctors() );
        name = facility.getName();
        city = facility.getCity();
        zipCode = facility.getZipCode();
        street = facility.getStreet();
        noBuilding = facility.getNoBuilding();

        FacilityDTO facilityDTO = new FacilityDTO( name, city, zipCode, street, noBuilding, doctorsId );

        return facilityDTO;
    }

    @Override
    public Facility toFacility(FacilityCreateDto facilityCreateDto) {
        if ( facilityCreateDto == null ) {
            return null;
        }

        Facility facility = new Facility();

        facility.setName( facilityCreateDto.getName() );
        facility.setCity( facilityCreateDto.getCity() );
        facility.setZipCode( facilityCreateDto.getZipCode() );
        facility.setStreet( facilityCreateDto.getStreet() );
        facility.setNoBuilding( facilityCreateDto.getNoBuilding() );

        return facility;
    }
}
