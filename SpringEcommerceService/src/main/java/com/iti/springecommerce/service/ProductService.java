/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.springecommerce.service;


import com.iti.model.dao.CategoryDAO;
import com.iti.model.dao.ProductDAO;
import com.iti.model.dao.ProductImageDAO;
import com.iti.model.entity.Category;
import com.iti.model.entity.Product;
import com.iti.model.entity.ProductImage;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 *
 * @author Mrawi
 */
@Service
public class ProductService {

    @Autowired
    private ProductDAO productRepo;
    @Autowired
    private CategoryDAO catRepo;
    @Autowired
    private ProductImageDAO productImgRepo;

    public boolean isExists(int ProductID) {
        return productRepo.exists(ProductID);
    }

//add new product with no image//tested
    public boolean addProduct(Product newProduct) {
        Product p = productRepo.save(newProduct);
        System.out.println("new product is:" + p);
        return p != null;
    }
//add new product with image
//tested
    @Transactional
    public boolean addProduct(Product newProduct, ProductImage img) {

        Product p = productRepo.save(newProduct);
        if (p != null) {
            img.setProductId(p);
            ProductImage lastimg = productImgRepo.save(img);
            p.setImageId(lastimg.getId());
            p.getProductImageCollection().add(lastimg);
            productRepo.save(p);
        }else{
            return false;
        }

        System.out.println("new product is:" + p);
        return p != null;
    }
    @Transactional
    public boolean addImageToProduct(int productId,ProductImage img){
        if(isExists(productId)){
            Product targetProduct = productRepo.findOne(productId);
            img.setProductId(targetProduct);
           ProductImage savedimg =productImgRepo.save(img);
            targetProduct.setImageId(savedimg.getId());
            productRepo.save(targetProduct);
        }
        return true;
    }
    
    @Transactional
    public boolean deleteProduct(int productID) {
        Product deletedProduct = productRepo.findOne(productID);
        if(deletedProduct!=null){
             productImgRepo.delete(deletedProduct.getImageId());
        
        }else{
            return false;
        }
       return true;
    }
    //display page with all product
    //tested
    public Page<Product> getProductPage(int pageNumber, int pageSize) {
        Pageable pgb1 = new PageRequest(pageNumber, pageSize);
        return productRepo.findAll(pgb1);
    }

    //display page of product with in category
    //need to loop for all pages or make print method
    //tested for page1
    public Page<Product> getProductbyCatgeory(Category catgeory, int pageNumber, int pageSize) {
        Pageable pgb1 = new PageRequest(pageNumber, pageSize);
        return productRepo.findAllByproductCategory(catgeory, pgb1);
    }

    
//////////////catgeory/////////
//tested
    public Category getCategory(int id) {
        return catRepo.findOne(id);
    }

    public boolean addCategory(Category newCategory) {
        Category savedCat = catRepo.save(newCategory);
        return savedCat != null;
    }

    public boolean updateCatgeory(Category updatedCategory) {
        return addCategory(updatedCategory);
    }
}
