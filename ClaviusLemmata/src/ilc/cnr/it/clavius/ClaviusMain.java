/**
 * 
 */
package ilc.cnr.it.clavius;

import ilc.cnr.it.clavius.constants.HandleConstants;
import ilc.cnr.it.clavius.corpus.TextHandler;
import ilc.cnr.it.clavius.corpus.TreeBankHandler;

import ilc.cnr.it.clavius.lemmata.ParseToken;

import ilc.cnr.it.clavius.utils.ClaviusUtils;
import ilc.cnr.it.clavius.utils.SentencesHandler;
import ilc.cnr.it.clavius.utils.TextUtils;


import java.io.IOException;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jdom2.Document;
import org.jdom2.JDOMException;


/**
 * @author angelodel80
 *
 */
public class ClaviusMain {

	private String msg = "";
	private String sentName = "";
	private StringBuilder outBuilder = null;

	/**
	 * 
	 */
	public ClaviusMain() {
		this.msg = "this is the default message!";
		outBuilder = new StringBuilder();
	}

	public ClaviusMain(String msg){
		this.msg = msg;
		outBuilder = new StringBuilder();
	}

	public void printMsg(){
		System.out.println(this.msg);
	}

	/**
	 * @param args
	 */

	private void process(String pathModel, String pathBin) {
		HunposTagger hunPos = new HunposTagger(pathModel, pathBin);
		String msgTagged = hunPos.tag(this.msg);
		String tok = "";
		String pos="";
		String cts= getSentName().split(":: ")[0];
		String sentence = getSentName().split(":: ")[1];
		Pattern p = null;
		Matcher m = null;
		StringBuffer tmpBuffer = new StringBuffer();

		//System.out.println(msgTagged);
		//hunPos.printPath();
		//System.err.println("in process:" + msgTagged);
		String[] lines = msgTagged.split("\n");
		outBuilder.append(getSentName()+"\n");

		/* for due to manage the cts sub references*/
		/* tmpBuffer needs for handling words repetitions */
		for (String line : lines) {
			if(!"".equals(line)){
				tok = line.split("\t")[0];
				pos = line.split("\t")[1];
				tmpBuffer.append(tok+" ");
				p = Pattern.compile("\\b"+tok.replaceAll("([\\?\\.\\[\\]\\\\\\)\\(])", "\\\\$1")+"\\b");
				m = p.matcher(tmpBuffer);
				int c = 0;
				while(m.find())c++;
				if(c==0){
					p = Pattern.compile(tok.replaceAll("([\\?\\.\\[\\]\\\\\\)\\(])", "\\\\$1"));
					m = p.matcher(tmpBuffer);
				}
				while(m.find())c++;
				tok = cts.concat(String.format("@%s[%s]", tok,String.valueOf(c)));
				outBuilder.append(String.format("%s\t%s\n", tok,pos));
			}	
		}

		outBuilder.append("\n");
	}


	private void writeFullText(Map<String, String> sentences,
			String fullTextFile) {
		// TODO Auto-generated method stub
		StringBuilder sBuilder = null;

		if (null != sentences){
			sBuilder = new StringBuilder();
			Object[] sents = sentences.values().toArray();
			for (Object sent : sents) {
				String s = (String)sent;
				sBuilder.append(s.trim()+"\n");
				//System.out.print(s.trim()+"\n");
			}
			TextUtils.StringToFile(sBuilder, fullTextFile);
		}

	}


	public void writeOut(String outPath) {
		try{
			if(""==outBuilder.toString() | null==outBuilder)
				throw  new IOException("errore nella srittura dell'output");
			System.out.println(outBuilder.toString());
			TextUtils.StringToFile(outBuilder, outPath);
		}catch(IOException io){
			System.err.println(io.getMessage());
			io.printStackTrace();
		}
	}

	public void manageCorpus(String treeBankName){
		TreeBankHandler tbh = new TreeBankHandler(treeBankName);
		//		try {
		//			tbh.printFile();
		//			
		//		} catch (IOException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
		tbh.transformToTrain();
	}

	public void printEnv (){
		Map<String, String> env = System.getenv();
		for (String envName : env.keySet()) {
			System.out.format("%s=%s%n",
					envName,
					env.get(envName));
		}
	}

	/**
	 * @param args
	 */


	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg.replaceAll("\\s+", " ").trim();
	}


	/**
	 * @return the sentName
	 */
	public String getSentName() {
		return sentName;
	}

	/**
	 * @param sentName the sentName to set
	 */
	public void setSentName(String sentName) {
		this.sentName = sentName;
	}




	public static void main(String[] args) {

		ClaviusMain lemmatizationRun = new ClaviusMain();
		TextHandler th = new TextHandler();

		/* estrazione delle sentece da documenti TEI */
		Map<String, String> sentences = th.getSentences(HandleConstants.getXmlTeiFile());

		
		/* Funzionalità per la scrittura del testo in fullText */
		lemmatizationRun.writeFullText(sentences, HandleConstants.getFullTextFile());


		/* Funzionalità per il PoS Tagging */
		Object[] sents = sentences.values().toArray();
		Object[] sKeys =  sentences.keySet().toArray();

		for(int i = 0; i< sents.length; i++){
			lemmatizationRun.setMsg((String)sents[i]);
			lemmatizationRun.setSentName(String.format("%s:: %s", (String)sKeys[i], lemmatizationRun.getMsg()));
			System.out.println(lemmatizationRun.getSentName());
			lemmatizationRun.process(HandleConstants.getModelforHunPos(), HandleConstants.getPathToHunPos());
		}
		
		lemmatizationRun.writeOut(HandleConstants.getTaggedFile()); // Scrivo il contenuto del posTagging

		
		/* processo per la ricerca del lemma nella banca dati formario-lemmario */
		ParseToken.main(new String[] {HandleConstants.getTaggedFile(), HandleConstants.getTabFileAnalyzed()});
		
		/*Processo per la costruzione del file XML*/
		try {
			Document xmlSentences = TextUtils.TabToXml(HandleConstants.getTabFileAnalyzed(), true);
			ClaviusUtils.makeSentenceXML(xmlSentences);
			// TODO build XML for integration purposes (Tokens and Linguistical_Analysis)
			ClaviusUtils.makeIntegrationXMLforAnalysis(xmlSentences);
			SentencesHandler.main(new String[]{HandleConstants.getWorkDir(), HandleConstants.getLetterRif()});
		} catch (JDOMException e) {
			e.getMessage();
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}



		/* Processo per la query al LemLat */

		//		ParseXMLAnalized par = new ParseXMLAnalized("C:/tmp/MP/VERG/VERG/Test1_an.xml");
		//		StringBuilder lemLatBuider = new StringBuilder();
		//		try {
		//			List<String> tokens = par.extractTokens();
		//			for (String token : tokens) {
		//				//System.out.println(token);
		//				lemLatBuider.append("TOKEN URI: " + token);
		//				String[] argLemLat = new String[1];
		//				argLemLat[0] = token.replaceAll("(.+)@+?", "").replaceAll("\\[\\d\\]", "").toLowerCase();
		//				lemLatBuider.append("\n\tTOKEN STRING: " + argLemLat[0]+"\n");
		//				LemLatQuery.main(argLemLat);
		//				lemLatBuider.append(LemLatQuery.analysisStringBuider() + "\n");
		//				//lemLatBuider.append("\t\tANALYSIS" + "\n");
		//				lemLatBuider.append("\n");
		//				TextUtils.StringToFile(lemLatBuider, "C:/tmp/MP/VERG/VERG/LemLat_17032014_an.txt");
		//			}
		//		} catch (JDOMException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		} catch (IOException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}

		//ClaviusMain main1 = new ClaviusMain();
		//main1.manageCorpus("ldt-1.5.xml");


		//		main1.setMsg("fidelis dulcem amat virgo poetam");
		//		main1.setSentName("phrase_1");

		/* processo per la costruzione del training set */
		//		main1.process("testFirst.model", "");
		//		//System.out.println(main1.outBuilder.toString());
		//		main1.setMsg("virgo fidelis dulcem poetam amat");
		//		main1.setSentName("phrase_2");
		//		main1.process("testFirst.model", "");
		//		System.out.println(main1.outBuilder.toString());
	}


}
