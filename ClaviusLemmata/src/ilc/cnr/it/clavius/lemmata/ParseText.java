
/**
 * 
 */
package ilc.cnr.it.clavius.lemmata;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * @author Angelo Del Grosso
 *
 */
public class ParseText {

	protected static File myFile = null;
	protected static File outFile = null;
	protected static StringBuilder outString = null;
	
	/**
	 * @param args
	 */
	public static boolean init(String fileIn, String fileOut){
		boolean ret = false;
		myFile = new File(fileIn);
		outFile = new File(fileOut);
		try {
			System.err.println(myFile.getAbsolutePath());
			ret = true;
		} catch (Exception e) {
			System.err.println(e.getCause());
		} finally{
			System.out.println("finally");
		}
		return ret;
	}
	
	protected static BufferedReader getReader(File inFile) throws UnsupportedEncodingException, FileNotFoundException{
		return new BufferedReader(new InputStreamReader( new FileInputStream(inFile), "utf-8"));
	}
	
	public static boolean run(){
		boolean ret = false;
		BufferedReader reader = null;
		try {
			System.err.println(myFile.getAbsolutePath());
			reader = getReader(myFile);
			leggi (reader);
			scrivi (outString);
			ret = true;
		} catch (Exception e) {
			System.err.println(e.getCause());
		} finally{
			//TODO;
			try {
				reader.close();
				System.err.println("chiuso reader");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return ret;
	}
	
	protected static void scrivi(StringBuilder outStr) throws IOException, FileNotFoundException {
		// TODO Auto-generated method stub
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
		writer.write(outString.toString());
		writer.flush();
		writer.close();
		
	}

	private static void leggi(BufferedReader reader) throws IOException {
		// TODO Auto-generated method stub
		outString = new StringBuilder("");
		Integer nLine = new Integer(0);
		Integer count = new Integer(0);
		String line = "";
		while((line = reader.readLine())!=null){
			// System.out.println(line);
			nLine = Integer.valueOf(nLine.intValue() + 1);
			String[] tabs = line.split("\t");
			if(tabs.length!=10){
				String strLine ="";
				count = Integer.valueOf(count.intValue() +1);
				strLine = "[line:"+nLine.intValue()+"]\t" + tabs.length +'\n';
				System.out.println(strLine);
				outString.append(strLine);
			}
		}
		String strTot = "total anomalies: " + count.intValue();
		System.out.println(strTot);
		outString.append(strTot);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length==2){
			System.out.println(init(args[0], args[1]));
			System.out.println(run());
			}else
				System.err.println("problem with arguments!");

	}

}
