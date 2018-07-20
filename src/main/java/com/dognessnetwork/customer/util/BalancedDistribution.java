/*package com.dognessnetwork.customer.util;
*//**
 * 均衡分配客服工具类
 * @author Dogness
 *
 *//*

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dognessnetwork.customer.domain.Chatroom;
import com.dognessnetwork.customer.domain.CustomerCare;
import com.dognessnetwork.customer.domain.ServiceQuality;
import com.dognessnetwork.customer.service.api.CustomerCareService;
import com.mysema.commons.lang.Assert;

import cn.hutool.core.lang.Console;
*//**
 * 均衡分配
 * @author Dogness
 *
 *//*
@Component
public class BalancedDistribution {
	public	final	Long	id=0L;
	@Autowired
	CustomerCareService		customerCareService;
	*//**
	 * 找出服务评分最好的客服
	 * @param chatrooms
	 * @return	返回坐席的ID
	 *//*
	public	Long	sort(List<Chatroom>	chatrooms){
		//保证质量的情况下算出服务数量最少的
		List<ServiceQuality>	list = new	ArrayList<ServiceQuality>();
		chatrooms.forEach(action->{list.add(customerCareService.findOne(action.getSeat().getId()).getServiceQuality());});
		list.sort(Comparator.comparing(ServiceQuality::getAvgrate).reversed().thenComparing(ServiceQuality::getServiceQuantity));
		
		for (Chatroom integer : chatrooms) {
			CustomerCare	seat	=	customerCareService.findOne(integer.getSeat().getId());
			Console.log("平均分："+getAug(seat.getServiceQuality()));

			list.add(seat.getServiceQuality());
		}
		
		list.forEach(action->Console.log("排序后"+action.getId()));
		return	list.get(0).getId();
	}
	*//**
	 * 计算评分，满分为十分
	 * @param serviceQuality
	 * @return
	 *//*
	public	double	getAug(ServiceQuality	serviceQuality){
		//各个评分个数
		int	vote_1	=	serviceQuality.getVote_1();
		int	vote_2	=	serviceQuality.getVote_2();
		int	vote_3	=	serviceQuality.getVote_3();
		int	vote_4	=	serviceQuality.getVote_4();
		int	vote_5	=	serviceQuality.getVote_5();
		//总个数
		int	num	=	vote_1+vote_2+vote_3+vote_4+vote_5;
		Console.log("总个数:"+num);
		//总分数
		int	sum	=	vote_1*1+vote_2*2+vote_3*3+vote_4*4+vote_5*5;
		Console.log("总分数:"+sum);
		//平均分
		//float	avg	=	sum/num;
		double	avg	=	div((double)sum,(double)num,2);
		Console.log("平均分:"+avg*2);
		return	avg*2;
	}
	*//**  
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。  
     *  
     * @param v1  
     *            被除数  
     * @param v2  
     *            除数  
     * @param scale  
     *            表示需要精确到小数点以后几位。  
     * @return 两个参数的商  
     *//*   
    public double div(double v1, double v2, int scale) {   
        if (scale < 0) {   
            throw new IllegalArgumentException(   
                    "The scale must be a positive integer or zero");   
        }   
        BigDecimal b1 = new BigDecimal(Double.toString(v1));   
        BigDecimal b2 = new BigDecimal(Double.toString(v2));   
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();   
    }
	public static void main(String[] args) {
	}
}
*/