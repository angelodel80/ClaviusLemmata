/**
 * 
 */
package ilc.cnr.it.clavius.lemmata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.regex.Pattern;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * @author Angelo Del Grosso
 *
 */
public class ParseXML extends ParseText {

	/**
	 * 
	 */
	public ParseXML() {
		// TODO Auto-generated constructor stub
	}

	public static boolean run(){
		boolean ret = false;
		System.out.println("ParseXML run");
		outString = new StringBuilder();
		BufferedReader reader = null;

		try{
			reader = getReader(myFile);
			leggi(reader);
			scrivi(outString);
		}catch(Exception e){

		}finally{
			try{
				reader.close();
			}catch(IOException io){

			}
		}

		return ret;
	}


	public static void leggi(BufferedReader reader) throws IOException{
		System.out.println("leggi in parseXML");
		String line1 = "ciao";
		String line = "";
		while( (line = reader.readLine()) != null) {
			//System.out.println("entro nel while");
			//System.out.print(line.trim());
			try{
			outString.append(line+"\n");
			//	System.out.print(line.trim());
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		String textFile = outString.toString();
		//System.out.println(textFile);
		Document XMLdoc = getXML(textFile);
		elabora(XMLdoc);
	}
	
	public static Document getXML(String XMLtext){
		Document doc = null;
		SAXBuilder sax = new SAXBuilder();
		try {
			doc = sax.build(new StringReader(XMLtext));
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
		
	}
	
	public static void elabora(Document Xdoc){
		outString.delete(0, outString.length());
		outString.setLength(0);
		outString.trimToSize();
		String tokenPatternString = "\\b(.+?)\\b";
		Pattern tokenPattern = Pattern.compile(tokenPatternString);
		java.util.regex.Matcher matcher = null;
		Element root = Xdoc.getRootElement();
		String text = root.getValue().replaceAll("\\s+", " ");
		
		Document doc = new Document(new Element("field").setAttribute("name", "analysis_a"));
		XMLOutputter xout = new XMLOutputter(Format.getPrettyFormat());
		Element Droot = doc.getRootElement();
		
		
		
		matcher = tokenPattern.matcher(text);
		
		while(matcher.find()){
			//System.out.println(matcher.group());
			if(!(" ".equals(matcher.group()))){
				//outString.append(matcher.group() + "\t fr \n");
				try{
				Element word = new Element("w").setAttribute("token", matcher.group()).setAttribute("lang", "Fr");
				Droot.addContent(word);
				}catch(Exception e ){
					e.printStackTrace();
				}
			}
		}
		
		outString.append(xout.outputString(doc));
		System.out.println(outString.toString());
		//System.out.println(text);
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (init(args[0], args[1]))
			run();

	}

}
