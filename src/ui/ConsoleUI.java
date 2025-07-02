package ui;

import service.TaskService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
	private TaskService service = new TaskService();
	private Scanner scanner = new Scanner(System.in);
	
	public void runService() {
		System.out.println("=== Task Manager ===");
		while(true) {
			showMenu();
			try {
				int option = Integer.parseInt(scanner.nextLine().trim());
				switch(option) {
					case 1 -> addTask();
					case 2 -> showAllTasks();
					case 3 -> showPendingTasks();
					case 4 -> markDone();
					case 0 -> {
						System.out.println("Bye!");
						return;
					}
					default -> System.out.println("Invalid! Pick a number between 0 and 4");
				}
			} catch (NumberFormatException e) {
				System.out.println("Please select a valid number");
			}
		}
	}
	
	private void showMenu() {
		System.out.println("\n=== Menu ===");
        System.out.println("\n1. Add Task\n2. Show All\n3. Show Pending\n4. Mark Done\n0. Exit");
        System.out.println("Select an option:");
    }
	
	private void addTask() {
		try {
			System.out.println("Description: ");
			String desc = scanner.nextLine().trim();
			
			if (desc.isEmpty()) {
	            System.out.println("Description is empty!");
	            return;
	        }
			
			System.out.println("Deadline (YYYY-MM-DD): ");
			LocalDate date = LocalDate.parse(scanner.nextLine().trim());
			service.addTask(desc, date);
		} catch (DateTimeParseException e) {
			System.out.println("Invalid date format!");
		}
	}
	
	private void showAllTasks() {
		List<Task> tasks = service.getAllTasks();
        if (tasks.isEmpty()) {
            System.out.println("\nThere are no tasks!");
        } else {
            System.out.println("\n=== All Tasks ===");
            tasks.forEach(System.out::println);
        }
	}
	
	private void showPendingTasks() {
        List<Task> pendingTasks = service.getPendingTasks();
        if (pendingTasks.isEmpty()) {
            System.out.println("\nNo pending tasks!");
        } else {
            System.out.println("\n=== Pending Tasks ===");
            pendingTasks.forEach(System.out::println);
        }
    }
	
	private void markDone() {
		List<Task> pendingTasks = service.getPendingTasks();
        if (pendingTasks.isEmpty()) {
            System.out.println("\nNo tasks to be completed!");
            return;
        }

        System.out.println("\nTasks:");
        pendingTasks.forEach(System.out::println);
        System.out.println("\n");
        
        try {
            System.out.print("Pick a task's ID you completed: \n");
            int id = Integer.parseInt(scanner.nextLine().trim());
            service.markDone(id);
        } catch (NumberFormatException e) {
            System.out.println("Pick a valid number!\n");
        }
	}
}
