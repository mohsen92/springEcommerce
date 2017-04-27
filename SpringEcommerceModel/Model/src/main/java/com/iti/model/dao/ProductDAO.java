/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.model.dao;

import com.iti.model.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Samir
 */
@Repository
public interface ProductDAO extends PagingAndSortingRepository<Product, Integer>{
    
}
