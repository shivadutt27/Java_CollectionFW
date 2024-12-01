package dev.duttech;

import java.util.Objects;

public class Task implements Comparable<Task> {
	
	
	enum Status{
		ASSIGNED,IN_PROGRESS,NOT_YET_ASSIGNED;
	}
	
	enum Priority{
		HIGH,LOW,MEDIUM;
	}
	
	private String assignee;
	private String projectName;
	private String description;
	private Status status;
	private Priority priority;
	
	
	public Task(String projectName, String description, String assignee, Priority priority, Status status) {
		this.projectName = projectName;
		this.description = description;
		this.assignee = assignee;
		this.status = status;
		this.priority = priority;
	}
	
	public Task(String projectName, String description, String assignee, Priority priority) {
		this(projectName, description, assignee, priority, assignee == null ? Status.NOT_YET_ASSIGNED: Status.ASSIGNED);
	}
	
	public Task(String projectName, String description, Priority priority, Status status) {
		this(projectName, description, null, priority, status);
	}

	public Task(String projectName, String description, Priority priority) {
		this(projectName,description,priority,null);
	}
	
	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	@Override
	public int compareTo(Task task) {
		int result = this.projectName.compareTo(task.projectName);
		if(result == 0) {
			result = this.description.compareTo(task.description);
		}
		return result;
	}
	
	
	
	
	@Override
	public int hashCode() {
		int result = getProjectName().hashCode();
		result = 31* result+getDescription().hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if(!getProjectName().equals(other.getProjectName())) return false;
		return getDescription().equals(other.getDescription());
	}

	@Override
	public String toString() {
		return "%-20s %-25s  %-10s %n".formatted(projectName,description,priority);
	}
	
}
