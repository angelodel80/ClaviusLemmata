/**
 * 
 */
package ilc.cnr.it.clavius.corpus;

import ilc.cnr.it.clavius.ClaviusUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.filter.ElementFilter;
import org.jdom2.filter.Filter;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.util.IteratorIterable;

/**
 * @author angelodel80
 *
 */
public class TreeBankHandler {

	File corpusFile;
	Document corpus = null;
	String contextPath = "/Users/angelodel80/Risorse/sources/";
	String fileOut = "corpusForTrain";
	
	/**
	 * 
	 */
	public TreeBankHandler(String fileName) {
		// TODO Auto-generated constructor stub
		this.corpusFile = new File(getContextPath()+fileName);
		try{
			ClaviusUtils.verifyFile(this.corpusFile, false);
			initDocument();
		}catch(IllegalArgumentException ae){
			System.err.println(ae.getMessage());
		}
	}
	
	private void initDocument(){
		try{
			SAXBuilder sb = new SAXBuilder();
			setCorpus(sb.build(this.corpusFile));
		}catch(JDOMException je){
			System.err.println(je.getMessage());
			je.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void transformToTrain(){
		Element root = getCorpus().getRootElement();
		System.err.println(root.toString());
//		List<Element> annotators = root.getChildren("annotator");
//		for (Element annotator : annotators) {
//			System.out.println(annotator.getChild("name").getText());
//		}
		root.removeChildren("annotator");
		List<Element> sentences = root.getChildren("sentence");
		for (Element sentence : sentences) {
			sentence.removeChildren("primary");
			sentence.removeChildren("secondary");
			sentence.removeAttribute("document_id");
			sentence.removeAttribute("subdoc");
			sentence.removeAttribute("span");
		}
		
		ElementFilter wordsFilter = new ElementFilter("word");
		IteratorIterable<Element> words = root.getDescendants(wordsFilter);
		
		for (Element word : words) {
			//System.err.println(word.getAttributeValue("form"));
			word.removeAttribute("lemma");
			word.removeAttribute("head");
			word.removeAttribute("relation");
		}
	
		Namespace additional = root.getNamespace("xsi");
		root.setNamespace(Namespace.NO_NAMESPACE);
		//root.setName("sentences");
		
		Element newRoot = new Element("sentences").setNamespace(Namespace.NO_NAMESPACE);
		newRoot.setContent(root.cloneContent());
		getCorpus().detachRootElement();
		getCorpus().setRootElement(newRoot);
		
		try {
			printFileXML();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printFileXML() throws IOException{
		if(null != getCorpus()){
			System.out.println(getCorpus().toString());
			XMLOutputter xout = new XMLOutputter();
			xout.setFormat(Format.getPrettyFormat());
			//xout.output(getCorpus(), System.out);
			xout.output(getCorpus(), new FileWriter(getContextPath()+getFileOut()+".xml"));
		}
		else{
			System.out.println("errore nella stampa del documento XML");
		}
	}
	
	public void printFileTabular() throws IOException{
		if(null != getCorpus()){
			StringBuilder sout = new StringBuilder();
			Element root = getCorpus().getRootElement();
			for (Element sentence : root.getChildren("sentence")) {
				
				for (Element word : sentence.getChildren("word")) {
					sout.append(String.format("%s\t%s\n", word.getAttributeValue("form"),word.getAttributeValue("postag")));
				}
				
				sout.append("\n");
				
			}
			
			BufferedWriter out = new BufferedWriter(new FileWriter(getContextPath()+getFileOut()+".txt"));  
	        out.write(sout.toString());  
	        out.flush();  
	        out.close();
		}
	}

	/**
	 * @return the corpus
	 */
	public Document getCorpus() {
		return corpus;
	}

	/**
	 * @param corpus the corpus to set
	 */
	public void setCorpus(Document corpus) {
		this.corpus = corpus;
	}

	/**
	 * @return the contextPath
	 */
	public String getContextPath() {
		return contextPath;
	}

	/**
	 * @param contextPath the contextPath to set
	 */
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	/**
	 * @return the fileOut
	 */
	public String getFileOut() {
		return fileOut;
	}

	/**
	 * @param fileOut the fileOut to set
	 */
	public void setFileOut(String fileOut) {
		this.fileOut = fileOut;
	}
	

}
