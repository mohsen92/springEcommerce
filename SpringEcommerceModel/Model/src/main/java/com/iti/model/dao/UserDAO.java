/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.model.dao;

import com.iti.model.entity.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Samir
 */
@Repository
public interface UserDAO extends CrudRepository<User, Integer>{
      @Override
    User findOne(Integer id);

    @Override
    List<User> findAll();
   
    public User findOneByEmail(String em);
    
    public User findOneByEmailAndPassword(String em,String password);
    
}
