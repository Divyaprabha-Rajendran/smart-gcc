

import java.io.IOException;
import java.io.InputStream;

public class Typical {

	Runtime rt = Runtime.getRuntime();
	public void codegeneration()
	{	
		printOutput errorReported, outputMessage;
		
		try {
	String[] cmds = {"cmd.exe", "/c", "cd C:/MinGW/bin/ &&  g++ -S hello.cpp -fverbose-asm -Os -o -"};

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
	public void Optimisation()
	{	
		printOutput errorReported, outputMessage;
		
		try {
	String[] cmds = {"cmd.exe", "/c", "cd C:/MinGW/bin/ &&  g++ -o hello hello.cpp -O2 && size hello.exe"};

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
		 
		Typical new_main = new Typical();
		new_main.codegeneration();
	
   
	}
}
