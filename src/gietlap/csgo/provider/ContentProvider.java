package gietlap.csgo.provider;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import gietlap.csgo.windows.DataGuardian;
import gietlap.csgo.windows.ErrorMessage;
import net.lingala.zip4j.exception.ZipException;

public class ContentProvider {

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
			System.out.println("IO");
			ErrorMessage.main(e);
			e.printStackTrace();
		}
	}

	/**
	 * Unzips given source to its destination with given password if necessary.
	 * 
	 * @param source
	 *            Path of the source file
	 * @param destination
	 *            destination of unziped files
	 * @param password
	 *            password for ziped file
	 * @return
	 */
	public static int unzip(String source, String destination, String password) {
		try {
			net.lingala.zip4j.core.ZipFile zip = new net.lingala.zip4j.core.ZipFile(source);
			if (zip.isEncrypted()) {
				if (password == null || password.equals("")) {
					System.out.println("[INFO] - " + "Zip file needs a password.");
					return 2;
				} else
					zip.setPassword(password);
			}
			zip.extractAll(destination);
			return 0;
		} catch (ZipException e) {
			if (e.getMessage().contains("Wrong Password")) {
				deleteContent(DataGuardian.contentPath);
				return 3;
			}
			System.err.println("[ERROR] - " + "Zip extraction failed :" + e.getMessage());
			e.printStackTrace();
			return 1;
		}
	}

	public static void deleteContent(String contentPath) {
		File dir = new File(contentPath);
		try {
			FileUtils.deleteDirectory(dir);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Gets current Client Build version.
	 * 
	 * @return Client Build
	 */
	public static int getClientVersion() {
		return 6;
	}

	/**
	 * Gets local content version number. Returns 0 if no content is available.
	 * 
	 * @return
	 */
	public static int getContentVersion() {
		String content = FileProvider.getFileContentAsUTF8("data/content/ver.dat");
		if (content != null) {
			return Integer.parseInt(content);
		} else
			return 0;
	}

	/**
	 * Gets Map name from "name.dat"-file in given directory
	 * 
	 * @param path
	 *            Where the file can be found
	 * @return
	 */
	public static String getMapName(String path) {
		String name = FileProvider.getFileContentAsUTF8(path + "/name.dat");
		if (name == null || name.equals("") || name.equals("null")) {
			name = "Kein Map-Name vorhanden";
		}
		return name;
	}

	/**
	 * Gets Map description from "desc.dat"-file in given directory
	 * 
	 * @param path
	 *            Where the file can be found
	 * @return
	 */
	public static String getMapDesc(String path) {
		String desc = FileProvider.getFileContentAsUTF8(path + "/desc.dat");
		if (desc == null || desc.equals("") || desc.equals("null")) {
			desc = "Keine Map-Beschreibung vorhanden";
		}
		return desc;
	}

	/**
	 * Gets Nade description from "desc.dat"-file in given directory
	 * 
	 * @param path
	 *            Where the file can be found
	 * @return
	 */
	public static String getNadeDesc(String path) {
		String desc = FileProvider.getFileContentAsUTF8(path + "/desc.dat");
		if (desc == null || desc.equals("") || desc.equals("null")) {
			desc = "Keine Nade-Beschreibung vorhanden";
		}
		return desc;
	}

	/**
	 * Gets Nade name from "name.dat"-file in given directory
	 * 
	 * @param path
	 *            Where the file can be found
	 * @return
	 */
	public static String getNadeName(String path) {
		String name = FileProvider.getFileContentAsUTF8(path + "/name.dat");
		if (name == null || name.equals("") || name.equals("null")) {
			name = "Kein Nade-Name vorhanden";
		}
		return name;
	}

	/**
	 * Gets Nade steps from "steps.dat"-file in given directory
	 * 
	 * @param path
	 *            Where the file can be found
	 * @return
	 */
	public static String getNadeSteps(String path) {
		String steps = FileProvider.getFileContentAsUTF8(path + "/steps.dat");
		if (steps == null || steps.equals("") || steps.equals("null")) {
			steps = "Keine Nade-Schritte vorhanden";
		}
		return steps;
	}

	/**
	 * Gets Routine description from "desc.dat"-file in given directory
	 * 
	 * @param path
	 *            Where the file can be found
	 * @return
	 */
	public static String getRoutineDesc(String path) {
		String desc = FileProvider.getFileContentAsUTF8(path + "/desc.dat");
		if (desc == null || desc.equals("") || desc.equals("null")) {
			desc = "Keine Map-Beschreibung vorhanden";
		}
		return desc;
	}

	/**
	 * Gets Routine name from "name.dat"-file in given directory
	 * 
	 * @param path
	 *            Where the file can be found
	 * @return
	 */
	public static String getRoutineName(String path) {
		String name = FileProvider.getFileContentAsUTF8(path + "/name.dat");
		if (name == null || name.equals("") || name.equals("null")) {
			name = "Keine Map-Beschreibung vorhanden";
		}
		return name;
	}
}
