package gietlap.csgo.windows;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

//TODO: Mach eine Passwortabfrage!
public class PasswordDialog extends JFrame {
	private static String password = "";
	private static boolean isRetry = false;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(boolean retry) {
		password = null;
		isRetry = retry;
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
		setTitle("Wache");
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(PasswordDialog.class.getResource("/gietlap/csgo/windows/ic.png")));
		setType(Type.POPUP);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
				password = sb.toString();
				if (sb.length() != 0) {
					sb.replace(0, sb.length() - 1, "-REDACTED-");
				}
				sb = null;
				for (int i = 0; i < passwordArr.length; i++) {
					passwordArr[i] = ' ';
				}
				DataGuardian.setZipPasswordAndTry(password);
				password = null;
				dispose();
			}
		});
		btnProceed.setBackground(SystemColor.menu);
		btnProceed.setBounds(341, 87, 72, 23);
		btnProceed.setEnabled(false);
		contentPane.add(btnProceed);

		JTextPane txtpnMessage = new JTextPane();
		txtpnMessage.setEditable(false);
		txtpnMessage.setForeground(Color.WHITE);
		txtpnMessage.setBackground(Color.DARK_GRAY);
		if (isRetry) {
			txtpnMessage.setText(
					"Das eingegebene Passwort ist falsch.\r\nBitte gebe ein anderes Passwort zum entschl\u00FCsseln ein!");
		} else
			txtpnMessage.setText(
					"Die Daten sind verschl\u00FCsselt.\r\nBitte gebe das Passwort zum entschl\u00FCsseln ein!");

		txtpnMessage.setBounds(77, 11, 325, 34);
		contentPane.add(txtpnMessage);

		JTextPane textPasswortDesc = new JTextPane();
		textPasswortDesc.setEditable(false);
		textPasswortDesc.setForeground(Color.WHITE);
		textPasswortDesc.setBackground(Color.DARK_GRAY);
		textPasswortDesc.setText("Passwort:");
		textPasswortDesc.setBounds(25, 56, 72, 20);
		contentPane.add(textPasswortDesc);

		JButton btnCancel = new JButton("Abbrechen");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				password = null;
				dispose();
			}
		});
		btnCancel.setBackground(SystemColor.menu);
		btnCancel.setBounds(225, 87, 106, 23);
		contentPane.add(btnCancel);

		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				char[] passwordArr = passwordField.getPassword();
				StringBuilder sb = new StringBuilder();
				for (char ichar : passwordArr) {
					sb.append(ichar);
				}
				password = sb.toString();
				if (password == null || password.equals("")) {
					sb = null;
					return;
				}
				if (sb.length() != 0) {
					sb.replace(0, sb.length() - 1, "-REDACTED-");
				}
				sb = null;
				for (int i = 0; i < passwordArr.length; i++) {
					passwordArr[i] = ' ';
				}
				DataGuardian.setZipPasswordAndTry(password);
				password = null;
				dispose();

			}
		});
		passwordField.getDocument().addDocumentListener(new DocumentListener() {
			public void removeUpdate(DocumentEvent e) {
				char[] passwordArr = passwordField.getPassword();
				if (passwordArr.length <= 0) {
					btnProceed.setEnabled(false);
					repaint();
					revalidate();
				}
			}

			public void insertUpdate(DocumentEvent e) {
				char[] passwordArr = passwordField.getPassword();
				if (passwordArr.length > 0) {
					btnProceed.setEnabled(true);
					repaint();
					revalidate();
				}
			}

			public void changedUpdate(DocumentEvent e) {
			}
		});
		passwordField.setBackground(SystemColor.menu);
		passwordField.setBounds(122, 56, 291, 20);
		contentPane.add(passwordField);
	}
}
