package com.oracle.app.eventticketsapp.controllers;

import com.oracle.app.eventticketsapp.dtos.category.CategoryDTO;
import com.oracle.app.eventticketsapp.services.EventTicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author {ANAS DR}
 **/
@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class CategoryRestController {
    private EventTicketService eventTicketService;

    @GetMapping("/categories")
    public List<CategoryDTO> getCategories() {
        return eventTicketService.getCategories();
    }

    @PostMapping("/categories")
    public CategoryDTO saveCategory(@RequestBody CategoryDTO category) {
        return eventTicketService.saveCategory(category);
    }

    @PutMapping("/categories/{id}")
    public CategoryDTO updateCategory(@PathVariable String id, @RequestBody CategoryDTO category) {
        return eventTicketService.updateCategory(id,category);
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable String id) {
        eventTicketService.deleteCategory(id);
    }
}
