package main;

import java.io.File;
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

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by Lukasz on 20.03.2017.
 */
public class Main extends JFrame implements ActionListener {

	private static final long serialVersionUID = 8028280795858780790L;

	public static Logger LOGGER = Logger.getLogger("Currency Comparator");
	JFormattedTextField dateField1;
	JFormattedTextField dateField2;

	JComboBox<String> baseCurrency;

	ComponentBuilder builder;
	CheckBoxManager checkBoxManager;
	XMLManager xmlManager;

	List<JCheckBox> checkboxList = new ArrayList<JCheckBox>();

	JButton compare;
	JButton saveToHtml;

	public static void main(String[] args)/* throws IOException, JSONException */{

		//new Main().setVisible(true);
		MainWindow mainWindow = new MainWindow();
		mainWindow.setVisible(true);

	}

	private Main() {
		//super("Currency comparator");
		setSize(600, 600);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout()); // setting proper layout

		this.builder = new ComponentBuilder(this);
		this.checkBoxManager = new CheckBoxManager(this);
		this.xmlManager = new XMLManager();

		createComponents(this.builder);
		compare.setEnabled(false);
		saveToHtml.setEnabled(false);
	}

	public void actionPerformed(ActionEvent e) {
		String name = e.getActionCommand();

		if (name.equalsIgnoreCase("send")) {
			try {
				if (!dateField1.getText().matches("\\d{4}-\\d{2}-\\d{2}")
						|| !dateField2.getText().matches("\\d{4}-\\d{2}-\\d{2}")) {
					throw new Exception("Date 1 or date 2 is not filled!");
				}

				String chosenBaseCurrency = String.valueOf(baseCurrency.getSelectedItem());

				xmlManager.createXMLFiles(dateField1.getText(), dateField2.getText(), chosenBaseCurrency);

				if (!xmlManager.validateXmlWithXsd()) {
					showMessageDialog(null,
							"Xml is not valid \n" + "Sometimes the provided courses are not precise enough \n "
									+ "Please choose another dates.",
							"InfoBox: " + "error", JOptionPane.INFORMATION_MESSAGE);
					this.revalidate();
					this.repaint();
					return;
				}

				compare.setEnabled(true);
				updateLayout();

			} catch (IOException e1) {
				LOGGER.error("Problem with inpout file or sth related to this", e1);
			} catch (JSONException e2) {
				LOGGER.error("Json exception, check what happened there", e2);
			} catch (Exception e3) {
				LOGGER.info("Date exception : ", e3);
			}

			// this.dateField1.setText("");
			// this.dateField2.setText("");

		} else if (name.equals("compare")) {
			xmlManager.generateXsltTransformFile(checkBoxManager.getAllCheckedBoxes());
			saveToHtml.setEnabled(true);
			File htmlFile = new File("src/main/resources/response.xml");
			try {
				Desktop.getDesktop().open(htmlFile);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (name.equals("save")) {
			xmlManager.transformXmlToHtml("src/main/resources/response.xml", "src/main/resources/transform.xsl");
			showMessageDialog(null, "Results saved to HTML", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
		} else if (name.equalsIgnoreCase("exit")) {
			System.out.println("closed");
			System.exit(0); // TO SLUZY TO DO WYCHODZENIA Z PROGRAMU
		}
	}

	private void updateLayout() {

		String[] currencies = { "CHF", "HRK", "MXN", "LVL", "LTL", "ZAR", "INR", "CNY", "THB", "AUD", "KRW", "JPY",
				"GBP", "IDR", "HUF", "PHP", "TRY", "RUB", "HKD", "EUR", "DKK", "CAD", "MYR", "USD", "BGN", "EEK", "NOK",
				"RON", "SGD", "CZK", "SEK", "NZD", "BRL" };
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		int k = 0;

		for (int columnn = 0; columnn < 7; columnn++) {
			if (k == currencies.length - 1)
				break;
			for (int row = 4; row < 9; row++) {
				if (k == currencies.length - 1)
					break;

				this.checkboxList.add(builder.addCheckBox(currencies[k], columnn, row));
				k++;
			}
		}

		this.revalidate();
		this.repaint();
	}

	private void createComponents(ComponentBuilder builder) {
		builder.addLabel("Welcome to currency comparator", GridBagConstraints.LINE_START);
		builder.addLabel("First Date", 0, 1);
		builder.addLabel("Second Date", 0, 2);
		builder.addLabel("Choose base currency", 0, 3);
		this.dateField1 = builder.addMaskedField(1, 1);
		this.dateField2 = builder.addMaskedField(1, 2);
		this.baseCurrency = builder.addComboBox(1, 3);

		builder.addConfirmButton("Confirm Dates", "send", 0, 9);
		compare = builder.addCompareButton("compare", 0, 10);
		builder.addConfirmButton("Exit", "exit", 0, 12);
		saveToHtml = builder.addCompareButton("Save results", "save", 0, 11);
	}
}
