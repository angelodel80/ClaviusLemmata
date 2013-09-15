/**
 * 
 */
package ilc.cnr.it.clavius.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * @author angelodel80
 *
 */
public class TextUtils {

	/**
	 * 
	 */
	public TextUtils() {
		// TODO Auto-generated constructor stub
	}

	public static Document fileToDocument (String filePath) throws JDOMException, IOException{
		File file = new File(filePath);
		ClaviusUtils.verifyFile(file, true);
		Document doc = null;
		SAXBuilder builder = new SAXBuilder();
		doc = builder.build(file);
		return doc;
	}

	public static void StringToFile(StringBuilder sBuilder, String path){
		BufferedWriter writer = null;
		try{
			File outFile = new File(path);
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
			writer.write(sBuilder.toString());
			
		}catch(FileNotFoundException fe){

		}catch(IOException ioe){

		}finally{
			
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public static Document TabToXml(String tabFile){
		Document doc = null;
		
		return doc;
	}

}
