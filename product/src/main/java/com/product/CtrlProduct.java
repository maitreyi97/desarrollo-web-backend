package com.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Arrays;

@RestController
@RequestMapping("/category")
public class CtrlProduct {

    @GetMapping
    public List<Category> getCategories(){
        return Arrays.asList(
            new Category(1, "Lentes", "Lts", 1),
            new Category(2, "Relojes", "Rljs", 1),
            new Category(3, "Monitores", "Mtrs", 1)
        );
    }
}
