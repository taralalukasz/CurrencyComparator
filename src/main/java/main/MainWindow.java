package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JFormattedTextField;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 7509764308609412560L;

	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Currency Comparator");
		setSize(482, 350);
		getContentPane().setLayout(null);

		JPanel panelConfig = new JPanel();
		panelConfig.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Set configuration",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelConfig.setBounds(6, 158, 464, 100);
		getContentPane().add(panelConfig);
		panelConfig.setLayout(null);

		JLabel lblFirstDate = new JLabel("First date");
		lblFirstDate.setBounds(10, 19, 100, 14);
		panelConfig.add(lblFirstDate);

		JLabel lblSecondDate = new JLabel("Second date");
		lblSecondDate.setBounds(10, 47, 100, 14);
		panelConfig.add(lblSecondDate);

		JFormattedTextField firstDate = new JFormattedTextField();

		JFormattedTextField secondDate = new JFormattedTextField();

		try {
			firstDate = new JFormattedTextField(new MaskFormatter("####-##-##"));
			secondDate = new JFormattedTextField(new MaskFormatter("####-##-##"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		firstDate.setBounds(120, 16, 74, 20);
		panelConfig.add(firstDate);
		secondDate.setBounds(120, 44, 74, 20);
		panelConfig.add(secondDate);

		JLabel lblChosenCurrency = new JLabel("Chosen currency:");
		lblChosenCurrency.setBounds(10, 75, 100, 14);
		panelConfig.add(lblChosenCurrency);

		final JLabel lblLblcurrency = new JLabel("N/A");
		lblLblcurrency.setBounds(120, 75, 74, 14);
		panelConfig.add(lblLblcurrency);

		JPanel panelActions = new JPanel();
		panelActions.setBorder(new TitledBorder(null, "Actions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelActions.setBounds(6, 263, 464, 50);
		getContentPane().add(panelActions);
		panelActions.setLayout(null);

		JButton btnCompare = new JButton("Compare");
		btnCompare.setBounds(149, 16, 101, 23);
		panelActions.add(btnCompare);

		JButton btnSaveResults = new JButton("Save results");
		btnSaveResults.setBounds(254, 16, 141, 23);
		panelActions.add(btnSaveResults);

		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(399, 16, 58, 23);
		panelActions.add(btnExit);

		JButton btnConfirmDates = new JButton("Confirm dates");
		btnConfirmDates.setBounds(4, 16, 141, 23);
		panelActions.add(btnConfirmDates);

		JPanel panelBullshit = new JPanel();
		panelBullshit
				.setBorder(new TitledBorder(null, "Currencies", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelBullshit.setBounds(6, 5, 464, 148);
		getContentPane().add(panelBullshit);
		panelBullshit.setLayout(null);

		JScrollPane scrollPaneCurrencies = new JScrollPane();
		scrollPaneCurrencies.setBounds(325, 21, 129, 116);
		panelBullshit.add(scrollPaneCurrencies);

		final JList<String> listCurrencies = new JList<String>();
		scrollPaneCurrencies.setViewportView(listCurrencies);
		listCurrencies.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;

			String[] currencies = { "CHF", "HRK", "MXN", "LVL", "LTL", "ZAR", "INR", "CNY", "THB", "AUD", "KRW", "JPY",
					"GBP", "IDR", "HUF", "PHP", "TRY", "RUB", "HKD", "EUR", "DKK", "CAD", "MYR", "USD", "BGN", "EEK",
					"NOK", "RON", "SGD", "CZK", "SEK", "NZD", "BRL" };

			public int getSize() {
				return currencies.length;
			}

			public String getElementAt(int index) {
				return currencies[index];
			}
		});
		listCurrencies.setBorder(new LineBorder(new Color(0, 0, 0)));
		listCurrencies.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				lblLblcurrency.setText(listCurrencies.getSelectedValue());
			}
		});

		JLabel lblAvailableCurrencies = new JLabel("Available currencies:");
		scrollPaneCurrencies.setColumnHeaderView(lblAvailableCurrencies);

		JScrollPane scrollPaneDesc = new JScrollPane();
		scrollPaneDesc.setBounds(10, 21, 305, 116);
		panelBullshit.add(scrollPaneDesc);

		JTextArea txtrDescription = new JTextArea();
		txtrDescription.setEditable(false);
		txtrDescription.setFont(new Font("Arial", Font.PLAIN, 11));
		txtrDescription.setText("description");
		txtrDescription.setWrapStyleWord(true);
		txtrDescription.setLineWrap(true);
		scrollPaneDesc.setViewportView(txtrDescription);

		JLabel lblProjectDescription = new JLabel("Project description");
		scrollPaneDesc.setColumnHeaderView(lblProjectDescription);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

	}
}
