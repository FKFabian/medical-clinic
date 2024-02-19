package com.FKFabian.medicalclinic.service;

import com.FKFabian.medicalclinic.common.DoctorValidator;
import com.FKFabian.medicalclinic.exceptions.DoctorNotFoundException;
import com.FKFabian.medicalclinic.exceptions.FacilityNotFoundException;
import com.FKFabian.medicalclinic.exceptions.PatientNotFoundException;
import com.FKFabian.medicalclinic.mapper.DoctorMapper;
import com.FKFabian.medicalclinic.model.Doctor;
import com.FKFabian.medicalclinic.model.DoctorCreateDto;
import com.FKFabian.medicalclinic.model.DoctorDTO;
import com.FKFabian.medicalclinic.model.Facility;
import com.FKFabian.medicalclinic.repository.DoctorRepository;
import com.FKFabian.medicalclinic.repository.FacilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final FacilityRepository facilityRepository;

    public List<DoctorDTO> getDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctorMapper.toDoctorDtoList(doctors);
    }

    public DoctorDTO getDoctor(String email) {
        Doctor doctor = doctorRepository.findByEmail(email)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor with given email " + email + " not found."));
        return doctorMapper.toDoctorDto(doctor);
    }

    public DoctorDTO addDoctor(DoctorCreateDto doctorCreateDto) {
        DoctorValidator.checkIfAnyDoctorAlreadyExist(doctorCreateDto, doctorRepository.findAll());
        DoctorValidator.checkIfAnyDoctorsValueIsNull(doctorCreateDto);
        Doctor doctor = doctorMapper.toDoctor(doctorCreateDto);
        Doctor savedDoctor = doctorRepository.save(doctor);
        return doctorMapper.toDoctorDto(savedDoctor);
    }

    public DoctorDTO assignToFacility(String email, Long facilityId) {
        Doctor doctor = doctorRepository.findByEmail(email)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor with given email " + email + " not found."));
        Facility facility = facilityRepository.findById(facilityId)
                .orElseThrow(() -> new FacilityNotFoundException("Facility with id " + facilityId + " not found."));
        if (!facility.getDoctors().contains(doctor)) {
            doctor.getFacilities().add(facility);
            doctorRepository.save(doctor);
        } else {
            throw new IllegalArgumentException("Doctor is already assigned to this facility.");
        }
        return doctorMapper.toDoctorDto(doctor);
    }
}