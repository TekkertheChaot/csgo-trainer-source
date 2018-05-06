package gietlap.csgo.windows;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import gietlap.csgo.provider.PropertyManager;

public class Startup extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Startup frame = new Startup();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Startup() {
		setResizable(false);
		setAlwaysOnTop(true);
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 498, 257);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);

		JTextPane txtpnGreeting = new JTextPane();
		txtpnGreeting.setForeground(Color.WHITE);
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtpnGreeting, 5, SpringLayout.NORTH, contentPane);
		txtpnGreeting.setEditable(false);
		txtpnGreeting.setBackground(Color.DARK_GRAY);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtpnGreeting, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtpnGreeting, -5, SpringLayout.EAST, contentPane);
		txtpnGreeting.setText(
				"Moin! \r\nDas Programm hier soll alles, was wir in M0xxi's Training gesehen, gezeigt und erkl\u00E4rt bekommen haben nochmal festhalten und (hoffentlich) \u00FCberschaubar darstellen.\r\n\r\nDas war's! Viel Spa\u00DF mit dem Progr\u00E4mmchen! :)\r\n\r\n(PS: Du willst direkt was auf dem Server versuchen? Gehe im Men\u00FCbalken auf \"Programm -> Verbinde zum Server\" und schon verbindest du dich zum Server! ;))");
		contentPane.add(txtpnGreeting);

		JCheckBox chckbxDontShowAgain = new JCheckBox(
				"Ja, ist ja ok. Du kannst das Fenster n\u00E4chstes mal weglassen...");
		chckbxDontShowAgain.setForeground(Color.WHITE);
		chckbxDontShowAgain.setBackground(Color.DARK_GRAY);
		sl_contentPane.putConstraint(SpringLayout.WEST, chckbxDontShowAgain, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, txtpnGreeting, -6, SpringLayout.NORTH, chckbxDontShowAgain);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, chckbxDontShowAgain, -10, SpringLayout.SOUTH, contentPane);
		try {
			Properties prop = PropertyManager.initProperties();
			String bool = prop.getProperty("dontshow");
			switch (bool) {
			case "true":
				chckbxDontShowAgain.setSelected(true);
				break;
			case "false":
				chckbxDontShowAgain.setSelected(false);
				break;
			default:
				chckbxDontShowAgain.setSelected(false);
				break;
			}
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		chckbxDontShowAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(chckbxDontShowAgain);

		JButton btnOK = new JButton("OK");
		btnOK.setBackground(Color.RED);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnOK, 0, SpringLayout.NORTH, chckbxDontShowAgain);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnOK, -5, SpringLayout.EAST, contentPane);
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Properties prop = null;
				try {
					prop = PropertyManager.initProperties();
					prop.setProperty("dontshow", "" + chckbxDontShowAgain.isSelected());
					PropertyManager.writeProperties(prop);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				dispose();
			}
		});
		contentPane.add(btnOK);
	}
}
