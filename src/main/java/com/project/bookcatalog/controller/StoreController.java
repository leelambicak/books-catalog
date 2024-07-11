package com.project.bookcatalog.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import com.project.bookcatalog.entity.Store;
import com.project.bookcatalog.repositry.SalesRepo;
import com.project.bookcatalog.repositry.StoresRepo;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoresRepo storesRepo;

    @Autowired
    private SalesRepo salesRepo;

    @PostMapping("/add")
   // @PreAuthorize("hasRole('ADMIN')")
    // / @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Store> storeEntry(@RequestBody Store stores) {
        Store store = storesRepo.save(stores);
        return new ResponseEntity<>(store, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Store> dispaly() {
        List<Store> stores = storesRepo.findAll();
        return stores;
    }

    @PutMapping("/update/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Store> updatePublisher(@PathVariable("id") int sId, @RequestBody Store store) {
        Optional<Store> s = storesRepo.findById(sId);
        if (s.isPresent()) {
            Store s1 = s.get();
            System.out.println(s1.hashCode());

            if (store.getLocation() != null) {
                s1.setLocation(store.getLocation());
            }
            if (store.getCity() != null) {
                s1.setCity(store.getCity());
            }
            if (store.getCountry() != null) {
                s1.setCountry(store.getCountry());
            }
            storesRepo.save(s1);

            return new ResponseEntity<>(s1, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteTitle(@PathVariable("id") int sId) {
        Optional<Store> s = storesRepo.findById(sId);
        if (s.isPresent()) {
            Store s1 = s.get();
            storesRepo.delete(s1);
            return ResponseEntity.ok("Title with ID : " + sId + " is deleted successfully");
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/top5/stores/titlesold")
    public ResponseEntity<List<Store>> getByPublisher() {
        try {
            List<Integer> sales = salesRepo.findTop5ByTitlesSold();
            List<Store> stores = storesRepo.findAllById(sales);
            return new ResponseEntity<>(stores, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
