package com.kbs.appointment.services;

import java.util.UUID;

import com.kbs.appointment.model.Appointment;
import com.kbs.appointment.validation.UpdateAppointmentValid;

public interface UpdateAppointment {
	Appointment execute(UUID id, UpdateAppointmentValid appointmentValid);
}
