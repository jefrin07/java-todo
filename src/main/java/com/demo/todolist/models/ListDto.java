package com.demo.todolist.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ListDto {
	@NotEmpty(message = "The Name is required")
	private String name;
	
	@Size(min = 10,message = "Enter atleast 10 characters")
	@Size(max = 2000,message = "Cannot exceed 2000 characters")
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
