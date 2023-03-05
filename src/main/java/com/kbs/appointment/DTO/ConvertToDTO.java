package com.kbs.appointment.DTO;

import java.beans.JavaBean;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.kbs.appointment.model.Appointment;

import lombok.AllArgsConstructor;
import lombok.Data;

@JavaBean
public class ConvertToDTO {
	
	public static List<AppointmentDTO> all (List<Appointment> appointments) {
	
	return appointments.stream().map(appoint -> {

        var barber = new AppointmentBarberDTO(
     		   appoint.getBarber().getId(), 
     		   appoint.getBarber().getName(), 
     		   appoint.getBarber().getLastname(), 
     		   appoint.getBarber().getPhoneNumber(), 
     		   appoint.getBarber().getEmail()
     		   );
  
        var customer = new AppointmentCustomerDTO(
					appoint.getCustomer().getId(),
					appoint.getCustomer().getName(), 
					appoint.getCustomer().getLastname(),
					appoint.getCustomer().getEmail(), 
					appoint.getCustomer().getPhoneNumber()
					);
		
        return new AppointmentDTO(appoint.getId(), appoint.getService(),
				appoint.getStartDate(),
				appoint.getStartTime(), appoint.getEndDate(), 
				appoint.getEndTime(), barber, customer);	
			}).collect(Collectors.toList());
	
}
	
	public static AppointmentDTO one (Appointment appointment) {
		AppointmentBarberDTO barber = new AppointmentBarberDTO(
				appointment.getBarber().getId(),
				appointment.getBarber().getName(), 
				appointment.getBarber().getLastname(),
				appointment.getBarber().getPhoneNumber(), 
				appointment.getBarber().getEmail());
		
		AppointmentCustomerDTO customer = new AppointmentCustomerDTO(
				appointment.getCustomer().getId(),
				appointment.getCustomer().getName(), 
				appointment.getCustomer().getLastname(),
				appointment.getCustomer().getEmail(), 
				appointment.getCustomer().getPhoneNumber());
		
		return new AppointmentDTO(
				appointment.getId(), appointment.getService(),
				appointment.getStartDate(),
				appointment.getStartTime(), appointment.getEndDate(), 
				appointment.getEndTime(), 
				barber, customer);
		
	}
}

@Data
@AllArgsConstructor
class AppointmentBarberDTO {

	private UUID id;
	private String name;

	private String lastname;
	private String phoneNumber;
	private String email;
}

@Data
@AllArgsConstructor
class AppointmentCustomerDTO {
	private UUID id;

	private String name;

	private String lastname;

	private String email;

	private String phoneNumber;
}