/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.model.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Samir
 */
@Entity
@Table(name = "chargecard")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChargeCard.findAll", query = "SELECT c FROM ChargeCard c")
    , @NamedQuery(name = "ChargeCard.findByCreditCardid", query = "SELECT c FROM ChargeCard c WHERE c.creditCardid = :creditCardid")
    , @NamedQuery(name = "ChargeCard.findByAmonut", query = "SELECT c FROM ChargeCard c WHERE c.amonut = :amonut")
    , @NamedQuery(name = "ChargeCard.findByPrinted", query = "SELECT c FROM ChargeCard c WHERE c.printed = :printed")
    , @NamedQuery(name = "ChargeCard.findByCharged", query = "SELECT c FROM ChargeCard c WHERE c.charged = :charged")})
public class ChargeCard implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "creditCard_id")
    private Integer creditCardid;
    @Basic(optional = false)
    @Column(name = "amonut")
    private double amonut;
    @Basic(optional = false)
    @Lob
    @Column(name = "number")
    private String number;
    @Column(name = "printed")
    private Boolean printed;
    @Column(name = "charged")
    private Boolean charged;

    public ChargeCard() {
    }

    public ChargeCard(Integer creditCardid) {
        this.creditCardid = creditCardid;
    }

    public ChargeCard(Integer creditCardid, double amonut, String number) {
        this.creditCardid = creditCardid;
        this.amonut = amonut;
        this.number = number;
    }

    public Integer getCreditCardid() {
        return creditCardid;
    }

    public void setCreditCardid(Integer creditCardid) {
        this.creditCardid = creditCardid;
    }

    public double getAmonut() {
        return amonut;
    }

    public void setAmonut(double amonut) {
        this.amonut = amonut;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Boolean getPrinted() {
        return printed;
    }

    public void setPrinted(Boolean printed) {
        this.printed = printed;
    }

    public Boolean getCharged() {
        return charged;
    }

    public void setCharged(Boolean charged) {
        this.charged = charged;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (creditCardid != null ? creditCardid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChargeCard)) {
            return false;
        }
        ChargeCard other = (ChargeCard) object;
        if ((this.creditCardid == null && other.creditCardid != null) || (this.creditCardid != null && !this.creditCardid.equals(other.creditCardid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iti.model.entity.ChargeCard[ creditCardid=" + creditCardid + " ]";
    }
    
}
