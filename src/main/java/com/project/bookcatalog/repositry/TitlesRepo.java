package com.project.bookcatalog.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.bookcatalog.entity.Publisher;
import com.project.bookcatalog.entity.Title;

public interface TitlesRepo extends JpaRepository<Title,Integer>{

    List<Title> findAllByPublishers(Publisher publisher);

    

    List<Title> findByPriceBetween(int min, int max);

    List<Title> findTop5ByOrderByYtdSalesDesc();

    @Query(value ="SELECT title_id from title WHERE title = :title",nativeQuery = true)
    int findTitleIdByTitle(@Param("title")String title);

    List<Title> findAllByTitleContaining(String title);

}
