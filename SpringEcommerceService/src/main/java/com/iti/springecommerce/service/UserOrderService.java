/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.springecommerce.service;

import com.iti.model.dao.UserOrderDAO;
import com.iti.model.entity.User;
import com.iti.model.entity.UserOrder;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Samir
 */
@Component
public class UserOrderService {

    @Autowired
    private UserOrderDAO userOrderDAO;

    public UserOrder add(UserOrder userOrder) {
        return userOrderDAO.save(userOrder);
    }

    public UserOrder update(UserOrder userOrder) {
        return add(userOrder);
    }

    public void delete(UserOrder userOrder) {
        userOrderDAO.delete(userOrder);
    }

    List<UserOrder> findUserOrders(User user) {
        return userOrderDAO.findUserOrders(user);
    }
}
