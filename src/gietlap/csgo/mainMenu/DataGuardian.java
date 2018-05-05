package gietlap.csgo.mainMenu;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import org.apache.commons.io.FileUtils;

import gietlap.csgo.content.ContentProvider;

public class DataGuardian extends JFrame {
	private static String contentPath = "data/content";
	private static String downloadPath = "data/downloaded";
	private static String urlContent = "https://github.com/TekkertheChaot/csgo-trainer/raw/master/content.zip";
	private static String urlVer = "https://raw.githubusercontent.com/TekkertheChaot/csgo-trainer/master/ver.dat";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JTextPane textLocalVersion = new JTextPane();
	private int localVer = 0;
	private int remoteVer = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataGuardian frame = new DataGuardian();
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
	public DataGuardian() {
		JTextPane txtpnGreeting = new JTextPane();
		JTextPane textDownloadDesc = new JTextPane();
		JTextPane txtpnDeletewarning = new JTextPane();
		JTextPane txtpnOnlineVersion = new JTextPane();
		JTextPane txtpnNotStartable = new JTextPane();
		JButton btnDeleteContent = new JButton("Lokale Daten l\u00F6schen");
		JButton btnProceed = new JButton("Starte das Programm!");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu.main(null);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		JButton btnDownload = new JButton("Aktualisiere Daten");

		txtpnOnlineVersion.setText("Online Datenversion: (Verbinde...)");
		txtpnNotStartable.setText("Du kannst das Programm nicht starten, da keine lokalen Daten vorhanden sind!");
		txtpnDeletewarning.setText("L\u00F6sche alle lokale Daten, falls Probleme auftreten.");
		textLocalVersion.setText("Aktuelle Datenversion: ");

		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ContentProvider.download(new URL(urlContent), downloadPath + "/content");
					System.out.println(ContentProvider.unzip(downloadPath + "/content", contentPath, null));
					ContentProvider.download(new URL(urlVer), contentPath + "/ver.dat");
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				localVer = getLocalVersion();
				remoteVer = getOnlineVersion();
				txtpnOnlineVersion.setText("Online Datenversion: " + remoteVer);
				textLocalVersion.setText(("Aktuelle Datenversion: " + localVer));
				textDownloadDesc.setText("Deine Daten sind auf dem aktuellsten Stand.");
				textDownloadDesc.setForeground(Color.WHITE);
				textDownloadDesc.setBackground(Color.DARK_GRAY);
				textDownloadDesc.setBounds(210, 57, 396, 23);
				if (localVer < remoteVer) {
					btnDownload.setEnabled(true);
					textDownloadDesc.setForeground(Color.GREEN);
					textDownloadDesc.setText("Eine neue Version der Trainingsdaten ist zum herunterladen verf�gbar!");
				} else if (localVer >= remoteVer) {
					btnDownload.setEnabled(false);
				}
				if (new File(contentPath).listFiles() == null) {
					btnProceed.setEnabled(false);
					btnProceed.setVisible(false);
					txtpnNotStartable.setVisible(true);
				} else if (new File(contentPath).listFiles() != null) {
					System.out.println("Content available");
					btnProceed.setEnabled(true);
					btnProceed.setVisible(true);
					txtpnNotStartable.setVisible(false);
				}
				repaint();
				revalidate();

			}
		});

		btnDeleteContent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File dir = new File(contentPath);
				try {
					FileUtils.deleteDirectory(dir);
				} catch (IOException e) {
					e.printStackTrace();
				}

				localVer = getLocalVersion();
				remoteVer = getOnlineVersion();
				txtpnOnlineVersion.setText("Online Datenversion: " + remoteVer);
				textLocalVersion.setText(("Aktuelle Datenversion: " + localVer));
				textDownloadDesc.setText("Deine Daten sind auf dem aktuellsten Stand.");
				textDownloadDesc.setForeground(Color.WHITE);
				textDownloadDesc.setBackground(Color.DARK_GRAY);
				textDownloadDesc.setBounds(210, 57, 396, 23);
				if (localVer < remoteVer) {
					btnDownload.setEnabled(true);
					textDownloadDesc.setForeground(Color.GREEN);
					textDownloadDesc.setText("Eine neue Version der Trainingsdaten ist zum herunterladen verf�gbar!");
				} else if (localVer >= remoteVer) {
					btnDownload.setEnabled(false);
				}
				if (new File(contentPath).listFiles() == null) {
					btnProceed.setEnabled(false);
					btnProceed.setVisible(false);
					txtpnNotStartable.setVisible(true);
				} else if (new File(contentPath).listFiles() != null) {
					System.out.println("Content available");
					btnProceed.setEnabled(true);
					btnProceed.setVisible(true);
					txtpnNotStartable.setVisible(false);
				}
				repaint();
				revalidate();
			}
		});

		localVer = getLocalVersion();
		remoteVer = getOnlineVersion();
		txtpnOnlineVersion.setText("Online Datenversion: " + remoteVer);
		textLocalVersion.setText(("Aktuelle Datenversion: " + localVer));
		textDownloadDesc.setText("Deine Daten sind auf dem aktuellsten Stand.");
		textDownloadDesc.setForeground(Color.WHITE);
		textDownloadDesc.setBackground(Color.DARK_GRAY);
		textDownloadDesc.setBounds(210, 57, 396, 23);
		if (localVer < remoteVer) {
			btnDownload.setEnabled(true);
			textDownloadDesc.setForeground(Color.GREEN);
			textDownloadDesc.setText("Eine neue Version der Trainingsdaten ist zum herunterladen verf�gbar!");
		} else if (localVer >= remoteVer) {
			btnDownload.setEnabled(false);
		}
		if (new File(contentPath).listFiles() == null) {
			btnProceed.setEnabled(false);
			btnProceed.setVisible(false);
			txtpnNotStartable.setVisible(true);
		} else if (new File(contentPath).listFiles() != null) {
			System.out.println("Content available");
			btnProceed.setEnabled(true);
			btnProceed.setVisible(true);
			txtpnNotStartable.setVisible(false);
		}
		repaint();
		revalidate();

		setTitle("Datenw\u00E4chter");
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(DataGuardian.class.getResource("/gietlap/csgo/mainMenu/ic.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 673, 244);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtpnGreeting.setForeground(Color.WHITE);
		txtpnGreeting.setBackground(Color.DARK_GRAY);
		txtpnGreeting.setText("Hey! Bevor es losgeht, kannst du hier \u00FCberpr\u00FCfen, ob die Daten aktuell sind.");
		txtpnGreeting.setBounds(126, 11, 480, 35);
		contentPane.add(txtpnGreeting);

		btnDownload.setBackground(Color.RED);
		btnDownload.setBounds(37, 57, 163, 23);
		contentPane.add(btnDownload);

		contentPane.add(textDownloadDesc);

		btnProceed.setBackground(Color.RED);
		btnProceed.setBounds(212, 139, 173, 23);
		contentPane.add(btnProceed);

		btnDeleteContent.setBackground(Color.RED);
		btnDeleteContent.setBounds(37, 91, 163, 23);
		contentPane.add(btnDeleteContent);

		txtpnDeletewarning.setForeground(Color.WHITE);
		txtpnDeletewarning.setBackground(Color.DARK_GRAY);

		txtpnDeletewarning.setBounds(210, 91, 396, 23);
		contentPane.add(txtpnDeletewarning);
		textLocalVersion.setForeground(Color.LIGHT_GRAY);
		textLocalVersion.setBackground(Color.DARK_GRAY);

		textLocalVersion.setBounds(0, 195, 181, 20);
		contentPane.add(textLocalVersion);

		txtpnOnlineVersion.setForeground(Color.LIGHT_GRAY);
		txtpnOnlineVersion.setBackground(Color.DARK_GRAY);
		txtpnOnlineVersion.setBounds(191, 195, 210, 20);
		contentPane.add(txtpnOnlineVersion);

		txtpnNotStartable.setForeground(Color.RED);
		txtpnNotStartable.setBackground(Color.DARK_GRAY);
		txtpnNotStartable.setBounds(100, 142, 527, 20);
		contentPane.add(txtpnNotStartable);

	}

	private int getOnlineVersion() {
		try {
			ContentProvider.download(new URL(urlVer), downloadPath + "/rVer.dat");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(downloadPath + "/rVer.dat"));
			sb.append(br.readLine());
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Integer.parseInt(sb.toString());
	}

	private int getLocalVersion() {
		return ContentProvider.getContentVersion();
	}
}
