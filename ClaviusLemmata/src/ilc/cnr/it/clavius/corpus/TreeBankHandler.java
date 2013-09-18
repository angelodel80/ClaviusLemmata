/**
 * 
 */
package ilc.cnr.it.clavius.corpus;

import ilc.cnr.it.clavius.constants.HandleContsants;
import ilc.cnr.it.clavius.utils.ClaviusUtils;

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

	File corpusFile = null;
	Document corpus = null;
	String fileOut = "";
	
	/**
	 * 
	 */
	public TreeBankHandler(String fileName) {
		this.corpusFile = new File(HandleContsants.getContextForTrain()+fileName);
		this.fileOut = HandleContsants.getTrainFileOut();
		try{
			ClaviusUtils.verifyFile(getCorpusFile(), false);
			initDocument();
		}catch(IllegalArgumentException ae){
			System.err.println(ae.getMessage());
		}
	}
	
	private void initDocument(){
		try{
			SAXBuilder sb = new SAXBuilder();
			setCorpus(sb.build(getCorpusFile()));
		}catch(JDOMException je){
			System.err.println(je.getMessage());
			je.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void transformToTrain(){
		Element root = getCorpus().getRootElement();
		System.err.println(root.toString());
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
	
		root.setNamespace(Namespace.NO_NAMESPACE);
		
		Element newRoot = new Element("sentences").setNamespace(Namespace.NO_NAMESPACE);
		newRoot.setContent(root.cloneContent());
		getCorpus().detachRootElement();
		getCorpus().setRootElement(newRoot);
		
		try {
			printFileXML();
			printFileTabular();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void printFileXML() throws IOException{
		if(null != getCorpus()){
			System.out.println(getCorpus().toString());
			XMLOutputter xout = new XMLOutputter();
			xout.setFormat(Format.getPrettyFormat());
			//xout.output(getCorpus(), System.out);
			xout.output(getCorpus(), new FileWriter( HandleContsants.getContextForTrain()+getFileOut()+".xml"));
		}
		else{
			System.err.println("The XML print got an error");
		}
	}
	
	public void printFileTabular() throws IOException{
		if(null != getCorpus()){
			StringBuilder sout = new StringBuilder();
			Element root = getCorpus().getRootElement();
			for (Element sentence : root.getChildren("sentence")) {
				
				for (Element word : sentence.getChildren("word")) {
					sout.append(String.format("%s\t%s\t\n", word.getAttributeValue("form"),word.getAttributeValue("postag")));
				}
				
				sout.append("\n");
				
			}
			
			BufferedWriter out = new BufferedWriter(new FileWriter(HandleContsants.getContextForTrain()+getFileOut()+".txt"));  
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

	/**
	 * @return the corpusFile
	 */
	public File getCorpusFile() {
		return corpusFile;
	}

	/**
	 * @param corpusFile the corpusFile to set
	 */
	public void setCorpusFile(File corpusFile) {
		this.corpusFile = corpusFile;
	}
	

}
