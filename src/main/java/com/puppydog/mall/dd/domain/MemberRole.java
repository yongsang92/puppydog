package com.puppydog.mall.dd.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@EqualsAndHashCode(of = "key")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter 
@Entity
public class MemberRole {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key")
    private Long key;

    private String role;

    @Builder
    public MemberRole(String role) {
        this.role = role;
    }

}