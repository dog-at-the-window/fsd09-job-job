package com.example.project.service;

import com.example.project.dto.ListingDTO;
import com.example.project.entity.Category;
import com.example.project.entity.Listing;
import com.example.project.mapper.ListingMapperHelper;
import com.example.project.repository.CategoryRepository;
import com.example.project.repository.ListingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListingServiceImpl implements ListingService {

    private final ListingRepository listingRepository;
    private final CategoryRepository categoryRepository;
    private final ListingMapperHelper mapperHelper;

    public ListingServiceImpl(ListingRepository listingRepository, CategoryRepository categoryRepository, ListingMapperHelper mapperHelper) {
        this.listingRepository = listingRepository;
        this.categoryRepository = categoryRepository;
        this.mapperHelper = mapperHelper;
    }

    @Override
    public List<ListingDTO> getAllListings() {
        List<Listing> listings = listingRepository.findAll();
        return mapperHelper.convertListingListToListingDTOList(listings);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void saveListing(ListingDTO listingDTO) {
        Listing listing = mapperHelper.convertListingDTOToListing(listingDTO);
        Category category = getCategoryById(listingDTO.getCategoryId());
        listing.setCategory(category);
        listingRepository.save(listing);
    }

    @Override
    public ListingDTO getListingById(Long id) {
        Optional<Listing> byId = listingRepository.findById(id);
        if(byId.isPresent()) {
            Listing listing = byId.get();
            ListingDTO listingDTO = mapperHelper.convertListingToListingDTO(listing);
            listingDTO.setCategoryId(listing.getCategory().getId());
            return listingDTO;
        }
        throw new RuntimeException("Unable to find listing.");
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()) {
            return category.get();
        }
        throw new RuntimeException("Unable to find category.");
    }

    @Override
    public void deleteListingById(Long id) {
        listingRepository.deleteById(id);
    }
}
