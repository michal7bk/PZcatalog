package pl.bak.word;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class WordReader {

    private static final WordReader INSTANCE = new WordReader();
    HashSet set = new HashSet();

    private HashSet forbiddenWords;

    public WordReader() {
    }

    public static WordReader anInstance() {
        return INSTANCE;
    }



    public HashSet getForbiddenWords() {
        if (forbiddenWords == null) {
            try {
                forbiddenWords = read();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
        }

        return forbiddenWords;//

    }

    private HashSet read() throws ParserConfigurationException, IOException, SAXException {
        File fXmlFile = new File("src/main/resources/forbiddenWords.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("word");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                set.add(nList.item(temp).getTextContent());
            }

        }

            return  set;

    }
}
