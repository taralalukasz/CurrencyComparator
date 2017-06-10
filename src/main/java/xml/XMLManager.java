package xml;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;


/**
     * Created by Lukasz on 06.03.2017.
     */
    public class XMLManager {

        public static Logger LOGGER = LogManager.getLogger("xml response");

        public String modifyJsonToXml (JSONObject json1, JSONObject json2) throws JSONException {
            StringBuilder sb = new StringBuilder();
            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            sb.append("\n");
            sb.append("<?xml-stylesheet type=\"text/xsl\" version=\"1.0\" href=\"transform.xsl\"?>");
            sb.append("\n");
            sb.append("<comparator>");
            sb.append("<currencies>");
            sb.append("\n");
            sb.append(XML.toString(json1));
            sb.append("\n");
            sb.append("</currencies>");
            sb.append("<currencies>");
            sb.append("\n");
            sb.append(XML.toString(json2));
            sb.append("\n");
            sb.append("</currencies>");
            sb.append("</comparator>");
            String xmlfile = sb.toString();

            xmlfile = xmlfile.replace("><", ">\n<");

            return xmlfile;
        }

        public void changeXmlParametersToAttributes(String sourceFile, String targetFile) throws FileNotFoundException {
            String[] separatedTransformedXml = readFile(sourceFile).split("\n");
            try {
                for(int i = 0; i < separatedTransformedXml.length; i++) {
                    if (separatedTransformedXml[i].contains("<rates>")) {
                        for (int j = i + 1; j < separatedTransformedXml.length; j++) {
                            if (separatedTransformedXml[j].contains("</rates>")) {
                                break;
                            } else {
                                separatedTransformedXml[j] = replaceCurrencyFormat(separatedTransformedXml[j]);
                            }
                        }
                    }
                }

                saveXmlAsArrayToFile(separatedTransformedXml, targetFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    private void saveXmlAsArrayToFile(String[] xmlAsArray, String targetFile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile));

        for (String line : xmlAsArray) {
            writer.write(line + "");
            writer.newLine();
        }

        writer.flush();
        writer.close();
    }

    private String replaceCurrencyFormat(String oldFormat) {
        // old format is   <CHF>0.29556</CHF>
        StringBuilder sb = new StringBuilder();
        String currency = oldFormat.replaceAll("\\s+","").substring(1, 4);
        String value = oldFormat.replaceAll("\\s+","").substring(5,9);
        value += "0";

        sb.append("\t\t\t<currency");
        sb.append(" type=\"" + currency + "\">");
        sb.append(value);
        sb.append("</currency>");

        return sb.toString();
    }

    private String readFile(String sourceFile) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
        String readFileContent = "";
        try {
            String line = reader.readLine();
            while (line != null) {
                sb.append(line + "\n");
                line = reader.readLine();
            }

            readFileContent = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return readFileContent;
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


    public static void saveXmlToFile(String xmlInString, String filename) throws IOException, TransformerException, ParserConfigurationException, SAXException {

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("src/main/resources/" + filename));
            out.write(xmlInString);
            out.close();
        } catch (IOException e) {
            LOGGER.error("Failed to save  : " + e);
        }
    }

    public void createXMLFiles(String firstDate, String secondDate, String base) throws IOException, JSONException {
        String reposnseString = "";
        try {
            JSONObject json1 = readJsonFromUrl("http://api.fixer.io/" + firstDate + "?base=" + base);
            JSONObject json2 = readJsonFromUrl("http://api.fixer.io/" + secondDate + "?base=" + base);

            System.out.println("\n");
            reposnseString = modifyJsonToXml(json1, json2);

            saveXmlToFile(reposnseString, "response.xml");

            changeXmlParametersToAttributes("src/main/resources/response.xml", "src/main/resources/response.xml");



        } catch (Exception e) {
            LOGGER.error("unable to save XML to file", e);
        }
    }

    public void generateXsltTransformFile(List<String> checkBoxList) {
        File file =new File("src/main/resources/template.xsl");
        Scanner in;
        String transformBody = "";
            try {
                in = new Scanner(file);
                while(in.hasNext())
                {
                    String line=in.nextLine();
                    transformBody += line;
                    transformBody += "\n";

                    if(line.contains("<table class=\"table table-striped\">")) {
                        transformBody += "                                <tr>\n" +
                                "                                    <th>currency</th>\n" +
                                "                                    <th>value</th>\n" +
                                "                                </tr>\n";

                        for (String checkboxValue : checkBoxList) {
                            transformBody += "                                <tr>\n" +
                                    "                                    <td>" + checkboxValue + "</td>\n" +
                                    "                                    <td><xsl:value-of select=\"/comparator/currencies/rates/currency[@type='" + checkboxValue + "']\"/></td>\n" +
                                    "                                </tr>\n";
                        }
                    }
                }

                saveXmlToFile(transformBody, "transform.xsl");
            } catch (FileNotFoundException e) {

                LOGGER.error("File transform.xml not found", e);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void transformXmlToHtml(String responseSource, String transformSource) {
        try {
             TransformerFactory tFactory = TransformerFactory.newInstance();

             Transformer transformer = tFactory.newTransformer(new StreamSource(transformSource));

             transformer.transform  (new javax.xml.transform.stream.StreamSource(responseSource ),
                     new StreamResult( new FileOutputStream("src/main/resources/output.html")));
        }
        catch (Exception e) {
            e.printStackTrace( );
        }
    }

    public boolean validateXmlWithXsd() {
        File schemaFile = new File("src/main/resources/validate.xsd");
        Source xmlFile = new StreamSource(new File("src/main/resources/response.xml"));
        SchemaFactory schemaFactory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();

            validator.validate(xmlFile);
            System.out.println(xmlFile.getSystemId() + " is valid");

            return true;
        } catch (SAXException e) {
            System.out.println(xmlFile.getSystemId() + " is NOT valid reason:" + e);
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
