package com.puppydog.mall.repository;
import java.util.List;

import com.puppydog.mall.dd.domain.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order,Long> {

	List<Order> findByMemberId(String memberId);

}