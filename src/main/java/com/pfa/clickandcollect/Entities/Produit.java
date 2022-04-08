package com.pfa.clickandcollect.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 6, max = 20)
    private String nomPrd;

    @DecimalMin("10")
    private double prixUt;

    @NotEmpty
    private String description;

    @NotEmpty
    private String image;

    @ManyToOne()
    private Categorie cat;
}
