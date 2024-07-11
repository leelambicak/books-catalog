package com.project.bookcatalog.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.bookcatalog.entity.Author;

public interface AuthorsRepo extends JpaRepository<Author,Integer>{


}
