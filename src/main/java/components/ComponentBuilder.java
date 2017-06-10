package components;

import main.Main;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

import static xml.XMLManager.LOGGER;

/**
 * Created by Lukasz on 30.03.2017.
 */
public class ComponentBuilder {

	public ComponentBuilder(Main frame) {
		this.frame = frame;
	}

	private Main frame;

	public JCheckBox addCheckBox(String boxValue, int gridx, int gridy) {
		GridBagConstraints c = new GridBagConstraints(); // ograniczenia guzikow

		c.gridx = gridx;
		c.gridy = gridy;

		JCheckBox checkBox = new JCheckBox(boxValue);
		frame.add(checkBox, c);

		return checkBox;
	}

	public JComboBox<String> addComboBox(int gridx, int gridy) {
		GridBagConstraints c = new GridBagConstraints(); // ograniczenia guzikow

		c.gridx = gridx;
		c.gridy = gridy;

		String[] currencies = { "CHF", "HRK", "MXN", "LVL", "LTL", "ZAR", "INR", "CNY", "THB", "AUD", "KRW", "JPY",
				"GBP", "IDR", "HUF", "PHP", "TRY", "RUB", "HKD", "EUR", "DKK", "CAD", "MYR", "USD", "BGN", "EEK", "NOK",
				"RON", "SGD", "CZK", "SEK", "NZD", "BRL" };
		JComboBox<String> comboBox = new JComboBox<String>(currencies);
		comboBox.setSelectedIndex(4);
		// comboBox.addActionListener(this);
		frame.add(comboBox, c);

		return comboBox;
	}

	public void addLabel(String labelText, int placement, double weighty) {
		GridBagConstraints c = new GridBagConstraints(); // ograniczenia guzikow

		c.weighty = weighty;
		c.anchor = placement;

		JLabel label = new JLabel(labelText);
		frame.add(label, c);
	}

	public void addLabel(String labelText, int placement) {
		GridBagConstraints c = new GridBagConstraints(); // ograniczenia guzikow

		c.anchor = placement;
		c.gridwidth = 3;

		JLabel label = new JLabel(labelText);
		frame.add(label, c);
	}

	public void addLabel(String labelText, int gridx, int gridy) {
		GridBagConstraints c = new GridBagConstraints(); // ograniczenia guzikow

		c.gridx = gridx;
		c.gridy = gridy;

		JLabel label = new JLabel(labelText);
		frame.add(label, c);
	}

	public void addLabel(String labelText, int gridx, int gridy, double weighty) {
		GridBagConstraints c = new GridBagConstraints(); // ograniczenia guzikow

		c.gridx = gridx;
		c.gridy = gridy;
		c.weighty = weighty;

		JLabel label = new JLabel(labelText);
		frame.add(label, c);
	}

	public JFormattedTextField addMaskedField(int gridx, int gridy) {
		GridBagConstraints c = new GridBagConstraints(); // ograniczenia guzikow

		c.gridx = gridx;
		c.gridy = gridy;
		// field with data
		JPanel datePanel = new JPanel(new BorderLayout()); // stworzenie panelu
		MaskFormatter format;
		JFormattedTextField dateTextField = null;
		try {
			format = new MaskFormatter("####-##-##");
			dateTextField = new JFormattedTextField(format); // textfield z
																// formatem daty
			datePanel.add(dateTextField); // dodanie textfieldu z formatem do
											// date panelu
			datePanel.setPreferredSize(new Dimension(70, 20));

			frame.add(datePanel, c); // dodanie panelu do guzila
		} catch (ParseException e) {
			LOGGER.error("Cannot parse the date, wrong format", e);
		}

		return dateTextField;
	}

	public void addMaskedField(int gridx, int gridy, int gridwidth) {
		GridBagConstraints c = new GridBagConstraints(); // ograniczenia guzikow

		c.gridx = gridx;
		c.gridy = gridy;
		c.gridwidth = gridwidth;
		// field with data
		JPanel datePanel = new JPanel(new BorderLayout()); // stworzenie panelu
		MaskFormatter format;

		try {
			format = new MaskFormatter("####-##-##");
			JFormattedTextField dateTextField = new JFormattedTextField(format); // textfield
																					// z
																					// formatem
																					// daty
			datePanel.add(dateTextField); // dodanie textfieldu z formatem do
											// date panelu

			frame.add(datePanel, c); // dodanie panelu do guzila
		} catch (ParseException e) {
			LOGGER.error("Cannot parse the date, wrong format", e);
		}
	}

	public void addCustomButton(String buttonName, String buttonCommand, int gridx, int gridy, int placement) {
		JButton button = new JButton(buttonName); // create a button
		button.setActionCommand(buttonCommand);
		button.addActionListener(frame);

		GridBagConstraints c = new GridBagConstraints(); // ograniczenia guzikow

		c.anchor = placement;

		frame.add(button, c);
	}

	public void addConfirmButton(String buttonName, String buttonCommand, int gridx, int gridy) {
		JButton button = new JButton(buttonName); // create a button
		button.setActionCommand(buttonCommand);
		button.addActionListener(frame);

		GridBagConstraints c = new GridBagConstraints(); // ograniczenia guzikow

		c.gridx = gridx;
		c.gridy = gridy;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;

		frame.add(button, c);
	}

	public JButton addCompareButton(String buttonCommand, int gridx, int gridy) {
		JButton button = new JButton("Compare"); // create a button
		button.setActionCommand(buttonCommand);
		button.addActionListener(frame);

		GridBagConstraints c = new GridBagConstraints(); // ograniczenia guzikow

		c.gridx = gridx;
		c.gridy = gridy;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;

		frame.add(button, c);

		return button;
	}

	public JButton addCompareButton(String buttonName, String buttonCommand, int gridx, int gridy) {
		JButton button = new JButton(buttonName); // create a button
		button.setActionCommand(buttonCommand);
		button.addActionListener(frame);

		GridBagConstraints c = new GridBagConstraints(); // ograniczenia guzikow

		c.gridx = gridx;
		c.gridy = gridy;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;

		frame.add(button, c);

		return button;
	}

	public void createMenuBar() {
		// jmenubar
		JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("File");// guzik na pasku menu
		JMenuItem newMenuButton = new JMenuItem("New");
		JMenuItem save = new JMenu("Save"); // dodanie guzika ktory moze byc
											// dalej rozwijany
		JMenuItem close = new JMenuItem("Exit");
		close.addActionListener(frame);

		bar.add(file); // dodanie przycisku file do paska menu "bar"
		file.add(newMenuButton); // dodanie rozwijanych opcji do guzika "file" z
									// menu
		file.add(save);
		file.addSeparator(); // Separator linii
		file.add(close);
		frame.add(bar); // dodanie paska do listy

		// jmenubar
		JMenu extra = new JMenu("Extra");
		JMenuItem hello = new JMenuItem("Hello");
		JMenuItem hello2 = new JMenuItem("Hello2");

		file.add(extra);
		extra.add(hello);
		extra.add(hello2);

	}
}
