/**
 * 
 */
package ilc.cnr.it.clavius.utils;

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
public class TextUtils {

	/**
	 * 
	 */
	public TextUtils() {
		// TODO Auto-generated constructor stub
	}
	
	public static Document fileToDocument (String filePath) throws JDOMException, IOException{
		File file = new File(filePath);
		ClaviusUtils.verifyFile(file, true);
		Document doc = null;
		SAXBuilder builder = new SAXBuilder();
		doc = builder.build(file);
		return doc;
	}

}
