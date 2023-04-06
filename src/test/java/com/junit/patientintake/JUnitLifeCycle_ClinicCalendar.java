package com.junit.patientintake;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JUnitLifeCycle_ClinicCalendar {

	/*
	 * @BeforeAll is used to signal that the annotated method should be
	 * executed before all tests in the current test class. 
	 */
	@BeforeAll
	static void initAll() {
		System.out.println("Before All");
	}
	
	/*
	 * @BeforeEach is used to signal that the annotated method should be
	 * executed before each @Test, @RepeatedTest, @ParameterizedTest, 
	 * @TestFactory,and @TestTemplate method in the current test class. 
	 */
	@BeforeEach
	void init() {
		System.out.println("Before Each");
	}
	
	/*
	 * @Test is used to signal that the annotated method is a test method. 
	 */
	@Test
	void test1() {
		System.out.println("Before Test 1");
		assertTrue(true);
		System.out.println("After Test 1");
	}

	/*
	 * If Asserts fails, all code next to it will not execute
	 */
	@Test
	void test2() {
		System.out.println("Before Test 2");
		assertTrue(false);
		System.out.println("After Test 2");
	}
	
	/*
	 * @AfterEach is used to signal that the annotated method should be
	 * executed after each @Test, @RepeatedTest, @ParameterizedTest, 
	 * @TestFactory,and @TestTemplate method in the current test class. 
	 */
	@AfterEach
	void tearDown() {
		System.out.println("After Each");
	}
	
	/*
	 * @AfterAll is used to signal that the annotated method should be
	 * executed after all tests in the current test class. 
	 */
	@AfterAll
	static void tearDownAll() {
		System.out.println("After All");
	}
}
