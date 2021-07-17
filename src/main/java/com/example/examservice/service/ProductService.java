package com.example.examservice.service;

import com.example.examservice.entity.Product;
import com.example.examservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;
import java.util.Optional;

@Component(value = "ProductService")
@WebService
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @WebMethod
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @WebMethod
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    @WebMethod
    public Product sellProduct(Long id, int quantity) {
        Product existProduct = productRepository.findById(id).orElse(null);
        if (existProduct != null && existProduct.getQuantity() > quantity){
            existProduct.setQuantity(existProduct.getQuantity() - quantity);
            productRepository.save(existProduct);
            return existProduct;
        }
        return null;
    }
    @WebMethod
    public Product findById(Long id){
     Optional<Product> productOptional = productRepository.findById(id);
     if(productOptional.isPresent()){
         return productOptional.get();
     }
        return null;
    }
}
