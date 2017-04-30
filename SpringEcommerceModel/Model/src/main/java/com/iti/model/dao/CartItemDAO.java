/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.model.dao;



import com.iti.model.entites.CartItem;
import com.iti.model.entites.Product;
import com.iti.model.entites.User;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Samir
 */
@Repository
public interface CartItemDAO extends CrudRepository<CartItem, Integer>{
    List<CartItem> findCartItemsByUserId_userId(Integer userId);
    void deleteCarttItemByUserId_userId(Integer userId);
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN 'true' ELSE 'false' END FROM CartItem c WHERE c.productId = ? AND c.userId = ?")
    Boolean existsByProductAndUser(Product product, User user);
    CartItem findOneByproductIdAndUserId(Product product, User user);
    void deleteCarttItemByUserId_userIdAndProductId_productId(Integer userId,Integer productId);
}
