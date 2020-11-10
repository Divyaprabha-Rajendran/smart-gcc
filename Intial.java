import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Intial 
{
	Runtime rt = Runtime.getRuntime();

	
	public void Suppress_Warnings()
	{
printOutput errorReported, outputMessage;
		
		try {
			String[] cmds = {"cmd.exe", "/c", "cd C:/MinGW/bin/ && g++ -W hello.cpp -o hello"};

			Process proc1 = rt.exec(cmds);
			
			errorReported = getStreamWrapper(proc1.getErrorStream(), "ERROR");
			outputMessage = getStreamWrapper(proc1.getInputStream(), "OUTPUT");
			errorReported.start();
			outputMessage.start();
			
		} 
		catch (IOException e){

			e.printStackTrace();
		}
	}
	public void Request_Warnings()
	{
printOutput errorReported, outputMessage;
		
		try {
			String[] cmds = {"cmd.exe", "/c", "cd C:/MinGW/bin/ && g++ -Wall hello.cpp -o hello"};

			Process proc1 = rt.exec(cmds);
			
			errorReported = getStreamWrapper(proc1.getErrorStream(), "ERROR");
			outputMessage = getStreamWrapper(proc1.getInputStream(), "OUTPUT");
			errorReported.start();
			outputMessage.start();
			
		} 
		catch (IOException e){

			e.printStackTrace();
		}
	}
	public void Configuration_info()
	{	
		printOutput errorReported, outputMessage;
		
		try {
			String[] cmds = {"cmd.exe", "/c", "cd C:/MinGW/bin/ &&  g++ -v "};

			Process proc1 = rt.exec(cmds);
			
			errorReported = getStreamWrapper(proc1.getErrorStream(), "ERROR");
			outputMessage = getStreamWrapper(proc1.getInputStream(), "OUTPUT");
			errorReported.start();
			outputMessage.start();
			
		} 
		catch (IOException e){

			e.printStackTrace();
		}
	}
	public void Compilation_Time()
	{	
		printOutput errorReported, outputMessage;
		
		try {
			String[] cmds = {"cmd.exe", "/c", "cd C:/MinGW/bin/ &&  g++ -s -O3 -ftime-report hello.cpp -o hello.exe "};

			Process proc1 = rt.exec(cmds);
			
			errorReported = getStreamWrapper(proc1.getErrorStream(), "ERROR");
			outputMessage = getStreamWrapper(proc1.getInputStream(), "OUTPUT");
			errorReported.start();
			outputMessage.start();
			
		} 
		catch (IOException e){

			e.printStackTrace();
		}
	}
		
	public printOutput getStreamWrapper(InputStream is, String type) {
		return new printOutput(is, type);
	}

	

	public static void main(String[] args) 
	{
		 
		Intial new_main = new Intial();
		new_main.Compilation_Time();
	
   
	}
}
