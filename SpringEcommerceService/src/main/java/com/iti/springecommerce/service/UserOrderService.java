/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.springecommerce.service;

import com.iti.model.dao.UserOrderDAO;
import com.iti.model.entites.CartItem;
import com.iti.model.entites.OrderItem;
import com.iti.model.entites.Product;
import com.iti.model.entites.User;
import com.iti.model.entites.UserOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Samir
 */
@Service
public class UserOrderService {

    @Autowired
    private UserOrderDAO userOrderDAO;
    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;

    @Transactional
    public List<?> checkOut(User user, UserOrder userOrder, List<CartItem> cartItems) {
        List<?> result;
        List<Product> unAvailableProducts = checkProductsAvailability(cartItems);
        if (unAvailableProducts.isEmpty()) {
            if (user.getCredit() >= getTotalPrice(cartItems)) {
                UserOrder uOrder = createOrder(user, userOrder);
                List<OrderItem> orderItems = new ArrayList<>();

                cartItems.forEach((cartItem) -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setDate(new Date());
                    orderItem.setOrderId(uOrder);
                    orderItem.setPrice(cartItem.getProductId().getPrice());
                    orderItem.setProductId(cartItem.getProductId());
                    orderItem.setQuantityOrdered(cartItem.getQuantity());
                    orderItems.add(orderItem);
                });
                uOrder.setOrderitemCollection(orderItems);
                cartItems.forEach((cartItem) -> {
                    productService.reduceQuantity(cartItem.getProductId().getProductId(), cartItem.getQuantity());
                });

                cartService.freeCart(user.getUserId());
                result = new ArrayList<String>(Arrays.asList("Thank You ! your order ready for shipping"));
            } else {
                result = new ArrayList<String>(Arrays.asList("There is not enough credit in your account"));
            }
        } else {
            result = unAvailableProducts;
        }
        return result;
    }

    /*check products availability in stock */
    private List<Product> checkProductsAvailability(List<CartItem> cartItems) {
        List<Product> unAvailableProducts = new ArrayList<>();
        cartItems.forEach((cartItem) -> {
            Integer quantityInStock = cartItem.getProductId().getQuantityInStock();
            if (quantityInStock < cartItem.getQuantity()) {
                Product unAvailableProduct = cartItem.getProductId();
                unAvailableProducts.add(unAvailableProduct);
            }
        });
        return unAvailableProducts;
    }

    /*get total price of cartItems*/
    private float getTotalPrice(List<CartItem> cartItems) {
        float totalPrice = 0.0f;
        for (CartItem cartItem : cartItems) {
            totalPrice += cartItem.getProductId().getPrice();
        }
        return totalPrice;
    }

    /*creating order for specific user */
    private UserOrder createOrder(User user, UserOrder userOrder) {
        userOrder.setUserId(user);
        UserOrder savedOrder = userOrderDAO.save(userOrder);
        return savedOrder;
    }

    public List<UserOrder> findUserOrders(int userId) {
        return userOrderDAO.findAllByUserId_userId(userId);
    }
}