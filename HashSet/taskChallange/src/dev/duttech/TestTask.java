package dev.duttech;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestTask {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Set<Task> getTasks = TaskData.getData("Ann");
		//Comparator<Task> sortByPriority = Comparator.comparing(Task::getPriority);
		//sortAndPrint("annTasks", getTasks, sortByPriority);
		
		List<Set<Task>> setTasksList = new ArrayList<>();
		
		List<String> names= List.of("Ann","Bob","Carol","All");
		names.forEach(name -> setTasksList.add(TaskData.getData(name)));
		
		Set<Task> completeSet= completeTaskSet(setTasksList);
		
		Set<Task> tasksNotAssigned = difference(completeSet, TaskData.getData("All"));
		
		Set<Task> intersectTasks = intersectionSet(completeSet, completeTaskSet(List.of(
				TaskData.getData("Ann"), TaskData.getData("Bob"),TaskData.getData("Carol")
				)));
		
		multiAssignedTask(List.of(
				TaskData.getData("Ann"), TaskData.getData("Bob"),TaskData.getData("Carol")
				));
		
//		System.out.println(multiAssignedTask);
		
		
		
//		Task t1 = new Task("Ces","confluence");
//		Task t2 = new Task("Ces","documentation");
//		Task t3 = new Task("Bpm","putty");
//		Task t4 = new Task("Save","FDR");
//		
//		List<Task> tasks = new ArrayList<>(List.of(t1,t2,t3,t4));
//		
//		//tasks.forEach(task -> System.out.println(task));
//		
//		
//		System.out.println("==========================================================");
//		
//		Collections.sort(tasks);
//		
//		//tasks.forEach(task -> System.out.println(task));
//		
//		TaskData td1 = new TaskData();
//		Set<Task> getTask = td1.getData("ann");
//		System.out.println(getTask);

	}
	
	public static Set<Task> completeTaskSet(List<Set<Task>> taskList) {
		Set<Task> unionTask = new HashSet<>();
		
		for(int i=0; i<taskList.size();i++) {
			unionTask.addAll(taskList.get(i));
		}
		return unionTask;
	}
	
	public static Set<Task> difference (Set<Task> a, Set<Task> b){
		Set<Task> finalSet = new HashSet<>(a);
		finalSet.removeAll(b);
		return finalSet;
	}
	
	public static Set<Task> intersectionSet(Set<Task> a, Set<Task> b){
		Set<Task> finalSet = new HashSet<Task>(a);
		finalSet.retainAll(b);
		return finalSet;
	}
	
	
//	public static void intersectTask(List<Set<Task>> taskList, List<String> owners) {
//		
//		for(String owner: owners) {
//			Set<Task> ownerTaskSet = TaskData.getData(owner);
//			for(Set<Task> taskSet: taskList) {
//				if(taskSet.contains(owners))
//			}
//		}
//	}
	
	public static void multiAssignedTask(List<Set<Task>> taskList) {
		int pointer =0;
		Set<Task> finalSet = new HashSet<>();
		List<Set<Task>> finalList = new ArrayList<>();
		
		while(pointer < taskList.size()) {
			for(int i=1; i<taskList.size(); i++) {
				Set<Task> tempTask = intersectionSet(taskList.get(pointer),taskList.get(i));
				finalList.add(tempTask);
			}
			pointer++;
		}
		System.out.println(completeTaskSet(finalList));
	}
	
	
	public static void sortAndPrint(String header, Collection<Task> collection) {
		sortAndPrint(header,collection,null);
	}
	
	public static void sortAndPrint(String header, Collection<Task> collection, Comparator<Task> sorter) {
		String str = "".repeat(90);
		System.out.println(str);
		System.out.println(header);
		System.out.println(str);
		
		List<Task> list = new ArrayList<>(collection);
		list.sort(sorter);
		collection.forEach(System.out::println);
	}

}
