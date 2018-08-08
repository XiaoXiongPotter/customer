package com.dognessnetwork.customer.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMessages is a Querydsl query type for Messages
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMessages extends EntityPathBase<Messages> {

    private static final long serialVersionUID = -532437204L;

    public static final QMessages messages = new QMessages("messages");

    public final StringPath formUser = createString("formUser");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<MessageStatus> messageStatus = createEnum("messageStatus", MessageStatus.class);

    public final EnumPath<MessageType> messageType = createEnum("messageType", MessageType.class);

    public final StringPath petUserName = createString("petUserName");

    public final StringPath postMessages = createString("postMessages");

    public final StringPath seat = createString("seat");

    public final NumberPath<Long> sendTime = createNumber("sendTime", Long.class);

    public final NumberPath<Long> startTime = createNumber("startTime", Long.class);

    public final StringPath toUser = createString("toUser");

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QMessages(String variable) {
        super(Messages.class, forVariable(variable));
    }

    public QMessages(Path<? extends Messages> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMessages(PathMetadata metadata) {
        super(Messages.class, metadata);
    }

}

