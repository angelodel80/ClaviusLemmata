/**
 * 
 */
package ilc.cnr.it.clavius.constants;

/**
 * @author angelodel80
 * 
 */
public class HandleConstants {
	
	private static String xmlTeiFile = "/Users/angelodel80/Risorse/sources/clavius/calviusTraduzioni/ClaviusResources/147/147_APUG_530_cc.129-130.xml";
	private static String modelforHunPos = "testFirst.model";
	private static String tabFileAnalyzed = "/Users/angelodel80/Risorse/sources/clavius/out-tokensLemmatized-30112013.txt";
	private static String taggedFile = "/Users/angelodel80/Risorse/sources/clavius/TaggedFile.txt";
	private static String workDir = "/Users/angelodel80/Risorse/sources/clavius/";
	private static String letterRif = "147";
	
	private static String letterAnalyzed = "/Letter"+HandleConstants.letterRif+"_sentences_Analyzed";
	
	private static String pathToHunPos = System.getenv("HOME")+System.getProperty("file.separator")+"Risorse"+System.getProperty("file.separator")+"tools"+System.getProperty("file.separator")+"hunpos-1.0-macosx"+System.getProperty("file.separator")+"hunpos-tag";
	private static String pathToHunPosModel = System.getenv("HOME")+System.getProperty("file.separator")+"Risorse"+System.getProperty("file.separator")+"tools"+System.getProperty("file.separator");
	
	private static String xpathForSentences = "/tei:TEI/tei:text/tei:body/tei:div/tei:div/tei:ab/tei:s";
	private static String atttributeSentencesName = "n";
	private static String xpathForText = "node() | ./tei:w/text() | ./tei:choice/tei:expan/text() | ./tei:code/text() | ./tei:date/text() | ./tei:signatures/text() | ./tei:signatures/tei:choice/tei:expan/text()";
	
	private static String ctsUriInvariant = "urn:cts:histReSci:clavius.apug.edApug:";
	
	private static String contextForTrain = "/Users/angelodel80/Risorse/sources/";
	private static String trainFileOut = "corpusForTrain";
	
	
	private static String lemmaQuery = "SELECT lemma_text, morph_code FROM hib_parses as f LEFT JOIN hib_lemmas as l on f.lemma_id = l.lemma_id WHERE f.bare_form = \"{[?form?]}\" and f.morph_code like \"{[?pos?]}%\"" ;
	private static String formReplace = "\\{\\[\\?form\\?\\]\\}";
	private static String posReplace = "\\{\\[\\?pos\\?\\]\\}";
	private static String dataBase = "jdbc:mysql://localhost:3306/perseus";
	private static String dbUser = "angelodel80";
	private static String dbPassword = "190280";
	
	/**
	 * @return the pathToHunPos
	 */
	public static String getPathToHunPos() {
		return pathToHunPos;
	}

	/**
	 * @param pathToHunPos the pathToHunPos to set
	 */
	public static void setPathToHunPos(String pathToHunPos) {
		HandleConstants.pathToHunPos = pathToHunPos;
	}

	/**
	 * @return the letterRif
	 */
	public static String getLetterRif() {
		return letterRif;
	}

	/**
	 * @param letterRif the letterRif to set
	 */
	public static void setLetterRif(String letterRif) {
		HandleConstants.letterRif = letterRif;
	}

	/**
	 * @return the workDir
	 */
	public static String getWorkDir() {
		return workDir;
	}

	/**
	 * @param workDir the workDir to set
	 */
	public static void setWorkDir(String workDir) {
		HandleConstants.workDir = workDir;
	}

	/**
	 * @return the taggedFile
	 */
	public static String getTaggedFile() {
		return taggedFile;
	}

	/**
	 * @param taggedFile the taggedFile to set
	 */
	public static void setTaggedFile(String taggedFile) {
		HandleConstants.taggedFile = taggedFile;
	}

	/**
	 * @return the tabFileAnalized
	 */
	public static String getTabFileAnalyzed() {
		return tabFileAnalyzed;
	}

	/**
	 * @param tabFileAnalyzed the tabFileAnalized to set
	 */
	public static void setTabFileAnalyzed(String tabFileAnalyzed) {
		HandleConstants.tabFileAnalyzed = tabFileAnalyzed;
	}

	/**
	 * @return the modelforHunPos
	 */
	public static String getModelforHunPos() {
		return modelforHunPos;
	}

	/**
	 * @param modelforHunPos the modelforHunPos to set
	 */
	public static void setModelforHunPos(String modelforHunPos) {
		HandleConstants.modelforHunPos = modelforHunPos;
	}

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
	private void setXmlTeiFile(String xmlTeiFile) {
		HandleConstants.xmlTeiFile = xmlTeiFile;
	}

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
	public static void setPathToHunPosModel(String pathToHunPosModel) {
		HandleConstants.pathToHunPosModel = pathToHunPosModel;
	}

	/**
	 * @return the xpathForSentences
	 */
	public static String getXpathForSentences() {
		return xpathForSentences;
	}

	/**
	 * @param xpathForSentences the xpathForSentences to set
	 */
	public static void setXpathForSentences(String xpathForSentences) {
		HandleConstants.xpathForSentences = xpathForSentences;
	}

	/**
	 * @return the atttributeSentencesName
	 */
	public static String getAtttributeSentencesName() {
		return atttributeSentencesName;
	}

	/**
	 * @param atttributeSentencesName the atttributeSentencesName to set
	 */
	public static void setAtttributeSentencesName(String atttributeSentencesName) {
		HandleConstants.atttributeSentencesName = atttributeSentencesName;
	}

	/**
	 * @return the xpathForText
	 */
	public static String getXpathForText() {
		return xpathForText;
	}

	/**
	 * @param xpathForText the xpathForText to set
	 */
	public static void setXpathForText(String xpathForText) {
		HandleConstants.xpathForText = xpathForText;
	}

	/**
	 * @return the contextForTrain
	 */
	public static String getContextForTrain() {
		return contextForTrain;
	}

	/**
	 * @param contextForTrain the contextForTrain to set
	 */
	public static void setContextForTrain(String contextForTrain) {
		HandleConstants.contextForTrain = contextForTrain;
	}

	/**
	 * @return the trainFileOut
	 */
	public static String getTrainFileOut() {
		return trainFileOut;
	}

	/**
	 * @param trainFileOut the trainFileOut to set
	 */
	public static void setTrainFileOut(String trainFileOut) {
		HandleConstants.trainFileOut = trainFileOut;
	}

	/**
	 * @return the lemmaQuery
	 */
	public static String getLemmaQuery() {
		return lemmaQuery;
	}

	/**
	 * @param lemmaQuery the lemmaQuery to set
	 */
	public static void setLemmaQuery(String lemmaQuery) {
		HandleConstants.lemmaQuery = lemmaQuery;
	}

	/**
	 * @return the formReplace
	 */
	public static String getFormReplace() {
		return formReplace;
	}

	/**
	 * @param formReplace the formReplace to set
	 */
	public static void setFormReplace(String formReplace) {
		HandleConstants.formReplace = formReplace;
	}

	/**
	 * @return the posReplace
	 */
	public static String getPosReplace() {
		return posReplace;
	}

	/**
	 * @param posReplace the posReplace to set
	 */
	public static void setPosReplace(String posReplace) {
		HandleConstants.posReplace = posReplace;
	}

	/**
	 * @return the dataBase
	 */
	public static String getDataBase() {
		return dataBase;
	}

	/**
	 * @param dataBase the dataBase to set
	 */
	public static void setDataBase(String dataBase) {
		HandleConstants.dataBase = dataBase;
	}

	/**
	 * @return the dbUser
	 */
	public static String getDbUser() {
		return dbUser;
	}

	/**
	 * @param dbUser the dbUser to set
	 */
	public static void setDbUser(String dbUser) {
		HandleConstants.dbUser = dbUser;
	}

	/**
	 * @return the dbPassword
	 */
	public static String getDbPassword() {
		return dbPassword;
	}

	/**
	 * @param dbPassword the dbPassword to set
	 */
	public static void setDbPassword(String dbPassword) {
		HandleConstants.dbPassword = dbPassword;
	}

	/**
	 * @return the letterAnalyzed
	 */
	public static String getLetterAnalyzed() {
		return letterAnalyzed;
	}

	/**
	 * @param letterAnalyzed the letterAnalyzed to set
	 */
	public static void setLetterAnalyzed(String letterAnalyzed) {
		HandleConstants.letterAnalyzed = letterAnalyzed;
	}

	/**
	 * @return the ctsUriInvariant
	 */
	public static String getCtsUriInvariant() {
		return ctsUriInvariant;
	}

	/**
	 * @param ctsUriInvariant the ctsUriInvariant to set
	 */
	public static void setCtsUriInvariant(String ctsUriInvariant) {
		HandleConstants.ctsUriInvariant = ctsUriInvariant;
	}

}
