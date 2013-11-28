/**
 * 
 */
package ilc.cnr.it.clavius;

import ilc.cnr.it.clavius.constants.HandleConstants;
import ilc.cnr.it.clavius.corpus.TextHandler;
import ilc.cnr.it.clavius.corpus.TreeBankHandler;
import ilc.cnr.it.clavius.lemmata.ParseToken;
import ilc.cnr.it.clavius.utils.ClaviusUtils;
import ilc.cnr.it.clavius.utils.TextUtils;

import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.JDOMException;

/**
 * @author angelodel80
 *
 */
public class ClaviusMain {

	private String msg = "";
	private String sentName = "";
	private StringBuilder outBuilder = new StringBuilder();

	/**
	 * 
	 */
	public ClaviusMain() {
		this.msg = "this is the default message!";
	}

	public ClaviusMain(String msg){
		this.msg = msg;
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
		//System.out.println(msgTagged);
		//hunPos.printPath();
		outBuilder.append(getSentName()+"\n");
		outBuilder.append(msgTagged);
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

		//ClaviusMain main1 = new ClaviusMain();
		
		ClaviusMain main2 = new ClaviusMain();

		//main2.manageCorpus("ldt-1.5.xml");
		
		TextHandler th = new TextHandler();
		Map<String, String> sentences = th.getSentences(HandleConstants.getXmlTeiFile());
		Object[] sents = sentences.values().toArray();
		Object[] sKeys =  sentences.keySet().toArray();
		for(int i = 0; i< sents.length; i++){
			main2.setMsg((String)sents[i]);
			main2.setSentName(String.format("%s:%s", (String)sKeys[i], main2.getMsg()));
			System.out.println(main2.getSentName());
//			main2.process(HandleConstants.getModelforHunPos(),"");
		}
		//main2.writeOut(HandleContants.getTaggedFile());
		//ParseToken.init(HandleContants.getTaggedFile(), HandleConstants.getTaggedFile());
		//ParseToken.run();
//		try {
//			Document xmlSentences = TextUtils.TabToXml(HandleConstants.getTabFileAnalyzed(), true);
//			ClaviusUtils.makeSentenceXML(xmlSentences);
//		} catch (JDOMException e) {
			// TODO Auto-generated catch block
//			e.getMessage();
//			e.printStackTrace();
//		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
		
//		main1.setMsg("fidelis dulcem amat virgo poetam");
//		main1.setSentName("phrase_1");
//		main1.process("testFirst.model", "");
//		//System.out.println(main1.outBuilder.toString());
//		main1.setMsg("virgo fidelis dulcem poetam amat");
//		main1.setSentName("phrase_2");
//		main1.process("testFirst.model", "");
//		System.out.println(main1.outBuilder.toString());
	}

	
}
