/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.springecommerce.service;

import com.iti.model.dao.ChargeCardDAO;
import com.iti.model.entites.ChargeCard;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Samir
 */
@Service
public class RechargeService {
    @Autowired
    ChargeCardDAO chargeCardDAO; 
    
    public ChargeCard add(ChargeCard chargeCard){
        return chargeCardDAO.save(chargeCard);
    }
    public ChargeCard update(ChargeCard chargeCard){
        return add(chargeCard);
    }
    public List<ChargeCard> findNotChargedChargeCard(){
        return chargeCardDAO.findAllByChargedFalse();
    }
    public List<ChargeCard> findChargedChargeCard(){
        return chargeCardDAO.findAllByChargedTrue();
    }
    public List<ChargeCard> findNotPrintedChargeCard(){
        return chargeCardDAO.findAllByPrintedFalse();
    }
    public List<ChargeCard> findPrintedChargeCard(){
        return chargeCardDAO.findAllByPrintedTrue();
    }
}
