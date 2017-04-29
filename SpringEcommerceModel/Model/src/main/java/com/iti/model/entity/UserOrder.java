/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.model.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Samir
 */
@Entity
@Table(name = "userorder")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserOrder.findAll", query = "SELECT u FROM UserOrder u")
    , @NamedQuery(name = "UserOrder.findByOrderId", query = "SELECT u FROM UserOrder u WHERE u.orderId = :orderId")
    , @NamedQuery(name = "UserOrder.findByShipAddress", query = "SELECT u FROM UserOrder u WHERE u.shipAddress = :shipAddress")
    , @NamedQuery(name = "UserOrder.findByCity", query = "SELECT u FROM UserOrder u WHERE u.city = :city")
    , @NamedQuery(name = "UserOrder.findByState", query = "SELECT u FROM UserOrder u WHERE u.state = :state")
    , @NamedQuery(name = "UserOrder.findByCountry", query = "SELECT u FROM UserOrder u WHERE u.country = :country")
    , @NamedQuery(name = "UserOrder.findByPhone", query = "SELECT u FROM UserOrder u WHERE u.phone = :phone")
    , @NamedQuery(name = "UserOrder.findByZip", query = "SELECT u FROM UserOrder u WHERE u.zip = :zip")})
public class UserOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "order_id")
    private Integer orderId;
    @Basic(optional = false)
    @Column(name = "shipAddress")
    private String shipAddress;
    @Basic(optional = false)
    @Column(name = "city")
    private String city;
    @Basic(optional = false)
    @Column(name = "state")
    private String state;
    @Basic(optional = false)
    @Column(name = "country")
    private String country;
    @Basic(optional = false)
    @Column(name = "phone")
    private String phone;
    @Basic(optional = false)
    @Column(name = "zip")
    private String zip;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
    private Collection<OrderItem> orderItemCollection;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User userId;

    public UserOrder() {
    }


    public UserOrder(String shipAddress, String city, String state, String country, String phone, String zip) {
        this.shipAddress = shipAddress;
        this.city = city;
        this.state = state;
        this.country = country;
        this.phone = phone;
        this.zip = zip;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @XmlTransient
    public Collection<OrderItem> getOrderItemCollection() {
        return orderItemCollection;
    }

    public void setOrderItemCollection(Collection<OrderItem> orderItemCollection) {
        this.orderItemCollection = orderItemCollection;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserOrder)) {
            return false;
        }
        UserOrder other = (UserOrder) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iti.model.entity.UserOrder[ orderId=" + orderId + " ]";
    }
    
}
