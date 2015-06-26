package alt.sow.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLUtil {
	public static List<String> parse() throws ParserConfigurationException,
			SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		// Build Document
		//InputStream u = Class.class.getResourceAsStream("/resources/struts.xml");
		File f = new File("resources", "struts.xml");
		System.out.println(f.getCanonicalPath());
		Document document = builder.parse(f);

		// Normalize the XML Structure; It's just too important !!
		document.getDocumentElement().normalize();

		// Here comes the root node
		Element root = document.getDocumentElement();
		System.out.println(root.getNodeName());

		// Get all employees
		NodeList nList = document.getElementsByTagName("action");
		System.out.println("============================");
		List<String> strings = new ArrayList<String>();
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node node = nList.item(temp);
			System.out.println(""); // Just a separator
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				// Print each employee's detail
				Element eElement = (Element) node;
				// System.out.println("Action : " +
				// eElement.getAttribute("name"));
				strings.add(eElement.getAttribute("name"));
			}
		}
		return strings;
	}

	public static void main(String[] a) {
		try {
			System.out.println(Arrays.toString(parse().toArray(new String[0])));
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
