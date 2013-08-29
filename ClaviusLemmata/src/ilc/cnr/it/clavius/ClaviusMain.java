/**
 * 
 */
package ilc.cnr.it.clavius;

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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClaviusMain main1 = new ClaviusMain();
		ClaviusMain main2 = new ClaviusMain("this is the second class");
		
		main1.printMsg();
		main2.printMsg();
		
		main2.process("en_wsj.model","");
	}	
}
