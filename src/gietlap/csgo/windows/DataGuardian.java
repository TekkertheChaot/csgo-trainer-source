package gietlap.csgo.windows;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import gietlap.csgo.provider.ContentProvider;
import gietlap.csgo.provider.FileProvider;

public class DataGuardian extends JFrame {
	public static String contentPath = "data/content";
	private static String downloadPath = "data/downloaded";
	private static String urlContent = "https://github.com/TekkertheChaot/csgo-trainer/raw/master/content.zip";
	private static String urlClient = "https://raw.githubusercontent.com/TekkertheChaot/csgo-trainer/master/cver.dat";
	private static String urlVer = "https://raw.githubusercontent.com/TekkertheChaot/csgo-trainer/master/ver.dat";
	/**
	 * Version of class
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JTextPane textLocalVersion = new JTextPane();
	private int localVer = 0;
	private int remoteVer = 1;
	private int ClientVer = 1;
	private int onlineClientVer = 0;

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
					System.err.println(e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DataGuardian() {
		// initialize components
		JTextPane txtpnGreeting = new JTextPane();
		JTextPane textDownloadDesc = new JTextPane();
		JTextPane txtpnDeletewarning = new JTextPane();
		JTextPane txtpnOnlineVersion = new JTextPane();
		JTextPane txtpnNotStartable = new JTextPane();
		JTextPane txtpnUpdateClientDesc = new JTextPane();
		JTextPane txtpnClientVersion = new JTextPane();
		JButton btnUpdate = new JButton("");
		JButton btnDeleteContent = new JButton("Lokale Daten l\u00F6schen");
		JButton btnProceed = new JButton("Starte das Programm!");

		JButton btnUpdateClient = new JButton("Aktualisiere Client");
		btnUpdateClient.setForeground(Color.WHITE);
		btnUpdateClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Desktop desk = Desktop.getDesktop();
				URI uri = null;
				try {
					uri = new URI("https://github.com/TekkertheChaot/csgo-trainer-source/releases");
				} catch (URISyntaxException e1) {
					System.err.println(e1.getMessage());
				}
				try {
					desk.browse(uri);
				} catch (IOException e1) {
					System.err.println(e1.getMessage());
				}
			}
		});

		btnDeleteContent.setForeground(Color.WHITE);
		btnProceed.setForeground(Color.WHITE);
		// loads Main Window, thus starting the client
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu.main(null);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					System.err.println(e1.getMessage());
				}
				dispose();
			}
		});
		JButton btnDownload = new JButton("Aktualisiere Daten");
		btnDownload.setForeground(Color.WHITE);
		txtpnOnlineVersion.setText("Online Datenversion: (Verbinde...)");
		txtpnNotStartable.setText("Du kannst das Programm nicht starten, da keine lokalen Daten vorhanden sind!");
		txtpnDeletewarning.setText("L\u00F6sche alle lokale Daten, falls Probleme auftreten.");
		textLocalVersion.setText("Aktuelle Datenversion: ");

		/**
		 * A subclass to provide an update method within the windowmethod
		 * 
		 * @author gietlap
		 *
		 */
		final class UPDATER {
			public void update() {
				ClientVer = getLocalClientVersion();
				onlineClientVer = getOnlineClientVersion();
				localVer = getLocalVersion();
				remoteVer = getOnlineContentVersion();
				txtpnUpdateClientDesc.setText("Dein Client ist auf dem aktuellsten Stand.");
				txtpnOnlineVersion.setText("Online Datenversion: " + remoteVer);
				textLocalVersion.setText(("Aktuelle Datenversion: " + localVer));
				textDownloadDesc.setText("Deine Daten sind auf dem aktuellsten Stand.");
				textDownloadDesc.setForeground(Color.WHITE);
				textDownloadDesc.setBackground(Color.DARK_GRAY);
				textDownloadDesc.setBounds(180, 45, 396, 23);
				if (ClientVer >= onlineClientVer) {
					btnUpdateClient.setEnabled(false);
					txtpnUpdateClientDesc.setForeground(Color.WHITE);
				} else if (ClientVer < onlineClientVer) {
					btnUpdateClient.setEnabled(true);
					txtpnUpdateClientDesc.setForeground(Color.GREEN);
					txtpnUpdateClientDesc.setText("Eine neue Client-Version ist verf�gbar!");
				}
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
					System.out.println("[INFO] - " + "Content on file system available");
					btnProceed.setEnabled(true);
					btnProceed.setVisible(true);
					txtpnNotStartable.setVisible(false);
				}
				txtpnClientVersion.setText("Client Version: " + ClientVer + " (" + onlineClientVer + ")");
				repaint();
				revalidate();
			}
		}

		UPDATER ud = new UPDATER();

		// downloads new Content Version
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ContentProvider.download(new URL(urlContent), downloadPath + "/content");
					int unzipSuccess = ContentProvider.unzip(downloadPath + "/content", contentPath, null);
					switch (unzipSuccess) {
					case 0:
						ContentProvider.download(new URL(urlVer), contentPath + "/ver.dat");
						break;
					case 1:
						break;
					case 2:
						PasswordDialog.main(false);
						break;
					default:
						break;
					}
				} catch (MalformedURLException e) {
					System.err.println(e.getMessage());
				}
				ud.update();

				addWindowFocusListener(new WindowFocusListener() {
					public void windowGainedFocus(WindowEvent arg0) {
						ud.update();
					}
					public void windowLostFocus(WindowEvent e) {
					}
				});
			}
		});

		btnDeleteContent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ContentProvider.deleteContent(contentPath);
				ud.update();
			}
		});

		ud.update(); // Update everything for startup.

		setTitle("Datenw\u00E4chter");
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(DataGuardian.class.getResource("/gietlap/csgo/windows/ic.png")));
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
		txtpnGreeting.setBounds(126, 11, 480, 23);
		contentPane.add(txtpnGreeting);

		btnDownload.setBackground(Color.RED);
		btnDownload.setBounds(10, 45, 163, 23);
		contentPane.add(btnDownload);

		contentPane.add(textDownloadDesc);

		btnProceed.setBackground(Color.RED);
		btnProceed.setBounds(212, 139, 173, 23);
		contentPane.add(btnProceed);

		btnDeleteContent.setBackground(Color.RED);
		btnDeleteContent.setBounds(10, 68, 163, 23);
		contentPane.add(btnDeleteContent);

		txtpnDeletewarning.setForeground(Color.WHITE);
		txtpnDeletewarning.setBackground(Color.DARK_GRAY);

		txtpnDeletewarning.setBounds(180, 68, 396, 23);
		contentPane.add(txtpnDeletewarning);
		textLocalVersion.setForeground(Color.LIGHT_GRAY);
		textLocalVersion.setBackground(Color.DARK_GRAY);

		textLocalVersion.setBounds(0, 195, 181, 20);
		contentPane.add(textLocalVersion);

		txtpnOnlineVersion.setForeground(Color.LIGHT_GRAY);
		txtpnOnlineVersion.setBackground(Color.DARK_GRAY);
		txtpnOnlineVersion.setBounds(227, 195, 210, 20);
		contentPane.add(txtpnOnlineVersion);

		txtpnNotStartable.setForeground(Color.RED);
		txtpnNotStartable.setBackground(Color.DARK_GRAY);
		txtpnNotStartable.setBounds(100, 142, 527, 20);
		contentPane.add(txtpnNotStartable);

		btnUpdateClient.setBackground(Color.RED);
		btnUpdateClient.setBounds(10, 91, 163, 23);
		contentPane.add(btnUpdateClient);

		txtpnUpdateClientDesc.setBackground(Color.DARK_GRAY);
		txtpnUpdateClientDesc.setBounds(180, 91, 396, 20);
		contentPane.add(txtpnUpdateClientDesc);

		txtpnClientVersion.setForeground(Color.LIGHT_GRAY);
		txtpnClientVersion.setBackground(Color.DARK_GRAY);
		txtpnClientVersion.setBounds(506, 195, 132, 20);
		contentPane.add(txtpnClientVersion);

		
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setToolTipText("\u00DCberpr\u00FCfe auf neue Versionen");
		btnUpdate.setBorder(null);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ud.update();
			}
		});

		btnUpdate.setBackground(Color.DARK_GRAY);
		btnUpdate.setBounds(644, 192, 23, 23);
		ImageIcon updateIcon = new ImageIcon(DataGuardian.class.getResource("/gietlap/csgo/windows/update.png"));
		Image updateImage = updateIcon.getImage();
		updateIcon = new ImageIcon(
				updateImage.getScaledInstance(btnUpdate.getWidth(), btnUpdate.getHeight(), Image.SCALE_SMOOTH));
		btnUpdate.setIcon(updateIcon);
		contentPane.add(btnUpdate);

	}

	private int getOnlineClientVersion() {
		try {
			ContentProvider.download(new URL(urlClient), downloadPath + "/rcver.dat");
		} catch (MalformedURLException e) {
			System.err.println(e.getMessage());
		}
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(downloadPath + "/rcver.dat"));
			sb.append(br.readLine());
			br.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return Integer.parseInt(sb.toString());
	}

	private int getOnlineContentVersion() {
		try {
			ContentProvider.download(new URL(urlVer), downloadPath + "/rver.dat");
		} catch (MalformedURLException e) {
			System.err.println(e.getMessage());
			return 0;
		}

		String ver = FileProvider.getFileContentAsUTF8(downloadPath + "/rver.dat");
		if (ver == null || ver.equals("")) {
			ver = "0";
		}
		return Integer.parseInt(ver);
	}

	private int getLocalClientVersion() {
		return ContentProvider.getClientVersion();
	}

	private int getLocalVersion() {
		return ContentProvider.getContentVersion();
	}

	public static void setZipPasswordAndTry(String pw) {
		int state = ContentProvider.unzip(downloadPath + "/content", contentPath, pw);
		switch (state) {
		case 0:
			try {
				ContentProvider.download(new URL(urlVer), contentPath + "/ver.dat");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			break;
		case 1:
			break;
		case 2:
			PasswordDialog.main(false);
			break;
		case 3:
			PasswordDialog.main(true);
		default:
			break;
		}
	}
}
