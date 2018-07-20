package com.dognessnetwork.customer.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatRoom is a Querydsl query type for ChatRoom
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QChatRoom extends EntityPathBase<ChatRoom> {

    private static final long serialVersionUID = 1366999603L;

    public static final QChatRoom chatRoom = new QChatRoom("chatRoom");

    public final NumberPath<Float> avgrate = createNumber("avgrate", Float.class);

    public final SetPath<Comment, QComment> comment = this.<Comment, QComment>createSet("comment", Comment.class, QComment.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath petUser = createString("petUser");

    public final NumberPath<Long> petUserAt = createNumber("petUserAt", Long.class);

    public final StringPath seat = createString("seat");

    public final NumberPath<Long> seatAt = createNumber("seatAt", Long.class);

    public final EnumPath<RoomStatus> status = createEnum("status", RoomStatus.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QChatRoom(String variable) {
        super(ChatRoom.class, forVariable(variable));
    }

    public QChatRoom(Path<? extends ChatRoom> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChatRoom(PathMetadata metadata) {
        super(ChatRoom.class, metadata);
    }

}

