package java_compiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class main {
	Runtime rt = Runtime.getRuntime();
	
	public void compile()
	{	
		printOutput errorReported, outputMessage;
		
		try {
			String[] cmds = {"bash", "-c", "g++ hello.cpp"};

			Process proc1 = rt.exec(cmds);
			//Process proc2 = rt.exec("./a.out");
			errorReported = getStreamWrapper(proc1.getErrorStream(), "ERROR");
			outputMessage = getStreamWrapper(proc1.getInputStream(), "OUTPUT");
			errorReported.start();
			outputMessage.start();
			//execute();
		} 
		catch (IOException e){

			e.printStackTrace();
		}
	}
	
	public void execute()
	{
printOutput errorReported, outputMessage;
		
		try {
			String[] cmds = {"bash", "-c", "./a.out"};

			Process proc1 = rt.exec(cmds);
			//Process proc2 = rt.exec("./a.out");
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
		main new_main = new main();
		new_main.compile();
		
		

	}

}
class printOutput extends Thread {
	InputStream is = null;

	printOutput(InputStream is, String type) {
		this.is = is;
	}

	public void run() {
		String s = null;
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(is));
			while ((s = br.readLine()) != null) {
				System.out.println(s);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
