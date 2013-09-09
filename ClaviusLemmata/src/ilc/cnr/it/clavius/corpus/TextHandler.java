/**
 * 
 */
package ilc.cnr.it.clavius.corpus;

import ilc.cnr.it.clavius.utils.TextUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.filter.ElementFilter;
import org.jdom2.filter.Filter;
import org.jdom2.filter.Filters;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

/**
 * @author angelodel80
 *
 */
public class TextHandler {

	/**
	 * 
	 */
	public TextHandler() {
		// TODO Auto-generated constructor stub
	}

	public Map<String, String> getSentences(String filePath){
		Map<String,String> sentences = null;
		try {
			Document doc = TextUtils.fileToDocument(filePath);
			Namespace ns = doc.getRootElement().getNamespace();
			XPathExpression<Element> sentencesExpression = 
					XPathFactory.instance().compile("//s", Filters.element());

			List<Element> listOfSentences = sentencesExpression.evaluate(doc);

			if (null != listOfSentences){
				sentences = new HashMap<String, String>();
				for (Element sent : listOfSentences) {
					String[] sentence = getSentence(sent);
					sentences.put(sentence[0], sentence[1]);
				}

			}



		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return sentences;
	}

	public String[] getSentence(Element sent){
		String sentence[] = new String[2];
		sentence[0] = sent.getAttributeValue("n");
		sentence[1] = sent.getTextNormalize();
		return sentence;

	}

}
