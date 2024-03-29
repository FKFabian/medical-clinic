package com.FKFabian.medicalclinic.service;

import com.FKFabian.medicalclinic.common.DoctorValidator;
import com.FKFabian.medicalclinic.exceptions.DoctorNotFoundException;
import com.FKFabian.medicalclinic.exceptions.FacilityNotFoundException;
import com.FKFabian.medicalclinic.mapper.DoctorMapper;
import com.FKFabian.medicalclinic.model.Doctor;
import com.FKFabian.medicalclinic.model.DoctorCreateDto;
import com.FKFabian.medicalclinic.model.DoctorDTO;
import com.FKFabian.medicalclinic.model.Facility;
import com.FKFabian.medicalclinic.repository.DoctorRepository;
import com.FKFabian.medicalclinic.repository.FacilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final FacilityRepository facilityRepository;

    public List<DoctorDTO> getDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctorMapper.toDoctorsDto(doctors);
    }

    public DoctorDTO getDoctor(String email) {
        Doctor doctor = doctorRepository.findByEmail(email)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor with given email " + email + " not found."));
        return doctorMapper.toDoctorDto(doctor);
    }

    @Transactional
    public DoctorDTO addDoctor(DoctorCreateDto doctorCreateDto) {
        DoctorValidator.checkIfAnyDoctorAlreadyExist(doctorCreateDto, doctorRepository.findAll());
        DoctorValidator.checkIfAnyDoctorsValueIsNull(doctorCreateDto);
        Doctor doctor = doctorMapper.toDoctor(doctorCreateDto);
        Doctor savedDoctor = doctorRepository.save(doctor);
        return doctorMapper.toDoctorDto(savedDoctor);
    }

    @Transactional
    public DoctorDTO assignToFacility(String email, Long facilityId) {
        Doctor doctor = doctorRepository.findByEmail(email)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor with given email " + email + " not found."));
        Facility facility = facilityRepository.findById(facilityId)
                .orElseThrow(() -> new FacilityNotFoundException("Facility with id " + facilityId + " not found."));
        checkIfDoctorIsAlreadyAssign(doctor, facility);
        return doctorMapper.toDoctorDto(doctor);
    }

    private void checkIfDoctorIsAlreadyAssign(Doctor doctor, Facility facility) {
        if (!facility.getDoctors().contains(doctor)) {
            doctor.getFacilities().add(facility);
            doctorRepository.save(doctor);
        } else {
            throw new IllegalArgumentException("Doctor is already assigned to this facility.");
        }
    }
}