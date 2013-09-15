/**
 * 
 */
package ilc.cnr.it.clavius.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.filter.ElementFilter;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * @author angelodel80
 *
 */
public class ClaviusUtils {

	/**
	 * 
	 */
	public ClaviusUtils() {
		// TODO Auto-generated constructor stub
	}

	public static void verifyFile( File aFile, boolean w ) {

		if (aFile == null) {
			throw new IllegalArgumentException("File should not be null.");
		}

		if (!aFile.exists()) {
			throw new IllegalArgumentException ("File does not exist: " + aFile);
		}
		if (!aFile.isFile()) {
			throw new IllegalArgumentException("Should not be a directory: " + aFile);
		}
		if (w && !aFile.canWrite()) {
			throw new IllegalArgumentException("File cannot be written: " + aFile);
		}

		System.out.println("verifying file: " + aFile.getAbsolutePath() + "\n");
	}

	public static String streamToString(InputStream is){
		String ret = "";
		if(null != is) {
			BufferedInputStream binp = new BufferedInputStream(is);
			StringWriter writer = new StringWriter();

			try {
				IOUtils.copy(binp, writer, Charsets.UTF_8);
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}


			ret = writer.toString();
			try {
				binp.close();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ret;

	}

	public static List<Element> makeDocs(BufferedReader reader) {
		// TODO Auto-generated method stub
		List<Element> docs = new ArrayList<Element>();
		String line = "";
		int wordcount = 0;
		Element doc = null;

		try {
			while (null != (line = reader.readLine() )){
				if(line.matches("")){
					System.err.println("ritorno a capo");
				}
				else if( (line.split("\t").length) < 2){
					if(null != doc)
						docs.add(doc);
					System.err.println("testo della sentence: " + line);
					wordcount = 0;
					List<Element> fields = makeFields(10);
					handleSentenceFields(line,fields);
					doc = new Element("doc").addContent(fields);
				}
				else {
					System.err.println("riga di analisi: " + line);
					wordcount++;
					handleAnalysisFields(line,doc,wordcount);
					//FIXME attenzione gestire ultimo elemento
				}
			}
			//docs.add(doc);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return docs;
	}

	private static void handleAnalysisFields(String line, Element doc, int count) {
		// TODO Auto-generated method stub
		try{
			doc.getChildren().get(8).addContent(
					makeElement(line.split("\t")[0], line.split("\t")[1], line.split("\t")[2],count));
			doc.getChildren().get(9).addContent(
					makeElement(line.split("\t")[0], line.split("\t")[1] + line.substring(line.lastIndexOf(9)), line.split("\t")[2],count));
		}catch(ArrayIndexOutOfBoundsException e){
			System.err.println(e.getMessage());
			e.printStackTrace();
			doc.getChildren().get(8).addContent(
					makeElement(line.split("\t")[0], line.split("\t")[1], line.split("\t")[0].toLowerCase()+"*",count));
			doc.getChildren().get(9).addContent(
					makeElement(line.split("\t")[0], line.split("\t")[1] + line.substring(line.lastIndexOf(9)), line.split("\t")[0].toLowerCase()+"*",count));

		}


	}
	
	private static Element makeElement(String token, String pos, String lemma,  int count){
		return new Element("w").setAttribute("prog", String.valueOf(count))
				.setAttribute("form", token.toLowerCase())
				.setAttribute("pos", pos)
				.setAttribute("lemma", lemma)
				.setAttribute("token",token);
	}

	private static void handleSentenceFields(String line, List<Element> fields) {
		// TODO Auto-generated method stub
		fields.get(0).getAttribute("name").setValue("id");
		fields.get(0).setText(line.substring(0, line.indexOf(":")));
		fields.get(1).getAttribute("name").setValue("sentence_txt");
		fields.get(1).setText(line.substring(line.indexOf(":")+1));
		fields.get(2).getAttribute("name").setValue("image_url");
		fields.get(2).setText("http://");
		fields.get(3).getAttribute("name").setValue("sentence_id");
		fields.get(3).setText(line.substring(0, line.indexOf(":")));
		fields.get(4).getAttribute("name").setValue("image_id");
		fields.get(4).setText(line.substring(0, line.indexOf(":")));
		fields.get(5).getAttribute("name").setValue("info_sentence");
		fields.get(5).setText(line.substring(0, line.indexOf(":")));
		fields.get(6).getAttribute("name").setValue("info_image");
		fields.get(6).setText(line.substring(0, line.indexOf(":")));
		fields.get(7).getAttribute("name").setValue("nota");
		fields.get(7).setText(line.substring(0, line.indexOf(":")));
		fields.get(8).getAttribute("name").setValue("sentence_analysis");
		fields.get(9).getAttribute("name").setValue("sentence_analysis2");
	}

	private static List<Element> makeFields(int n) {
		// TODO Auto-generated method stub
		List<Element> fields = new ArrayList<Element>(n);
		for(int i = 0; i<n; i++){
			Element ele = new Element("field").setAttribute("name", String.valueOf(i));
			fields.add(ele);
		}
		return fields;
	}

	public static void makeSentenceXML(Document xmlSentences) {
		// TODO Auto-generated method stub
		System.out.println("in makesentenceXML");
		XMLOutputter xo = new XMLOutputter(Format.getPrettyFormat());
		Element root = xmlSentences.getRootElement();
		List<Element> docs = root.getChildren("doc");
		System.out.println("in makesentenceXML: size list: " +docs.size());
		for (Element doc : docs) {
			Element tmp_doc = doc.clone();
			System.out.println(doc.getChildText("field"));
			try {//new Document().setRootElement(new Element("add").addContent(doc.detach()))
			xo.output(new Document().setRootElement(new Element("add").setContent(tmp_doc)), 
					new FileWriter("/Users/angelodel80/Risorse/sources/clavius/"+"Letter147_an-"+doc.getChildText("field")+".xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		}
		
	}

	//public static Document 

}
