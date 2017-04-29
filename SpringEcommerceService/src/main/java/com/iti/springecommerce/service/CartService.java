/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.springecommerce.service;

import com.iti.model.dao.CartItemDAO;
import com.iti.model.entity.CartItem;
import com.iti.model.entity.Product;
import com.iti.model.entity.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;



/**
 *
 * @author ahmed mohsen
 */
public class CartService {

    @Autowired
    CartItemDAO cartItemDAO;

    public void addToCart(User user, Product product, int quantity) {

        if (!cartItemDAO.existsByProductAndUser(product, user)) {
            CartItem item = new CartItem();
            item.setProductId(product);
            item.setUserId(user);
            item.setQuantity(quantity);
            cartItemDAO.save(item);
        } else {
            CartItem item = cartItemDAO.findOneByproductIdAndUserId(product, user);
            item.setQuantity(item.getQuantity()+quantity);
            cartItemDAO.save(item);
        }
    }

    public List<CartItem> getCartItemsOfUser(int userId){
        List<CartItem> cartItems = cartItemDAO.findCartItemsByUserId_userId(userId);
        return cartItems;
    }
    
    public void deleteCartItem(int userId, int productId){
        cartItemDAO.deleteCarttItemByUserId_userIdAndProductId_productId(userId, productId);
    }
    
    
    
}
