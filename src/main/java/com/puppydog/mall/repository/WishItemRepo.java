package com.puppydog.mall.repository;
import java.util.List;

import com.puppydog.mall.dd.domain.WishItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface WishItemRepo extends JpaRepository<WishItem,Long>{


    @Query("select wi.count from WishItem wi where wi.member.id=?1 order by wi.itemId")
    List<Integer> findWishCount(String id);

    @Modifying
    @Query("delete from WishItem wi where wi.itemId=?1 and wi.member.id=?2")
    public int delWishItem(Long itemId,String id);

    @Query("select wi.itemId from WishItem wi where wi.member.id=?1 ")
    List<Long> findWishItemIds(String id);

    @Query("select wi from WishItem wi where wi.member.id=?1 AND wi.itemId=?2")
    WishItem already(String memberId,Long itemId);
}