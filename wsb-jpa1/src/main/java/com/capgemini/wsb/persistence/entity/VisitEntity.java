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

	@Column(name = "patient_id")
	private Long patientId;

	// relacja dwustronna
	@ManyToOne
	@JoinColumn(name="patient_id", nullable=false, insertable = false, updatable = false)
	private PatientEntity patient;

	@Column(name = "doctor_id")
	private Long doctorId;

	// relacja dwustronna
	@ManyToOne
	@JoinColumn(name="doctor_id", nullable=false, insertable = false, updatable = false)
	private DoctorEntity doctor;

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

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public PatientEntity getPatient() {
		return patient;
	}

	public void setPatient(PatientEntity patient) {
		this.patient = patient;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public DoctorEntity getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorEntity doctor) {
		this.doctor = doctor;
	}

	public Set<MedicalTreatmentEntity> getMedicalTreatmentEntities() {
		return medicalTreatmentEntities;
	}

	public void setMedicalTreatmentEntities(Set<MedicalTreatmentEntity> medicalTreatmentEntities) {
		this.medicalTreatmentEntities = medicalTreatmentEntities;
	}
}
