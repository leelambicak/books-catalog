package com.project.bookcatalog.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.bookcatalog.entity.Sale;
import com.project.bookcatalog.entity.SalePk;

public interface SalesRepo extends JpaRepository<Sale,SalePk>{

    @Query(value="SELECT TOP 5 store_id FROM sale GROUP BY store_id ORDER BY sum(qty_sold) DESC",nativeQuery=true)
    List<Integer> findTop5ByTitlesSold();

    @Query(value = "SELECT title_id from sale WHERE store_id = :storeid",nativeQuery = true)
    List<Integer> findAllTitleIdByStoreId(@Param("storeid")int storeid);

    List<Sale> findAllByTitles_TitleId(int tid);

}
