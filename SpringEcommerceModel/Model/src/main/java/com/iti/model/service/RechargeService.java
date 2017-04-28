/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.model.service;

import com.iti.model.dao.ChargeCardDAO;
import com.iti.model.entity.ChargeCard;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Samir
 */
@Component
@Transactional
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
        return chargeCardDAO.findNotChargedChargeCard();
    }
    List<ChargeCard> findChargedChargeCard(){
        return chargeCardDAO.findChargedChargeCard();
    }
    List<ChargeCard> findNotPrintedChargeCard(){
        return chargeCardDAO.findNotPrintedChargeCard();
    }
    List<ChargeCard> findPrintedChargeCard(){
        return chargeCardDAO.findPrintedChargeCard();
    }
}
