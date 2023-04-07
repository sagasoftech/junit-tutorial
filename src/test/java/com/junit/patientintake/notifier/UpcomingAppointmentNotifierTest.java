package com.junit.patientintake.notifier;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.junit.patientintake.ClinicCalendar;

import static org.junit.jupiter.api.Assertions.*;

class UpcomingAppointmentNotifierTest {

   private EmailNotifierTestDouble emailDouble;

   @BeforeEach
   void init() {
      emailDouble = new EmailNotifierTestDouble();
   }

   @Test
   void sendNotificationWithCorrectFormat() {
      ClinicCalendar calendar = new ClinicCalendar(LocalDate.of(2018, 8, 26));
      calendar.addAppointment("Jim", "Weaver", "avery",
         "08/27/2018 2:00 pm");
      UpcomingAppointmentNotifier notifier = new UpcomingAppointmentNotifier(calendar, emailDouble);

      notifier.run();

      assertEquals(1, emailDouble.receivedMessages.size());
      EmailNotifierTestDouble.Message expectedMessage = emailDouble.receivedMessages.get(0);
      assertAll(
         () -> assertEquals("weaverjim@mail.com", expectedMessage.toAddress),
         () -> assertEquals("Appointment Reminder", expectedMessage.subject),
         () -> assertEquals("You have an appointment tomorrow at 2:00 PM" +
            " with Dr. Ralph Avery.", expectedMessage.body)
      );
   }

}