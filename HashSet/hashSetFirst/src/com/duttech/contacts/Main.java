package com.duttech.contacts;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

	public static void main(String[] args) {

		List<Contact> emails = ContactData.getData("email");
		List<Contact> phones = ContactData.getData("phone");
		//print("email",emails);
		//print("phone",phones);
		
		/*
		 * Let's assume phone operator provides us the list of emails and phones.
		 * keep a set of emails and phones so that there won't be any duplicate emails and phone.
		 * */
		Set<Contact> emailSet = new HashSet<>(emails); //Created hashset for emailcontacts(Objective is to not having duplicate emailContact)
		Set<Contact> phoneSet = new HashSet<>(phones); //Created hashset for phonecontacts(Objective is to not having duplicate phoneContacts)
		//print("emailContacs", emailSet);
		//print("phoneContacts", phoneSet);
		
		/*
		 * Now we are running the code for adding multiple emails and phones to single contact
		 * */
		
		int index = emails.indexOf(new Contact("Robin Hood"));
		Contact contact = emails.get(index);
		//System.out.println("contact is: "+contact);
		contact.addCompanyEmail("Charles Schwab");
		contact.addCompanyEmail("Charles Schwab");
		
		/*
		 * printing this data will giving us duplicate.
		 * Reason: We don't have equals and hashcode method in Contact class yet and it is referencing from Object Class.
		 * And each contact is a new object that's why it is not duplicate.
		 * After this code we are adding equals and hashcode method in Contact class.
		 * */
		
		
		/*
		 * --------------------Set Operations: Unions and Intersection-------------------------------------------------------
		 * Union:-> Returning elements from both sets and remove the duplicates if any exists
		 * Intersection:-> Returning only the overlapped or duplicate elements from both the sets.
		 * */
		
		//-----------------Performing Union Operation-----------------------------------------------------------
		
		Set<Contact> unionAB = new HashSet<>(emailSet); // we cloned emailset to new setA
		unionAB.addAll(phoneSet); // addAll method in Set allows to perform union operation
		print("A u B: Union of emailSet and phoneSet",unionAB);
		
		
		Set<Contact> unionBA = new HashSet<>(phoneSet);
		unionBA.addAll(emailSet);
		//print("Union of phoneSet and emailSet is ",unionBA);
		
		/*
		 * As you see (AUB) == (BUA), that means union operation is symmetric.
		 * */
		
		//-------------Performing Intersection operation----------------------------------------------------------

		/*
		 * when we are performing intersection of emailSet and phoneSet
		 * It checks the duplicate elements in both the sets and returns the elements present in emailSet
		 * */
		Set<Contact> intersectionAB = new HashSet<>(emailSet);
		intersectionAB.retainAll(phoneSet);
		//print("Intersection of emailSet and phoneSet",intersectionAB);
		

		/*
		 * For case, Intersection of phone set and email set,
		 * it returns the elements of phoneSet that matches the duplicate elements.
		 * */
		Set<Contact> intersectionBA = new HashSet<>(phoneSet);
		intersectionBA.retainAll(emailSet);
		//print("Intersection of phoneSet and emailSet",intersectionBA);
		
		/*
		 * As you see (A Intersection B) == (B Intersection A), that means Intersection operation is symmetric.
		 * */
		
		
		/*
		 * Now we have a union and we want to return only the elements present in either set
		 * we will perform --> set difference operations
		 * */
		
		unionAB.removeAll(phoneSet);
		print("Distinct elements", unionAB);
		
	}

	public static void print(String header, Collection<Contact> contacts) {
		System.out.println("-------------------------------");
		System.out.println(header);
		//contacts.forEach(c -> System.out.println(c.hashCode()));
		contacts.forEach(System.out::println);
	}
}
