package service;

import model.Task;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

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

	public boolean serializeTasksToFile(String filename) {
		try (FileOutputStream fileOut = new FileOutputStream(filename);
			 BufferedOutputStream bufferOut = new BufferedOutputStream(fileOut);
			 ObjectOutputStream objectOut = new ObjectOutputStream(bufferOut)) {
			
			objectOut.writeObject(tasks);
			System.out.println("Tasks saved successfully to: " + filename);
			return true;
		} catch (Exception e) {
			System.out.println("Error saving tasks: " + e.getMessage());
			return false;
		}
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
