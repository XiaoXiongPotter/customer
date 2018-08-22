package com.dognessnetwork.customer.domain;
import org.springframework.roo.addon.javabean.annotations.RooEquals;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;
import io.springlets.format.EntityFormat;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;
import java.util.Calendar;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Enumerated;

/**
 * = Messages
 TODO Auto-generated class documentation
 *
 */
@RooJavaBean
@RooToString
@RooJpaEntity
@RooEquals(isJpaEntity = true)
public class Messages {

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
     * TODO Auto-generated attribute documentation
     *
     */
    @NotNull
    @Column(name = "post_messages")
    private String postMessages;

    /**
     * TODO 发送消息的当前时间戳
     *
     */
    private Long sendTime;

    /**
     * TODO 消息发送者
     *
     */
    private String formUser;

    /**
     * TODO 消息接收者
     *
     */
    private String toUser;

    /**
     * TODO 消息类型
     *
     */
    @Enumerated
    private MessageType messageType;

    /**
     * TODO 消息送达状态
     */
    private MessageStatus messageStatus;

    /**
     * TODO 接入时间
     */
    private Long startTime;

    /**
     * TODO 用户名
     *
     */
    private String petUserName;

    /**
     * TODO 客服
     *
     */
    private String seat;
}
