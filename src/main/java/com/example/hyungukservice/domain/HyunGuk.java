package com.example.hyungukservice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@ToString
@Entity
@Getter
@Setter
@NoArgsConstructor
public class HyunGuk {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String area;
    @Column
    private String shop;
    @Column
    private String address;
    @Column
    private String phone;
    @Column
    private String sponsor;
    @Builder
    public HyunGuk(String area, String shop, String address, String phone, String sponsor){
        this.area=area;
        this.shop=shop;
        this.address=address;
        this.phone=phone;
        this.sponsor=sponsor;
    }
}
