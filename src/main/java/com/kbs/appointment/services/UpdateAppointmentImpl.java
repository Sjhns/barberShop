package com.kbs.appointment.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.kbs.appointment.erros.NotFoundAppointmentException;
import com.kbs.appointment.model.Appointment;
import com.kbs.appointment.repository.AppointmentRepository;
import com.kbs.appointment.validation.UpdateAppointmentValid;

@Service
public class UpdateAppointmentImpl implements UpdateAppointment {

	private AppointmentRepository appointmentRepository;

	public UpdateAppointmentImpl(AppointmentRepository appointmentRepository) {
		this.appointmentRepository = appointmentRepository;
	}

	@Override
	public Appointment execute(UUID id, UpdateAppointmentValid appointmentValid) {
		Optional<Appointment> appointment = this.appointmentRepository.findById(id);

		if (!appointment.isPresent()) {
			throw new NotFoundAppointmentException("Agendamento não encontrado.");
		}

		var appointmentSchema = new Appointment();
		BeanUtils.copyProperties(appointmentValid, appointmentSchema);

		appointmentSchema.setId(appointment.get().getId());
		appointmentSchema.setBarber(appointment.get().getBarber());
		appointmentSchema.setCustomer(appointment.get().getCustomer());

		return this.appointmentRepository.save(appointmentSchema);
	}

}
