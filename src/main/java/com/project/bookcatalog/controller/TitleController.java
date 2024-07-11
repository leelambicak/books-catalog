package com.project.bookcatalog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.project.bookcatalog.dto.TitleResponseDto;
import com.project.bookcatalog.entity.Publisher;
import com.project.bookcatalog.entity.Title;
import com.project.bookcatalog.repositry.PublishersRepo;
import com.project.bookcatalog.repositry.SalesRepo;
import com.project.bookcatalog.repositry.TitleAuthorsRepo;
import com.project.bookcatalog.repositry.TitlesRepo;
import com.project.bookcatalog.service.TitleService;

@RestController
@RequestMapping("/title")
@CrossOrigin(origins = "http://localhost:4200")
public class TitleController {

    @Autowired
    private TitlesRepo titlesRepo;

    @Autowired
    private TitleAuthorsRepo titleAuthorsRepo;

    @Autowired
    private PublishersRepo publishersRepo;

    @Autowired
    private SalesRepo salesRepo;

    @Autowired
    private TitleService titleService;
    
    
    @PostMapping("/add")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addTitle(@RequestBody Title title) {
        try {
            if (title.getPublishers() != null && title.getPublishers().getPubId() == 0) {
                Publisher publisher = publishersRepo.save(title.getPublishers());
                title.setPublishers(publisher);
            }
            titlesRepo.save(title);
            return new ResponseEntity<>("Title added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

  

    @PutMapping("/update/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Title> updateTitles(@PathVariable("id") int tId, @RequestBody Title title) {
        Optional<Title> t = titlesRepo.findById(tId);
        if (t.isPresent()) {
            Title t1 = t.get();

            if (title.getTitle() != null) {
                t1.setTitle(title.getTitle());
            }
            if (title.getPrice() != 0) {
                t1.setPrice(title.getPrice());
            }
            if (title.getYtdSales() != 0) {
                t1.setYtdSales(title.getYtdSales());
            }
            if (title.getReleaseYear() != 0) {
                t1.setReleaseYear(title.getReleaseYear());
            }
            if (title.getPublishers() != null && title.getPublishers().getPubId() != 0) {
                int pubid = title.getPublishers().getPubId();
                Optional<Publisher> existingPublisher = publishersRepo.findById(pubid);
                if (existingPublisher.isPresent()) {
                    t1.setPublishers(existingPublisher.get());
                } else {
                    Publisher newPublisher = publishersRepo.save(title.getPublishers());
                    t1.setPublishers(newPublisher);
                }
            }

            titlesRepo.save(t1);

            return new ResponseEntity<>(t1, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteTitle(@PathVariable("id") int tId) {
        Optional<Title> t = titlesRepo.findById(tId);
        if (t.isPresent()) {
            Title t1 = t.get();
            titlesRepo.delete(t1);
            return ResponseEntity.ok("Title with ID : " + tId + " is deleted successfully");
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public List<TitleResponseDto> dispaly() {
        List<Title> titles = titlesRepo.findAll();
        List<TitleResponseDto> titleResponseDtos = titleService.findByTitle(titles);
        return titleResponseDtos;
    }

    @GetMapping("/byauthor/{auId}")
    public ResponseEntity<?> getTitles(@RequestParam int auId) {
        try {
            List<Integer> tId = titleAuthorsRepo.findAllTitlesByAuId(auId);
            List<Title> titles = titlesRepo.findAllById(tId);
            if (titles.isEmpty()) {
                return new ResponseEntity<>("The given Author id is not found", HttpStatus.NOT_FOUND);
            } else {
                List<TitleResponseDto> titleResponseDtos = titleService.findByTitle(titles);
                return new ResponseEntity<>(titleResponseDtos, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/bypublishers/{pid}")
    public ResponseEntity<?> getByPublisher(@PathVariable int pid) {
        try {
            Publisher publisher = publishersRepo.findById(pid).orElse(null);
            List<Title> titles = titlesRepo.findAllByPublishers(publisher);
            if (titles.isEmpty()) {
                return new ResponseEntity<>("Titles with given publisher Id is not found", HttpStatus.NOT_FOUND);
            } else {
                List<TitleResponseDto> titleResponseDtos = titleService.findByTitle(titles);
                return new ResponseEntity<>(titleResponseDtos, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/matchingtitle/{title}")
    public ResponseEntity<?> matchingTitle(@PathVariable String title) {
        try {
            List<Title> titles = titlesRepo.findAllByTitleContaining(title);
            if (titles.isEmpty()) {
                return new ResponseEntity<>("No matching titles found", HttpStatus.NOT_FOUND);
            } else {
                List<TitleResponseDto> titleResponseDtos = titleService.findByTitle(titles);
                return new ResponseEntity<>(titleResponseDtos, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pricerange/{min}/{max}")
    public ResponseEntity<?> priceBetween(@PathVariable int min, @PathVariable int max) {
        try {
            List<Title> titles = titlesRepo.findByPriceBetween(min, max);
            if (titles.isEmpty()) {
                return new ResponseEntity<>("No Titles found for given range", HttpStatus.NOT_FOUND);
            } else {
                List<TitleResponseDto> titleResponseDtos = titleService.findByTitle(titles);
                return new ResponseEntity<>(titleResponseDtos, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/toptitles/byytdsales")
    public ResponseEntity<?> topTitlesByYtdsales() {
        List<Title> titles = titlesRepo.findTop5ByOrderByYtdSalesDesc();
        List<TitleResponseDto> titleResponseDtos = titleService.findByTitle(titles);
        return new ResponseEntity<>(titleResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/all/bystoreid/{storeid}")
    public ResponseEntity<?> getSoldTitles(@PathVariable int storeid) {
        try {
            List<Integer> tids = salesRepo.findAllTitleIdByStoreId(storeid);
            List<Title> titles = titlesRepo.findAllById(tids);
            if (titles.isEmpty()) {
                return new ResponseEntity<>("Cannot find the given store id", HttpStatus.NOT_FOUND);
            } else {
                List<TitleResponseDto> titleResponseDtos = titleService.findByTitle(titles);
                return new ResponseEntity<>(titleResponseDtos, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
