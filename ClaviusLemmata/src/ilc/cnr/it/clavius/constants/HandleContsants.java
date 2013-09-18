/**
 * 
 */
package ilc.cnr.it.clavius.constants;

/**
 * @author angelodel80
 * 
 */
public class HandleContsants {

	private static String xmtTeiFile = "/Users/angelodel80/Risorse/sources/clavius/147APUG530cc_129130_xml.xml";
	private static String modelforHunPos = "testFirst.model";
	private static String tabFileAnalyzed = "/Users/angelodel80/Risorse/sources/clavius/out-tokensLemmatized-11092013.txt";
	private static String taggedFile = "/Users/angelodel80/Risorse/sources/clavius/TaggedFile.txt";
	private static String workDir = "/Users/angelodel80/Risorse/sources/clavius/";
	private static String letterRif = "147";
	
	private static String pathToHunPos = System.getenv("HOME")+System.getProperty("file.separator")+"Risorse"+System.getProperty("file.separator")+"tools"+System.getProperty("file.separator")+"hunpos-1.0-macosx"+System.getProperty("file.separator")+"hunpos-tag";
	private static String pathToHunPosModel = System.getenv("HOME")+System.getProperty("file.separator")+"Risorse"+System.getProperty("file.separator")+"tools"+System.getProperty("file.separator");
	
	private static String xpathForSentences = "/TEI/text/body/div/p/s";
	private static String atttributeSentencesName = "n";
	private static String xpathForText = "node() | ./w/text() | ./choice/expan/text() | ./rs/text() | ./dateline/text()";
	
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
		HandleContsants.pathToHunPos = pathToHunPos;
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
		HandleContsants.letterRif = letterRif;
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
		HandleContsants.workDir = workDir;
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
		HandleContsants.taggedFile = taggedFile;
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
		HandleContsants.tabFileAnalyzed = tabFileAnalyzed;
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
		HandleContsants.modelforHunPos = modelforHunPos;
	}

	/**
	 * @return the xmtTeiFile
	 */
	public static String getXmtTeiFile() {
		return xmtTeiFile;
	}

	/**
	 * @param xmtTeiFile
	 *            the xmtTeiFile to set
	 */
	private void setXmtTeiFile(String xmtTeiFile) {
		HandleContsants.xmtTeiFile = xmtTeiFile;
	}

	/**
	 * 
	 */
	public HandleContsants() {
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
		HandleContsants.pathToHunPosModel = pathToHunPosModel;
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
		HandleContsants.xpathForSentences = xpathForSentences;
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
		HandleContsants.atttributeSentencesName = atttributeSentencesName;
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
		HandleContsants.xpathForText = xpathForText;
	}

}
