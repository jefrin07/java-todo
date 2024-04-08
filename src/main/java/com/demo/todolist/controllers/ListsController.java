package com.demo.todolist.controllers;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.todolist.models.ListDto;
import com.demo.todolist.services.ListsRepository;


import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;


@Controller
public class ListsController {
    @Autowired
    private ListsRepository repo;
    
    
    @GetMapping({"/all"})
    public String showList(Model model) {
        List<com.demo.todolist.models.List> lists = repo.findAll();
        model.addAttribute("lists", lists);
        return "lists/list"; 
    }
    
    @GetMapping("lists/create") 
    public String createListPage(Model model) {
        ListDto listDto = new ListDto();
        model.addAttribute("listDto", listDto);
        return "lists/create";
    }
    
    @PostMapping("lists/create")
    public String createList(
            @ModelAttribute ListDto listDto,
            BindingResult result
            ) {
        if (result.hasErrors()) {
            return "lists/create";
        }
        
        LocalDateTime createdAt = LocalDateTime.now();
        
        com.demo.todolist.models.List list = new com.demo.todolist.models.List();
        list.setName(listDto.getName());
        list.setDescription(listDto.getDescription());
        list.setCreatedAt(createdAt);
        repo.save(list);
        return "redirect:/all"; 
    }
    
    @GetMapping("lists/edit") 
    public String showEditPage(Model model,
        @RequestParam int id) {
        try {
            com.demo.todolist.models.List list = repo.findById(id).get();
            model.addAttribute("list", list);
            
            ListDto listDto = new ListDto();
            listDto.setName(list.getName());
            listDto.setDescription(list.getDescription());
            model.addAttribute("listDto", listDto);
            
        } catch (Exception e) {
            System.out.println("Errors");
            return "redirect:/all";
        }
        return "lists/edit";
    }
    @PostMapping("lists/edit")
    public String editList(Model model,
                           @RequestParam int id,
                           @ModelAttribute ListDto listDto,
                           BindingResult result) {
        try {
            com.demo.todolist.models.List list = repo.findById(id).orElse(null); // Fetch list or null if not found
            model.addAttribute("list", list);
            if (result.hasErrors()) {
                return "lists/edit";
            }

            // Update list properties
            list.setName(listDto.getName());
            list.setDescription(listDto.getDescription());
            repo.save(list);
            return "redirect:/all"; // Redirect to home page or any other appropriate page
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            // Handle the exception gracefully, for example, by returning an error view
            return "error";
        }
    }
    
    @GetMapping("lists/delete") 
    public String deletePage(Model model,
        @RequestParam int id) {
            com.demo.todolist.models.List list = repo.findById(id).get();
            repo.delete(list);
            return "redirect:/all";
    }

}
