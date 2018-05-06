package gietlap.csgo.windows;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

//TODO: Mach eine Passwortabfrage!
public class PasswordDialog extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PasswordDialog frame = new PasswordDialog();
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
	public PasswordDialog() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 429, 150);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnProceed = new JButton("OK");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				char[] passwordArr = passwordField.getPassword();
				StringBuilder sb = new StringBuilder();
				for (char ichar : passwordArr) {
					sb.append(ichar);
				}
				System.out.println(sb.toString());
				for (int i = 0; i < passwordArr.length; i++) {
					passwordArr[i] = ' ';
				}

			}
		});
		btnProceed.setBackground(SystemColor.menu);
		btnProceed.setBounds(341, 87, 72, 23);
		contentPane.add(btnProceed);

		JTextPane txtpnMessage = new JTextPane();
		txtpnMessage.setForeground(Color.WHITE);
		txtpnMessage.setBackground(Color.DARK_GRAY);
		txtpnMessage
				.setText("Die Daten sind verschl\u00FCsselt.\r\nBitte gebe das Passwort zum entschl\u00FCsseln ein!");
		txtpnMessage.setBounds(77, 11, 325, 34);
		contentPane.add(txtpnMessage);

		JTextPane textPasswortDesc = new JTextPane();
		textPasswortDesc.setForeground(Color.WHITE);
		textPasswortDesc.setBackground(Color.DARK_GRAY);
		textPasswortDesc.setText("Passwort:");
		textPasswortDesc.setBounds(25, 56, 72, 20);
		contentPane.add(textPasswortDesc);

		JButton btnCancel = new JButton("Abbrechen");
		btnCancel.setBackground(SystemColor.menu);
		btnCancel.setBounds(225, 87, 106, 23);
		contentPane.add(btnCancel);

		passwordField = new JPasswordField();
		passwordField.setBackground(SystemColor.menu);
		passwordField.setBounds(122, 56, 291, 20);
		contentPane.add(passwordField);
	}
}
