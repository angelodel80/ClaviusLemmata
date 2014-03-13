/**
 * 
 */
package ilc.cnr.it.clavius.lemmata;

import ilc.cnr.it.clavius.utils.ClaviusUtils;
import ilc.cnr.it.clavius.utils.TextUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.ElementFilter;
import org.jdom2.util.IteratorIterable;

/**
 * @author Angelo Del Grosso
 *
 */
public class ParseXMLAnalized {
	File XMLfile = null;
	/**
	 * 
	 */
	public ParseXMLAnalized(String XMLfile) {
		// TODO Auto-generated constructor stub
		this.XMLfile = new File(XMLfile);
		ClaviusUtils.verifyFile(this.XMLfile, false);
	}

	public List<String> extractTokens() throws JDOMException, IOException  {
		List<String> tokens = null;
		Document doc = null;
		doc = TextUtils.fileToDocument(this.XMLfile.getAbsolutePath());
		Element root = doc.getRootElement();
		
		IteratorIterable<Element> tokensIterator = root.getDescendants(new ElementFilter("token"));
		tokens = new ArrayList<String>(); 
		for (Element element : tokensIterator) {
			tokens.add(element.getAttributeValue("form"));
		}
		
		return tokens;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
