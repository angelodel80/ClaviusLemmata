/**
 * 
 */
package ilc.cnr.it.clavius;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Map;

import org.apache.commons.io.*;

/**
 * @author angelodel80
 *
 */
public class HunposTagger {
	
	private String pathToModel = "";
	private String pathToBin = System.getenv("HOME")+System.getProperty("file.separator")+"Risorse"+System.getProperty("file.separator")+"tools"+System.getProperty("file.separator")+"hunpos-1.0-macosx"+System.getProperty("file.separator")+"hunpos-tag";

	private File modelFile;
	private File hunposFile;
	
	/**
	 * 
	 */
	public HunposTagger(String pathToModel, String pathToBin) {
		// TODO Auto-generated constructor stub
		init(pathToModel,pathToBin);
	}
	
	/**
	 * 
	 */
	public HunposTagger() {
		// TODO Auto-generated constructor stub
		//init(this.pathToModel, this.pathToBin);
	}
	
	private void init(String pathToModel, String pathToBin) {
		// TODO Auto-generated method stub
		this.pathToModel = System.getenv("HOME")+System.getProperty("file.separator")+"Risorse"+System.getProperty("file.separator")+"tools"+System.getProperty("file.separator")+pathToModel;
		if ( !("".equals(pathToBin)) && (pathToBin != null) ){
			System.err.println("*****");
			this.pathToBin = pathToBin;
		}
		
		try{
			modelFile = new File(this.pathToModel);
			hunposFile = new File(this.pathToBin);
			if(!modelFile.exists()){
				throw new FileNotFoundException("ModelFile");
			}
			if (!hunposFile.exists()){
				throw new FileNotFoundException("HunposFile");
			}
			
			System.err.println("the files are installed inside the environment");
		}catch(FileNotFoundException e){
			e.printStackTrace();
			System.err.println("file non trovato: "+e.getMessage());
		}finally{}
		
		
	}
	
	public String tag(String msg){
		String verticalMsg;
		StringBuilder vertMsgBuilder = new StringBuilder();
		String tokens[] = msg.split(" ");
		for (String token : tokens) {
			vertMsgBuilder.append(token);
			vertMsgBuilder.append("\n");
		}
		vertMsgBuilder.append("\n");
		verticalMsg = vertMsgBuilder.toString();
		System.out.format("%s",verticalMsg);
		
		
		
		String verticalTagged = runCommand("");
		return verticalTagged;
	}
	
	private String runCommand(String argument){
		String ret = "";
		Runtime rt = java.lang.Runtime.getRuntime();
		Process p;
		
		try {
			 p = rt.exec("ls /Users");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			p = null;
		}
		
		BufferedInputStream binp = new BufferedInputStream(p.getInputStream());
		StringWriter writer = new StringWriter();
		
		
		try {
			IOUtils.copy(binp, writer, Charsets.UTF_8);
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		
		
		return writer.toString();
	}
	
	private String runProcess(String in){
		String ret = "";
		ProcessBuilder procBuild = new ProcessBuilder("ls", "-al","$HOME");
		
		Map<String, String> env = procBuild.environment();
		StringBuilder outStr = new StringBuilder();
		for (String envName : env.keySet()) {
			outStr.append(String.format("%s=%s%n",
                    envName,
                    env.get(envName)));
        }
		
		return ret;
	}
	
	public void printPath(){
		System.err.println("the path model is: " + this.pathToModel);
		System.err.println("the path bin is: " + this.pathToBin);
		//System.err.println(System.getenv("HOME"));
	}
	
	

}
