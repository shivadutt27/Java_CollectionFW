/*
 * In Main class we are instantiating two Map implemented classes 
 * 		->LinkedHashMap --> for purchase 
 * 		->TreeHashMap --> for Student
 * */


package org.duttech.sortedMap;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class Main {
	
	private static Map<String,Purchase> purchases = new LinkedHashMap<>();
	private static NavigableMap<String,Student> students = new TreeMap<>();
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Course jmc = new Course("jmc101","Java Master Class","Java");
		Course dsa = new Course("dsa007","Data Structure And Algorithm","Java");
		Course pyCourse = new Course("py102","Python Course", "Python");
		
		addPurchase("Shiva",jmc,198.00);
		addPurchase("Shiva",dsa,120);
		addPurchase("Vishal",pyCourse,130);
		addPurchase("Karan",jmc,179);
		
		addPurchase("Arjun",pyCourse,129.99);
		addPurchase("Sunny",dsa,169.99);
		addPurchase("Sonu",jmc,79.99);
		
//		purchases.forEach((key,value) -> System.out.println(key +":"+value));
//		System.out.println("----------------------------------------------");
//		students.forEach((key,value) -> System.out.println(key+":"+value));
		
		NavigableMap<LocalDate,List<Purchase>> purchaseList = new TreeMap<>();
		
		for(Purchase p: purchases.values()) {
			//System.out.println("value of P is: "+p);
			purchaseList.compute(p.purchaseYear(), 
					(pdate,pList) -> {
						List<Purchase> list = (pList==null) ? new ArrayList<>() : pList;
						list.add(p);
						return list;
					});
			
		}
		//System.out.println("------------------------------------------------");
		purchaseList.forEach((key,value) -> System.out.println(key+":"+value));
		
		Map<LocalDate,List<Purchase>> week1 = purchaseList.headMap(LocalDate.ofYearDay(2024,8));
		
		System.out.println("---------------------------------------------------");
		week1.forEach((key,value) -> System.out.println(key+":"+value));
		
		
		System.out.println("Testing purpose");
		
		totalPurchase(1, week1);
		

	}
	

	
	private static void addPurchase(String name, Course course, double price) {
		
		Student existingStudent = students.get(name);
		if(existingStudent == null) {
			existingStudent = new Student(name,course);
			students.put(name, existingStudent);
		}else {
			existingStudent.addCourse(course);
		}
		
	//	int day = purchases.size()+1;
		//	Changing the upper code line to support the next functionality 
		//	To support the new TreeMap functionality of purchaselist
		
		int day = new Random().nextInt(1, 15);
		
		String key = course.courseID() +"_"+existingStudent.getId();
		Purchase purchase = new Purchase(existingStudent.getId(),course.courseID(),price,LocalDate.now().getYear(),day);
		purchases.put(key, purchase);
	}
	
	
	/*
	 * Creating a method (displayStats) to print the total number of courses sold per given period
	 * This method can take two arguments (period, total purchase data of this given period)
	 * given purchase data contains all the courses, 
	 * but we need to calculate that total purchase of single course.
	 * let's take a map variable (totalCourses) having key 
	 * */
	
	private static void totalPurchase(int period, Map<LocalDate,List<Purchase>> purchaseList) {
		
		Map<String,Integer> courseSold = new TreeMap<>();
		
		purchaseList.forEach((key,value) -> {
			System.out.println(key+":"+value);
			for(Purchase p: value) {
				courseSold.merge(p.courseID(), 1, (prev,curr) -> prev+curr);
			}
		});
		
		courseSold.forEach((key,value) -> System.out.println(key+":"+value));
		
		
	}
	

}