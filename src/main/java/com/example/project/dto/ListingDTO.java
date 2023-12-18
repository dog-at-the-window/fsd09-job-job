package com.example.project.dto;

import com.example.project.entity.Category;
import com.example.project.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListingDTO {
    private Long id;

    @NotEmpty(message = "Title cannot be empty.")
    private String jobTitle;

    @NotEmpty(message = "Job description cannot be empty.")
    private String jobDescription;

    @NotNull(message = "Salary cannot be null.")
    @Positive
    private Double salary;

    private Long categoryId;
}
