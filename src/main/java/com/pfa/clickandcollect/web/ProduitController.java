package com.pfa.clickandcollect.web;

import com.pfa.clickandcollect.Entities.Produit;
import com.pfa.clickandcollect.Repositories.ProduitRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProduitController {
    private ProduitRepository produitRepository;

    @GetMapping(path = "/produits")
    public String Patients(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "5") int size,
                           @RequestParam(name = "keyword", defaultValue = "") String keyword)
    {
        Page<Produit> pageproduits = produitRepository.findByNomPrdContains(keyword, PageRequest.of(page,size));

        model.addAttribute("produits", pageproduits.getContent());
        model.addAttribute("pages", new int[pageproduits.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "produits";
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
