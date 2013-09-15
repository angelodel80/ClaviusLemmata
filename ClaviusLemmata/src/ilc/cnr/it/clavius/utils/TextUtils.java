/**
 * 
 */
package ilc.cnr.it.clavius.utils;

import ilc.cnr.it.clavius.lemmata.ParseText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

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
	
//	public static BufferedReader getReader(File inFile) throws UnsupportedEncodingException, FileNotFoundException{
//		return new BufferedReader(new InputStreamReader( new FileInputStream(inFile), "utf-8"));
//	}
	
	public static Document TabToXml(String tabFile, boolean aporia) throws JDOMException, IOException{
		Document doc = null;
		BufferedReader reader = null;
		File tFile = new File(tabFile);
		ClaviusUtils.verifyFile(tFile, true);
		if(!aporia){
			throw new JDOMException("This funcion works only for aporia XML document");
		}
		reader = ParseText.getReader(tFile);
		
		List<Element> docs = ClaviusUtils.makeDocs(reader);
		
		Element addNode = new Element("add").addContent(docs);
		doc = new Document(addNode);
		XMLOutputter xo = new XMLOutputter(Format.getPrettyFormat());
		xo.output(doc, new FileWriter(tabFile.substring(0, tabFile.lastIndexOf("/"))+"/Letter147_sentences_Analysed.xml"));
		return doc;
	}

}
