package com.puppydog.mall.dd.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;

@EqualsAndHashCode(of = "id")
@Entity
@Getter
public class Delivery {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id",nullable = false)
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    String address;
    
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;


    public void setOrder(Order order){
        this.order= order;
    }
    public void setStatus(DeliveryStatus status){
        this.status=status;
    }

    public void setAddress(String address){
        this.address=address;
    }

}
