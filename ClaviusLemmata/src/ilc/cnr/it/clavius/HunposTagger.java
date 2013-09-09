/**
 * 
 */
package ilc.cnr.it.clavius;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

		String verticalTagged =  runProcess(verticalMsg); //runCommand(""); 
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

		// chiamare funzione per copia stringa
		ret = ClaviusUtils.streamToString(p.getInputStream());
		return ret;
	}

	private String runProcess(String inMsg){
		String ret = "\n********\n";
		ProcessBuilder procBuild = new ProcessBuilder(pathToBin, pathToModel);
		final Process proc;

//		Map<String, String> env = procBuild.environment();
//		StringBuilder outStr = new StringBuilder();
//		for (String envName : env.keySet()) {
//			outStr.append(String.format("%s=%s%n",
//					envName,
//					env.get(envName)));
//		}
//		outStr.append("********");
		//ret = outStr.toString();
		System.out.format("%s",inMsg);

		try {
			
			proc = procBuild.start();
			OutputStream os = proc.getOutputStream();
			
			os.write(inMsg.getBytes());
//			os.write("\n\n".getBytes());
			os.flush();
			os.close();
			proc.waitFor();
			InputStream is = proc.getInputStream();
			ret = ret + "\n" + ClaviusUtils.streamToString(is);
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException ie) {
			// TODO Auto-generated catch block
			ie.printStackTrace();
		}finally{
			
		}

		return ret;
	}

	public void printPath(){
		System.err.println("the path model is: " + this.pathToModel);
		System.err.println("the path bin is: " + this.pathToBin);
		//System.err.println(System.getenv("HOME"));
	}



}
