package service;

import model.Task;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TaskService {
	private List<Task> tasks = new ArrayList<>();
	private int currentId = 1;
	
	public void addTask(String description, LocalDate deadline) {
		tasks.add(new Task(currentId++, description, deadline));
		System.out.println("Task added succesfully!");
	}
	
	public List<Task> getAllTasks(){
		return tasks;
	}
	
	public List<Task> getPendingTasks(){
		return tasks.stream().filter(t -> !t.isCompleted()).collect(Collectors.toList());
	}
	
	public boolean markDone(int id) {
		Optional<Task> task = tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst();
		if (task.isPresent()) {
			task.get().markCompleted();
			System.out.println("Task  with ID " + id + " completed!");
			return true;
		} else {
			System.out.println("Task with ID " + id + " wasn't found!");
			return false;
		}
	}
}
