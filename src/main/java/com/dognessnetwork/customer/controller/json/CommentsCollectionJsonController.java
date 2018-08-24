package com.dognessnetwork.customer.controller.json;
import com.dognessnetwork.customer.domain.Comment;
import com.dognessnetwork.customer.service.api.ChatRoomService;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/js/comments",name = "CommentsCollectionJsonController",produces = MediaType.APPLICATION_JSON_VALUE)
/**
 * = CommentsCollectionJsonController
 TODO Auto-generated class documentation
 *
 */
@RooController(entity = Comment.class, pathPrefix = "js", type = ControllerType.COLLECTION)
@RooJSON
public class CommentsCollectionJsonController {
    @Autowired
    ChatRoomService chatroomService;
    /**
     * 评论
     * @param fromPetUser
     * @param seat
     * @param content
     * @param star
     */
    @ResponseBody
    @PostMapping(value="/score",produces = "text/plain;charset=UTF-8")
    public String score(String  fromPetUser,String    seat,String content,String  star){
        
        Comment comment =   new Comment();
        comment.setCommentTime(System.currentTimeMillis());
        comment.setContent(content);
        comment.setStar(Integer.parseInt(star));
        comment.setFromPetUser(fromPetUser);
        comment.setChatRoom(chatroomService.findBySeat(seat));
        getCommentService().scoring(comment);
     return "";   
    }
    
    /**
     * 
     * @param seat
     * @param response
     */
    @ResponseBody
    @RequestMapping("/getAvg")
    public void getImg(@Param("seat")String seat,HttpServletResponse response){
        int width=280, height=35;
        Font    font    =   new Font("微软雅黑", Font.PLAIN, 32);
        // 创建图片  
        BufferedImage image = new BufferedImage(width, height,  
                BufferedImage.TYPE_INT_BGR);  
        Graphics g = image.getGraphics();  
        g.setClip(0, 0, width, height);  
        g.setColor(Color.white);  
        g.fillRect(0, 0, width, height);// 先用黑色填充整张图片,也就是背景  
        g.setColor(Color.red);// 在换成黑色  
        g.setFont(font);// 设置画笔字体  
        /** 用于获得垂直居中y */  
        Rectangle clip = g.getClipBounds();  
        FontMetrics fm = g.getFontMetrics(font);  
        int ascent = fm.getAscent();  
        int descent = fm.getDescent();  
        int y = (clip.height - (ascent + descent)) / 2 + ascent;  
        for (int i = 0; i < 6; i++) {// 256 340 0 680  
            g.drawString("  "+chatroomService.findBySeat(seat).getAvgrate(), i * 680, y);// 画出字符串  
        }  
        g.dispose();  
        if(image!=null){
            try {
                ImageIO.write(image, "JPEG", response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("打印异常:com.controller.Business_Ctrl.getImg(String, HttpServletResponse)");
            }
        }
    }
}
