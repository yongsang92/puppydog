package com.puppydog.mall.dd.item;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItem is a Querydsl query type for Item
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QItem extends EntityPathBase<Item> {

    private static final long serialVersionUID = 1597423969L;

    public static final QItem item = new QItem("item");

    public final com.puppydog.mall.config.QTime _super = new com.puppydog.mall.config.QTime(this);

    public final ListPath<com.puppydog.mall.dd.domain.CategoryItem, com.puppydog.mall.dd.domain.QCategoryItem> categoryItems = this.<com.puppydog.mall.dd.domain.CategoryItem, com.puppydog.mall.dd.domain.QCategoryItem>createList("categoryItems", com.puppydog.mall.dd.domain.CategoryItem.class, com.puppydog.mall.dd.domain.QCategoryItem.class, PathInits.DIRECT2);

    public final StringPath description = createString("description");

    public final NumberPath<Integer> discount = createNumber("discount", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regdate = _super.regdate;

    public final NumberPath<Integer> sales = createNumber("sales", Integer.class);

    public final StringPath thumbnail = createString("thumbnail");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedate = _super.updatedate;

    public QItem(String variable) {
        super(Item.class, forVariable(variable));
    }

    public QItem(Path<? extends Item> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItem(PathMetadata metadata) {
        super(Item.class, metadata);
    }

}

