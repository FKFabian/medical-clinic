package com.FKFabian.medicalclinic.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PATIENTS")
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(nullable = false, unique = true, name = "EMAIL")
    private String email;
    @Column(nullable = false, name = "PASSWORD")
    private String password;
    @Column(nullable = false, unique = true, name = "ID_CARD_NO")
    private String idCardNo;
    @Column(nullable = false, name = "FIRST_NAME")
    private String firstName;
    @Column(nullable = false, name = "LAST_NAME")
    private String lastName;
    @Column(nullable = false, name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(nullable = false, name = "BIRTHDAY")
    private LocalDate birthday;
    @OneToMany(mappedBy = "patient")
    private List<Visit> visits = new ArrayList<>();

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
                ", visits='" + Arrays.toString(visits.stream().map(Visit::getId).toArray()) +
                '}';
    }
}
