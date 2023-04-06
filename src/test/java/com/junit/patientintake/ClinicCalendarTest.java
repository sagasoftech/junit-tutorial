package com.junit.patientintake;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ClinicCalendarTest {

	@Test
	void allowEntryForAppointment() {
		ClinicCalendar cc = new ClinicCalendar(LocalDate.now());
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
	
	@Test
	void allowEntryForAppointment_AssertAll() {
		ClinicCalendar cc = new ClinicCalendar(LocalDate.now());
		cc.addAppointment("Sagar", "Dighe", "johnson","10/15/2023 10:30 AM");
		
		List<PatientAppointment> appointments = cc.getAppointments();
		
		//Check if Appointment added
		assertNotNull(appointments);
		
		//Check only one appointment added
		assertEquals(1, appointments.size());
		
	    PatientAppointment enteredAppt = appointments.get(0);
	    
	    /*
	     * If assert fails then next assert does not get executed. 
	     * With assertAll, all the asserts gets evaluated in the group
	     * 
	     */
	    assertAll(
		    () -> assertEquals("Sagar", enteredAppt.getPatientFirstName()),
		    () -> assertEquals("Notmatch", enteredAppt.getPatientLastName()),
		    () -> assertEquals(Doctor.johnson, enteredAppt.getDoctor()),
		    () -> assertEquals("10/15/2023 10:30 AM",
		         enteredAppt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a", Locale.US)))
	    );
	}	
	
	@Test
	void allowEntryForAppointment2() {
		ClinicCalendar cc = new ClinicCalendar(LocalDate.now());
		cc.addAppointment("Sagar", "Dighe", "johnson","10/15/2023 10:30 AM");
		
		List<PatientAppointment> appointments = cc.getAppointments();
		
		PatientAppointment enteredAppt = appointments.get(0);
		
		/*
		 * Below will fail as assertSame Assert that
		 * expected and actual refer to the same object. 
		 */
		//assertSame("jonson", enteredAppt.getDoctor());
		assertSame(Doctor.johnson, enteredAppt.getDoctor());
		
		//This will pass
		assertNotSame("jonson", enteredAppt.getDoctor());
	}
	
	   @Test
	   void returnTrueForHasAppointmentsIfThereAreAppointments() {
		  ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
	      calendar.addAppointment("Jim", "Weaver", "avery",
	         "09/01/2018 2:00 pm");
	      /*
	       * Assert that the supplied condition is true.
	       */
	      assertTrue(calendar.hasAppointment(LocalDate.of(2018, 9, 1)));
	   }

	   @Test
	   void returnFalseForHasAppointmentsIfThereAreNoAppointments() {
		   ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
		   /*
		    * Assert that the supplied condition is false.
		    */
	      assertFalse(calendar.hasAppointment(LocalDate.of(2018, 9, 1)));
	   }

	   @Tag("dateTime")
	   @Test
	   void returnCurrentDaysAppointments() {
		  ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
	      calendar.addAppointment("Jim", "Weaver", "avery",
	         "08/26/2018 2:00 pm");
	      calendar.addAppointment("Jim", "Weaver", "avery",
	         "08/26/2018 3:00 pm");
	      calendar.addAppointment("Jim", "Weaver", "avery",
	         "09/01/2018 2:00 pm");
	      
	      // This will fail as there are not appointment scheduled for today
	      assertEquals(2, calendar.getTodayAppointments().size());
	   }

	   @Test
	   /*
	    * @Disabled is used to signal that the annotated test class or
	    * test method is currently disabled and should not be executed.  
	    */
	   @Disabled
	   void returnFalseForHasAppointments_Disabled() {
		   ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
		   /*
		    * Assert that the supplied condition is false.
		    */
	      assertFalse(calendar.hasAppointment(LocalDate.of(2018, 9, 1)));
	   }
}
