package com.example.project.mapper;

import com.example.project.dto.ListingDTO;
import com.example.project.entity.Listing;
import com.example.project.repository.CategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListingMapperHelper {

    private final ObjectMapper mapper;

    @Autowired
    public ListingMapperHelper(CategoryRepository categoryRepository, ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public List<ListingDTO> convertListingListToListingDTOList (List<Listing> listings) {
        List<ListingDTO> listingDTOs = new ArrayList<>();
        for(Listing listing : listings) {
            listingDTOs.add(mapper.convertValue(listing, ListingDTO.class));
        }
        return listingDTOs;
    }


    public ListingDTO convertListingToListingDTO (Listing listing) {
        return mapper.convertValue(listing, ListingDTO.class);
    }

    public Listing convertListingDTOToListing (ListingDTO listingDTO) {
        return mapper.convertValue(listingDTO, Listing.class);
    }
}
