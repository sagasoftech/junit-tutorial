package com.junit.patientintake;

import static org.junit.jupiter.api.Assertions.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;

class ClinicCalendarTest {

	@Test
	void allowEntryForAppointment() {
		ClinicCalendar cc = new ClinicCalendar();
		cc.addAppointment("Sagar", "Dighe", "johnson","10/15/2023 10:30 AM");
		
		List<PatientAppointment> appointments = cc.getAppointments();
		
		//Check if Appointment added
		assertNotNull(appointments);
		
		//Check only one appointment added
		assertEquals(1, appointments.size());
		
	    PatientAppointment enteredAppt = appointments.get(0);
	    assertEquals("Sagar", enteredAppt.getPatientFirstName());
	    assertEquals("Dighe", enteredAppt.getPatientLastName());
	    assertEquals(Doctor.johnson, enteredAppt.getDoctor());
	    assertEquals("10/15/2023 10:30 AM",
	         enteredAppt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a", Locale.US)));
	}

}
