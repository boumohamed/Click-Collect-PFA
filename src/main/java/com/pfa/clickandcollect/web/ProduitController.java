package com.pfa.clickandcollect.web;

import com.pfa.clickandcollect.Entities.Produit;
import com.pfa.clickandcollect.Repositories.CategorieRepository;
import com.pfa.clickandcollect.Repositories.ProduitRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class ProduitController {
    private ProduitRepository produitRepository;
    private CategorieRepository categorieRepository;

    @GetMapping(path = "/produits")
    public String Patients(Model model,
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

    @GetMapping("/produitClients")
    public String produitClients(Model model,@RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "8") int size,
                                @RequestParam(name = "keyword", defaultValue = "") String keyword){

        Page<Produit> pageproduits = produitRepository.findByNomPrdContains(keyword, PageRequest.of(page,size));

        model.addAttribute("produits", pageproduits.getContent());
        model.addAttribute("pages", new int[pageproduits.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "produitClients";
    }

    @GetMapping("/detail")
    public String detail(Model model,Long id){
        Produit produit = produitRepository.findById(id).orElse(null);
        model.addAttribute("produits",produit);
        return "detailproduct";
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

    @PostMapping(path = "/saveproduit")
    public String saveproduit(Model model, @Valid Produit produit, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "update";
        produitRepository.save(produit);
        return "produits";
    }

    @GetMapping("/update")
    public String update(Model model, Long id, String keyword, int page){
        Produit produit = produitRepository.findById(id).orElse(null);
        model.addAttribute("produit",produit);
        model.addAttribute("cats",categorieRepository.findAll());
        model.addAttribute("page",page);
        model.addAttribute("keyword", keyword);
        return "update";
    }

}
