package com.concordia.soen.hci.controller;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import com.concordia.soen.hci.dao.UserDAO;
import com.concordia.soen.hci.pojo.User;

@Controller
@RequestMapping("/clerk/*")
public class LoginController {

	@Autowired 
	private HttpSession httpSession;
	
	
	@RequestMapping(value ="/clerk/user_login", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView user_login(HttpServletRequest request,
			HttpServletResponse response) throws SAXException, IOException, ParserConfigurationException {
		ArrayList<String> quick_ops = new ArrayList<String>();
		//System.out.println("im here");
		UserDAO dao = new UserDAO();
		String userName;
		ModelAndView mv=new ModelAndView();
		
			userName=request.getParameter("uname");  
			String password=request.getParameter("pswd");
			System.out.println();
			if(dao.check_credentials(userName, password)==true)
			{
				mv.setViewName("editor");
			String msg = " "+userName ;
			String usertype = dao.find_user(userName);
			httpSession.setAttribute("username", userName);
			httpSession.setAttribute("usertype", usertype);
			httpSession.setAttribute("check", "off");
			mv.addObject("message",msg);
			quick_ops=dao.getFrequentOptions(userName);
			mv.addObject("quick_ops",quick_ops);
			System.out.println(userName);
			System.out.println(password);
			System.out.println("success");
			}
			else
			{
				mv.addObject("msg", "Login failed. Try again");
				mv.setViewName("index");

			}
			return mv;
	}
	
	@RequestMapping(value ="/clerk/user_register", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView user_register(HttpServletRequest request,
			HttpServletResponse response) throws SAXException, IOException, ParserConfigurationException {		
		User user = new User();
		UserDAO dao = new UserDAO();
		String userName;
		ModelAndView mv=new ModelAndView();
		userName=request.getParameter("uname");  
		String password=request.getParameter("pswd");
		String usertype = request.getParameter("usertype");
		user.setUserName(userName);
		user.setPassword(password);
		user.setUserType(usertype);
		boolean created=dao.createUser(user);
		mv.setViewName("editor");
		ArrayList<String> quick_ops = new ArrayList<String>();
		quick_ops.add("quick_option");
		quick_ops.add("quick_option");
		quick_ops.add("quick_option");
		mv.addObject("quick_ops",quick_ops);
		httpSession.setAttribute("username", user.getUserName());
		httpSession.setAttribute("usertype", user.getUserType());
		httpSession.setAttribute("option", "Compile");
		String msg = " "+userName ;
		mv.addObject("message",msg);
		return mv;

		
			
	}
	
	
	public ModelAndView showEditor(User user, String userName) {
		ModelAndView mv=new ModelAndView();

		mv.setViewName("editor");
		ArrayList<String> quick_ops = new ArrayList<String>();
		quick_ops.add("quick_option");
		quick_ops.add("quick_option");
		quick_ops.add("quick_option");
		mv.addObject("quick_ops",quick_ops);
		httpSession.setAttribute("username", user.getUserName());
		httpSession.setAttribute("usertype", user.getUserType());
		httpSession.setAttribute("option", "Compile");
		String msg = " "+userName ;
		mv.addObject("message",msg);
		return mv;
		
	}
	
}

