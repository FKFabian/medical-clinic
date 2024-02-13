package com.FKFabian.medicalclinic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Facilities")
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String city;
    private String zipCode;
    private String street;
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
