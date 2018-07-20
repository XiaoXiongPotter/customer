package com.dognessnetwork.customer.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QComment is a Querydsl query type for Comment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QComment extends EntityPathBase<Comment> {

    private static final long serialVersionUID = 393940095L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QComment comment = new QComment("comment");

    public final QChatRoom chatRoom;

    public final NumberPath<Long> commentTime = createNumber("commentTime", Long.class);

    public final StringPath content = createString("content");

    public final StringPath fromPetUser = createString("fromPetUser");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> star = createNumber("star", Integer.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QComment(String variable) {
        this(Comment.class, forVariable(variable), INITS);
    }

    public QComment(Path<? extends Comment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QComment(PathMetadata metadata, PathInits inits) {
        this(Comment.class, metadata, inits);
    }

    public QComment(Class<? extends Comment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chatRoom = inits.isInitialized("chatRoom") ? new QChatRoom(forProperty("chatRoom")) : null;
    }

}

