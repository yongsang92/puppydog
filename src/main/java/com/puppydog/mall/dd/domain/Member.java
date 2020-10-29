package com.puppydog.mall.dd.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

import com.puppydog.mall.config.Time;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Getter
public class Member extends Time {

    @Id
    private String id;

    @Column(nullable = false)
    private String upw;

    @Column(nullable = false, unique = true) 
    private String nickName;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private List<MemberRole> rolse;

    

    @Builder
    public Member(String id, String upw, String nickName, String address) {
        this.id = id;
        this.upw = upw;
        this.nickName = nickName;
        this.address = address;
        rolse=new ArrayList<>();
        rolse.add(MemberRole.builder().role("BASIC").build());
    }

 

    public void setRolse(List<MemberRole> roles) {
        this.rolse = roles;
    }
    public void setUpw(String upw) {
        this.upw = upw;
    }

}
