package com.project.bookcatalog.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.bookcatalog.entity.Store;

public interface StoresRepo extends JpaRepository<Store,Integer>{


}
