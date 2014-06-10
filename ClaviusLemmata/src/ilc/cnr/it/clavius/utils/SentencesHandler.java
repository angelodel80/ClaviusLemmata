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
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * @author Angelo Del Grosso
 *
 */
public class SentencesHandler {

	private static Document sentenceAnalysis;
	/**
	 * 
	 */
	public SentencesHandler() {
		// TODO Auto-generated constructor stub
	}


	private static final boolean init(String file){
		boolean ret = false;
		try {
			SAXBuilder builder = new SAXBuilder();
			sentenceAnalysis = builder.build(file);
			ret = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		
		return ret;
	}

	private static final StringBuffer run(){
		StringBuffer ret = null;
		if(null!=sentenceAnalysis){
			System.err.println(sentenceAnalysis.toString());
			Element root = sentenceAnalysis.getRootElement();
			List<Element> sents = root.getChildren();
			handleSentences(sents);
			ret = outputSentences(sentenceAnalysis);
		}
		return ret;
	}


	private static StringBuffer outputSentences(Document sentenceAnalysis2) {
		// TODO Auto-generated method stub
		StringBuffer ret = null;
		XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
		ret = new StringBuffer(out.outputString(sentenceAnalysis2));
		return ret;
	}


	private static void handleSentences(List<Element> sents) {
		// TODO Auto-generated method stub
		int count = 0;
		for (Element sent : sents) {
			List<Element> toks = sent.getChildren();
			Element firstTok = toks.get(0);
			Element lastTok = toks.get(toks.size()-1);
			int start = count+Integer.parseInt(firstTok.getAttributeValue("start"));
			int end = count+Integer.parseInt(lastTok.getAttributeValue("end"));
			
			sent.setAttribute("start", String.valueOf(start));
			sent.setAttribute("end", String.valueOf(end));
			sent.setAttribute("span", firstTok.getAttributeValue("uri")+"-"+lastTok.getAttributeValue("uri"));
			count = end+1;
			//System.err.println(sent.toString());
		}
	}


	private static void BufferToFile(StringBuffer sentences, String file) {
		// TODO Auto-generated method stub
		BufferedWriter writer = null;
		try{
			File outFile = new File(file);
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
			writer.write(sentences.toString());
			
		}catch(FileNotFoundException fe){
			fe.printStackTrace();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{	
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringBuffer sentences = null;
		String file = String.format("%sLetter%s_an.xml", args[0],args[1]);
		String fileOut = String.format("%sLetter%s_anOUT.xml", args[0],args[1]);
		if(SentencesHandler.init(file)){
			sentences = SentencesHandler.run();
			System.out.println(sentences.toString());
			SentencesHandler.BufferToFile(sentences,fileOut);
		}
		else{
			System.out.println("nada!!");
		}



	}

}
