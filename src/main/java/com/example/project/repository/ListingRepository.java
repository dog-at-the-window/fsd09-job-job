package com.example.project.repository;

import com.example.project.entity.Category;
import com.example.project.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingRepository extends JpaRepository<Listing, Long> {
}
