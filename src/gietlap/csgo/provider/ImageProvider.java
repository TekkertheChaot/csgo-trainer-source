package gietlap.csgo.provider;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageProvider {
	public static ImageIcon getImageIconSized4Label(JLabel jlabel, ImageIcon imageic) {
		Image image = imageic.getImage();
		Image newimg = image.getScaledInstance(jlabel.getWidth(), jlabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon newic = new ImageIcon(newimg);
		return newic;
	}

	public static ImageIcon getImageFromPath(String path) {
		ImageIcon imageic = null;
		imageic = new ImageIcon(path);
		return imageic;
	}

	public static ImageIcon[] getAllImageIconsInDir(String path) {
		File file = new File(path);
		File[] files = file.listFiles();
		List<ImageIcon> ics = new ArrayList<>();
		for (File ifile : files) {
			String filePath = ifile.getPath();
			if (filePath.endsWith(".jpg") || filePath.endsWith(".png")) {
				ics.add(new ImageIcon(filePath));
			}
		}
		return ics.toArray(new ImageIcon[ics.size()]);
	}

}
