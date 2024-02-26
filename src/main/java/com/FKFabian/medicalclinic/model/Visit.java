package com.FKFabian.medicalclinic.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "VISITS")
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime startingVisitTime;
    @Column(nullable = false)
    private LocalDateTime endingVisitTime;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Visit)) {
            return false;
        }
        Visit other = (Visit) o;

        return id != null &&
                id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", startingVisitTime=" + startingVisitTime +
                ", endingVisitTime=" + endingVisitTime +
                ", patient=" + patient +
                ", doctor=" + doctor +
                '}';
    }
}
