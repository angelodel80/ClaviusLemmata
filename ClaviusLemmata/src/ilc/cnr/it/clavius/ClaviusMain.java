/**
 * 
 */
package ilc.cnr.it.clavius;

import ilc.cnr.it.clavius.corpus.TextHandler;
import ilc.cnr.it.clavius.corpus.TreeBankHandler;

import java.io.IOException;
import java.util.Map;

/**
 * @author angelodel80
 *
 */
public class ClaviusMain {

	private String msg = "";

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
		System.out.println(msgTagged);
		//hunPos.printPath();
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
		this.msg = msg;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClaviusMain main1 = new ClaviusMain();
		ClaviusMain main2 = new ClaviusMain("In nova fert animus mutatas dicere formas corpora");

		//main1.printMsg();
		main2.printMsg();
		//main2.manageCorpus();
		main2.process("en_wsj.model","");
		TextHandler th = new TextHandler();
		Map<String, String> sentences = th.getSentences("/Users/angelodel80/Risorse/sources/clavius/testXpathExpression.xml");
		Object[] sents = sentences.values().toArray();
		for(int i = 0; i< sents.length; i++){
			main2.setMsg((String)sents[i]);
			main2.process("testFirst.model","");
		}
	}

}
