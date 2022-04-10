package com.pfa.clickandcollect.web;

import com.pfa.clickandcollect.Entities.Produit;
import com.pfa.clickandcollect.Repositories.CategorieRepository;
import com.pfa.clickandcollect.Repositories.ProduitRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class ProduitController {
    private ProduitRepository produitRepository;
    private CategorieRepository categorieRepository;

    @GetMapping(path = "/produits")
    public String Produits(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "6") int size,
                           @RequestParam(name = "keyword", defaultValue = "") String keyword)
    {
        Page<Produit> pageproduits = produitRepository.findByNomPrdContains(keyword, PageRequest.of(page,size));

        model.addAttribute("produits", pageproduits.getContent());
        model.addAttribute("pages", new int[pageproduits.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "produits";
    }


    @GetMapping(path = "/saveproduit")
    public String Patients(Model model)
    {

        model.addAttribute("produit", new Produit());
        model.addAttribute("categories", categorieRepository.findAll());
        return "ajouterProduit";
    }


    @GetMapping("/delete")
    public String delete(Long id){
        produitRepository.deleteById(id);
        return "redirect:/produits";
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/produits";
    }

}
