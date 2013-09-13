/**
 * 
 */
package ilc.cnr.it.clavius.lemmata;

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
		// TODO Auto-generated constructor stub
	}

	public static boolean run() {
		System.out.println(outFile.getAbsolutePath());
		boolean ret = false;
		BufferedReader reader = null;
		outString = new StringBuilder("----- START FILE -----\n");
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
				// TODO Auto-generated catch block
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
				//outString.append("\n\n"+line+"\n");
				if(line.split("\t").length < 2){
					outString.append(line+"\n");

				}
				else{
					elabora(line);
				}
			}
			ret = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	private static boolean elabora_1(String line){
		boolean ret = false;
		Connection connection = null;
		Statement stm = null;
		ResultSet rs = null;

		//String query = "select * from hib_lemmas where lemma_text =\"";
		String query = "select count(morph_code) as morph_count, lemma_id from hib_parses where bare_form =\"";
		if(null!= line){
			String token = line.substring(line.indexOf("@")+1, line.indexOf("[")).replaceAll("[,;.?]", ""); 
			System.out.println("TOKEN: "+ token);
			//outString.append("TOKEN: "+ token +"\n");

			if(!("".equals(token))){
				query = query+token+"\"";
				// FIXME group by
				query = query+" group by lemma_id";
				connection = connectDatabase();
				try {
					stm = connection.createStatement();
					rs = stm.executeQuery(query);

					while (rs.next()){
						//String def = rs.getString("lemma_short_def");
						String morpho_code = rs.getString("morph_count");
						String lemma_id = rs.getString("lemma_id");
						System.out.println("[["+morpho_code+"]]" + "[[" + lemma_id+"]]");
						outString.append("[["+morpho_code+"]]"+"[[" + lemma_id+"]]" + "\n");
					}


				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally{
					try {
						stm.close();
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				ret = true;
			}
		}

		return ret;
	}

	public static boolean elabora(String line){
		System.err.println("in elabora()");
		boolean ret = false;
		Connection connection = null;
		Statement stm = null;
		ResultSet rs = null;

		//String query = "select * from hib_lemmas where lemma_text =\"";
		// esempio query select lemma_text, morph_code from hib_parses as f left join hib_lemmas as l on f.lemma_id = l.lemma_id where f.form = "erat" and f.morph_code like "v%" 
		//String query = "select count(morph_code) as morph_count, lemma_id from hib_parses where bare_form =\"";
		String query = "SELECT lemma_text, morph_code FROM hib_parses as f LEFT JOIN hib_lemmas as l on f.lemma_id = l.lemma_id WHERE f.bare_form = \"{[?form?]}\" and f.morph_code like \"{[?pos?]}%\"" ;
		if( (null!= line) && (line.split("\t").length >= 2) ){
			String token = line.split("\t")[0];
			String morphoTag = line.split("\t")[1];
			String pos = morphoTag.substring(0, 1);
			System.out.println("TOKEN: "+ token);
			System.out.println("PoS: "+ pos);
			//FIXME
			//IMPORTANTE: le attribuzioni dei tratti morfologici possono essere sbagliate, per esempio HunPos indica Occupazionibus come nome maschile, invece p femminile. Probabilmente utilizzando il lessico tale fenomeno diventa irrilevante

			//			outString.append("TOKEN: "+ token +"\n");
			//
			if(!("".equals(token))){
				//query = query+token+"\"";
				query = query.replaceFirst("\\{\\[\\?form\\?\\]\\}", token).replaceFirst("\\{\\[\\?pos\\?\\]\\}", pos);
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
						//						//String def = rs.getString("lemma_short_def");
						//						String morpho_code = rs.getString("morph_count");
						//						String lemma_id = rs.getString("lemma_id");
						//						System.out.println("[["+morpho_code+"]]" + "[[" + lemma_id+"]]");
						//						outString.append("[["+morpho_code+"]]"+"[[" + lemma_id+"]]" + "\n");

						lemma = rs.getString("lemma_text");
						morphos.append(" ("+rs.getString("morph_code")+") ");

					}
					outString.append(String.format("\n%s\t%s\t%s\t%s\n", token, morphoTag, lemma, morphos.toString()));

					//
					//
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally{
					try {
						stm.close();
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/perseus", "angelodel80", "190280");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
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
