/**
 * 
 */
package ilc.cnr.it.clavius.lemmata;

import ilc.cnr.it.clavius.constants.HandleContsants;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Angelo Del Grosso
 * 
 */
public class ParseToken extends ParseText {

	/**
	 * 
	 */
	public ParseToken() {
	}

	public static boolean run() {
		System.out.println(outFile.getAbsolutePath());
		boolean ret = false;
		BufferedReader reader = null;
		outString = new StringBuilder();
		try {
			reader = getReader(myFile);
			leggi(reader);
			scrivi(outString);
			ret = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	private static boolean leggi(BufferedReader reader) {
		String line = "";
		boolean ret = false;
		try {
			while (null != (line = reader.readLine())){
				System.out.println(line);
				if(line.split("\t").length < 2){
					outString.append(line+"\n"); // save the sentence content

				}
				else{
					elabora(line); // manage the analysis
				}
			}
			ret = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

	

	public static boolean elabora(String line){
		boolean ret = false;
		Connection connection = null;
		Statement stm = null;
		ResultSet rs = null;

		String query = HandleContsants.getLemmaQuery();
		if( (null!= line) && (line.split("\t").length >= 2) ){
			String token = line.split("\t")[0];
			String morphoTag = line.split("\t")[1];
			String pos = morphoTag.substring(0, 1);
			//System.out.println("TOKEN: "+ token);
			//System.out.println("PoS: "+ pos);
			//FIXME
			//IMPORTANTE: le attribuzioni dei tratti morfologici possono essere sbagliate, per esempio HunPos indica Occupazionibus come nome maschile, invece è femminile. Probabilmente utilizzando un lessico tale fenomeno diventa irrilevante

			//			outString.append("TOKEN: "+ token +"\n");
			//
			if(!("".equals(token))){
				query = query.replaceFirst(HandleContsants.getFormReplace(), token).replaceFirst(HandleContsants.getPosReplace(), pos);
				System.out.println(query);
				//				// FIXME group by
				//				query = query+" group by lemma_id";
				connection = connectDatabase();
				try {
					stm = connection.createStatement();
					rs = stm.executeQuery(query);
					StringBuilder morphos = new StringBuilder();
					String lemma="";
					//
					while (rs.next()){
				
						lemma = rs.getString("lemma_text");
						morphos.append(" ("+rs.getString("morph_code")+") ");

					}
					outString.append(String.format("\n%s\t%s\t%s\t%s\n", token, morphoTag, lemma, morphos.toString()));

					//
					//
				} catch (SQLException e) {
					e.printStackTrace();
				} finally{
					try {
						stm.close();
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
				ret = true;
			}
		}

		return ret;
	}

	public static Connection connectDatabase(){
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(HandleContsants.getDataBase(), HandleContsants.getDbUser(), HandleContsants.getDbPassword());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

}
