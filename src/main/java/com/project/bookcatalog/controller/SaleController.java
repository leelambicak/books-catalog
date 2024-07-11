package com.project.bookcatalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.bookcatalog.entity.Sale;
import com.project.bookcatalog.entity.SalePk;
import com.project.bookcatalog.entity.Store;
import com.project.bookcatalog.entity.Title;
import com.project.bookcatalog.repositry.SalesRepo;
import com.project.bookcatalog.repositry.StoresRepo;
import com.project.bookcatalog.repositry.TitlesRepo;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SalesRepo salesRepo;

    @Autowired
    private TitlesRepo titlesRepo;

    @Autowired
    private StoresRepo storesRepo;

    @PostMapping("/add")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> saleEntry(@RequestBody Sale sale) {
        try {
            Title title = titlesRepo.findById(sale.getSalesPk().getTitleId()).orElse(null);
            Store store = storesRepo.findById(sale.getSalesPk().getStoreId()).orElse(null);
            if (title == null || store == null) {
                return new ResponseEntity<>("Title or Store not found", HttpStatus.NOT_FOUND);
            }
            sale.setTitles(title);
            sale.setStores(store);
            Sale newSale = salesRepo.save(sale);
            return new ResponseEntity<>(newSale, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{titleid}/{storeid}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateSale(@PathVariable int titleid, @PathVariable int storeid,@RequestBody Sale updatedSale) {
        try {
            SalePk salePk = new SalePk(titleid, storeid);
            Sale sale = salesRepo.findById(salePk).orElse(null);
            if (sale == null) {
                return new ResponseEntity<>("Sale not found", HttpStatus.NOT_FOUND);
            }
            sale.setQtySold(updatedSale.getQtySold());
            Sale updatedSaleEntity = salesRepo.save(sale);
            return new ResponseEntity<>(updatedSaleEntity, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{titleid}/{storeid}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteSale(@PathVariable int titleid, @PathVariable int storeid,@RequestBody Sale deletesale) {
        try {
            SalePk salePk = new SalePk(titleid, storeid);

            Sale sale = salesRepo.findById(salePk).orElse(null);
            if (sale == null) {
                return new ResponseEntity<>("Sale not found", HttpStatus.NOT_FOUND);
            }
            salesRepo.delete(sale);
            return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/all")
    public List<Sale> getAllSales() {
        return salesRepo.findAll();
    }

    @GetMapping("/bytitle/{title}")
    public ResponseEntity<?> getSaleByTitle(@RequestParam String title) {
        try {
            int tid = titlesRepo.findTitleIdByTitle(title);
            List<Sale> sales = salesRepo.findAllByTitles_TitleId(tid);
            return new ResponseEntity<>(sales, HttpStatus.OK);
        }catch (NullPointerException e) {
            return new ResponseEntity<>("Given title not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
