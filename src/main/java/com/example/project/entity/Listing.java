package com.example.project.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "listing")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name="job_title")
    private String jobTitle;

    @NotBlank
    @Column(name="job_description")
    private String jobDescription;

    @NotNull
    @Column(name="salary")
    private Double salary;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="category", referencedColumnName = "id")
    private Category category;
}
