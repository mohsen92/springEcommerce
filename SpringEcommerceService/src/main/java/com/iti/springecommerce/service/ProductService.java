/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.springecommerce.service;

import com.iti.model.dao.CategoryDAO;
import com.iti.model.dao.ProductDAO;
import com.iti.model.dao.ProductImageDAO;
import com.iti.model.entites.Category;
import com.iti.model.entites.Product;
import com.iti.model.entites.ProductImage;
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

    public Product getProduct(int productId){
        if(isExists(productId)){
            return productRepo.findOne(productId);
        }else
            return null;
    }
//add new product with no image//tested
    public boolean addProduct(Product newProduct) {
        Product p = productRepo.save(newProduct);
        System.out.println("new product is:" + p);
        return p != null;
    }
//add new product with image

    @Transactional
    public boolean addProduct(Product newProduct, ProductImage img) {
        
        Product p = productRepo.save(newProduct);
        if (p != null) {
            if (addImageToProduct(p.getProductId(), img)) {
                System.out.println("new product is:" + p + "the size of product collection:"
                        + p.getProductimageCollection().size());
                
            }
//            p.getProductimageCollection().add(lastimg);
//            productRepo.save(p);
        } else {
            return false;
        }
        
        return true;
    }
    //tested
    public boolean updateProduct(Product updatedProduct) {
        Product done;
        if (isExists(updatedProduct.getProductId())) {
            done = productRepo.save(updatedProduct);
        } else {
            return false;
        }
        return done != null;
    }

    //add extra img to this product id and also the collection in this product is updated
    //tested
    @Transactional
    public boolean addImageToProduct(int productId, ProductImage img) {
        ProductImage savedimg ;
        if (isExists(productId)) {
            
            img.setProductId(productRepo.findOne(productId));
            savedimg = productImgRepo.save(img);
            System.out.println("after addImages" + productRepo.findOne(productId).getProductimageCollection().size());
        } else {
            return false;
        }
        return savedimg != null;
    }

    //delete the image with related with this productID
    //notWork :D 
    @Transactional
    public boolean deleteImageByProduct(int productId) {
        if (isExists(productId)) {
            Product p = productRepo.findOne(productId);
            System.out.println("current product"+p);
//            p.getCartitemCollection().forEach(img -> {
//                
//                productImgRepo.delete(img.getId());
//            });
//            System.out.println("com.iti.springecommerce.service.ProductService.deleteImageByProduct()"+img.getId());
            productImgRepo.deleteOneByproductId(p);
        } else {
            return false;
        }
        System.out.println("after removeImages" + productRepo.findOne(productId).getProductimageCollection().size());
        return true;
    }
    //tested
    @Transactional
    public boolean deleteProduct(int productID) {
        if (isExists(productID)) {
            deleteImageByProduct(productID);
            productRepo.delete(productID);
            
        } else {
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
        if (catRepo.exists(id)) {
            return catRepo.findOne(id);
        } else {
            return null;
        }
    }
   
    public boolean addCategory(Category newCategory) {
        Category savedCat = catRepo.save(newCategory);
        return savedCat != null;
    }
    
    public boolean updateCatgeory(Category updatedCategory) {
        if (catRepo.exists(updatedCategory.getId())) {
            return addCategory(updatedCategory);
        } else {
            return false;
        }
    }
    //tested
    @Transactional
    public boolean deleteCategory(int catId) {
        if (catRepo.exists(catId)) {
            catRepo.findOne(catId).getProductCollection().forEach(p -> {
                deleteProduct(p.getProductId());
            });
            catRepo.delete(catId);
        } else {
            return false;
        }
        return true;
    }
    
    @Transactional
    public int reduceQuantity(int ProductId, int number) {
        if (isExists(ProductId)) {
            Product targetProduct = productRepo.findOne(ProductId);
            if(targetProduct.getQuantityInStock()> 0){
                int oldQunt = targetProduct.getQuantityInStock();
                targetProduct.setQuantityInStock(oldQunt-number);
                productRepo.save(targetProduct);
            }
            return targetProduct.getQuantityInStock();

        } else 
            return -1;  
    }
}
