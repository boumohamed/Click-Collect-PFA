package com.pfa.clickandcollect;

import com.pfa.clickandcollect.Entities.Categorie;
import com.pfa.clickandcollect.Entities.Produit;
import com.pfa.clickandcollect.Repositories.CategorieRepository;
import com.pfa.clickandcollect.Repositories.ProduitRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class ClickAndCollectApplication implements CommandLineRunner {

    private ProduitRepository produitRepository;

    private CategorieRepository categorieRepository;
    public static void main(String[] args) {
        SpringApplication.run(ClickAndCollectApplication.class, args);

    }

    @Override
    public void run(String... args)  {
        categorieRepository.save(new Categorie(null, "Boisson", null));
        categorieRepository.save(new Categorie(null, "Hamburger", null));
        categorieRepository.save(new Categorie(null, "Tarte", null));


        for(int i = 1; i <= 50; i++ )
        {

            produitRepository.save(
                    new Produit(
                            null,
                            "BigMag " + i,
                            this.getRandomNumber(10, 100),
                            "desc " + i,
                            "image " + i,
                    categorieRepository.findById( Math.random() > 0.5 ? 1L: 2L).orElse(null)));
        }

    }

    // temporary
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}


