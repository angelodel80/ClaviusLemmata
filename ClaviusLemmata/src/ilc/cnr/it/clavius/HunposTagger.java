/**
 * 
 */
package ilc.cnr.it.clavius;

import ilc.cnr.it.clavius.constants.HandleContsants;
import ilc.cnr.it.clavius.utils.ClaviusUtils;

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
	private String pathToBin = "";

	private File modelFile;
	private File hunposFile;

	/**
	 * 
	 */
	public HunposTagger(String pathToModel, String pathToBin) {
		init(pathToModel,pathToBin);
	}

	/**
	 * 
	 */
	public HunposTagger() {
		//init(this.pathToModel, this.pathToBin);
		setPathToBin(HandleContsants.getPathToHunPos());
	}

	private void init(String pathToModel, String pathToBin) {
		setPathToModel(HandleContsants.getPathToHunPosModel()+pathToModel);
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
			System.err.println("file not found: "+e.getMessage());
		}finally{}


	}

	public String tag(String msg){
		String verticalMsg;
		StringBuilder vertMsgBuilder = new StringBuilder();
		msg = msg.replaceAll("([\\.,;!?<>:]+)", " $1");
		String tokens[] = msg.split(" ");
		for (String token : tokens) {
			vertMsgBuilder.append(token);
			vertMsgBuilder.append("\n");
		}
		vertMsgBuilder.append("\n");
		verticalMsg = vertMsgBuilder.toString();

		String verticalTagged =  runProcess(verticalMsg);
		return verticalTagged;
	}

	private String runProcess(String inMsg){
		String ret = "";
		ProcessBuilder procBuild = new ProcessBuilder(getPathToBin(), getPathToModel());
		final Process proc;

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
		System.err.println("the path model is: " + getPathToModel());
		System.err.println("the path bin is: " + getPathToBin());
	}

	/**
	 * @return the pathToBin
	 */
	public String getPathToBin() {
		return pathToBin;
	}

	/**
	 * @param pathToBin the pathToBin to set
	 */
	public void setPathToBin(String pathToBin) {
		this.pathToBin = pathToBin;
	}

	/**
	 * @return the pathToModel
	 */
	public String getPathToModel() {
		return pathToModel;
	}

	/**
	 * @param pathToModel the pathToModel to set
	 */
	public void setPathToModel(String pathToModel) {
		this.pathToModel = pathToModel;
	}



}
