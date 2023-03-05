package com.kbs.appointment.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kbs.appointment.erros.NotFoundAppointmentException;
import com.kbs.appointment.model.Appointment;
import com.kbs.appointment.repository.AppointmentRepository;

@Service
public class GetAppointmentimpl implements GetAppointment {

	private AppointmentRepository appointmentRepository;

	public GetAppointmentimpl(AppointmentRepository appointmentRepository) {
		this.appointmentRepository = appointmentRepository;
	}

	@Override
	public List<Appointment> getAll() {
		return this.appointmentRepository.findAll();
	}

	@Override
	public Appointment getOne(UUID id) {
		Optional<Appointment> appointment = this.appointmentRepository.findById(id);
		if (!appointment.isPresent()) {
			throw new NotFoundAppointmentException( "Agendamento não encontrado.");
		}
		return appointment.get();
	}

}
