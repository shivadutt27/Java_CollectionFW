package com.duttech.contacts;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Contact {
	
	private String name;
	private Set<String> emails = new HashSet<>();
	private Set<String> phones = new HashSet<>();
	

	public Contact(String name) {
		this(name,null);
	}
	
	public Contact(String name, String email) {
		this(name,email,0);
	}
	
	public Contact(String name, String email, long phone) {
		this.name = name;
		if(email != null) {
			this.emails.add(email);
		}
		if(phone > 0) {
			String phoneStr = String.valueOf(phone);
			String ph= "(%s) %s-%s".formatted(phoneStr.substring(0, 3),phoneStr.substring(3, 6),phoneStr.substring(6));
			phones.add(ph);
		}
	}
	
	
	public String getName() {
		return this.name;
	}
	
	
	public Contact mergeContactData(Contact contact) {
		
		Contact newContact = new Contact(name);
		newContact.emails = new HashSet<>(this.emails);
		newContact.phones = new HashSet<>(this.phones);
		newContact.emails.addAll(contact.emails);
		newContact.phones.addAll(contact.phones);
		return newContact;
	}
	
	@Override
	public String toString() {
		return "%s: %s,%s".formatted(name,emails,phones);
	}


	/*
	 * Generating equals and hashCode methods from eclipse itself
	 * 
	 * First we use these ecplise default hashCode and equals method
	
	@Override
	public int hashCode() {
		return Objects.hash(emails, name, phones);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		return Objects.equals(emails, other.emails) && Objects.equals(name, other.name)
				&& Objects.equals(phones, other.phones);
	}

	* Now we are making a rule that distiction of hashcode is based on name
	* which means: one name can have multiple emails and phones.
	* but contact will have only one name
	* 
	 */

	
	@Override
	public int hashCode() {
		//System.out.println("hashCode "+getName()+" is: "+33*getName().hashCode());
		return name != null?33*getName().hashCode():0;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this==o) return true;
		if(name == null || getClass() != o.getClass()) return false;
		Contact contact = (Contact) o;
		return getName().equals(contact.getName())?true:false;
		
	}
	
	/*
	 * At this point we are getting the distinct result 
	 * However, in the process, we are missing: one contact can have multiple emails and phone
	 * Below the codes are added to perform those functionality.
	 * */
	
	//1. we are creating a method to add the company's email to the contact email list
	
	public void addCompanyEmail(String companyName) {
		String[] names = this.getName().split(" ");
		String companyEmail = "%c%s@%s.com".formatted(names[0].charAt(0),names[names.length -1],
				companyName.replaceFirst(" ", " "));
		if(!this.emails.add(companyEmail)) {
			System.out.println("this "+companyEmail+" is already present");
		}else {
			System.out.println(companyEmail+" is added to eamils");
			
		}
	}
	
}
