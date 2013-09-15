/**
 * 
 */
package ilc.cnr.it.clavius.constants;

/**
 * @author angelodel80
 * 
 */
public class HandleContants {

	private static String xmtTeiFile = "/Users/angelodel80/Risorse/sources/clavius/147APUG530cc_129130_xml.xml";
	private static String modelforHunPos = "testFirst.model";
	private static String tabFileAnalyzed = "/Users/angelodel80/Risorse/sources/clavius/out-tokensLemmatized-11092013.txt";
	private static String taggedFile = "/Users/angelodel80/Risorse/sources/clavius/TaggedFile.txt";
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
		HandleContants.taggedFile = taggedFile;
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
		HandleContants.tabFileAnalyzed = tabFileAnalized;
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
		HandleContants.modelforHunPos = modelforHunPos;
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
	public HandleContants() {
		// TODO Auto-generated constructor stub
	}

}
