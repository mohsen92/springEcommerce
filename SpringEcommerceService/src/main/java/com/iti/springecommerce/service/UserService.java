/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.springecommerce.service;

import com.iti.model.entity.*;
import com.iti.model.dao.*;
import com.iti.springecommerce.utility.BCrypt;
import com.iti.springecommerce.utility.Mailer;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sara metwalli
 */
@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean addUser(User user, String appUrl) {

        String em = user.getEmail();
        User u = userDAO.findOneByEmail(em);
        if (!userDAO.exists(u.getUserId())) {
            System.out.println(userDAO.exists(u.getUserId()));
            // encrypt password
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(hashedPassword);
            // generate confirm tokens
            String token = UUID.randomUUID().toString();
            user.setConfirmToken(token);
            String msg = "please click on this link to confirm your mail <br/> <a href='http://" + appUrl + "/Confirm?email=" + user.getEmail() + "&token=" + token + "'>click here to confirm</a><br/><br/>Spring Ecommerce";
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Mailer.send(user.getEmail(), "confirmation mail Spring Ecommerce", msg);
                }
            }).start();
            userDAO.save(user);
            return true;
        }
        return false;
    }

    public boolean signIn(String email, String Password) {

        User u = userDAO.findOneByEmailAndPassword(email, Password);
        if (u != null) {
            return (userDAO.exists(u.getUserId()));
        } else {
            return false;
        }
    }

    public User updateUser(User user) {
        return userDAO.save(user);

    }

    public boolean confirmEmail(String email, String token) {
        User user = userDAO.findOneByEmail(email);
        if (user != null && user.getConfirmToken().equals(token)) {
            user.setUserEmailVerified(true);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Mailer.send(email, "welcome to Spring Ecommerce", "welcome to Spring Ecommerce");
                }
            }).start();
            return true;
        }
        return false;
    }

    public boolean forgetPassword(String email, String appUrl) {
        //check if user already exsists
        //create new 2 fileds in user db one for password-reset-token and other for expiration date of token
        // send mail with servlet name /resetpassword + user email + usertoken
        User user = userDAO.findOneByEmail(email);
        if (user != null) {
            String passwordToken = UUID.randomUUID().toString();
            Date expireDate = new Date(System.currentTimeMillis() + 15 * 60 * 1000);
            user.setPasswordResetToken(passwordToken);
            user.setExpirationDate(expireDate);
            String msg = "please click on this link to reset your password  <br/> <a href='http://" + appUrl + "/ResetPassword?email=" + email + "&passwordToken=" + passwordToken + "'>click here to reset your password</a><br/><br/>Spring Ecommerce";
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Mailer.send(email, "Reset Password mail Spring Ecommerce", msg);
                }
            }).start();
            return true;
        }
        return false;
    }

    public boolean resetPassword(String email, String newPassword) {
        User user = userDAO.findOneByEmail(email);
        if (user != null) {
            String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            user.setPassword(hashedPassword);
            userDAO.save(user);
        }
        return false;
    }

    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

}
