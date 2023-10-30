package com.kanak.ims.repository;

import com.kanak.ims.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select * from category where category_name = :name",nativeQuery = true)
    Category findByCategoryName(@Param("name") String name);
}
