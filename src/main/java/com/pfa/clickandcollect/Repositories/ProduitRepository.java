package com.pfa.clickandcollect.Repositories;

import com.pfa.clickandcollect.Entities.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
    Page<Produit> findByNomPrdContains(String kw, Pageable pageable);
}
