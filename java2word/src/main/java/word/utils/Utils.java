package word.utils;

import java.io.BufferedReader;
//import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import word.api.interfaces.IDocument;

//import javax.xml.transform.Source;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.stream.StreamResult;
//import javax.xml.transform.stream.StreamSource;

//import org.dom4j.DocumentException;
//import org.dom4j.DocumentHelper;
//import org.dom4j.Node;

public class Utils {

	
	/**
	 * @return
	 * 
	 *         The root of the web app as String.
	 */
	public static String getAppRoot() {
		File file = new File(".");
		try {
			return file.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Can't get app root directory", e);
		}
	}

	/**
	 * 
	 * @param file
	 *            : It is the full path to the file
	 * 
	 * @return String with the content of the file
	 */
	public static String readFile(String file) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Can't find the file", e);
		}
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");
		try {
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}
			return stringBuilder.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			reader = null;
		}
	}

	

	public static String replaceSpecialCharacters(IDocument myDoc) {
//		String cont = myDoc.getContent();
//		cont = cont.replace("Ã¬", "&#236;");
//		cont = cont.replace("Ã­", "&#237;");
//		cont = cont.replace("Ã®", "&#238;");
//		cont = cont.replace("Ã¯", "&#239;");
		
//		System.out.println("#### FFF 02 ####");
//		System.out.println(myDoc.getContent());
		return myDoc.getContent().replace("Ã­", "&#237;");
	}
	
	
	/**
	 * Full list: http://en.wikipedia.org/wiki/List_of_XML_and_HTML_character_entity_references
	 * @param original: the original string that may contain special characters  
	 * @return a new string that all specials have been replaced with Unicode Code Point(Decimal)
	 */
	public static String replaceSpecialCharacters(String original) {
		Map<String, String> specials = new HashMap<String, String>();

		//from 192 to 255
		specials.put("Ã€", "&#192;");
		specials.put("Ã�", "&#193;");
		specials.put("Ã‚", "&#194;");
		specials.put("Ãƒ", "&#195;");
		specials.put("Ã„", "&#196;");
		specials.put("Ã„", "&#196;");
		specials.put("Ã…", "&#197;");
		specials.put("Ã†", "&#198;");
		specials.put("Ã‡", "&#199;");
		specials.put("Ãˆ", "&#200;");
		specials.put("Ã‰", "&#201;");
		specials.put("ÃŠ", "&#202;");
		specials.put("Ã‹", "&#203;");
		specials.put("ÃŒ", "&#204;");
		specials.put("Ã�", "&#205;");
		specials.put("ÃŽ", "&#206;");
		specials.put("Ã�", "&#207;");
		specials.put("Ã�", "&#208;");
		specials.put("Ã‘", "&#209;");
		specials.put("Ã’", "&#210;");
		specials.put("Ã“", "&#211;");
		specials.put("Ã”", "&#212;");
		specials.put("Ã•", "&#213;");
		specials.put("Ã–", "&#214;");
		specials.put("Ã—", "&#215;");
		specials.put("Ã˜", "&#216;");
		specials.put("Ã™", "&#217;");
		specials.put("Ãš", "&#218;");
		specials.put("Ã›", "&#219;");
		specials.put("Ãœ", "&#220;");
		specials.put("Ã�", "&#221;");
		specials.put("Ãž", "&#222;");
		specials.put("ÃŸ", "&#223;");
		specials.put("Ã ", "&#224;");
		specials.put("Ã¡", "&#225;");
		specials.put("Ã¢", "&#226;");
		specials.put("Ã£", "&#227;");
		specials.put("Ã¤", "&#228;");
		specials.put("Ã¥", "&#229;");
		specials.put("Ã¦", "&#230;");
		specials.put("Ã§", "&#231;");
		specials.put("Ã¨", "&#232;");
		specials.put("Ã©", "&#233;");
		specials.put("Ãª", "&#234;");
		specials.put("Ã«", "&#235;");
		specials.put("Ã¬", "&#236;");
		specials.put("Ã­", "&#237;");
		specials.put("Ã®", "&#238;");
		specials.put("Ã¯", "&#239;");
		specials.put("Ã°", "&#240;");
		specials.put("Ã±", "&#241;");
		specials.put("Ã²", "&#242;");
		specials.put("Ã³", "&#243;");
		specials.put("Ã´", "&#244;");
		specials.put("Ãµ", "&#245;");
		specials.put("Ã¶", "&#246;");
		specials.put("Ã·", "&#247;");
		specials.put("Ã¸", "&#248;");
		specials.put("Ã¹", "&#249;");
		specials.put("Ãº", "&#250;");
		specials.put("Ã»", "&#251;");
		specials.put("Ã¼", "&#252;");
		specials.put("Ã½", "&#253;");
		specials.put("Ã¾", "&#254;");
		specials.put("Ã¿", "&#255;");

		//from xx to xx, if we need more

		for (String key : specials.keySet()) {
			original = original.replace(key, specials.get(key));
		}
		return original;
	}

	/**
	 * 
	 * @param xml
	 *            xml to be pretifized
	 * 
	 * @return pretifized xml
	 */
	/*
	 * public static String pretty(String xml) { try { org.dom4j.Document
	 * document = DocumentHelper.parseText(xml) .getDocument();
	 * 
	 * String res = formatXml(document, false); return res; } catch
	 * (DocumentException e) { e.printStackTrace(); throw new
	 * RuntimeException("Can't parse xml", e); } }
	 */

	/*
	 * public static String formatXml(Node node, boolean oneLine) {
	 * 
	 * try { TransformerFactory tf = TransformerFactory.newInstance();
	 * Transformer transformer = tf.newTransformer();
	 * 
	 * transformer.setOutputProperty("omit-xml-declaration", "yes");
	 * transformer.setOutputProperty("method", "xml");
	 * transformer.setOutputProperty("encoding", "ISO-8859-1");
	 * transformer.setOutputProperty(
	 * "{http://xml.apache.org/xslt}indent-amount", "4");
	 * transformer.setOutputProperty("indent", "yes"); java.io.StringWriter sw =
	 * new java.io.StringWriter(); StreamResult sr = new StreamResult(sw); //
	 * Must strip out new lines and whitespace, because formatter thinks // this
	 * is content that should be preserved in pretty String xml =
	 * node.asXML().replaceAll(">\\s*(\\r|\\n)\\s*", ">")
	 * .replaceAll("\\s*(\\r|\\n)\\s*<", "<");
	 * 
	 * // String xml = node.asXML(); ByteArrayInputStream inputStream = new
	 * ByteArrayInputStream(xml .getBytes("UTF-8")); Source domSource = new
	 * StreamSource(inputStream); transformer.transform(domSource, sr);
	 * 
	 * String prettyXml = sw.toString();
	 * 
	 * // Although this looks like the same thing as above, it actually // isn't
	 * // if (oneLine) { // prettyXml =
	 * prettyXml.replaceAll(">\\s*(\\r|\\n)\\s*<", "><"); // }
	 * 
	 * return prettyXml; } catch (Exception e) { throw new RuntimeException(e);
	 * } }
	 */

}
