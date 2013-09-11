/**
 * 
 */
package ilc.cnr.it.clavius.corpus;

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
		// TODO Auto-generated constructor stub
	}

	public Map<String, String> getSentences(String filePath){
		Map<String,String> sentences = null;
		try {
			Document doc = TextUtils.fileToDocument(filePath);
			Namespace ns = doc.getRootElement().getNamespace();
			System.err.println("prefix: " + ns.getPrefix());
			System.err.println("names: " + ns.getURI());
			XPathExpression<Element> sentencesExpression = 
					XPathFactory.instance().compile("/TEI/text/body/div/p/s", Filters.element(), null, Namespace.getNamespace(ns.getPrefix(),ns.getURI()));
			List<Element> listOfSentences = sentencesExpression.evaluate(doc);

			if (null != listOfSentences){
				System.err.println(listOfSentences.size());
				sentences = new LinkedHashMap<String, String>();
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
		sentence[1] = getTextValue(sent);
		return sentence;

	}
	
	private String getTextValue(Element sent){
		StringBuilder sb = new StringBuilder();
		XMLOutputter xo = new XMLOutputter(Format.getPrettyFormat());
		List<Content> conts = sent.getContent();
		List<Element> Childs = sent.getChildren();
		//System.out.println("in getTextValue()");
		
//		System.out.println("xo String Content: " + xo.outputString(conts));
		
//		for (Content content : conts) {
//			System.out.println(content.getCType()+" : "+content.getParentElement().getAttributeValue("n"));
//			System.out.println("content: " + content.);
//			if(content.getCType() == content.getCType().Text){
//				sb.append(content.getValue());
//			}else if(content.getCType() == content.getCType().Element){
//				System.out.println("elementToString: " + content.toString());
//				content.getCType().
//			}
//		}
		
		XPathExpression<Element> sentencesExpression = 
				XPathFactory.instance().compile(".//lb", Filters.element(), null, Namespace.getNamespace("",""));
		List<Element> list = sentencesExpression.evaluate(sent);
		if(list == null || list.size() == 0){
			//System.out.println("la lista è vuota");
		}
		for (Element lb : list) {
			//System.out.println("word: " + lb.getAttributeValue("n"));
			lb.detach();
		}
		
		String xpathWord = "./w";
		XPathExpression<Element> wordExpression = 
				XPathFactory.instance().compile(xpathWord, Filters.element(), null, Namespace.getNamespace("",""));
		List<Element> wordlist = wordExpression.evaluate(sent);
		if(wordlist == null || wordlist.size() == 0){
			//System.out.println("la sentence " + sent.getAttributeValue("n") + " non ha tag <w />");
		}
		for (Element word : wordlist) {
			word.setText(word.getValue().replaceAll("\\s+", ""));
			//System.out.println("word: " + word.getText());
			//sb.append(word.getTextNormalize());
		}
		
		
		String xpath = "node() | ./w/text() | ./choice/expan/text() | ./rs/text() | ./dateline/text()";
		//String xpath = "./w/text()";
		XPathExpression<Text> textExpression = 
				XPathFactory.instance().compile(xpath, Filters.textOnly(), null, Namespace.getNamespace("",""));
		List<Text> textlist = textExpression.evaluate(sent);
		if(textlist == null || textlist.size() == 0){
			//System.out.println("la lista del testo è vuota della sent" + sent.getAttributeValue("n"));
		}
		for (Text txt : textlist) {
			//System.out.println("txt: " + sent.getAttributeValue("n")+ ":" + txt.getText());
			sb.append(txt.getTextNormalize()+" ");
		}
		
		//System.out.println("textValue: " + sb.toString());
		//System.out.println("xo String sent: " + xo.outputString(sent));
		return sb.toString().replaceAll("\\s+", " ").replaceAll("\\s+([\\.;:!?,])", "$1");
	}
	

}
