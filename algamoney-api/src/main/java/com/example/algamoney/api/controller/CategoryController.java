package com.example.algamoney.api.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.algamoney.api.exception.ResourceNotFoundException;
import com.example.algamoney.api.model.Category;
import com.example.algamoney.api.repository.CategoryRepository;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	@Autowired
	CategoryRepository categoryRepository;
	
	@GetMapping
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Category getCategoryById(@PathVariable Long id){
		return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Categoria", "id", id));
	}
	
	@PostMapping
	public ResponseEntity<Category> postCategory(@Valid @RequestBody Category category) {
		Category savedCategory = categoryRepository.save(category);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(savedCategory.getId()).toUri();
		
		return ResponseEntity.created(uri).body(savedCategory);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Category> putCategory(@PathVariable Long id, @Valid @RequestBody Category updatedCategory){
		return categoryRepository.findById(id).map(category -> {
			category.setName(updatedCategory.getName());
			categoryRepository.save(category);
			return ResponseEntity.ok(category);
		}).orElseThrow(() -> new ResourceNotFoundException("Categoria", "id", id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long id){
		Category category  = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Categoria", "id", id));
		categoryRepository.delete(category);
		return ResponseEntity.noContent().build();
	}
}
