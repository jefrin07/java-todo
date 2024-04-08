package com.demo.todolist.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.todolist.models.List;

public interface ListsRepository extends JpaRepository<List, Integer> {

}
