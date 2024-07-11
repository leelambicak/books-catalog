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

import com.project.bookcatalog.entity.Author;
import com.project.bookcatalog.repositry.AuthorsRepo;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorsRepo authorsRepo;

    @PostMapping("/add")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Author> authorEntry(@RequestBody Author authors) {
        if (authors != null) {
            Author author = authorsRepo.save(authors);
            return new ResponseEntity<>(author, HttpStatus.CREATED);
        }
        throw new IllegalArgumentException("Author name cannot be null or empty");
    }

    @GetMapping("/all")
    public List<Author> dispaly() {
        List<Author> authors = authorsRepo.findAll();
        return authors;
    }

    @PutMapping("/update/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Author> updatePublisher(@PathVariable("id") int aId, @RequestBody Author author) {
        Optional<Author> a = authorsRepo.findById(aId);
        if (a.isPresent()) {
            Author a1 = a.get();

            if (author.getAuName() != null) {
                a1.setAuName(author.getAuName());
            }
            if (author.getEmail() != null) {
                a1.setEmail(author.getEmail());
            }
            if (author.getMobile() != null) {
                a1.setMobile(author.getMobile());
            }
            if (author.getCity() != null) {
                a1.setCity(author.getCity());
            }
            if (author.getCountry() != null) {
                a1.setCountry(author.getCountry());
            }
            authorsRepo.save(a1);

            return new ResponseEntity<>(a1, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteTitle(@PathVariable("id") int aId) {
        Optional<Author> a = authorsRepo.findById(aId);
        if (a.isPresent()) {
            Author a1 = a.get();
            authorsRepo.delete(a1);
            return ResponseEntity.ok("Title with ID : " + aId + " is deleted successfully");
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
