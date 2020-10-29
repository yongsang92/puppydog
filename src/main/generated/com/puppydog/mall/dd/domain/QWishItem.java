package com.puppydog.mall.dd.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWishItem is a Querydsl query type for WishItem
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QWishItem extends EntityPathBase<WishItem> {

    private static final long serialVersionUID = -2037284777L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWishItem wishItem = new QWishItem("wishItem");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> itemId = createNumber("itemId", Long.class);

    public final QMember member;

    public QWishItem(String variable) {
        this(WishItem.class, forVariable(variable), INITS);
    }

    public QWishItem(Path<? extends WishItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWishItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWishItem(PathMetadata metadata, PathInits inits) {
        this(WishItem.class, metadata, inits);
    }

    public QWishItem(Class<? extends WishItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

