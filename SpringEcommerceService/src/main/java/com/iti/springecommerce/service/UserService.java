/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.springecommerce.service;


import com.iti.model.dao.*;
import com.iti.model.entites.User;
import static javafx.scene.input.KeyCode.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author sara metwalli
 */
@Service("userService")
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void addUser(User user) {

        String em = user.getEmail();
        User u = userDAO.findOneByEmail(em);
        if (!userDAO.exists(u.getUserId())) {
            System.out.println(userDAO.exists(u.getUserId()));
            userDAO.save(user);
        }
    }

    public boolean signIn(String email, String Password) {

        User u = userDAO.findOneByEmailAndPassword(email, Password);
        if (u != null) {
            return (userDAO.exists(u.getUserId()));
        }
        else
            return false;
    }
    public User updateUser(User user)
    {
        return userDAO.save(user); 
        
    }
   
      

}
