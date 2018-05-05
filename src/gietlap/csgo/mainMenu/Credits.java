package gietlap.csgo.mainMenu;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import gietlap.csgo.content.ContentProvider;

public class Credits extends JFrame {
	private int versionBuild = 0;

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
					Credits frame = new Credits();
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
	public Credits() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Credits.class.getResource("/gietlap/csgo/mainMenu/ic.png")));
		setResizable(false);
		setType(Type.POPUP);
		versionBuild = ContentProvider.getClientVersion();
		setTitle("Information");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 464, 149);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextPane txtpnEinKleinesProgramm = new JTextPane();
		txtpnEinKleinesProgramm.setForeground(Color.WHITE);
		txtpnEinKleinesProgramm.setBackground(Color.DARK_GRAY);
		txtpnEinKleinesProgramm.setEditable(false);
		txtpnEinKleinesProgramm.setText(
				"Ein kleines Programm um die Infos aus M0xxis Training (hoffentlich) anschaulicher, verst\u00E4ndlicher und bequem abrufbar zu machen. :)\r\n\r\n(Zusammengeschustert by Palte) ");
		txtpnEinKleinesProgramm.setBounds(10, 11, 428, 88);
		contentPane.add(txtpnEinKleinesProgramm);

		JTextPane txtpnVersion = new JTextPane();
		txtpnVersion.setText("Buildnummer: " + versionBuild);
		txtpnVersion.setForeground(Color.LIGHT_GRAY);
		txtpnVersion.setBackground(Color.DARK_GRAY);
		txtpnVersion.setBounds(0, 100, 172, 20);
		contentPane.add(txtpnVersion);
	}
}
