package com.project.bookcatalog.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.bookcatalog.entity.Publisher;

public interface PublishersRepo extends JpaRepository<Publisher,Integer>{

}
