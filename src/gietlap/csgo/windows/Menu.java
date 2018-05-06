package gietlap.csgo.windows;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

import gietlap.csgo.provider.ContentProvider;
import gietlap.csgo.provider.ImageProvider;
import gietlap.csgo.provider.PropertyManager;
import gietlap.csgo.treebuilder.Paths;
import gietlap.csgo.treebuilder.TreeFromFolderTree;

public class Menu extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTree tree = new JTree();
	JPanel treePanel = new JPanel();
	JPanel showPanel = new JPanel();
	JPanel mapPanel = new JPanel();
	JPanel nadePanel = new JPanel();
	JPanel hintPanel = new JPanel();
	JPanel scrollPanel = new JPanel();
	JButton btnScrollFow = new JButton(">>");
	JButton btnScrollBack = new JButton("<<");
	JTextPane txtpnBeschreibung = new JTextPane();
	JTextPane nadeDesc = new JTextPane();
	JTextPane stepDesc = new JTextPane();
	JLabel nadeLabel = new JLabel("Nade Name NULL");
	JLabel lblImageViewer = new JLabel("ImageLabel");
	JLabel lblMapName = new JLabel("Map Name NULL");
	JLabel lblMapcalls = new JLabel("Keine Call-Map verf\u00FCgbar.");
	JLabel lblMappic = new JLabel("Kein Banner verf\u00FCgbar.");
	ImageIcon[] nadeICArr = null;
	int imageIndex = 0;
	static Properties props = null;
	String contentPath = "data/content";
	String testPath = contentPath + "//nade/Smokes";

	private JPanel contentPane;
	private final JPanel routinePanel = new JPanel();
	private final JTextPane txtpnRoutineDesc = new JTextPane();
	private final JLabel lblRoutineHead = new JLabel("Keine \u00DCberschrift gefunden");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try {
						props = PropertyManager.initProperties();
					} catch (IOException e) {
						System.err.println("[ERROR] - "+e.getMessage());
						PropertyManager.firstRun();
						props = PropertyManager.initProperties();
					}
					if (props.getProperty("dontshow").equals("false")) {
						Startup.main(null);
					}
					Menu frame = new Menu();
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
	public Menu() {
		setResizable(false);
		tree.setRootVisible(false);
		tree.setModel(TreeFromFolderTree.getJTree().getModel());
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/gietlap/csgo/windows/ic.png")));
		setTitle("Counter Strike: Global Offensive Trainingskompendium");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1275, 811);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.DARK_GRAY);
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Programm");
		mnNewMenu.setForeground(Color.WHITE);
		menuBar.add(mnNewMenu);

		JMenuItem mntmVerbindeZumCsgo = new JMenuItem("Verbinde zum CSGO Server");
		mntmVerbindeZumCsgo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Desktop desk = Desktop.getDesktop();
				URI uri = null;
				try {
					uri = new URI("steam://connect/imne.cstrike.to");
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
				try {
					desk.browse(uri);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		mnNewMenu.add(mntmVerbindeZumCsgo);

		JMenuItem mntmOpenupdater = new JMenuItem("\u00D6ffne Updater");
		mntmOpenupdater.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataGuardian.main(null);
				dispose();
			}
		});
		mnNewMenu.add(mntmOpenupdater);

		JMenu mnInfo = new JMenu("Info");
		mnInfo.setForeground(Color.WHITE);
		menuBar.add(mnInfo);

		JMenuItem mntmber = new JMenuItem("\u00DCber");
		mntmber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Credits.main(null);
			}
		});
		mnInfo.add(mntmber);

		JMenuItem mntmBegrung = new JMenuItem("Begr\u00FC\u00DFung");
		mnInfo.add(mntmBegrung);

		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblMappic.setForeground(Color.LIGHT_GRAY);

		lblMappic.setBounds(515, 38, 401, 308);

		treePanel = new JPanel();
		treePanel.setBounds(5, 5, 256, 735);
		contentPane.add(treePanel);
		tree.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.RED));
		tree.setBackground(Color.GRAY);
		tree.setBounds(0, 0, 256, 735);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent arg0) {
				TreeNode node = (TreeNode) tree.getLastSelectedPathComponent();
				if (node == null)
					return;
				if (node.getParent().toString().equals("Maps")) {
					showPanel.removeAll();
					showPanel.add(mapPanel);
					mapPanel.setVisible(true);
					showPanel.repaint();
					showPanel.revalidate();
					lblMappic.setIcon(ImageProvider.getImageIconSized4Label(lblMappic,
							ImageProvider.getImageFromPath(contentPath + "/maps/" + node.toString() + "/banner.jpg")));
					lblMapcalls.setIcon(ImageProvider.getImageIconSized4Label(lblMapcalls,
							ImageProvider.getImageFromPath(contentPath + "/maps/" + node.toString() + "/calls.png")));
					txtpnBeschreibung.setText(ContentProvider.getMapDesc(contentPath + "/maps/" + node.toString()));
					lblMapName.setText(ContentProvider.getMapName(contentPath + "/maps/" + node.toString()));
				} else

				if (node.getParent().getParent().toString().equals("Granaten")) {
					showPanel.removeAll();
					showPanel.add(nadePanel);
					nadePanel.setVisible(true);
					showPanel.repaint();
					showPanel.revalidate();
					nadeICArr = ImageProvider.getAllImageIconsInDir(contentPath + "/" + Paths.getNodePath(node));
					System.out.println("[INFO] - "+nadeICArr.length + " Bilder aus Verzeichnis: "+contentPath + "/" + Paths.getNodePath(node)+" geladen");
					if (nadeICArr.length != 0) {
						lblImageViewer.setIcon(ImageProvider.getImageIconSized4Label(lblImageViewer, nadeICArr[0]));
					}
					if (nadeICArr.length == 0) {
						lblImageViewer.setIcon(null);
						lblImageViewer.setText("Kein Bild vorhanden!");
					}
					imageIndex = 0;
					btnScrollBack.setEnabled(true);
					btnScrollFow.setEnabled(true);
					if ((imageIndex + 1) >= nadeICArr.length) {
						btnScrollFow.setEnabled(false);
					}
					if ((imageIndex - 1) < nadeICArr.length) {
						btnScrollBack.setEnabled(false);
					}
					nadeLabel.setText(ContentProvider.getNadeName(contentPath + "/" + Paths.getNodePath(node)));
					nadeDesc.setText(ContentProvider.getNadeDesc(contentPath + "/" + Paths.getNodePath(node)));
					stepDesc.setText(ContentProvider.getNadeSteps(contentPath + "/" + Paths.getNodePath(node)));
				} else

				if (node.getParent().toString().equals("Routinen")) {
					showPanel.removeAll();
					showPanel.add(routinePanel);
					routinePanel.setVisible(true);
					showPanel.repaint();
					showPanel.revalidate();
					lblRoutineHead.setText(ContentProvider.getRoutineName(contentPath + "/" + Paths.getNodePath(node)));
					txtpnRoutineDesc.setText(ContentProvider.getRoutineDesc(contentPath + "/" + Paths.getNodePath(node)));
				}
			}
		});
		treePanel.setLayout(null);
		treePanel.add(tree);

		showPanel.setBounds(271, 5, 978, 735);
		contentPane.add(showPanel);
		showPanel.setLayout(null);

		hintPanel.setBounds(0, 0, 978, 735);
		hintPanel.setBackground(Color.DARK_GRAY);
		hintPanel.setLayout(null);

		JTextPane txtpnWhleEineMap = new JTextPane();
		txtpnWhleEineMap.setForeground(Color.WHITE);
		txtpnWhleEineMap
				.setText("W\u00E4hle eine Map, seine Routine oder eine Nade aus, um mehr Informationen zu erhalten.");
		txtpnWhleEineMap.setBackground(Color.DARK_GRAY);
		txtpnWhleEineMap.setBounds(262, 242, 505, 220);
		hintPanel.add(txtpnWhleEineMap);
		showPanel.add(hintPanel);
		mapPanel.setBackground(Color.DARK_GRAY);

		mapPanel.setBounds(0, 0, 978, 735);
		showPanel.add(mapPanel);
		mapPanel.setLayout(null);
		txtpnBeschreibung.setForeground(Color.WHITE);
		txtpnBeschreibung.setBackground(Color.GRAY);

		txtpnBeschreibung.setBounds(10, 357, 958, 378);
		txtpnBeschreibung.setEditable(false);
		txtpnBeschreibung.setText("Map Description NULL");
		mapPanel.add(txtpnBeschreibung);

		lblMapName.setForeground(Color.WHITE);
		lblMapName.setBounds(10, 0, 190, 28);
		lblMapName.setFont(new Font("Tahoma", Font.BOLD, 16));
		mapPanel.add(lblMapName);
		lblMapcalls.setForeground(Color.LIGHT_GRAY);

		lblMapcalls.setBounds(62, 38, 401, 308);
		mapPanel.add(lblMapcalls);

		mapPanel.add(lblMappic);
		mapPanel.setVisible(false);
		nadePanel.setBackground(Color.DARK_GRAY);

		nadePanel.setLayout(null);
		nadePanel.setBounds(0, 0, 978, 735);
		showPanel.add(nadePanel);

		nadeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nadeLabel.setForeground(Color.WHITE);
		nadeLabel.setBounds(313, 0, 405, 20);
		nadePanel.add(nadeLabel);
		nadeLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

		nadeDesc.setText("nade Description");
		nadeDesc.setBackground(Color.GRAY);
		nadeDesc.setEditable(false);
		nadeDesc.setBounds(687, 42, 281, 657);
		nadePanel.add(nadeDesc);

		scrollPanel.setBackground(Color.DARK_GRAY);
		scrollPanel.setBounds(10, 31, 667, 668);
		nadePanel.add(scrollPanel);
		scrollPanel.setLayout(null);

		stepDesc.setBackground(Color.GRAY);
		stepDesc.setBounds(0, 292, 667, 376);
		scrollPanel.add(stepDesc);
		stepDesc.setText("Steps Description");
		stepDesc.setEditable(false);
		lblImageViewer.setForeground(Color.LIGHT_GRAY);

		lblImageViewer.setBounds(93, 11, 480, 270);
		scrollPanel.add(lblImageViewer);
		btnScrollBack.setForeground(Color.WHITE);
		btnScrollBack.addActionListener(new ActionListener() { // set action for Scroll Back
			public void actionPerformed(ActionEvent e) {
				imageIndex--;
				lblImageViewer.setIcon(ImageProvider.getImageIconSized4Label(lblImageViewer, nadeICArr[imageIndex]));
				try {
					if ((imageIndex - 1) < 0) {
						btnScrollBack.setEnabled(false);
					}
					if ((imageIndex + 1) == nadeICArr.length) {
						btnScrollFow.setEnabled(false);
					} else
						btnScrollFow.setEnabled(true);
				} catch (NullPointerException enull) {
					enull.printStackTrace();
				}
			}
		});

		btnScrollBack.setBackground(Color.RED);
		btnScrollBack.setBounds(10, 11, 78, 270);
		scrollPanel.add(btnScrollBack);
		btnScrollFow.setForeground(Color.WHITE);
		btnScrollFow.addActionListener(new ActionListener() { // set action for Scroll Foward
			public void actionPerformed(ActionEvent e) {
				imageIndex++;
				lblImageViewer.setIcon(ImageProvider.getImageIconSized4Label(lblImageViewer, nadeICArr[imageIndex]));
				try {
					if ((imageIndex + 1) == nadeICArr.length) {
						btnScrollFow.setEnabled(false);
					}
					if ((imageIndex - 1) < 0) {
						btnScrollBack.setEnabled(false);
					} else
						btnScrollBack.setEnabled(true);
				} catch (NullPointerException enull) {
					enull.printStackTrace();
				}
			}
		});

		btnScrollFow.setBackground(Color.RED);
		btnScrollFow.setBounds(579, 11, 78, 270);
		scrollPanel.add(btnScrollFow);
		routinePanel.setVisible(false);
		routinePanel.setBackground(Color.DARK_GRAY);
		routinePanel.setBounds(0, 0, 978, 735);

		showPanel.add(routinePanel);
		routinePanel.setLayout(null);
		txtpnRoutineDesc.setText("Kein Inhalt verf\u00FCgbar");
		txtpnRoutineDesc.setForeground(Color.WHITE);
		txtpnRoutineDesc.setBackground(Color.GRAY);
		txtpnRoutineDesc.setBounds(0, 55, 978, 680);

		routinePanel.add(txtpnRoutineDesc);
		lblRoutineHead.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRoutineHead.setForeground(Color.WHITE);
		lblRoutineHead.setBounds(10, 11, 935, 33);

		routinePanel.add(lblRoutineHead);

		mntmBegrung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Startup.main(null);
			}
		});
		nadePanel.setVisible(false);
		repaint();
		revalidate();
	}

	public TreeModel getTreeModel() {
		return tree.getModel();
	}

	public void setTreeModel(TreeModel model) {
		tree.setModel(model);
	}
}
