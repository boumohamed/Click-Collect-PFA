package com.pfa.clickandcollect.web;

import com.pfa.clickandcollect.Entities.Categorie;
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
public class CategorieController {
    private CategorieRepository categorieRepository;

    @GetMapping(path = "/categories")
    public String Patients(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "6") int size,
                           @RequestParam(name = "keyword", defaultValue = "") String keyword)
    {
        Page<Categorie> pagecategories = categorieRepository.findByDesignationContains(keyword, PageRequest.of(page,size));

        model.addAttribute("categories", pagecategories.getContent());
        model.addAttribute("pages", new int[pagecategories.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "categories";
    }

    @GetMapping("/deleteCat")
    public String delete(Long id){
        categorieRepository.deleteById(id);
        return "redirect:/categories";
    }



}
