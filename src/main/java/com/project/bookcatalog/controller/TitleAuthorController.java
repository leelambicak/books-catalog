package com.project.bookcatalog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bookcatalog.dto.TitleAuthorDto;
import com.project.bookcatalog.entity.Author;
import com.project.bookcatalog.entity.Title;
import com.project.bookcatalog.entity.TitleAuthor;
import com.project.bookcatalog.entity.TitleAuthorPk;
import com.project.bookcatalog.repositry.AuthorsRepo;
import com.project.bookcatalog.repositry.TitleAuthorsRepo;
import com.project.bookcatalog.repositry.TitlesRepo;



@RestController
@RequestMapping("/titleauthor")
public class TitleAuthorController {

    @Autowired
    private TitleAuthorsRepo titleAuthorsRepo;
    @Autowired
    private TitlesRepo titlesRepo;
    @Autowired
    private AuthorsRepo authorsRepo;

    @PostMapping("/add")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addTitleAuthor(@RequestBody TitleAuthor titleAuthor) {
        try {
            Title title = titlesRepo.findById(titleAuthor.getTitleAuthorsPk().getTitleId()).orElse(null);
            Author author = authorsRepo.findById(titleAuthor.getTitleAuthorsPk().getAuId()).orElse(null);
            if (title == null || author == null) {
                return new ResponseEntity<>("Author id or Title id not found", HttpStatus.NOT_FOUND);
            }
            titleAuthor.setTitle(title);
            titleAuthor.setAuthor(author);
            TitleAuthor newTitleAuthor = titleAuthorsRepo.save(titleAuthor);
            return new ResponseEntity<>(newTitleAuthor, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
    @PutMapping("/update/{titleid}/{auid}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateSale(@PathVariable int titleid, @PathVariable int auid,@RequestBody TitleAuthor updatedTitleAuthor) {
        try {
            TitleAuthorPk titleAuthorPk = new TitleAuthorPk(titleid, auid);
            TitleAuthor titleAuthor = titleAuthorsRepo.findById(titleAuthorPk).orElse(null);
            if (titleAuthor == null) {
                return new ResponseEntity<>("Title or Author not found", HttpStatus.NOT_FOUND);
            }
            titleAuthor.setRoyaltyPct(updatedTitleAuthor.getRoyaltyPct());
            TitleAuthor updatedRoyalityPct = titleAuthorsRepo.save(updatedTitleAuthor);
            return new ResponseEntity<>(updatedRoyalityPct, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{titleid}/{auid}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteSale(@PathVariable int titleid, @PathVariable int auid) {
        try {
            TitleAuthorPk titleAuthorPk = new TitleAuthorPk(titleid, auid);
            TitleAuthor titleAuthor = titleAuthorsRepo.findById(titleAuthorPk).orElse(null);
            if (titleAuthor == null) {
                return new ResponseEntity<>("Title or Author not found", HttpStatus.NOT_FOUND);
            }
            titleAuthorsRepo.delete(titleAuthor);
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/all")
    public List<TitleAuthor> getAllTitleAuthors() {
        return titleAuthorsRepo.findAll();
    }


    @GetMapping("/auname/pubname/{tid}")
    public ResponseEntity<?> getAll(@PathVariable int tid) {
        try {
            List<TitleAuthor> titleAuthors = titleAuthorsRepo.findAllByTitle_TitleId(tid);
            List<TitleAuthorDto> titleAuthorDtos = new ArrayList<>();
            for (TitleAuthor t : titleAuthors) {
                TitleAuthorDto tad = new TitleAuthorDto();
                tad.setTitle(t.getTitle().getTitle());
                tad.setPrice(t.getTitle().getPrice());
                tad.setYtdSales(t.getTitle().getYtdSales());
                tad.setAuName(t.getAuthor().getAuName());
                tad.setPubName(t.getTitle().getPublishers().getPubName());
                titleAuthorDtos.add(tad);
            }
            return new ResponseEntity<>(titleAuthorDtos, HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}