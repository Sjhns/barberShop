package com.kbs.appointment.services;

import java.util.List;
import java.util.UUID;

import com.kbs.appointment.model.Appointment;

public interface GetAppointment {
	List<Appointment> getAll();

	Appointment getOne(UUID id);
}
