/**
 * 
 */
package ilc.cnr.it.clavius.utils;

import ilc.cnr.it.clavius.constants.HandleConstants;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.mysql.jdbc.DocsConnectionPropsHelper;

/**
 * @author angelodel80
 *
 */
public class ClaviusUtils {

	/**
	 * 
	 */
	public ClaviusUtils() {
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
				ex.printStackTrace();
			}


			ret = writer.toString();
			try {
				binp.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ret;

	}

	public static List<Element> makeDocs(BufferedReader reader) {
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
				}
			}
			docs.add(doc);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return docs;
	}

	private static void handleAnalysisFields(String line, Element doc, int count) {
		try{
			doc.getChildren().get(8).addContent(
					makeElement(line.split("\t")[0], line.split("\t")[1], line.split("\t")[2],count,doc.getChildren("field").get(1).getText()));
			doc.getChildren().get(9).addContent(
					makeElement(line.split("\t")[0], line.split("\t")[1] + line.substring(line.lastIndexOf(9)), line.split("\t")[2],count,doc.getChildren("field").get(1).getText()));
		}catch(ArrayIndexOutOfBoundsException e){
			//System.err.println(e.getMessage());
			//e.printStackTrace();
			doc.getChildren().get(8).addContent(
					makeElement(line.split("\t")[0], line.split("\t")[1], line.split("\t")[0].substring(line.split("\t")[0].indexOf('@')+1, line.split("\t")[0].indexOf('[')).toLowerCase()+"*",count,doc.getChildren("field").get(1).getText()));
			doc.getChildren().get(9).addContent(
					makeElement(line.split("\t")[0], line.split("\t")[1] + line.substring(line.lastIndexOf(9)),line.split("\t")[0].substring(line.split("\t")[0].indexOf('@')+1, line.split("\t")[0].indexOf('[')).toLowerCase()+"*",count,doc.getChildren("field").get(1).getText()));

		}
	}

	private static Element makeElement(String token, String pos, String lemma,  int count, String sentence){
		return new Element("w").setAttribute("prog", String.valueOf(count))
				.setAttribute("form", token.substring(token.indexOf('@')+1, token.indexOf('[')).toLowerCase())
				.setAttribute("pos", pos)
				.setAttribute("lemma", lemma)
				.setAttribute("token",token.substring(token.indexOf('@')+1, token.indexOf('[')))
				.setAttribute("extended",token)
				.setAttribute("start", String.valueOf(handleOffset(token,sentence,0)))
				.setAttribute("end", String.valueOf(handleOffset(token,sentence,1)));
	}
	/* soe : start=0 oppure end=1 */
	public static int handleOffset(String ctsToken, String sentence, int soe){
		int ret = 0;
		int count = 0;
		int times = Integer.parseInt(ctsToken.substring(ctsToken.indexOf('[')+1, ctsToken.indexOf(']')));
		System.err.println(times);
		String literalToken = ctsToken.substring(ctsToken.indexOf('@')+1, ctsToken.indexOf('['));
		String patternQuote = "";
		if(literalToken.matches("\\p{Punct}")){
			System.out.println("in punct:" + literalToken);
			patternQuote = Pattern.quote(literalToken);
		}
		else{
			patternQuote = "\\b"+Pattern.quote(literalToken)+"\\b";	
		}

		System.out.println(patternQuote);
		System.out.println(sentence);
		// FIXME attenzione la regex deve avere i boudary del token, altrimenti matcha alche le sottostringhe falsando gli offset
		Pattern pa = Pattern.compile(patternQuote);
		Matcher ma = pa.matcher(sentence);
		System.out.println(pa.pattern());
		/* se il token esiste devo prendere la sua giusta occorrenza, quindi scorro i founds finchè non raggiungo l'occorrenza corretta valutando la variabile times */
		while(ma.find()){
			count = count +1;
			if(count == times){
				if(0==soe)
					ret = ma.start();
				else
					ret = ma.end();
			}
		}
		//			System.err.println(ma.group());
		//			System.err.println(ma.start());
		//			System.err.println(ma.end());
		return ret;
	}

	private static void handleSentenceFields(String line, List<Element> fields) {
		/* be care the file in input has to start with the correct information with out any header*/

		fields.get(0).getAttribute("name").setValue("id");
		fields.get(0).setText(line.substring(line.indexOf("s_")+2, line.indexOf(":: ")));
		fields.get(1).getAttribute("name").setValue("sentence_txt");
		fields.get(1).setText(line.substring(line.indexOf(":: ")+3));
		fields.get(2).getAttribute("name").setValue("image_url");
		//fields.get(2).setText(HandleConstants.getLetterRif()+"-"+line.substring(line.indexOf("s_"), line.indexOf(":: "))+".png"); OK for Clavius
		fields.get(2).setText(HandleConstants.getLetterRif()+"-"+"xyz.png"); // OK for Virgilius
		fields.get(3).getAttribute("name").setValue("sentence_id");
		fields.get(3).setText(line.substring(0, line.indexOf(":: ")));
		fields.get(4).getAttribute("name").setValue("image_id");
		fields.get(4).setText("CITE for " + line.substring(0, line.indexOf(":: ")));
		fields.get(5).getAttribute("name").setValue("info_sentence");
		fields.get(5).setText("additional information for sentence " + line.substring(0, line.indexOf(":: ")));
		fields.get(6).getAttribute("name").setValue("info_image");
		fields.get(6).setText("additional information for image " + line.substring(0, line.indexOf(":: ")));
		fields.get(7).getAttribute("name").setValue("nota");
		fields.get(7).setText(line.substring(0, line.indexOf(":: ")));
		fields.get(8).getAttribute("name").setValue("sentence_analysis");
		fields.get(9).getAttribute("name").setValue("sentence_analysis2");
	}

	private static List<Element> makeFields(int n) {
		List<Element> fields = new ArrayList<Element>(n);
		for(int i = 0; i<n; i++){
			Element ele = new Element("field").setAttribute("name", String.valueOf(i));
			fields.add(ele);
		}
		return fields;
	}

	private static List<Element> docsSentences(Element root){
		List<Element> sentences = null;
		if(null!=root){
			sentences = root.getChildren("doc");
		}
		return sentences;
	}

	public static void makeSentenceXML(Document xmlSentences) {
		System.out.println("in makesentenceXML");
		XMLOutputter xo = new XMLOutputter(Format.getPrettyFormat());
		Element root = xmlSentences.getRootElement();
		List<Element> docs = docsSentences(root);
		System.out.println("in makesentenceXML: size list: " +docs.size());
		for (Element doc : docs) {
			Element tmp_doc = doc.clone();
			System.out.println(doc.getChildText("field"));
			try {
				xo.output(new Document().setRootElement(new Element("add").setContent(tmp_doc)), 
						new FileWriter( HandleConstants.getWorkDir()+ "Letter"+HandleConstants.getLetterRif() +"_an-"+doc.getChildText("field")+".xml"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public static void makeIntegrationXMLforAnalysis(Document xmlDoc){
		System.out.println("in makeIntegrationXMLforAnalysis");
		Element root = xmlDoc.getRootElement();
		XMLOutputter xo = new XMLOutputter(Format.getPrettyFormat());
		List<Element> docs = docsSentences(root);
		List<Element> sentecesAnalysis = new ArrayList<Element>();
		List<Element> tokens = new ArrayList<Element>();
		Integer sentenceOffset = new Integer(0);
		for (Element doc : docs) {
			Element sentenceAnalysis = new Element("sentence");
			PopulateSentenceAnalysis(sentenceAnalysis, doc);
			sentecesAnalysis.add(sentenceAnalysis);
			//sentence offset is for handling start end in a letter scope instead in a sentence scope
			sentenceOffset = PopulateTokens(tokens,doc,sentenceOffset);
		}
		try {	
			xo.output(new Document().setRootElement(new Element("linguistical_analysis").setContent(sentecesAnalysis)), new FileWriter(HandleConstants.getWorkDir()+ "Letter"+HandleConstants.getLetterRif() +"_an.xml"));
		}catch(IOException e){
			e.printStackTrace();
		}
		try{
			xo.output(new Document().setRootElement(new Element("tokens").setAttribute("uri", "").setContent(tokens)), new FileWriter(HandleConstants.getWorkDir()+ "Letter"+HandleConstants.getLetterRif() +"_tokens.xml"));
		}catch(IOException e){
			e.printStackTrace();
		}

	}

	private static List<Element> getWordFromXmldoc(Element docxml){
		return docxml.getChildren().get(8).getChildren();
	}
	private static String getCtsuri(Element docxml){
		return docxml.getChildren().get(3).getText();
	}

	private static String handleSentenceOffSet(Integer offset, String base){
		String ret = null;
		int baseInt = Integer.valueOf(base).intValue();
		ret = String.valueOf(baseInt+offset.intValue());
		return ret;
	}

	private static Integer PopulateTokens(List<Element> toks,Element doc,Integer Offset){
		List<Element> words = getWordFromXmldoc(doc);
		int localOffset = 0;
		for (Element w : words) {
			toks.add(new Element("token")
			.setAttribute("uri", w.getAttributeValue("extended"))
			.setAttribute("start",handleSentenceOffSet(Offset,w.getAttributeValue("start")))
			.setAttribute("end", handleSentenceOffSet(Offset,w.getAttributeValue("end")))
			.addContent(w.getAttributeValue("token"))
					);
			localOffset = Integer.valueOf(w.getAttributeValue("end")).intValue();
		}
		Offset = Integer.valueOf(Offset.intValue()+localOffset+1);
		System.out.println("Offset: "+Offset +" localOffset: "+localOffset);
		return Offset;
	}

	private static void PopulateSentenceAnalysis(Element newAnalysis, Element oldAnalysis){
		List<Element> words = getWordFromXmldoc(oldAnalysis);
		String ctsUri = getCtsuri(oldAnalysis);
		//System.out.println(ctsUri);
		newAnalysis.setAttribute("uri", ctsUri);
		for (Element w : words) {
			//System.out.println(w.getAttributeValue("form"));
			newAnalysis.addContent(new Element("token")
			.setAttribute("uri", w.getAttributeValue("extended"))
			.setAttribute("prog",w.getAttributeValue("prog"))
			.setAttribute("start",w.getAttributeValue("start"))
			.setAttribute("end",w.getAttributeValue("end"))
			.setAttribute("form",w.getAttributeValue("form"))
			.setAttribute("morphoCode",w.getAttributeValue("pos"))
			.setAttribute("lemma",w.getAttributeValue("lemma"))
					);
		}
	}

}
