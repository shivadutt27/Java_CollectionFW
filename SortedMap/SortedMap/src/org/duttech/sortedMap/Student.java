/*
 * This class represents a student who can enroll in a course and purchase a course.
 * Create a record for Course and one for Purchase
 * Course can have following attributes 
 * 		-> Course name
 * 		-> Course Id
 * 		-> Course Subject
 * Purchase can have following records
 * 		-> Student Id
 * 		-> Course ID
 * 		-> price of course
 * 		-> purchase year	
 * 
 * */


package org.duttech.sortedMap;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

record Course (String courseID, String name, String subject) {}

record Purchase (int studentID, String courseID, double price, int year, int dayOfYear) {
	
	public LocalDate purchaseYear() {
		return LocalDate.ofYearDay(year, dayOfYear);
	}
}


public class Student {
	
	private static int LAST_ID = 1;
	
	private String name;
	
	private int id;
	
	private List<Course> courses;
	
	public Student(String name, List<Course> courses) {
		
		this.name = name;
		this.courses = courses;
		this.id = LAST_ID++;
	}

	public Student(String name, Course course) {
		this(name, new ArrayList<>(List.of(course)));
	}
	
	public String getName() {return this.name;}
	
	public int getId() {return this.id;}
	
	public void addCourse(Course course) {
		this.courses.add(course);
	}
	
	@Override
	public String toString() {
		String[] courseNames = new String[courses.size()];
		Arrays.setAll(courseNames, i -> courses.get(i).name());
		return "[%d] : %s".formatted(id, String.join(",", courseNames));
	}
	
}
