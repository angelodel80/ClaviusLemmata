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
	public static String getTabFileAnalized() {
		return tabFileAnalyzed;
	}

	/**
	 * @param tabFileAnalized the tabFileAnalized to set
	 */
	public static void setTabFileAnalized(String tabFileAnalized) {
		HandleContsants.tabFileAnalyzed = tabFileAnalized;
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
		this.xmtTeiFile = xmtTeiFile;
	}

	/**
	 * 
	 */
	public HandleContsants() {
		// TODO Auto-generated constructor stub
	}

}
