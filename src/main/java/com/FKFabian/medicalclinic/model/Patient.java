package com.FKFabian.medicalclinic.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PATIENTS")
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String idCardNo;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private LocalDate birthday;

    public void update(PatientCreateDto patientCreateDto) {
        this.email = patientCreateDto.getEmail();
        this.password = patientCreateDto.getPassword();
        this.idCardNo = patientCreateDto.getIdCardNo();
        this.firstName = patientCreateDto.getFirstName();
        this.lastName = patientCreateDto.getLastName();
        this.phoneNumber = patientCreateDto.getPhoneNumber();
        this.birthday = patientCreateDto.getBirthday();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) {
            return false;
        }
        Patient other = (Patient) o;

        return id != null &&
                id.equals(other.getId());
    }


    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", idCardNo='" + idCardNo + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
