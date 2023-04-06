package com.junit.patientintake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/*
 * @DisplayName is used to declare a custom display name for the 
 * annotated test class or test method. 
 */
@DisplayName("Date Time Converter")
class DateTimeConverterTest {

	   /*
	    * @Nested is used to signal that the annotated class is a nested
	    * It displays the results in nested manner (see output)
	    */
	   @Nested
	   @DisplayName("convert string with 'today' keyword")
	   class TodayTests {
	      @Test
	      @DisplayName("correctly")
	      void convertTodayStringCorrectly() {
	         LocalDate today = LocalDate.of(2018, 9, 1);
	         LocalDateTime result = DateTimeConverter.convertStringToDateTime("today 1:00 pm",
	            today);
	         /*
	          * We can add third parameter to assertEquals to print more specific message
	          */
	         assertEquals(result, LocalDateTime.of(2018, 9, 1, 13, 0),
	            "Failed to convert 'today' string to expected date time, today passed was: " +today);
	      }

	      @Test
	      @DisplayName("regardless of case")
	      void convertTodayStringCorrectlyCaseInsensitive() {
	         LocalDate today = LocalDate.of(2018, 9, 1);
	         LocalDateTime result = DateTimeConverter.convertStringToDateTime("ToDay 1:00 pm",
	            today);

        	/*
        	 * Third parameter get evaluated every time even when test pass and they could 
        	 * slow down the execution a bit as they may have complex logic to execute.
        	 * 
        	 * Use Lambda function, an anonymous function basically, instead of fix message as parameter
        	 * Will have same results exactly on failure, but lambda expression will be evaluated
        	 * only if there is failure
        	 */
	         assertEquals(result, LocalDateTime.of(2018, 9, 1, 13, 0),
	            () -> "Failed to convert 'today' string to expected date time, today passed was: " +today);
	      }
	   }

	   @Test
	   @DisplayName("convert expected date time pattern in string correctly")
	   void convertCorrectPatternToDateTime() {
	      LocalDateTime result = DateTimeConverter.convertStringToDateTime("9/2/2018 1:00 pm",
	         LocalDate.of(2018, 9, 1));
	      assertEquals(result, LocalDateTime.of(2018, 9, 2, 13, 0));
	   }

	   @Test
	   @DisplayName("throw exception if entered pattern of string incorrect")
	   void throwExceptionIfIncorrectPatternProvided() {
		   /*
		    * Assert that execution of the supplied executable throws
		    * an exception of the expectedType and return the exception. 
		    */
	      Throwable error = assertThrows(RuntimeException.class, () ->
	         DateTimeConverter.convertStringToDateTime("9/2/2018 100 pm",
	         LocalDate.of(2018, 9, 1)));
	      assertEquals("Unable to create date time from: [9/2/2018 100 pm], " +
	         "please enter with format [M/d/yyyy h:mm a], Text '9/2/2018 100 PM' " +
	         "could not be parsed at index 12", error.getMessage());
	   }

}
