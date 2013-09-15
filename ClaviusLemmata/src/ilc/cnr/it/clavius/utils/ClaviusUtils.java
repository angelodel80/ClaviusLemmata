/**
 * 
 */
package ilc.cnr.it.clavius.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

/**
 * @author angelodel80
 *
 */
public class ClaviusUtils {

	/**
	 * 
	 */
	public ClaviusUtils() {
		// TODO Auto-generated constructor stub
	}

public static void verifyFile( File aFile, boolean w ) {
		
		if (aFile == null) {
	      throw new IllegalArgumentException("File should not be null.");
	    }
		
	    if (!aFile.exists()) {
	      throw new IllegalArgumentException ("File does not exist: " + aFile);
	    }
	    if (!aFile.isFile()) {
	      throw new IllegalArgumentException("Should not be a directory: " + aFile);
	    }
	    if (w && !aFile.canWrite()) {
	      throw new IllegalArgumentException("File cannot be written: " + aFile);
	    }
	    
	    System.out.println("verifying file: " + aFile.getAbsolutePath() + "\n");
	  }
	
	public static String streamToString(InputStream is){
		String ret = "";
		if(null != is) {
			BufferedInputStream binp = new BufferedInputStream(is);
			StringWriter writer = new StringWriter();

			try {
				IOUtils.copy(binp, writer, Charsets.UTF_8);
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}


			ret = writer.toString();
			try {
				binp.close();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ret;

	}
	
	//public static Document 

}
