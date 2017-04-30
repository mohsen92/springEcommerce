/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.model.dao;

import com.iti.model.entites.Category;
import com.iti.model.entites.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Samir
 */
@Repository
public interface ProductDAO extends PagingAndSortingRepository<Product, Integer> {

    Page<Product> findAllByproductCategory(Category category, Pageable pgbl);

    Page<Product> findAllByProductNameLike(String productName, Pageable pgbl);

    Page<Product> findAllByproductCategoryAndProductNameLike(Category category, String productName, Pageable pgbl);

    Page<Product> findAllByproductCategoryAndProductNameLikeAndPriceBetween(Category category, String productName, double lowPrice, double highPrice, Pageable pgbl);

}
