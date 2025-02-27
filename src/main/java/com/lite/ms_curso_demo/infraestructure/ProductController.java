package com.lite.ms_curso_demo.infraestructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lite.ms_curso_demo.application.ProductApplication;
import com.lite.ms_curso_demo.domain.Product;
import com.lite.ms_curso_demo.exception.ProductNotFoundException;

@RestController
@RequestMapping("/productos")
public class ProductController {

    @Autowired
    public ProductApplication productApplication;

    @GetMapping("/")
    public List<Product> getAllProducts() {
        return productApplication.findAll();
    }

    @GetMapping("/{id}")
    public Product getIdProduct(@PathVariable Long id) {
        return productApplication.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @GetMapping("/name")
    public List<Product> getProductsByName(@RequestParam String name) {
        List<Product> products = productApplication.findByNameContaining(name);
        if (products.isEmpty()) {
            throw new ProductNotFoundException(name);
        }
        return products;
    }

    @PostMapping("/add")
    public Product createProduct(@RequestBody Product product) {
        return productApplication.save(product);
    }

    @PutMapping("/edit")
    public Product updateProduct(@RequestBody Product product) {
        productApplication.findById(product.getId()).orElseThrow(() -> new ProductNotFoundException(product.getId()));
        return productApplication.save(product);
    }

    @PutMapping("/edit/{id}")
    public Product updateProductById(@PathVariable Long id, @RequestBody Product product) {

        Product productToUpdate = productApplication.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        productToUpdate.setName(product.getName());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setStock(product.getStock());

        return productApplication.save(productToUpdate);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, String> deleteProduct(@PathVariable Long id) {
        Product productToDelete = productApplication.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        productApplication.delete(productToDelete);
        Map<String, String> response = new HashMap<>();

        response.put("message", "Producto eliminado: " + id);

        return response;
    }
}
