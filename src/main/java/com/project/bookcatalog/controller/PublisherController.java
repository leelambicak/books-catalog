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

import com.project.bookcatalog.entity.Publisher;
import com.project.bookcatalog.repositry.PublishersRepo;

@RestController
@RequestMapping("/publisher")
public class PublisherController {

    @Autowired
    private PublishersRepo publisherRepo;

    @PostMapping("/add")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Publisher> publisherEntry(@RequestBody Publisher publishers) {
        Publisher publisher = publisherRepo.save(publishers);
        return new ResponseEntity<>(publisher, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable("id") int pId, @RequestBody Publisher publisher) {
        Optional<Publisher> p = publisherRepo.findById(pId);
        if (p.isPresent()) {
            Publisher p1 = p.get();

            if (publisher.getPubName() != null) {
                p1.setPubName(publisher.getPubName());
            }
            if (publisher.getEmail() != null) {
                p1.setEmail(publisher.getEmail());
            }

            if (publisher.getCity() != null) {
                p1.setCity(publisher.getCity());
            }
            if (publisher.getCountry() != null) {
                p1.setCountry(publisher.getCountry());
            }
            publisherRepo.save(p1);

            return new ResponseEntity<>(p1, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deletePublisher(@PathVariable("id") int pId) {
        Optional<Publisher> p = publisherRepo.findById(pId);
        if (p.isPresent()) {
            Publisher p1 = p.get();
            publisherRepo.delete(p1);
            return ResponseEntity.ok("Title with ID : " + pId + " is deleted successfully");
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    @GetMapping("/all")
    public List<Publisher> dispaly() {
        List<Publisher> publishers = publisherRepo.findAll();
        return publishers;
    }
}
