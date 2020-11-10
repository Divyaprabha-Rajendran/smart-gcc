package com.concordia.soen.hci.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;


public class Compiler_interface_unix {
	static Runtime rt = Runtime.getRuntime();
	
	
	public String readFile(HttpServletRequest request) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(request.getServletContext().getRealPath("") + File.separator + Constants.UPLOAD_DIRECTORY+"//op.txt"));
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
	
	
	public String execute(String[] cmds)
	{
		
		printOutput errorReported, outputMessage;
		String result = "";
		try {

			Process proc1 = rt.exec(cmds);
			errorReported = getStreamWrapper(proc1.getErrorStream(), "ERROR");
			outputMessage = getStreamWrapper(proc1.getInputStream(), "OUTPUT");
			errorReported.start();
			outputMessage.start();
			//result=readFile();
			
		} 
		catch (IOException e){

			e.printStackTrace();
		}
		
		return result;
	}
	
		
	public ModelAndView performOption(String option,ModelAndView mv,HttpServletRequest request) throws IOException, InterruptedException
	{
		switch(option)
		{
		case "Compile" :
		{
			String[] cmds = {"bash","-c","cd "+request.getServletContext().getRealPath("") + File.separator + Constants.UPLOAD_DIRECTORY+"/ && g++ -o output file.cpp"};
			execute(cmds);
			Thread.sleep(5000);
			String result=readFile(request);
			if(result.equals(""))
			{
				mv.addObject("result","compilation was successful");
			}
			else
			{
			mv.addObject("result",result);
			}
			break;
		}
		case "Execute" :
		{
			String[] cmds = {"bash","-c","cd "+request.getServletContext().getRealPath("") + File.separator + Constants.UPLOAD_DIRECTORY+"/ && g++ -o output file.cpp && ./output"};
			execute(cmds);;
			Thread.sleep(5000);
			String result= readFile(request);
			mv.addObject("result",result);
			break;
		}
		case "ConfigurationInfo" :
		{
			String[] cmds = {"bash","-c","cd "+request.getServletContext().getRealPath("") + File.separator + Constants.UPLOAD_DIRECTORY+"/ &&  g++ -v "};
			execute(cmds);
			Thread.sleep(5000);
			String result= readFile(request);
			mv.addObject("result",result);
			break;
		}
		case "CodeGeneration" :
		{
			String[] cmds = {"bash","-c","cd "+request.getServletContext().getRealPath("") + File.separator + Constants.UPLOAD_DIRECTORY+"/ &&  g++ -S file.cpp -fverbose-asm -Os -o -"};
			execute(cmds);
			Thread.sleep(5000);
			String result=readFile(request);
			mv.addObject("result",result);
			break;
		}
		case "CodeOptimisation" :
		{
			
			String[] cmds = {"bash","-c","cd "+request.getServletContext().getRealPath("") + File.separator + Constants.UPLOAD_DIRECTORY+"/ &&  g++ -o output file.cpp -O2 && size output"};
			execute(cmds);
			Thread.sleep(5000);
			String result=readFile(request);
			mv.addObject("result",result);
			break;
		}
		case "RequestWarnings" :
		{
			String[] cmds = {"bash","-c","cd "+request.getServletContext().getRealPath("") + File.separator + Constants.UPLOAD_DIRECTORY+"/ && g++ -Wall file.cpp -o output"};
			execute(cmds);
			Thread.sleep(5000);
			String result=readFile(request);
			mv.addObject("result",result);
			break;
		}
		case "SuppressWarnings" :
		{
			String[] cmds = {"bash","-c","cd "+request.getServletContext().getRealPath("") + File.separator + Constants.UPLOAD_DIRECTORY+"/ && g++ -W file.cpp -o output"};
			execute(cmds);
			Thread.sleep(5000);
			String result=readFile(request);
			mv.addObject("result",result);
			break;
		}
		case "CompilationTime" :
		{
			String[] cmds = {"bash","-c","cd "+request.getServletContext().getRealPath("") + File.separator + Constants.UPLOAD_DIRECTORY+"/ &&  g++ -s -O3 -ftime-report file.cpp -o output"};
			execute(cmds);
			Thread.sleep(5000);
			String result=readFile(request);
			mv.addObject("result",result);
			break;
		}
		default:
		{
			break;
		}
		}
		return mv;
	}
	
	public static printOutput getStreamWrapper(InputStream is, String type) {
		return new printOutput(is, type);
	}

	public static void main(String[] args) 
	{
		//performOption("SuppressWarnings");
	}

}

class printOutput extends Thread {
	InputStream is = null;
	String s = null;
	String filepath = "//Users//nareesh//Downloads//SOEN_6461-master//SmartGCC//src//main//webapp//upload//op.txt";
	FileWriter opWriter=null;
	printOutput(InputStream is, String type) {
		this.is = is;
	}
	public void run() {
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			FileOutputStream outStream = new FileOutputStream(filepath);
			while ((s = br.readLine()) != null) {
				outStream.write(s.getBytes());
				System.out.println(s);	
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
