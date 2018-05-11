package gietlap.csgo.windows;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class ErrorMessage extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton btnProceed = new JButton("OK");
	JTextPane textSummary = new JTextPane();
	JTextPane textDetails = new JTextPane();
	static Exception exc;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(Exception e) {
		exc=e;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErrorMessage frame = new ErrorMessage();
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
	public ErrorMessage() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ErrorMessage.class.getResource("/gietlap/csgo/windows/ic.png")));
		setTitle("Ein Fehler ist aufgetaucht!");
		setType(Type.POPUP);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 719, 327);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnProceed.setBackground(Color.RED);
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		btnProceed.setBounds(312, 264, 89, 23);
		contentPane.add(btnProceed);
		textSummary.setEditable(false);

		textSummary.setForeground(Color.WHITE);
		textSummary.setBackground(Color.DARK_GRAY);
		textSummary.setText("Whoops, Da ist etwas schief gelaufen... (" + exc.getClass().getName()+ ")");
		textSummary.setBounds(10, 11, 522, 23);
		contentPane.add(textSummary);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(10, 45, 693, 208);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(textDetails);
		textDetails.setEditable(false);
		
				textDetails.setBackground(Color.GRAY);
				textDetails.setForeground(Color.WHITE);
				textDetails.setText(getStackTraceAsString(exc));
	}

	private static String getStackTraceAsString(Exception e) {
		e.printStackTrace();
		StringBuilder sb = new StringBuilder();
		StackTraceElement[] steArr = e.getStackTrace();
		sb.append(e.toString()+"\n");
		for (StackTraceElement ste : steArr) {
			sb.append("\t"+ste.toString() + "\n");
		}
		return sb.toString();
	}
}
