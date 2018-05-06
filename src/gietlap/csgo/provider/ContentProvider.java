package gietlap.csgo.provider;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import net.lingala.zip4j.exception.ZipException;

public class ContentProvider {
	private static String zipPassword = null;
	/**
	 * Unzips a file and returns its success state.
	 * 
	 * @param source
	 *            Source path of the file
	 * @param destination
	 *            Destination path of the file
	 * @param password
	 *            optional password
	 * @return success state
	 */
	public static void download(URL url, String destination) {
		try {
			FileUtils.copyURLToFile(url, new File(destination));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
/**
 * Unzips given source to its destination with given password if necessary.
 * @param source Path of the source file
 * @param destination destination of unziped files
 * @param password password for ziped file
 * @return
 */
	public static int unzip(String source, String destination, String password) {
		try {
			net.lingala.zip4j.core.ZipFile zip = new net.lingala.zip4j.core.ZipFile(source);
			if (zip.isEncrypted()) {
				zipPassword=password;
				if(zipPassword==null) {
					return 2;
				}else
				zip.setPassword(password);
			}
			zip.extractAll(destination);
			return 0;
		} catch (ZipException e) {
			e.printStackTrace();
			return 1;
		}
	}

	public static int getClientVersion() {
		return 3;
	}

	public static int getContentVersion() {
		return Integer.parseInt(FileProvider.getFileContentAsUTF8("data/content/ver.dat"));
	}

	public static String getMapName(String path) {
		String name = FileProvider.getFileContentAsUTF8(path + "/name.dat");
		if (name == null || name.equals("") || name.equals("null")) {
			name = "Kein Map-Name vorhanden";
		}
		return name;
	}

	public static String getMapDesc(String path) {
		String desc = FileProvider.getFileContentAsUTF8(path + "/desc.dat");
		if (desc == null || desc.equals("") || desc.equals("null")) {
			desc = "Keine Map-Beschreibung vorhanden";
		}
		return desc;
	}

	public static String getNadeDesc(String path) {
		String desc = FileProvider.getFileContentAsUTF8(path + "/desc.dat");
		if (desc == null || desc.equals("") || desc.equals("null")) {
			desc = "Keine Nade-Beschreibung vorhanden";
		}
		return desc;
	}

	public static String getNadeName(String path) {
		String name = FileProvider.getFileContentAsUTF8(path + "/name.dat");
		if (name == null || name.equals("") || name.equals("null")) {
			name = "Kein Nade-Name vorhanden";
		}
		return name;
	}

	public static String getNadeSteps(String path) {
		String steps = FileProvider.getFileContentAsUTF8(path + "/steps.dat");
		if (steps == null || steps.equals("") || steps.equals("null")) {
			steps = "Keine Nade-Schritte vorhanden";
		}
		return steps;
	}

	public static String getRoutineDesc(String path) {
		String desc = FileProvider.getFileContentAsUTF8(path + "/desc.dat");
		if (desc == null || desc.equals("") || desc.equals("null")) {
			desc = "Keine Map-Beschreibung vorhanden";
		}
		return desc;
	}
	public static String getRoutineName(String path) {
		String name = FileProvider.getFileContentAsUTF8(path + "/name.dat");
		if (name == null || name.equals("") || name.equals("null")) {
			name = "Keine Map-Beschreibung vorhanden";
		}
		return name;
	}
}
