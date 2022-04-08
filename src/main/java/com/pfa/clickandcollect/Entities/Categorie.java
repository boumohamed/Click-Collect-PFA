package com.pfa.clickandcollect.Entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String designation;

    @OneToMany(mappedBy = "cat", fetch = FetchType.LAZY)
    private Collection<Produit> produits;
}
