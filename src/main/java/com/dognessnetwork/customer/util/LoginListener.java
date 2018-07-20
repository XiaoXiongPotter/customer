/*package com.dognessnetwork.customer.util;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.dognessnetwork.customer.domain.PetUser;
@WebListener
public class LoginListener implements HttpSessionListener, ServletContextListener {
	private ServletContext application = null;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub queue
		application = sce.getServletContext();
        Set<Long> onlinePetUserSet = new HashSet<Long>();
        Set<Long> onlineCuatomerSet = new HashSet<Long>();
        Set<Long> queueList = new HashSet<Long>();
        
        application.setAttribute("onlineCuatomerSet", onlineCuatomerSet);
        application.setAttribute("onlinePetUserSet", onlinePetUserSet);
        application.setAttribute("queueList", queueList);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSession session = se.getSession();
        Set<Long> onlineCuatomerSet = (Set<Long>)application.getAttribute("onlineCuatomerSet");
        Set<Long> onlinePetUserSet = (Set<Long>)application.getAttribute("onlinePetUserSet");
        Set<Long> queueList = (Set<Long>)application.getAttribute("queueList");
        
        PetUser customer = (PetUser)session.getAttribute("customer");
        if(customer!=null)onlineCuatomerSet.remove(customer);
        application.setAttribute("onlineCuatomerSet", onlineCuatomerSet);
        onlineCuatomerSet = (Set<Long>)application.getAttribute("onlineCuatomerSet");
        
        PetUser petUser = (PetUser)session.getAttribute("petUser");
        if(petUser!=null)onlinePetUserSet.remove(petUser);
        application.setAttribute("onlinePetUserSet", onlinePetUserSet);
        onlinePetUserSet = (Set<Long>)application.getAttribute("onlinePetUserSet");
        
        PetUser queue = (PetUser)session.getAttribute("queue");
        
        if(queue!=null)queueList.remove(queue.getId());
        application.setAttribute("queueList", queueList);
        queueList = (Set<Long>)application.getAttribute("queueList");
        
	}

}
*/