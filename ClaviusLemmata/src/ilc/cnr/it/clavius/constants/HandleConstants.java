/**
 * 
 */
package ilc.cnr.it.clavius.constants;

/**
 * @author angelodel80
 * 
 */
public class HandleConstants {
	
	private final static String letterRif = "6-336";
	private final static String TeiFile = "AP_6-336.xml";
	private final static String workDir = "C:/tmp/MP/TEO/";
			//"C:/tmp/Clavius/TEI-MarkUp/08042014/"+letterRif+"/";
			//"C:/tmp/Clavius/TEI-MarkUp/08042014/136/";
			// "C:/tmp/MP/1/";
	
	//"136_APUG_530_cc.138-139.xml";"aeneis_1.xml";
	
	private final static String xmlTeiFile = workDir+TeiFile;
			//"C:/tmp/MP/VERG/VERG/test.xml"; 
			//"/Users/angelodel80/Risorse/sources/clavius/calviusTraduzioni/ClaviusResources/147/147_APUG_530_cc.129-130.xml";
			//"/Users/angelodel80/Risorse/sources/clavius/test/TestLemmata.xml"; 
	private final static String FullTextFile = workDir+"fullText.txt";
	
	private final static String modelforHunPos = "testFirst.model";
	private final static String tabFileAnalyzed = workDir+"out-tokens_Lemmata.txt";
			//"C:/tmp/MP/VERG/VERG/out-tokens_TestLemmata.txt";
	private final static String taggedFile = workDir+"taggedFile.txt";
			//"C:/tmp/MP/VERG/VERG/testOutput.txt";
			//"/Users/angelodel80/Risorse/sources/clavius/test/TaggedFile.txt";
	
	
	
	private final static String letterAnalyzed = "/Letter"+HandleConstants.letterRif+"_sentences_Analyzed";
	
	//private final static String pathToHunPos = System.getenv("HOME")+System.getProperty("file.separator")+"Risorse"+System.getProperty("file.separator")+"tools"+System.getProperty("file.separator")+"hunpos-1.0-macosx"+System.getProperty("file.separator")+"hunpos-tag";
	//private final static String pathToHunPosModel = System.getenv("HOME")+System.getProperty("file.separator")+"Risorse"+System.getProperty("file.separator")+"tools"+System.getProperty("file.separator");
	
	private final static String pathToHunPos = "C:/opt/hunpos-1.0-win/hunpos-1.0-win/hunpos-tag.exe";
	private final static String pathToHunPosModel = "C:/opt/hunpos-1.0-win/hunpos-1.0-win/";
	
	private final static String xpathForSentences = "/tei:TEI/tei:text/tei:group/tei:text/tei:body/tei:div1/tei:p/tei:s";
			//"/tei:TEI/tei:text/tei:body/tei:div/tei:div/tei:ab/tei:s";
			//"/tei:TEI/tei:text/tei:body/tei:div/tei:div/tei:ab/tei:s"; "tei:TEI/tei:text/tei:body/tei:div/tei:s"; // for virgilio
	private final static String atttributeSentencesName = "n";
	private final static String xpathForText = "node() | ./tei:w/text() | ./tei:sic/text() | ./tei:choice/tei:expan/text() | ./tei:date/tei:choice/tei:expan/text() |./tei:date/tei:hi/text() | ./tei:code/text() | ./tei:date/text() | ./tei:signatures/text() | ./tei:signatures/tei:choice/tei:expan/text() | ./tei:choice/tei:corr/text()";
	
	private final static String ctsUriInvariant = "urn:cts:greekLit:tlg0005.tlg002.perseus-grc1:";
			//"urn:cts:histReSci:clavius.apug.edApug:";
			//"urn:cts:latinLit:phi0690.phi003.mqdq-lat01:"; 
			
	
	private final static String contextForTrain = "/Users/angelodel80/Risorse/sources/";
	private final static String trainFileOut = "corpusForTrain";
	
	
	private final static String lemmaQuery = "SELECT lemma_text, morph_code FROM hib_parses as f LEFT JOIN hib_lemmas as l on f.lemma_id = l.lemma_id WHERE f.form = \"{[?form?]}\" and f.morph_code like \"{[?pos?]}%\"" ;
			//"SELECT lemma_text, morph_code FROM hib_parses as f LEFT JOIN hib_lemmas as l on f.lemma_id = l.lemma_id WHERE f.bare_form = \"{[?form?]}\" and f.morph_code like \"{[?pos?]}%\"" ;
	private final static String formReplace = "\\{\\[\\?form\\?\\]\\}";
	private final static String posReplace = "\\{\\[\\?pos\\?\\]\\}";
	private final static String dataBase = "jdbc:mysql://localhost:3306/perseus";
	private final static String dbUser = "angelodel80";
	private final static String dbPassword = "190280";
	
	/**
	 * @return the pathToHunPos
	 */
	public static String getPathToHunPos() {
		return pathToHunPos;
	}

	/**
	 * @param pathToHunPos the pathToHunPos to set
	 */
	
	/**
	 * @return the letterRif
	 */
	public static String getLetterRif() {
		return letterRif;
	}

	/**
	 * @param letterRif the letterRif to set
	 */
	

	/**
	 * @return the workDir
	 */
	public static String getWorkDir() {
		return workDir;
	}

	/**
	 * @param workDir the workDir to set
	 */
	

	/**
	 * @return the taggedFile
	 */
	public static String getTaggedFile() {
		return taggedFile;
	}

	/**
	 * @param taggedFile the taggedFile to set
	 */
	

	/**
	 * @return the tabFileAnalized
	 */
	public static String getTabFileAnalyzed() {
		return tabFileAnalyzed;
	}

	/**
	 * @param tabFileAnalyzed the tabFileAnalized to set
	 */
	

	/**
	 * @return the modelforHunPos
	 */
	public static String getModelforHunPos() {
		return modelforHunPos;
	}

	/**
	 * @param modelforHunPos the modelforHunPos to set
	 */
	

	/**
	 * @return the xmtTeiFile
	 */
	public static String getXmlTeiFile() {
		return xmlTeiFile;
	}

	/**
	 * @param xmlTeiFile
	 *            the xmtTeiFile to set
	 */
	

	/**
	 * 
	 */
	public HandleConstants() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the pathToHunPosModel
	 */
	public static String getPathToHunPosModel() {
		return pathToHunPosModel;
	}

	/**
	 * @param pathToHunPosModel the pathToHunPosModel to set
	 */
	

	/**
	 * @return the xpathForSentences
	 */
	public static String getXpathForSentences() {
		return xpathForSentences;
	}

	/**
	 * @param xpathForSentences the xpathForSentences to set
	 */
	

	/**
	 * @return the atttributeSentencesName
	 */
	public static String getAtttributeSentencesName() {
		return atttributeSentencesName;
	}

	/**
	 * @param atttributeSentencesName the atttributeSentencesName to set
	 */
	

	/**
	 * @return the xpathForText
	 */
	public static String getXpathForText() {
		return xpathForText;
	}

	/**
	 * @param xpathForText the xpathForText to set
	 */
	

	/**
	 * @return the contextForTrain
	 */
	public static String getContextForTrain() {
		return contextForTrain;
	}

	/**
	 * @param contextForTrain the contextForTrain to set
	 */
	

	/**
	 * @return the trainFileOut
	 */
	public static String getTrainFileOut() {
		return trainFileOut;
	}

	/**
	 * @param trainFileOut the trainFileOut to set
	 */
	

	/**
	 * @return the lemmaQuery
	 */
	public static String getLemmaQuery() {
		return lemmaQuery;
	}

	/**
	 * @param lemmaQuery the lemmaQuery to set
	 */
	
	/**
	 * @return the formReplace
	 */
	public static String getFormReplace() {
		return formReplace;
	}

	/**
	 * @param formReplace the formReplace to set
	 */
	
	/**
	 * @return the posReplace
	 */
	public static String getPosReplace() {
		return posReplace;
	}

	/**
	 * @param posReplace the posReplace to set
	 */
	

	/**
	 * @return the dataBase
	 */
	public static String getDataBase() {
		return dataBase;
	}

	/**
	 * @param dataBase the dataBase to set
	 */
	

	/**
	 * @return the dbUser
	 */
	public static String getDbUser() {
		return dbUser;
	}

	/**
	 * @param dbUser the dbUser to set
	 */
	

	/**
	 * @return the dbPassword
	 */
	public static String getDbPassword() {
		return dbPassword;
	}

	/**
	 * @param dbPassword the dbPassword to set
	 */
	

	/**
	 * @return the letterAnalyzed
	 */
	public static String getLetterAnalyzed() {
		return letterAnalyzed;
	}

	/**
	 * @param letterAnalyzed the letterAnalyzed to set
	 */
	

	/**
	 * @return the ctsUriInvariant
	 */
	public static String getCtsUriInvariant() {
		return ctsUriInvariant;
	}

	/**
	 * @return the fulltextfile
	 */
	public static String getFullTextFile() {
		return FullTextFile;
	}

	/**
	 * @return the teifile
	 */
	public static String getTeifile() {
		return TeiFile;
	}

	/**
	 * @param ctsUriInvariant the ctsUriInvariant to set
	 */
	

}
