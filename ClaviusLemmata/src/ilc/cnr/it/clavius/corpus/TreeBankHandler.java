/**
 * 
 */
package ilc.cnr.it.clavius.corpus;

import ilc.cnr.it.clavius.ClaviusUtils;

import java.io.File;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * @author angelodel80
 *
 */
public class TreeBankHandler {

	File corpusFile;
	Document corpus = null;
	
	/**
	 * 
	 */
	public TreeBankHandler(String fileName) {
		// TODO Auto-generated constructor stub
		this.corpusFile = new File("/Users/angelodel80/Risorse/sources/"+fileName);
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
			corpus = sb.build(this.corpusFile);
		}catch(JDOMException je){
			System.err.println(je.getMessage());
			je.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printFile(){
		if(null != corpus){
			System.out.println(corpus.toString());
		}
		else{
			System.out.println("errore nella stampa del documento XML");
		}
	}
	

}
