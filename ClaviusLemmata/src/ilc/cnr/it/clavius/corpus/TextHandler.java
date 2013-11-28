/**
 * 
 */
package ilc.cnr.it.clavius.corpus;

import ilc.cnr.it.clavius.constants.HandleConstants;
import ilc.cnr.it.clavius.utils.TextUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.Text;
import org.jdom2.filter.ElementFilter;
import org.jdom2.filter.Filter;
import org.jdom2.filter.Filters;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
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
	}

	public Map<String, String> getSentences(String filePath){
		Map<String,String> sentences = null;
		try {
			Document doc = TextUtils.fileToDocument(filePath);
			Namespace ns = doc.getRootElement().getNamespace();
			XPathExpression<Element> sentencesExpression = 
					XPathFactory.instance().compile(HandleConstants.getXpathForSentences(), Filters.element(), null, Namespace.getNamespace("tei",ns.getURI()));
			List<Element> listOfSentences = sentencesExpression.evaluate(doc);

			if (null != listOfSentences){
				System.err.println("number of sentences: " + listOfSentences.size());
				sentences = new LinkedHashMap<String, String>();
				for (Element sent : listOfSentences) {
					String[] sentence = getSentence(sent);
					sentences.put(sentence[0], sentence[1]);
				}

			}

		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


		return sentences;
	}

	public String[] getSentence(Element sent){
		String sentence[] = new String[2];
		sentence[0] = sent.getAttributeValue(HandleConstants.getAtttributeSentencesName());
		sentence[1] = getTextValue(sent);
		return sentence;

	}

	private String getTextValue(Element sent){
		StringBuilder sb = new StringBuilder();
		
		XPathExpression<Element> sentencesExpression = 
				XPathFactory.instance().compile(".//lb", Filters.element(), null, Namespace.getNamespace("tei",sent.getNamespaceURI()));
		List<Element> list = sentencesExpression.evaluate(sent);
		if(list == null || list.size() == 0){
			//System.out.println("la lista è vuota");
		}
		for (Element lb : list) {
			//System.out.println("word: " + lb.getAttributeValue("n"));
			lb.detach();
		}

		String xpathWord = "./tei:w";
		XPathExpression<Element> wordExpression = 
				XPathFactory.instance().compile(xpathWord, Filters.element(), null, Namespace.getNamespace("tei",sent.getNamespaceURI()));
		List<Element> wordlist = wordExpression.evaluate(sent);
		if(wordlist == null || wordlist.size() == 0){
			System.err.println("the sentence " + sent.getAttributeValue(HandleConstants.getAtttributeSentencesName()) + " has not tag <w />");
		}
		for (Element word : wordlist) {
			word.setText(word.getValue().replaceAll("\\s+", ""));
			
			//System.out.println("word: " + word.getText());
			//sb.append(word.getTextNormalize());
		}


		String xpath = HandleConstants.getXpathForText();
		//String xpath = "./w/text()";
		XPathExpression<Text> textExpression = 
				XPathFactory.instance().compile(xpath, Filters.textOnly(), null, Namespace.getNamespace("tei", sent.getNamespaceURI()));
		List<Text> textlist = textExpression.evaluate(sent);
		if(textlist == null || textlist.size() == 0){
			//System.out.println("the text list is empty of the sentence: " + sent.getAttributeValue("n"));
		}
		for (Text txt : textlist) {
			//System.out.println("txt: " + sent.getAttributeValue("n")+ ":" + txt.getText());
			sb.append(txt.getTextNormalize()+" ");
		}

		//XMLOutputter xo = new XMLOutputter(Format.getPrettyFormat());
		//System.out.println("textValue: " + sb.toString());
		//System.out.println("xo String sent: " + xo.outputString(sent));
		return sb.toString().replaceAll("\\s+", " ").replaceAll("\\s+([\\.;:!?,])", "$1");
	}


}
