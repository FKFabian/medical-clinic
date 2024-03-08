package com.FKFabian.medicalclinic.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "DOCTORS")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(nullable = false, unique = true, name = "EMAIL")
    private String email;
    @Column(nullable = false, name = "PASSWORD")
    private String password;
    @Column(nullable = false, name = "NAME")
    private String name;
    @Column(nullable = false, name = "SURNAME")
    private String surname;
    @Column(nullable = false, name = "SPECIALIZATION")
    private String specialization;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "DOCTORS_FACILITIES",
            joinColumns = {@JoinColumn(name = "DOCTORS_ID")},
            inverseJoinColumns = {@JoinColumn(name = "FACILITIES_ID")}
    )
    List<Facility> facilities = new ArrayList<>();
    @OneToMany(mappedBy = "doctor")
    List<Visit> visits = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Doctor)) {
            return false;
        }
        Doctor other = (Doctor) o;

        return id != null &&
                id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", specialization='" + specialization + '\'' +
                ", facilities=" + Arrays.toString(facilities.stream().map(Facility::getId).toArray()) +
                ", visits='" + Arrays.toString(visits.stream().map(Visit::getId).toArray()) +
                '}';
    }
}
