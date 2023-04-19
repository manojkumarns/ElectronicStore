package com.lcwd.electronic.store.repositories;

import com.lcwd.electronic.store.entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, String> {

    List<Category>  findByTitleContaining(String keyword);
}
