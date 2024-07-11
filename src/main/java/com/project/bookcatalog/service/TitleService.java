package com.project.bookcatalog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.bookcatalog.dto.TitleResponseDto;
import com.project.bookcatalog.entity.Title;

@Service
public class TitleService {
    
    public List<TitleResponseDto> findByTitle(List<Title> titles) {
        List<TitleResponseDto> titleResponseDtos = new ArrayList<>();
        for (Title t : titles) {
            TitleResponseDto trd = new TitleResponseDto();
            trd.setPrice(t.getPrice());
            trd.setPubId(t.getPublishers().getPubId());
            trd.setReleaseYear(t.getReleaseYear());
            trd.setTitle(t.getTitle());
            trd.setYtdSales(t.getYtdSales());
            trd.setTitleId(t.getTitleId());

            titleResponseDtos.add(trd);
        }
        return titleResponseDtos;
    }
}
