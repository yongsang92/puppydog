package com.puppydog.mall.dd.item;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSnack is a Querydsl query type for Snack
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSnack extends EntityPathBase<Snack> {

    private static final long serialVersionUID = -2010412096L;

    public static final QSnack snack = new QSnack("snack");

    public final QItem _super = new QItem(this);

    //inherited
    public final ListPath<com.puppydog.mall.dd.domain.CategoryItem, com.puppydog.mall.dd.domain.QCategoryItem> categoryItems = _super.categoryItems;

    //inherited
    public final StringPath description = _super.description;

    //inherited
    public final NumberPath<Integer> discount = _super.discount;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath name = _super.name;

    //inherited
    public final NumberPath<Integer> price = _super.price;

    //inherited
    public final NumberPath<Integer> quantity = _super.quantity;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regdate = _super.regdate;

    //inherited
    public final NumberPath<Integer> sales = _super.sales;

    //inherited
    public final StringPath thumbnail = _super.thumbnail;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedate = _super.updatedate;

    public QSnack(String variable) {
        super(Snack.class, forVariable(variable));
    }

    public QSnack(Path<? extends Snack> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSnack(PathMetadata metadata) {
        super(Snack.class, metadata);
    }

}

