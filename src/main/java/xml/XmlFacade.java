package xml;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;




    /**
     * Created by Lukasz on 06.03.2017.
     */
    public class XmlFacade {

       public static Logger LOGGER = LogManager.getLogger("xml response");


    public static String modifyJsonToXml (JSONObject json) throws JSONException {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("\n");
        sb.append("<?xml-stylesheet type=\"text/xsl\" version=\"1.0\" href=\"transform.xsl\"?>");
        sb.append("\n");
        sb.append("<currency>");
        sb.append("\n");
        sb.append(XML.toString(json));
        sb.append("\n");
        sb.append("</currency>");
        String xmlfile = sb.toString();

        xmlfile = xmlfile.replace("><", ">\n<");



        return xmlfile;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }


    public static String readXml(String url) throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        String response = "";
        try {
            Document doc = db.parse(new URL(url).openStream());


            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);

            System.out.println("XML IN String format is: \n" + writer.toString());

            response = writer.toString();
        } catch (Exception e) {
            LOGGER.error("reading  XML failed with exception : " + e);
        }
        return response;
    }

    public static void saveXmlToFile(String xmlInString) throws IOException, TransformerException, ParserConfigurationException, SAXException {

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("src/main/resources/response.xml"));
            out.write(xmlInString);
            out.close();
        } catch (IOException e) {
            LOGGER.error("Failed to save  : " + e);
        }
    }

    public static void indentXml(String sourceFile) throws TransformerException, ParserConfigurationException, IOException, SAXException {
        // Output the XML
        // set up a transformer
        DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
        File xmlFile = new File(sourceFile);
        Document doc = docBuilder.parse(xmlFile);


        TransformerFactory transfac = TransformerFactory.newInstance();
        Transformer trans = transfac.newTransformer();
        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        trans.setOutputProperty(OutputKeys.INDENT, "yes");
        // create string from xml tree
        StringWriter sw = new StringWriter();
        StreamResult result = new StreamResult(sw);
        DOMSource source = new DOMSource(doc);
        trans.transform(source, result);
        String xmlString = sw.toString();
        // print xml
        System.out.println("Here's the xml:\n\n" + xmlString);

    }
}
