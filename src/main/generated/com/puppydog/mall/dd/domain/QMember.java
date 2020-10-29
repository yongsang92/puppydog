package com.puppydog.mall.dd.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 1830613463L;

    public static final QMember member = new QMember("member1");

    public final com.puppydog.mall.config.QTime _super = new com.puppydog.mall.config.QTime(this);

    public final StringPath address = createString("address");

    public final StringPath id = createString("id");

    public final StringPath nickName = createString("nickName");

    public final ListPath<Order, QOrder> orders = this.<Order, QOrder>createList("orders", Order.class, QOrder.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regdate = _super.regdate;

    public final ListPath<MemberRole, QMemberRole> rolse = this.<MemberRole, QMemberRole>createList("rolse", MemberRole.class, QMemberRole.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedate = _super.updatedate;

    public final StringPath upw = createString("upw");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

