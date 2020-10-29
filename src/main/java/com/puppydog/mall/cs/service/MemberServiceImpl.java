package com.puppydog.mall.cs.service;
import java.util.Optional;

import javax.transaction.Transactional;

import com.puppydog.mall.dd.domain.Member;
import com.puppydog.mall.repository.MemberRepo;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepo memberRepo;
    private final PasswordEncoder pwEncoder;

    @Override
    public boolean idCheck(String id) {
        return memberRepo.findById(id).isPresent();
    }

    @Transactional
    @Override
    public void save(Member member) {
        member.setUpw(pwEncoder.encode(member.getUpw()));
        memberRepo.save(member);
    }

    @Override
    public Optional<Member> findMember(String id) {
        return memberRepo.findById(id);
    }


}