package com.demo.todolist.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name="lists")
public class List {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	private Timestamp createdAt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public LocalDateTime getCreatedAt() {
        return createdAt.toLocalDateTime();
	}
	public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = java.sql.Timestamp.valueOf(createdAt);
    }
	
	
}
