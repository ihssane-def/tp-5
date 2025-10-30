package web;

import entities.Product;
import entities.Category;
import metier.ProductDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductDaoImpl productDao;

    @GetMapping
    @ResponseBody
    @Transactional(readOnly = true)
    public List<Product> listProducts() {
        return productDao.findAll();
    }

    @PostMapping
    @ResponseBody
    @Transactional
    public String addProduct(@RequestParam String name,
                             @RequestParam double price,
                             @RequestParam int categoryId) {
        Category category = new Category();
        category.setId(categoryId);

        Product product = new Product(name, price, category);
        productDao.create(product);

        return "Product added successfully!";
    }
}
