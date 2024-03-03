package com.FKFabian.medicalclinic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String specialization;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Doctors_Facilities",
            joinColumns = {@JoinColumn(name = "doctors_id")},
            inverseJoinColumns = {@JoinColumn(name = "facilities_id")}
    )

    List<Facility> facilities = new ArrayList<>();

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
                '}';
    }
}
