package com.puppydog.mall.cs.service;
import java.util.Optional;

import com.puppydog.mall.dd.domain.Member;


public interface MemberService {
    public boolean idCheck(String id);
    public void save(Member member);
    public Optional<Member> findMember(String id);
}