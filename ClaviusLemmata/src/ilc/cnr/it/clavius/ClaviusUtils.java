/**
 * 
 */
package ilc.cnr.it.clavius;

import java.io.BufferedInputStream;
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

	public static String StreamToString(InputStream is){
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

}
