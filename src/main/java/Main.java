import org.json.JSONException;
import org.json.JSONObject;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import static xml.XmlFacade.*;


/**
 * Created by Łukasz on 20.03.2017.
 */
public class Main {
    public static String URL = "http://www.ecb.int/stats/eurofxref/eurofxref-daily.xml";
    public static void main(String[] args) throws IOException, JSONException {

        String reposnseString = "";
        try {
            //test zaczytania xml
            readXml(URL);
            //test jsona
            JSONObject json1 = readJsonFromUrl("http://api.fixer.io/latest?base=EUR");
            JSONObject json2 = readJsonFromUrl("http://api.fixer.io/2009-05-12?base=PLN");
            //test parsa jsona do xml

            System.out.println("\n");
            reposnseString = modifyJsonToXml(json1, json2);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        try {
            saveXmlToFile(reposnseString);
//                XmlFacade.indentXml("src/main/resources/response.xml");
        } catch (Exception e) {
            LOGGER.error("unable to save XML to file", e);
        }
    }

}
