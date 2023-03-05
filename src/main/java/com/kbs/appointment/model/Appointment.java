package com.kbs.appointment.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

import com.kbs.Customer.model.Customer;
import com.kbs.barber.models.Barber;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "appointments")
@NoArgsConstructor
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(nullable = false)
	private String service;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "barber_id")
	private Barber barber;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false, name = "start_day")
	private LocalDate startDate;

	@DateTimeFormat(pattern = "HH:mm:ss")
	@Column(nullable = false, name = "start_time")
	private LocalTime startTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "end_day")
	private LocalDate endDate;

	@DateTimeFormat(pattern = "HH:mm:ss")
	@Column(name = "end_time")
	private LocalTime endTime;

}
