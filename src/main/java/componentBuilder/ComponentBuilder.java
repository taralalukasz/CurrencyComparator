package componentBuilder;

import main.Main;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

import static xml.XmlFacade.LOGGER;

/**
 * Created by Lukasz on 30.03.2017.
 */
public class ComponentBuilder {

    public ComponentBuilder(Main frame) {
        this.frame = frame;
    }

    private Main frame;

    public void addCheckBox(String boxValue, int gridx, int gridy) {
        GridBagConstraints c = new GridBagConstraints(); //ograniczenia guzikow

        c.gridx = gridx;
        c.gridy = gridy;

        JCheckBox checkBox = new JCheckBox(boxValue);
        frame.add(checkBox, c);
    }

    public void addComboBox(int gridx, int gridy) {
        GridBagConstraints c = new GridBagConstraints(); //ograniczenia guzikow

        c.gridx = gridx;
        c.gridy = gridy;

        String[] currencies = {"SEK", "CZK", "HUF", "GBP", "PLN", "JPY", "AUD", "CHF", "EUD" , "RUB"};
        JComboBox<String> comboBox = new JComboBox<String>(currencies);
        comboBox.setSelectedIndex(4);
        //comboBox.addActionListener(this);
        frame.add(comboBox, c);
    }

    public void addLabel(String labelText, int placement, double weighty) {
        GridBagConstraints c = new GridBagConstraints(); //ograniczenia guzikow

        c.weighty = weighty;
        c.anchor = placement;

        JLabel label = new JLabel(labelText);
        frame.add(label, c);
    }

    public void addLabel(String labelText, int placement) {
        GridBagConstraints c = new GridBagConstraints(); //ograniczenia guzikow

        c.anchor = placement;
        c.gridwidth = 3;

        JLabel label = new JLabel(labelText);
        frame.add(label, c);
    }

    public void addLabel(String labelText, int gridx, int gridy) {
        GridBagConstraints c = new GridBagConstraints(); //ograniczenia guzikow

        c.gridx = gridx;
        c.gridy = gridy;

        JLabel label = new JLabel(labelText);
        frame.add(label, c);
    }

    public void addLabel(String labelText, int gridx, int gridy, double weighty) {
        GridBagConstraints c = new GridBagConstraints(); //ograniczenia guzikow

        c.gridx = gridx;
        c.gridy = gridy;
        c.weighty = weighty;

        JLabel label = new JLabel(labelText);
        frame.add(label, c);
    }

    public void addMaskedField(int gridx, int gridy) {
        GridBagConstraints c = new GridBagConstraints(); //ograniczenia guzikow

        c.gridx = gridx;
        c.gridy = gridy;
        //field with data
        JPanel datePanel = new JPanel(new BorderLayout()); //stworzenie panelu
        MaskFormatter format;

        try {
            format = new MaskFormatter("####-##-##");
            JFormattedTextField dateTextField = new JFormattedTextField(format);  //textfield z formatem daty
            datePanel.add(dateTextField);  //dodanie textfieldu z formatem do date panelu
            datePanel.setPreferredSize(new Dimension(70, 20));

            frame.add(datePanel, c); //dodanie panelu do guzila
        } catch (ParseException e) {
            LOGGER.error("Cannot parse the date, wrong format", e);
        }
    }

    public void addMaskedField(int gridx, int gridy, int gridwidth) {
        GridBagConstraints c = new GridBagConstraints(); //ograniczenia guzikow

        c.gridx = gridx;
        c.gridy = gridy;
        c.gridwidth = gridwidth;
        //field with data
        JPanel datePanel = new JPanel(new BorderLayout()); //stworzenie panelu
        MaskFormatter format;

        try {
            format = new MaskFormatter("####-##-##");
            JFormattedTextField dateTextField = new JFormattedTextField(format);  //textfield z formatem daty
            datePanel.add(dateTextField);  //dodanie textfieldu z formatem do date panelu

            frame.add(datePanel, c); //dodanie panelu do guzila
        } catch (ParseException e) {
            LOGGER.error("Cannot parse the date, wrong format", e);
        }
    }

    public void addCustomButton(String buttonName, String buttonCommand, int gridx, int gridy, int placement) {
        JButton button = new JButton(buttonName); //create a button
        button.setActionCommand(buttonCommand);
        button.addActionListener(frame);

        GridBagConstraints c = new GridBagConstraints(); //ograniczenia guzikow

        c.anchor = placement;

        frame.add(button, c);
    }

    public void addConfirmButton(String buttonName, String buttonCommand, int gridx, int gridy) {
        JButton button = new JButton(buttonName); //create a button
        button.setActionCommand(buttonCommand);
        button.addActionListener(frame);

        GridBagConstraints c = new GridBagConstraints(); //ograniczenia guzikow

        c.gridx = gridx;
        c.gridy = gridy;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;

        frame.add(button, c);
    }


    public void createMenuBar() {
        //jmenubar
        JMenuBar bar = new JMenuBar();
        JMenu file = new JMenu("File");//guzik na pasku menu
        JMenuItem newMenuButton = new JMenuItem("New");
        JMenuItem save = new JMenu("Save"); //dodanie guzika ktory moze byc dalej rozwijany
        JMenuItem close = new JMenuItem("Exit");
        close.addActionListener(frame);

        bar.add(file); // dodanie przycisku file do paska menu "bar"
        file.add(newMenuButton); //dodanie rozwijanych opcji do guzika "file" z menu
        file.add(save);
        file.addSeparator(); // Separator linii
        file.add(close);
        frame.add(bar); // dodanie paska do listy

        //jmenubar
        JMenu extra = new JMenu("Extra");
        JMenuItem hello = new JMenuItem("Hello");
        JMenuItem hello2 = new JMenuItem("Hello2");

        file.add(extra);
        extra.add(hello);
        extra.add(hello2);

    }
}
