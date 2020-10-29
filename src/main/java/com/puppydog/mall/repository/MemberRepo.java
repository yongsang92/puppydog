package com.puppydog.mall.repository;
import com.puppydog.mall.dd.domain.Member;

import org.springframework.data.repository.CrudRepository;

public interface MemberRepo extends CrudRepository<Member,String>{

}