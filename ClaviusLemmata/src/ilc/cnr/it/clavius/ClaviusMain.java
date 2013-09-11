/**
 * 
 */
package ilc.cnr.it.clavius;

import ilc.cnr.it.clavius.corpus.TextHandler;
import ilc.cnr.it.clavius.corpus.TreeBankHandler;
import ilc.cnr.it.clavius.utils.TextUtils;

import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.Map;

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
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		HunposTagger hunPos = new HunposTagger(pathModel, pathBin);
		String msgTagged = hunPos.tag(this.msg);
		//System.out.println(msgTagged);
		//hunPos.printPath();
		outBuilder.append(getSentName()+"\n");
		outBuilder.append(msgTagged);
	}

	
	public void writeOut(String outPath) {
		// TODO Auto-generated method stub
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
	
	public void manageCorpus(){
		TreeBankHandler tbh = new TreeBankHandler("ldt-1.5.xml");
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
		// TODO Auto-generated method stub
		ClaviusMain main1 = new ClaviusMain();
		ClaviusMain main2 = new ClaviusMain();

		//main1.printMsg();
		//main2.printMsg();
		//main2.manageCorpus();
		//main2.process("en_wsj.model","");
		TextHandler th = new TextHandler();
		Map<String, String> sentences = th.getSentences("/Users/angelodel80/Risorse/sources/clavius/147APUG530cc_129130_xml.xml");
		Object[] sents = sentences.values().toArray();
		Object[] sKeys =  sentences.keySet().toArray();
		for(int i = 0; i< sents.length; i++){
			main2.setMsg((String)sents[i]);
			main2.setSentName(String.format("%s:%s", (String)sKeys[i], main2.getMsg()));
			System.out.println("sentence:" + main2.getSentName() +" " + main2.getMsg());
			main2.process("testFirst.model","");
		}
		main2.writeOut("/Users/angelodel80/Risorse/sources/clavius/TaggedFile.txt");
//		main1.setMsg("Si huius generis est, iam constat esse mechanicum, et a nostro instituto alienum. Sin illius, cur non demonstratur.");
//		main1.setSentName("phrase_1");
//		main1.process("testFirst.model", "");
//		System.out.println(main1.outBuilder.toString());
	}

	
}
