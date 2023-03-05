package com.kbs.appointment.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kbs.appointment.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID>{
	boolean existsBystartDate (LocalDate date);
	boolean existsByStartTime (LocalTime time);
}
