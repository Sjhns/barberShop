package com.kbs.appointment.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kbs.Customer.model.Customer;
import com.kbs.Customer.repository.CustomerRepository;
import com.kbs.appointment.erros.ExistAppointmentException;
import com.kbs.appointment.erros.NotFoundAppointmentException;
import com.kbs.appointment.model.Appointment;
import com.kbs.appointment.repository.AppointmentRepository;
import com.kbs.barber.models.Barber;
import com.kbs.barber.repository.BarberRepository;

import jakarta.transaction.Transactional;

@Service
public class CreateAppointmentImpl implements CreateAppointment {
	private AppointmentRepository appointmentRepository;
	private BarberRepository barberRepository;
	private CustomerRepository customerRepository;

	public CreateAppointmentImpl(AppointmentRepository appointmentRepository, 
									BarberRepository barberRepository,
									CustomerRepository customerRepository) {
		this.appointmentRepository = appointmentRepository;
		this.barberRepository = barberRepository;
		this.customerRepository = customerRepository;
	}

	@Transactional
	@Override
	public Appointment execute(Appointment appointment) {

		boolean isUnavailable = this.appointmentRepository.existsBystartDate(appointment.getStartDate())
				&& this.appointmentRepository.existsByStartTime(appointment.getStartTime());

		Optional<Barber> existBarber = this.barberRepository.findById(appointment.getBarber().getId());
		Optional<Customer> existCustomer = this.customerRepository.findById(appointment.getCustomer().getId());

		if (!existBarber.isPresent()) {
			throw new NotFoundAppointmentException("Este Barbeiro não existe nos registros.");
		}
		
		if (!existCustomer.isPresent()) {
			throw new NotFoundAppointmentException("Este Cliente não existe nos registros.");
		}

		if (isUnavailable) {
			throw new ExistAppointmentException("Este horario e data não estão disponiveis.");
		}

		return this.appointmentRepository.save(appointment);

	}

}
