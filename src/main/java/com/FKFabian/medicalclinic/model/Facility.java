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
@Table(name = "FACILITIES")
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(nullable = false, unique = true, name = "NAME")
    private String name;
    @Column(nullable = false, name = "CITY")
    private String city;
    @Column(nullable = false, name = "ZIP_CODE")
    private String zipCode;
    @Column(nullable = false, name = "STREET")
    private String street;
    @Column(nullable = false, name = "NO_BUILDING")
    private String noBuilding;
    @ManyToMany(mappedBy = "facilities")
    List<Doctor> doctors = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Facility)) {
            return false;
        }
        Facility other = (Facility) o;

        return id != null &&
                id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Facility{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", street='" + street + '\'' +
                ", noBuilding='" + noBuilding + '\'' +
                ", doctors=" + Arrays.toString(doctors.stream().map(Doctor::getId).toArray()) +
                '}';
    }
}
