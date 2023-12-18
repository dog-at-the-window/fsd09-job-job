package com.example.project.controller;

import com.example.project.dto.ListingDTO;
import com.example.project.entity.Category;
import com.example.project.entity.Listing;
import com.example.project.mapper.ListingMapperHelper;
import com.example.project.service.ListingService;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.project.entity.User;
import com.example.project.dto.UserDto;
import com.example.project.service.UserService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {
    private final UserService userService;
    private final ListingService listingService;

    public IndexController(UserService userService, ListingService listingService, ListingMapperHelper mapperHelper) {
        this.userService = userService;
        this.listingService = listingService;
    }

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "Duplicate Account");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/listings/")
    public String showUserListings(Model model) {
        List<ListingDTO> listings = listingService.getAllListings();
        model.addAttribute("listings", listings);
        return "job-listing"; }

    @GetMapping("/listings/new")
    public String showFormForAdd(Model model) {
        ListingDTO listing = new ListingDTO();
        List<Category> categories = listingService.getAllCategories();
        model.addAttribute("listing", listing);
        model.addAttribute("categories", categories);
        return "listing-form";
    }

    @PostMapping("/listings/upsert")
    public String upsertListing(@Valid @ModelAttribute("listing") ListingDTO listingDTO,
                                BindingResult result, Model model) {
        if(result.hasErrors()){
            List<Category> categories = listingService.getAllCategories();
            model.addAttribute("categories", categories);
            return "listing-form";
        }
        listingService.saveListing(listingDTO);
        return "redirect:/listings/";
    }

    @GetMapping("/listings/update")
    public String showFormForUpdate(@RequestParam("id") Long id, Model model) {
        try{
            ListingDTO listing = listingService.getListingById(id);
            List<Category> categories = listingService.getAllCategories();
            model.addAttribute("listing", listing);
            model.addAttribute("categories", categories);
            return "listing-form";
        }
        catch (RuntimeException exception){
            List<Category> categories = listingService.getAllCategories();
            model.addAttribute("listing", null);
            model.addAttribute("categories", categories);
            model.addAttribute("exceptionMessage", "There was an error when attempting to update listing.");
            return "listing-form";
        }
    }

    @GetMapping("/listings/delete")
    public String delete(@RequestParam("id") Long id) {
        listingService.deleteListingById(id);
        return "redirect:/listings/";
    }
}
