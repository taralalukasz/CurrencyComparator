import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import static xml.XmlFacade.*;


/**
 * Created by Łukasz on 20.03.2017.
 */
public class Main extends JFrame implements ActionListener{
    public static String URL = "http://www.ecb.int/stats/eurofxref/eurofxref-daily.xml";

    public static void main(String[] args) throws IOException, JSONException {
        new Main().setVisible(true);
    }

    private Main() {
        super("Currency comparator");
        setSize(1024,768);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,3)); //setting proper layout

        //button1
        JButton button = new JButton("Button"); //create a button
        button.setActionCommand("click");
        button.addActionListener(this);

        //button2
        JButton button2 = new JButton("SecondButton"); //create a button
        button2.setActionCommand("doubleclick");
        button2.addActionListener(this);

        //jmenubar
        JMenuBar bar = new JMenuBar();
        JMenu file = new JMenu("File");//guzik na pasku menu
        JMenuItem newMenuButton = new JMenuItem("New");
        JMenuItem save = new JMenu("Save"); //dodanie guzika ktory moze byc dalej rozwijany
        JMenuItem close = new JMenuItem("Exit");
        close.addActionListener(this);

        bar.add(file); // dodanie przycisku file do paska menu "bar"
        file.add(newMenuButton); //dodanie rozwijanych opcji do guzika "file" z menu
        file.add(save);
        file.addSeparator(); // Separator linii
        file.add(close);
        add(bar); // dodanie paska do listy


        //jmenubar
        JMenu extra = new JMenu("Extra");
        JMenuItem hello = new JMenuItem("Hello");
        JMenuItem hello2 = new JMenuItem("Hello2");

        file.add(extra);
        extra.add(hello);
        extra.add(hello2);




        add(button); //dodawanie guzikow ktore zrobilismy wczesniej do widoku
        add(button2);
    }

    public void actionPerformed(ActionEvent e) {
        String name = e.getActionCommand();

        if(name.equalsIgnoreCase("click")) {
            System.out.println("button 1 pressed");
        } else if (name.equalsIgnoreCase("doubleclick")) {
            System.out.println("button 2 is pressed");
        } else if (name.equalsIgnoreCase("exit")) {
            System.out.println("closed");
            System.exit(0);            //TO SLUZY TO DO WYCHODZENIA Z PROGRAMU
        }
        System.out.println("ble");
    }
}



//        String reposnseString = "";
//        try {
//            //test zaczytania xml
//            readXml(URL);
//            //test jsona
//            JSONObject json1 = readJsonFromUrl("http://api.fixer.io/latest?base=EUR");
//            JSONObject json2 = readJsonFromUrl("http://api.fixer.io/2009-05-12?base=PLN");
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