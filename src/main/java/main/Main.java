package main;

import components.CheckBoxManager;
import components.ComponentBuilder;
import org.apache.log4j.*;
import org.json.JSONException;
import xml.XMLManager;

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
    public static Logger LOGGER = Logger.getLogger("Currency Comparator");
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

    JComboBox<String> baseCurrency;

    ComponentBuilder builder;
    CheckBoxManager checkBoxManager;
    XMLManager xmlManager;

    public static void main(String[] args) throws IOException, JSONException {

        new Main().setVisible(true);
    }

    private Main() {
        super("Currency comparator");
        setSize(300,300);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout()); //setting proper layout

        this.builder = new ComponentBuilder(this);
        this.checkBoxManager = new CheckBoxManager(this);
        this.xmlManager = new XMLManager();

        createComponents(this.builder);
    }

    public void actionPerformed(ActionEvent e) {
        String name = e.getActionCommand();

        if (name.equalsIgnoreCase("send")) {
            List<String> checkBoxList = checkBoxManager.getAllCheckedBoxes();

            try {
                if (!dateField1.getText().matches("\\d{4}-\\d{2}-\\d{2}") || !dateField2.getText().matches("\\d{4}-\\d{2}-\\d{2}")) {
                    throw new Exception("Date 1 or date 2 is not filled!");
                }

                String chosenBaseCurrency = String.valueOf(baseCurrency.getSelectedItem());

                xmlManager.createXMLFiles(dateField1.getText(), dateField2.getText(), chosenBaseCurrency, checkBoxList);
            } catch (IOException e1) {
                LOGGER.error("Problem with inpout file or sth related to this", e1);
            } catch (JSONException e2) {
                LOGGER.error("Json exception, check what happened there", e2);
            } catch (Exception e3) {
                LOGGER.info("Date exception : " , e3);
            }

            this.dateField1.setText("");
            this.dateField2.setText("");
        } else if (name.equalsIgnoreCase("exit")) {
            System.out.println("closed");
            System.exit(0);            //TO SLUZY TO DO WYCHODZENIA Z PROGRAMU
        }
    }

    private void createComponents(ComponentBuilder builder) {

        builder.addLabel("Welcome to currency comparator", GridBagConstraints.LINE_START);
        builder.addLabel("First Date", 0, 1);
        builder.addLabel("Second Date", 0, 2);
        builder.addLabel("Choose base currency", 0, 3);
        this.dateField1 = builder.addMaskedField(1, 1);
        this.dateField2 = builder.addMaskedField(1, 2);
        this.baseCurrency = builder.addComboBox(1,3);

        this.checkBox1 = builder.addCheckBox("SEK", 0, 4);
        this.checkBox2 = builder.addCheckBox("CZK", 0, 5);
        this.checkBox3 = builder.addCheckBox("HUF", 0, 6);
        this.checkBox4 = builder.addCheckBox("GBP", 0, 7);
        this.checkBox5 = builder.addCheckBox("PLN", 0, 8);

        this.checkBox6 = builder.addCheckBox("JPY", 1, 4);
        this.checkBox7 = builder.addCheckBox("AUD", 1, 5);
        this.checkBox8 = builder.addCheckBox("CHF", 1, 6);
        this.checkBox9 = builder.addCheckBox("EUR", 1, 7);
        this.checkBox10 = builder.addCheckBox("RUB", 1, 8);

        builder.addConfirmButton("Confirm", "send", 0, 9);
        builder.addConfirmButton("Exit", "exit", 0, 10);
    }
}








