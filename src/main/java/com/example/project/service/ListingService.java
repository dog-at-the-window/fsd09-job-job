package com.example.project.service;

import com.example.project.dto.ListingDTO;
import com.example.project.entity.Category;

import java.util.List;

public interface ListingService {

    List<ListingDTO> getAllListings();

    List<Category> getAllCategories();

    void saveListing(ListingDTO listingDTO);

    ListingDTO getListingById(Long id);

    Category getCategoryById(Long id);

    void deleteListingById(Long id);
}
