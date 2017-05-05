/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.model.dao;



import com.iti.model.entites.Product;
import com.iti.model.entites.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Mrawi
 */
public interface ProductImageDAO extends JpaRepository <ProductImage,Integer>{
    public void deleteOneByproductId(Product productId);
}
