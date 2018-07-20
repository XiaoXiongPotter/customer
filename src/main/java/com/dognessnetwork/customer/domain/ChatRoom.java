package com.dognessnetwork.customer.domain;
import org.springframework.roo.addon.javabean.annotations.RooEquals;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.entity.JpaRelationType;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaRelation;

import io.springlets.format.EntityFormat;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

/**
 * = Chatroom
 TODO Auto-generated class documentation
 *
 */
@RooJavaBean
@RooToString
@RooJpaEntity
@RooEquals(isJpaEntity = true)
public class ChatRoom {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Version
    private Integer version;
    /**
     * 坐席
     */
    @NotNull
    private	String	seat;
    
    /**
     * TODO Auto-generated attribute documentation
     *用户
     */
    private String	petUser;
    
    /**
     * 聊天室状态
     */
    @NotNull
    private	RoomStatus	status;
    
    /**
     * 客服上线时间
     */
    private	Long	seatAt;
    
    /**
     * 用户上线时间
     */
    private	Long	petUserAt;
    
    /**
     * 平均分
     */
    private	float	avgrate;
    
    /**
     * 对客服的评论
     */
    @OneToMany(cascade = { javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST }, fetch = FetchType.LAZY, mappedBy = "chatRoom")
    @RooJpaRelation(type = JpaRelationType.AGGREGATION)
    private Set<Comment> comment = new HashSet<Comment>();
    
    
}
