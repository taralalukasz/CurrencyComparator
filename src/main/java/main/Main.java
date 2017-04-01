package main;

import components.CheckBoxManager;
import components.ComponentBuilder;
import org.json.JSONException;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Lukasz on 20.03.2017.
 */
public class Main extends JFrame implements ActionListener{
    public static String URL = "http://www.ecb.int/stats/eurofxref/eurofxref-daily.xml";

    JFormattedTextField dateField1;
    JFormattedTextField dateField2;

    JCheckBox checkBox1;
    JCheckBox checkBox2;
    JCheckBox checkBox3;
    JCheckBox checkBox4;
    JCheckBox checkBox5;
    JCheckBox checkBox6;
    JCheckBox checkBox7;
    JCheckBox checkBox8;
    JCheckBox checkBox9;
    JCheckBox checkBox10;

    CheckBoxManager manager;

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
        this.manager = new CheckBoxManager(this);
        createComponents(builder);
    }

    private void createComponents(ComponentBuilder builder) {

        builder.addLabel("Welcome to currency comparator", GridBagConstraints.LINE_START);
        builder.addLabel("First Date", 0, 1);
        builder.addLabel("Second Date", 0, 2);
        builder.addLabel("Choose base currency", 0, 3);
        this.dateField1 = builder.addMaskedField(1, 1);
        this.dateField2 = builder.addMaskedField(1, 2);
        builder.addComboBox(1,3);

        this.checkBox1 = builder.addCheckBox("SEK", 0, 4);
        this.checkBox2 = builder.addCheckBox("CZK", 0, 5);
        this.checkBox3 = builder.addCheckBox("HUF", 0, 6);
        this.checkBox4 = builder.addCheckBox("GBP", 0, 7);
        this.checkBox5 = builder.addCheckBox("PLN", 0, 8);

        this.checkBox6 = builder.addCheckBox("JPY", 1, 4);
        this.checkBox7 = builder.addCheckBox("AUD", 1, 5);
        this.checkBox8 = builder.addCheckBox("CHF", 1, 6);
        this.checkBox9 = builder.addCheckBox("EUD", 1, 7);
        this.checkBox10 = builder.addCheckBox("RUB", 1, 8);

        builder.addConfirmButton("Confirm", "send", 0, 9);
        builder.addConfirmButton("Exit", "exit", 0, 10);


//        List<JCheckBox> checkboxes = new ArrayList<JCheckBox>();
//        for( Component comp : this.getComponents() ) {
//            if( comp instanceof JCheckBox) checkboxes.add( (JCheckBox)comp );
//        }
    }

    public void actionPerformed(ActionEvent e) {
        String name = e.getActionCommand();

        if (name.equalsIgnoreCase("send")) {
            List<String> checkBoxValues = manager.getAllCheckedBoxes();
            List<String> checkBoxValues2 = getAllCheckedBoxes(this);
            System.out.println("sraka");
            System.out.println(this.dateField1.getText());
            System.out.println(this.dateField2.getText());

            this.dateField1.setText("");
            this.dateField2.setText("");
        } else if (name.equalsIgnoreCase("exit")) {
            System.out.println("closed");
            System.exit(0);            //TO SLUZY TO DO WYCHODZENIA Z PROGRAMU
        }
    }

    private List<String> getAllCheckedBoxes(Main container) {
        List<Component> components = getAllComponents(container);
        List<JCheckBox> checkBoxList = getAllCheckBoxes(components);
        return getOnlyCheckedBoxes(checkBoxList);
    }

    private static List<Component> getAllComponents(final Container c) {
        Component[] comps = c.getComponents();
        List<Component> compList = new ArrayList<Component>();
        for (Component comp : comps) {
            compList.add(comp);
            if (comp instanceof Container)
                compList.addAll(getAllComponents((Container) comp));
        }
        return compList;
    }

    private static List<JCheckBox> getAllCheckBoxes(List<Component> components) {
        List<JCheckBox> lista = new ArrayList<JCheckBox>();
        for (Component comp : components) {
            if (comp instanceof JCheckBox) {
                lista.add((JCheckBox) comp);
            }
        }
        return lista;
    }

    private static List<String> getOnlyCheckedBoxes(List<JCheckBox> checkBoxList) {
        List<String> checkedValues = new ArrayList<String>();
        for (JCheckBox checkBox : checkBoxList) {
            if (checkBox.isSelected()) {
                checkedValues.add(checkBox.getText());
            }
        }

        return checkedValues;
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