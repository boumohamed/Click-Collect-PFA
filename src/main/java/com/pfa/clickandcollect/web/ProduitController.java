package com.pfa.clickandcollect.web;

import com.pfa.clickandcollect.Entities.Produit;
import com.pfa.clickandcollect.Repositories.ProduitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProduitController {
    private ProduitRepository produitRepository;

    @GetMapping(path = "/produits")
    public String Patients(Model model)
    {
        List<Produit> produits = produitRepository.findAll();

        model.addAttribute("produits", produits);

        return "produits";
    }
}
