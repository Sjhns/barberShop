package com.kbs.appointment.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbs.appointment.erros.DeleteAppointmentException;
import com.kbs.appointment.erros.NotFoundAppointmentException;
import com.kbs.appointment.model.Appointment;
import com.kbs.appointment.repository.AppointmentRepository;

@Service
public class DeleteAppointmentImpl implements DeleteAppointment {
	private AppointmentRepository appointmentRepository;

	public DeleteAppointmentImpl(AppointmentRepository appointmentRepository) {
		this.appointmentRepository = appointmentRepository;
	}
	
	@Transactional
	@Override
	public String execute(UUID id) {
		Optional<Appointment> appointment = this.appointmentRepository.findById(id);

		if (!appointment.isPresent()) {
			throw new NotFoundAppointmentException("Agendamento não encontrado.");
		}

		this.appointmentRepository.delete(appointment.get());

		Optional<Appointment> appointmentExist = this.appointmentRepository.findById(id);

		if (appointmentExist.isPresent()) {
			throw new DeleteAppointmentException(
					"Um erro ocorreu ao tentar excluir agendamento, por favor tente mais tarde.");
		}

		return "Agendamento foi excluido com sucesso.";
	}
}
