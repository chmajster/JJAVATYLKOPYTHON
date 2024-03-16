package com.capgemini.wsb.persistence.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "VISIT")
public class VisitEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	@Column(nullable = false)
	private LocalDateTime time;

	//relacja jednostronna od strony rodzica
	@OneToMany(cascade = CascadeType.ALL)
	private Set<MedicalTreatmentEntity> medicalTreatmentEntities;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public Set<MedicalTreatmentEntity> getMedicalTreatmentEntities() {
		return medicalTreatmentEntities;
	}

	public void setMedicalTreatmentEntities(Set<MedicalTreatmentEntity> medicalTreatmentEntities) {
		this.medicalTreatmentEntities = medicalTreatmentEntities;
	}
}
