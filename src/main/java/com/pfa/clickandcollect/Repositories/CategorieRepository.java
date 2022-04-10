package com.pfa.clickandcollect.Repositories;

import com.pfa.clickandcollect.Entities.Categorie;
import com.pfa.clickandcollect.Entities.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {

    Page<Categorie> findByDesignationContains(String kw, Pageable pageable);
}
