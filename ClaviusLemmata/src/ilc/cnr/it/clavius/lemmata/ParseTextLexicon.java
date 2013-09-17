/**
 * 
 */
package ilc.cnr.it.clavius.lemmata;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.xml.sax.ext.LexicalHandler;

/**
 * @author Angelo Del Grosso
 *
 */
public class ParseTextLexicon extends ParseText {

	private static Map<String,String> lexMorph = null;

	public static Map<String, String> getLexMorph() {
		return lexMorph;
	}

	public static void setLexMorph(Map<String, String> lexM) {
		lexMorph = lexM;
	}

	/**
	 * 
	 */
	public ParseTextLexicon() {
		// TODO Auto-generated constructor stub
	}

	public static boolean run(){
		boolean ret = true;
		BufferedReader reader = null;
		outString = new StringBuilder();
		setLexMorph(new TreeMap<String, String>());
		try {
			reader = getReader(myFile);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			leggi(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PrepareOut();
		try {
			scrivi(outString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return ret;

	}

	private static void PrepareOut() {
		// TODO Auto-generated method stub
		for (Map.Entry<String, String> entry : getLexMorph().entrySet())
		{
		    //System.out.println(entry.getKey() + "/" + entry.getValue());
		    outString.append(String.format("%s\t%s\n", entry.getKey(),entry.getValue()));
		}
		//outString.append(str);
	}

	protected static boolean leggi(BufferedReader reader) throws IOException{
		boolean ret = true;
		String line = null;
		while(null != (line = reader.readLine())){
			elabora(line);
		}
		return ret;
	}

	protected static boolean elabora(String line){
		boolean ret = true;
		String[] lexi = line.split(";");
		//System.out.println(String.format("parola: %s \t morpho: %s",lexi[0], lexi[1]));
		String morpho = getLexMorph().get(lexi[0].replaceAll("\"", ""));
		if(null == morpho )
			morpho = "";
		getLexMorph().put(lexi[0].replaceAll("\"", ""), morpho+"\t"+lexi[1].replaceAll("\"", ""));
		return ret;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (init(args[0], args[1]))
			run();

	}

}
