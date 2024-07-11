package com.project.bookcatalog.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.bookcatalog.entity.TitleAuthor;
import com.project.bookcatalog.entity.TitleAuthorPk;

@Repository
public interface TitleAuthorsRepo extends JpaRepository<TitleAuthor,TitleAuthorPk> {

    @Query(value = "SELECT title_id FROM title_author WHERE au_id = :auId", nativeQuery = true)
    List<Integer> findAllTitlesByAuId(@Param("auId")int auId);

    List<TitleAuthor> findAllByTitle_TitleId(int tid);

}
