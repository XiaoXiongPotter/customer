package com.dognessnetwork.customer.domain;
import org.springframework.roo.addon.javabean.annotations.RooEquals;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;

import io.springlets.format.EntityFormat;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

/**
 * = Comment
 TODO Auto-generated class documentation
 *
 */
@RooJavaBean
@RooToString
@RooJpaEntity
@RooEquals(isJpaEntity = true)
public class Comment {

    /**
     * TODO ID
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * TODO 版本
     *
     */
    @Version
    private Integer version;

    /**
     * TODO 内容
     *
     */
    private String content;
    
    /**
     * TODO 评论者
     *
     */
    private String fromPetUser;
    
    /**
     * TODO 评论的房间
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @EntityFormat
    private	ChatRoom	chatRoom;
    /**
     * TODO 评论时间
     */
    private	Long	commentTime;
    /**
     * TODO 星级
     */
    private	Integer	star;
}
