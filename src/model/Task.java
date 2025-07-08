package model;

import java.time.LocalDate;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;

public class Task implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String description;
	private boolean completed;
	private LocalDate deadline;
	
	public Task(int id, String description, LocalDate deadline) {
		this.id = id;
		this.description = description;
		this.deadline = deadline;
		this.completed = false;
	}
	
	public void markCompleted() {
		this.completed = true;
	}
	
	public boolean isCompleted() {
		return this.completed;
	}

	public int getId() {
		return id;
	}
	
  public String getDescription() {
      return description;
  }

  public LocalDate getDeadline() {
      return deadline;
  }

  @Override
  public String toString() {
      String status = completed ? "✓" : "✗";
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
      return String.format("[%d] %s - %s (Deadline: %s)", 
                          id, status, description, deadline.format(formatter));
  }
}
