package dev.duttech;

//import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
//import java.util.List;
import java.util.Set;

import dev.duttech.Task.Priority;

public class TaskData {
	
	private static String allTask = """
				Infrastructure, Logging, High
				Infrastructure, DB Access, Medium
				Infrastructure, Security, High
				Infrastructure, Password Policy, Medium
				Data Design, Task Table, Medium
				Data Design, Employee Table, Medium
				Data Design, Cross Reference Tables, High
				Data Design, Encryption Policy, High
				Data Access, Write Views, Low
				Data Access, Set Up Users, Low
				Data Access, Set Up Access Policy, Low
			""";
			
		private static String	annTasks = """ 
				Infrastructure, Security, High, In Progress
				Infrastructure, Password Policy,Medium, In Progress
				Research, Cloud solutions, Medium, In Progress
				Data Design, Encryption Policy, High
				Data Design, Project Table, Medium
				Data Access, Write Views,Low, In Progress
			""";
			
		private static String	bobTasks = """ 
				Infrastructure, Security, High, In Progress
				Infrastructure, Password Policy, Medium
				Data Design,Encryption Policy,High
				Data Access,Write Views, Low, In Progress
			""";
			
		private static String	carolTasks = """
				Infrastructure, Logging, High, In Progress
				Infrastructure, DB Access, Medium
				Infrastructure, Password Policy, Medium
				Data Design, Task Table, High
				Data Access, Write Views, Low
			""";

	public void testMethod() {
		String[] str = annTasks.split("\n");
		System.out.print("test method: "+str[2]);
	}
	
	public static Set<Task> getData(String name) {
		
		Set<Task> taskSet = new HashSet<>();
		String user = "ann,bob,carol".contains(name.toLowerCase())?name:"default";
	/*	
	 * These Switch statements is just for my educational purposes
	 * 
	 * ==================================Traditional Switch Statement===========================================================*/
		/*
		switch(name) {
		case "allTask" : 
			System.out.println(this.allTask);
			break;
		case "annTasks":
			System.out.println(this.annTasks);
			break;
		case "bobTasks":
			System.out.println(this.bobTasks);
			break;
		case "carolTasks":
			System.out.println(this.carolTasks);
			break;
		default:
			System.out.println("No option selected");
		}	
		*/
	/* ======================================Enhanced Switch Statement=================================================================	*/
		
		String selectedUserTasks = 	switch(user.toLowerCase()) {
									case "ann" -> annTasks;
									case "bob" -> bobTasks;
									case "carol" -> carolTasks;
									default -> allTask;
								};
								
		String[] userTasks = selectedUserTasks.split("\n");
		for(String userData: userTasks) {
			//System.out.println("userData is :"+userData);
			String[] data = userData.split(",");
			Arrays.asList(data).replaceAll(d -> d.trim());
			//System.out.println("length of Array: "+ data.length);
			
			taskSet.add(new Task(data[0],data[1],Priority.valueOf(data[2].toUpperCase())));
		}
		
		return taskSet;
	}
}
