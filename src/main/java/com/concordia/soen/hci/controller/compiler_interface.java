//package com.concordia.soen.hci.controller;
//
//
//import java.io.BufferedReader;
//import java.io.FileOutputStream;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.PrintWriter;
//
//
//public class compiler_interface {
//	static Runtime rt = Runtime.getRuntime();
//
//public static void Suppress_Warnings(String file)
//	{
//printOutput errorReported, outputMessage;
//		
//		try {
//			String[] cmds = {"cmd.exe", "/c", "cd C:/MinGW/bin/ && g++ -W "+file+".cpp -o "+file};
//
//			Process proc1 = rt.exec(cmds);
//			
//			errorReported = getStreamWrapper(proc1.getErrorStream(), "ERROR");
//			outputMessage = getStreamWrapper(proc1.getInputStream(), "OUTPUT");
//			errorReported.start();
//			outputMessage.start();
//			
//		} 
//		catch (IOException e){
//
//			e.printStackTrace();
//		}
//	}
//	public static void Request_Warnings(String file)
//	{
//printOutput errorReported, outputMessage;
//		
//		try {
//			String[] cmds = {"cmd.exe", "/c", "cd C:/MinGW/bin/ && g++ -Wall "+file+".cpp -o "+file};
//
//			Process proc1 = rt.exec(cmds);
//			
//			errorReported = getStreamWrapper(proc1.getErrorStream(), "ERROR");
//			outputMessage = getStreamWrapper(proc1.getInputStream(), "OUTPUT");
//			errorReported.start();
//			outputMessage.start();
//			
//		} 
//		catch (IOException e){
//
//			e.printStackTrace();
//		}
//	}
//	public static void Configuration_info()
//	{	
//		printOutput errorReported, outputMessage;
//		
//		try {
//			String[] cmds = {"cmd.exe", "/c", "cd C:/MinGW/bin/ &&  g++ -v "};
//
//			Process proc1 = rt.exec(cmds);
//			
//			errorReported = getStreamWrapper(proc1.getErrorStream(), "ERROR");
//			outputMessage = getStreamWrapper(proc1.getInputStream(), "OUTPUT");
//			errorReported.start();
//			outputMessage.start();
//			
//		} 
//		catch (IOException e){
//
//			e.printStackTrace();
//		}
//	}
//	public static void Compilation_Time(String file)
//	{	
//		printOutput errorReported, outputMessage;
//		
//		try {
//			String[] cmds = {"cmd.exe", "/c", "cd C:/MinGW/bin/ &&  g++ -s -O3 -ftime-report "+file+".cpp -o "+file+".exe"};
//
//			Process proc1 = rt.exec(cmds);
//			
//			errorReported = getStreamWrapper(proc1.getErrorStream(), "ERROR");
//			outputMessage = getStreamWrapper(proc1.getInputStream(), "OUTPUT");
//			errorReported.start();
//			outputMessage.start();
//			
//		} 
//		catch (IOException e){
//
//			e.printStackTrace();
//		}
//	}
//	
//	public static void Compilation_Time_Mac(String file)
//	{	
//		printOutput errorReported, outputMessage;
//		
//		try {
//			//String[] cmds = {"cmd.exe", "/c", "cd C:/MinGW/bin/ &&  g++ -s -O3 -ftime-report "+file+".cpp -o "+file+".exe"};
//			String[] cmds = {"bash", "-c", "cd /Users/nareesh/Downloads/SOEN_6461-master/SmartGCC/src/main/webapp/upload/ &&  g++ -s -O3 -ftime-report hello.cpp -o comp_op"};
//			Process proc1 = rt.exec(cmds);
//			
//			errorReported = getStreamWrapper(proc1.getErrorStream(), "ERROR");
//			outputMessage = getStreamWrapper(proc1.getInputStream(), "OUTPUT");
//			errorReported.start();
//			outputMessage.start();
//			
//		} 
//		catch (IOException e){
//
//			e.printStackTrace();
//		}
//	}
//	
//	public static void compile(String file)
//	{	
//		printOutput errorReported, outputMessage;
//		
//		try {
//			String[] cmds = {"cmd.exe", "/c", "cd C:/MinGW/bin/ && g++ -o "+file+".exe  "+file+".cpp"};
//
//			Process proc1 = rt.exec(cmds);
//			//Process proc2 = rt.exec("./a.out");
//			errorReported = getStreamWrapper(proc1.getErrorStream(), "ERROR");
//			outputMessage = getStreamWrapper(proc1.getInputStream(), "OUTPUT");
//			errorReported.start();
//			outputMessage.start();
//			//execute();
//		} 
//		catch (IOException e){
//
//			e.printStackTrace();
//		}
//	}
//	
//	public static void compile_mac(String file)
//	{	
//		printOutput errorReported, outputMessage;
//		
//		try {
//			//String[] cmds = {"cmd.exe", "/c", "cd C:/MinGW/bin/ && g++ -o "+file+".exe  "+file+".cpp"};
//			String[] cmds = {"bash","-c","cd /Users/nareesh/Downloads/SOEN_6461-master/SmartGCC/src/main/webapp/upload/ && g++ -o output hello.cpp"};
//			Process proc1 = rt.exec(cmds);
//			//Process proc2 = rt.exec("./a.out");
//			errorReported = getStreamWrapper(proc1.getErrorStream(), "ERROR");
//			outputMessage = getStreamWrapper(proc1.getInputStream(), "OUTPUT");
//			errorReported.start();
//			outputMessage.start();
//			//execute();
//		} 
//		catch (IOException e){
//
//			e.printStackTrace();
//		}
//	}
//	
//	public static void execute_mac(String file)
//	{
//		
//		printOutput errorReported, outputMessage;
//		
//		try {
//			String[] cmds = {"bash","-c","cd /Users/nareesh/Downloads/SOEN_6461-master/SmartGCC/src/main/webapp/upload/ && g++ -o output1 hello.cpp && ./output1 > this_op.txt"};
//
//			Process proc1 = rt.exec(cmds);
//			//Process proc2 = rt.exec("./a.out");
//			errorReported = getStreamWrapper(proc1.getErrorStream(), "ERROR");
//			outputMessage = getStreamWrapper(proc1.getInputStream(), "OUTPUT");
//			errorReported.start();
//			outputMessage.start();
//			//System.out.println(errorReported.returnResult());
//			//System.out.println("here "+outputMessage.returnResult());
//		} 
//		catch (IOException e){
//
//			e.printStackTrace();
//		}
//	}
//	
//	public static void execute(String file)
//	{
//		
//		printOutput errorReported, outputMessage;
//		
//		try {
//			String[] cmds = {"cmd.exe", "/c", "cd C:/MinGW/bin/ &&" +file+ ".exe"};
//
//			Process proc1 = rt.exec(cmds);
//			//Process proc2 = rt.exec("./a.out");
//			errorReported = getStreamWrapper(proc1.getErrorStream(), "ERROR");
//			outputMessage = getStreamWrapper(proc1.getInputStream(), "OUTPUT");
//			errorReported.start();
//			outputMessage.start();
//			System.out.println("here "+errorReported.returnResult());
//			System.out.println("here "+outputMessage.returnResult());
//		} 
//		catch (IOException e){
//
//			e.printStackTrace();
//		}
//	}
//	public static void codegeneration(String file)
//	{	
//		printOutput errorReported, outputMessage;
//		
//		try {
//	String[] cmds = {"cmd.exe", "/c", "cd C:/MinGW/bin/ &&  g++ -S "+file+".cpp -fverbose-asm -Os -o -"};
//
//			Process proc1 = rt.exec(cmds);
//			
//			errorReported = getStreamWrapper(proc1.getErrorStream(), "ERROR");
//			outputMessage = getStreamWrapper(proc1.getInputStream(), "OUTPUT");
//			errorReported.start();
//			outputMessage.start();
//			
//		} 
//		catch (IOException e){
//
//			e.printStackTrace();
//		}
//	}
//	public static void Optimisation(String file)
//	{	
//		printOutput errorReported, outputMessage;
//		
//		try {
//	String[] cmds = {"cmd.exe", "/c", "cd C:/MinGW/bin/ &&  g++ -o "+file+" "+file+".cpp -O2 && size "+file+".exe"};
//
//			Process proc1 = rt.exec(cmds);
//			
//			errorReported = getStreamWrapper(proc1.getErrorStream(), "ERROR");
//			outputMessage = getStreamWrapper(proc1.getInputStream(), "OUTPUT");
//			errorReported.start();
//			outputMessage.start();
//			
//		} 
//		catch (IOException e){
//
//			e.printStackTrace();
//		}
//	}
//	
//	public static void performOption(String file,String option)
//	{
//		switch(option)
//		{
//		case "Compile" :
//		{
//			//write a function to handle it.
//			//"bash", "-c", "./a.out" change this string appropriately with the file name and commands
//			compile(file);
//			break;
//		}
//		case "Execute" :
//		{
//			execute(file);
//			break;
//		}
//		case "ConfigurationInfo" :
//		{
//			Configuration_info();
//			break;
//		}
//		case "CodeGeneration" :
//		{
//			codegeneration(file);
//			break;
//		}
//		case "CodeOptimisation" :
//		{
//			
//			Optimisation(file);
//			break;
//		}
//		case "RequestWarnings" :
//		{
//			Request_Warnings(file);
//			break;
//		}
//		case "SuppressWarnings" :
//		{
//			Suppress_Warnings(file);
//			break;
//		}
//		case "CompilationTime" :
//		{
//			Compilation_Time(file);
//			break;
//		}
//		default:
//		{
//			break;
//		}
//		}
//	}
//	
//	public static printOutput getStreamWrapper(InputStream is, String type) {
//		return new printOutput(is, type);
//	}
//
//	public static void main(String[] args) 
//	{
//		compiler_interface new_main = new compiler_interface();
//		String file = "hello";
//		String option = "hello";
//		//performOption( file, option);
//		//compile_mac("");
//		//execute_mac("");
//		Compilation_Time_Mac("");
//	}
//
//}
//class printOutput1 extends Thread {
//	InputStream is = null;
//	String s = null;
//	String filepath = "//Users//nareesh//Downloads//SOEN_6461-master//SmartGCC//src//main//webapp//upload//op.txt";
//	FileWriter opWriter=null;
//	printOutput1(InputStream is, String type) {
//		this.is = is;
//	}
//	public String returnResult()
//	{
//		return this.s;
//	}
//	public void run() {
//		
//		try {
//			BufferedReader br = new BufferedReader(new InputStreamReader(is));
//			FileOutputStream outStream = new FileOutputStream(filepath);
//			while ((s = br.readLine()) != null) {
//				outStream.write(s.getBytes());
//				//outStream.
//				//outStream.newLine();
//				System.out.println(s);
//				
//			}
//		} catch (IOException ioe) {
//			ioe.printStackTrace();
//		}
//	}
//}
