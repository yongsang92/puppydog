package com.puppydog.mall.config;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.puppydog.mall.dd.domain.Member;
import com.puppydog.mall.dd.domain.MemberRole;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationUser extends User {

    private Member member;

    public AuthenticationUser(Member member) {
        super(member.getId(),member.getUpw(), makeGrantedAuthority(member.getRolse()));
        this.member=member;
    } 

    private static Collection<? extends GrantedAuthority> makeGrantedAuthority(List<MemberRole> roles) {
        List<GrantedAuthority> list=new ArrayList<>();

        roles.forEach(role->list.add(new SimpleGrantedAuthority("ROLE_"+role.getRole())));
        return list;
    }
 

}