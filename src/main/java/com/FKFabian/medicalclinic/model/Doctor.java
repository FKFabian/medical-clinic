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
@Table(name = "Doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String specialization;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Doctors_Facilities",
            joinColumns = {@JoinColumn(name = "doctors_id")},
            inverseJoinColumns = {@JoinColumn(name = "facilities_id")}
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
