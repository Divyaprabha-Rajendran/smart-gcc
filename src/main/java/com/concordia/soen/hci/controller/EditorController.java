package com.concordia.soen.hci.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import com.concordia.soen.hci.dao.UserDAO;


@Controller
@RequestMapping("/editor/*")


@MultipartConfig(maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)


public class EditorController {
	
	@Autowired 
	private HttpSession httpSession;
	
	private static final long serialVersionUID = 1L;
	
	public String readFile(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
	
	public void writeFile(String code, HttpServletRequest request )
	{
	    try {  
	    	String uploadPath = request.getServletContext().getRealPath("") + File.separator + Constants.UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            System.out.println(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String filePath = uploadPath + File.separator + "file.cpp";
	        FileWriter myWriter = new FileWriter(filePath);
	        myWriter.write(code);
	        myWriter.close();
	        System.out.println("Successfully wrote to the file.");
	      } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      } 

	}
	
	@RequestMapping(value = "/editor/readfile", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView readFile(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException, ParserConfigurationException, SAXException
	{   
		String fileName="";
		String filePath="";
	        if (ServletFileUpload.isMultipartContent(request)) {

	            DiskFileItemFactory factory = new DiskFileItemFactory();
	            factory.setSizeThreshold(Constants.MEMORY_THRESHOLD);
	            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

	            ServletFileUpload upload = new ServletFileUpload(factory);
	            upload.setFileSizeMax(Constants.MAX_FILE_SIZE);
	            upload.setSizeMax(Constants.MAX_REQUEST_SIZE);
	            String uploadPath = request.getServletContext().getRealPath("") + File.separator + Constants.UPLOAD_DIRECTORY;
	            File uploadDir = new File(uploadPath);
	            System.out.println(uploadPath);
	            if (!uploadDir.exists()) {
	                uploadDir.mkdir();
	            }

	            try {
	                List<FileItem> formItems = upload.parseRequest(request);

	                if (formItems != null && formItems.size() > 0) {
	                    for (FileItem item : formItems) {
	                        if (!item.isFormField()) {
	                            fileName = new File(item.getName()).getName();
	                            filePath = uploadPath + File.separator + fileName;
	                            File storeFile = new File(filePath);
	                            item.write(storeFile);
	                            request.setAttribute("message", httpSession.getAttribute("username"));
	                            System.out.println(fileName);
	            	            System.out.println(uploadPath);
	            	            System.out.println("File " + fileName + " has uploaded successfully!");
	                        }
	                    }
	                }
	            } catch (Exception ex) {
	                request.setAttribute("message", httpSession.getAttribute("username"));
	            }
	            
	        }
	    String code = readFile(filePath);
		ModelAndView mv=new ModelAndView();
		mv.setViewName("editor");
		UserDAO dao = new UserDAO();
		ArrayList<String> quick_ops = new ArrayList<String>();
		quick_ops=dao.getFrequentOptions(httpSession.getAttribute("username").toString());
		mv.addObject("quick_ops",quick_ops);
		mv.addObject("code",code);
		return mv;
	}
	
	@RequestMapping(value = "/editor/readcode", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView readCode(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException, InterruptedException, ParserConfigurationException, SAXException
	{
		ArrayList<String> quick_ops = new ArrayList<String>();
		
		ModelAndView mv=new ModelAndView();
		mv.setViewName("editor");
		
		String code=request.getParameter("code_editor");
		writeFile(code, request);
		String usertype=request.getParameter("usertype");
		String option="";
		
		String username = (String) httpSession.getAttribute("username");
		System.out.println(request.getParameter("quick_options"));
		if(request.getParameter("quick_options")==null)
		{		
		if(usertype.equals("Beginner"))
		{
			option=request.getParameter("novice_option");
		}
		else if (usertype.equals("Typical"))
		{
			option=request.getParameter("inter_option");
		}
		else if(usertype.equals("Expert"))
		{
			option=request.getParameter("expert_option");
		}
		
		System.out.println(option);
		}
		else
		{
			option=request.getParameter("quick_value");
			httpSession.setAttribute("quick_options", "on");
		}
		
		httpSession.setAttribute("usertype", usertype);
		httpSession.setAttribute("option", option);
		
		Compiler_interface_unix inter=new Compiler_interface_unix();
		mv = inter.performOption(option,mv,request);
		
		mv.addObject("message",httpSession.getAttribute("username"));
		mv.addObject("code",code);
		
		UserDAO user_dao = new UserDAO();
		boolean success=user_dao.editFrequentOptions(httpSession.getAttribute("username").toString(), option);
		if(success)
		{
			quick_ops=user_dao.getFrequentOptions(username);
			//System.out.println(quick_ops);
		}
		System.out.println(quick_ops);
		mv.addObject("quick_ops",quick_ops);
		
		return mv;
		
	}
	
	@RequestMapping(value = "/editor/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public void logout(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException, ParserConfigurationException, SAXException
	{
		System.out.println("logout");
		response.sendRedirect("/root");	
	}
	

}
