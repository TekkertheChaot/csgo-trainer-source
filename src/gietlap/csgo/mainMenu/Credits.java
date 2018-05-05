package gietlap.csgo.mainMenu;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class Credits extends JFrame {

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
		setTitle("Version 0.17dev");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 464, 149);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextPane txtpnEinKleinesProgramm = new JTextPane();
		txtpnEinKleinesProgramm.setBackground(SystemColor.menu);
		txtpnEinKleinesProgramm.setEditable(false);
		txtpnEinKleinesProgramm.setText(
				"Ein kleines Programm um die Infos aus M0xxis Training (hoffentlich) anschaulicher, verst\u00E4ndlicher und bequem abrufbar zu machen. :)\r\n\r\n(Zusammengeschustert by Palte) ");
		txtpnEinKleinesProgramm.setBounds(10, 11, 428, 88);
		contentPane.add(txtpnEinKleinesProgramm);
	}
}
