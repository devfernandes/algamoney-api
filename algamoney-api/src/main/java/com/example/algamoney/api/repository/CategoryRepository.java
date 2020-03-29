package com.example.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamoney.api.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> { }
