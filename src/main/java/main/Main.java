package main;

import componentBuilder.ComponentBuilder;
import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


/**
 * Created by Lukasz on 20.03.2017.
 */
public class Main extends JFrame implements ActionListener{
    public static String URL = "http://www.ecb.int/stats/eurofxref/eurofxref-daily.xml";

    public static void main(String[] args) throws IOException, JSONException {

        new Main().setVisible(true);
    }

    private Main() {
        super("Currency comparator");
        setSize(300,300);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout()); //setting proper layout

        ComponentBuilder builder = new ComponentBuilder(this);
        createComponents(builder);
    }

    private void createComponents(ComponentBuilder builder) {

        builder.addLabel("Welcome to currency comparator", GridBagConstraints.LINE_START);
        builder.addLabel("First Date", 0, 1);
        builder.addLabel("Second Date", 0, 2);
        builder.addLabel("Choose base currency", 0, 3);
        builder.addMaskedField(1, 1);
        builder.addMaskedField(1, 2);
        builder.addComboBox(1,3);

        builder.addCheckBox("SEK", 0, 4);
        builder.addCheckBox("CZK", 0, 5);
        builder.addCheckBox("HUF", 0, 6);
        builder.addCheckBox("GBP", 0, 7);
        builder.addCheckBox("PLN", 0, 8);

        builder.addCheckBox("JPY", 1, 4);
        builder.addCheckBox("AUD", 1, 5);
        builder.addCheckBox("CHF", 1, 6);
        builder.addCheckBox("EUD", 1, 7);
        builder.addCheckBox("RUB", 1, 8);

        builder.addConfirmButton("confirm", "send", 0, 9);
    }

    public void actionPerformed(ActionEvent e) {
        String name = e.getActionCommand();

        if(name.equalsIgnoreCase("confirm")) {
            System.out.println("button 1 pressed");
        } else if (name.equalsIgnoreCase("send")) {
            System.out.println("button 2 is pressed");
        } else if (name.equalsIgnoreCase("exit")) {
            System.out.println("closed");
            System.exit(0);            //TO SLUZY TO DO WYCHODZENIA Z PROGRAMU
        }
        System.out.println("ble");
    }
}







//                String reposnseString = "";
//        try {
//            //test zaczytania xml
//            readXml(URL);
//            //test jsona
//            JSONObject json1 = readJsonFromUrl("http://api.fixer.io/2012-06-12?base=PLN");
//            JSONObject json2 = readJsonFromUrl("http://api.fixer.io/2015-05-30?base=EUR");
//            //test parsa jsona do xml
//
//            System.out.println("\n");
//            reposnseString = modifyJsonToXml(json1, json2);
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            saveXmlToFile(reposnseString);
////                XmlFacade.indentXml("src/main/resources/response.xml");
//        } catch (Exception e) {
//            LOGGER.error("unable to save XML to file", e);
//        }