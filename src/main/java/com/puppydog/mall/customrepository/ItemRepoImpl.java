package com.puppydog.mall.customrepository;

import java.util.List;

import javax.persistence.EntityManager;

import static com.puppydog.mall.dd.item.QItem.item;
import static com.puppydog.mall.dd.item.QSnack.snack;
import com.puppydog.mall.dto.ItemShowDto;
import com.puppydog.mall.dto.WishItemDto;
import com.puppydog.mall.dto.searchCondition.ItemSearchCondition;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;

public class ItemRepoImpl implements ItemRepoCustom {

        private final JPAQueryFactory qf;

        public ItemRepoImpl(EntityManager em) {
                qf = new JPAQueryFactory(em);
        }

        // 스낵관련 전부 찾고 Dto로 보여주기
        @Override
        public Page<ItemShowDto> searchByCategory(ItemSearchCondition itemCondition, Pageable pageable) {
                List<ItemShowDto> content = qf
                                .select(Projections.bean(ItemShowDto.class, snack.id, snack.name, snack.thumbnail,
                                                snack.price, snack.sales))
                                .from(snack).limit(pageable.getPageSize()).offset(pageable.getOffset()).fetch();

                JPAQuery<ItemShowDto> countQuery = qf.select(Projections.bean(ItemShowDto.class, snack.id, snack.name,
                                snack.thumbnail, snack.price, snack.sales)).from(snack);

                return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
        }

        // 스낵관련 전부 찾고 Dto로 보여주는데 싼 가격순으로
        @Override
        public Page<ItemShowDto> searchByPriceASC(ItemSearchCondition itemCondition, Pageable pageable) {
                List<ItemShowDto> content = qf
                                .select(Projections.bean(ItemShowDto.class, snack.id, snack.name, snack.thumbnail,
                                                snack.price, snack.sales))
                                .from(snack).orderBy(snack.price.asc()).limit(pageable.getPageSize())
                                .offset(pageable.getOffset()).fetch();

                JPAQuery<ItemShowDto> countQuery = qf.select(Projections.bean(ItemShowDto.class, snack.id, snack.name,
                                snack.thumbnail, snack.price, snack.sales)).from(snack);

                return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
        }

        // 스낵관련 전부 찾고 Dto로 보여주는데 비싼 가격순으로
        @Override
        public Page<ItemShowDto> searchByPriceDESC(ItemSearchCondition itemCondition, Pageable pageable) {
                List<ItemShowDto> content = qf
                                .select(Projections.bean(ItemShowDto.class, snack.id, snack.name, snack.thumbnail,
                                                snack.price, snack.sales))
                                .from(snack).orderBy(snack.price.desc()).limit(pageable.getPageSize())
                                .offset(pageable.getOffset()).fetch();

                JPAQuery<ItemShowDto> countQuery = qf.select(Projections.bean(ItemShowDto.class, snack.id, snack.name,
                                snack.thumbnail, snack.price, snack.sales)).from(snack);
                return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
        }

        // 스낵관련 전부 찾고 Dto로 보여주는데 판매량(인기순)으로
        @Override
        public Page<ItemShowDto> searchBySales(ItemSearchCondition itemCondition, Pageable pageable) {
                List<ItemShowDto> content = qf
                                .select(Projections.bean(ItemShowDto.class, snack.id, snack.name, snack.thumbnail,
                                                snack.price, snack.sales))
                                .from(snack).orderBy(snack.sales.desc()).limit(pageable.getPageSize())
                                .offset(pageable.getOffset()).fetch();

                JPAQuery<ItemShowDto> countQuery = qf.select(Projections.bean(ItemShowDto.class, snack.id, snack.name,
                                snack.thumbnail, snack.price, snack.sales)).from(snack);

                return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
        }

        @Override
        public List<WishItemDto> getWishItems(List<Long> ids) {

                return qf.select(Projections.fields(WishItemDto.class, item.id, item.name, item.thumbnail, item.price,
                                item.discount)).from(item).where(item.id.in(ids)).fetch();

        }

        /*
         * 위의 메서드를 사용한 예시 제목: 모든간식 조회 페이징 적용 querydsl 사용
         * 
         * @GetMapping("/snacks") public String snack(ItemSearchCondition itemCondition,
         * PageFilter pageFilter, Model model) {
         * 
         * Pageable pageable = pageFilter.makePageable(); Page<ItemShowDto> pagingResult
         * = itemRepo.searchByCategory(itemCondition, pageable);
         * model.addAttribute("result", new PageMaker<>(pagingResult)); return
         * "/item/snackList";
         * 
         * }
         */

}