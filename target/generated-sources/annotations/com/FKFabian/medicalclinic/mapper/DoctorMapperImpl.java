package com.FKFabian.medicalclinic.mapper;

import com.FKFabian.medicalclinic.model.Doctor;
import com.FKFabian.medicalclinic.model.DoctorCreateDto;
import com.FKFabian.medicalclinic.model.DoctorDTO;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-13T08:53:38+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class DoctorMapperImpl implements DoctorMapper {

    @Override
    public DoctorDTO toDoctorDto(Doctor doctor) {
        if ( doctor == null ) {
            return null;
        }

        List<Long> facilitiesId = null;
        String email = null;
        String name = null;
        String surname = null;
        String specialization = null;

        facilitiesId = mapFacilities( doctor.getFacilities() );
        email = doctor.getEmail();
        name = doctor.getName();
        surname = doctor.getSurname();
        specialization = doctor.getSpecialization();

        DoctorDTO doctorDTO = new DoctorDTO( email, name, surname, specialization, facilitiesId );

        return doctorDTO;
    }

    @Override
    public Doctor toDoctor(DoctorCreateDto doctorCreateDto) {
        if ( doctorCreateDto == null ) {
            return null;
        }

        Doctor doctor = new Doctor();

        doctor.setEmail( doctorCreateDto.getEmail() );
        doctor.setPassword( doctorCreateDto.getPassword() );
        doctor.setName( doctorCreateDto.getName() );
        doctor.setSurname( doctorCreateDto.getSurname() );
        doctor.setSpecialization( doctorCreateDto.getSpecialization() );

        return doctor;
    }
}
