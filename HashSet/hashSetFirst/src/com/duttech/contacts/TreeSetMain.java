package com.duttech.contacts;

import java.util.Comparator;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

/*
 * This class is representing TreeSet one of the three types of HashSet java provides
 * */

public class TreeSetMain {
	
	public static void main(String[] arge) {
		
		List<Contact> phones = ContactData.getData("phone");
		List<Contact> emails = ContactData.getData("email");
		
	/*
		NavigableSet<Contact> treeSet = new TreeSet<>(phones);
		
		The above method throws the class cast exception at runtime
			as Contact class doesn't implements the comparable interface
		
		Workaround for this
			Adding comparator to the constructor of TreeSet
			
	*/
		Comparator<Contact> mySort = Comparator.comparing(Contact::getName);
		NavigableSet<Contact> sorted = new TreeSet<>(mySort);
		sorted.addAll(phones);
		//sorted.addAll(emails);
		
		
		sorted.forEach(System.out::println);
	}

}
