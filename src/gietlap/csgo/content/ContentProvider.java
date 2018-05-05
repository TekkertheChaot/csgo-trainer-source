package gietlap.csgo.content;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

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
			e.printStackTrace();
		}
	}

	public static boolean unzip(String source, String destination, String password) {
		try {
			net.lingala.zip4j.core.ZipFile zip = new net.lingala.zip4j.core.ZipFile(source);
			if (zip.isEncrypted()) {
				zip.setPassword(password);
			}
			zip.extractAll(destination);
			return true;
		} catch (ZipException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static int getContentVersion() {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			br = new BufferedReader(new FileReader("data/content/ver.dat"));
			String newLine = br.readLine();
			sb.append(newLine);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
		return Integer.parseInt(sb.toString());
	}

	public static String getMapName(String path) {
		boolean flag = true;
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			br = new BufferedReader(new FileReader(path + "/name.dat"));
			while (flag) {
				String newLine = br.readLine();
				if (newLine == null || newLine.equals("")) {
					flag = false;
				} else
					sb.append(newLine + "\n");
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			return "Kein Map-Name verfügbar!";
		}
		return sb.toString();
	}

	public static String getMapDesc(String path) {
		boolean flag = true;
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			br = new BufferedReader(new FileReader(path + "/desc.dat"));
			while (flag) {
				String newLine = br.readLine();
				if (newLine == null || newLine.equals("")) {
					flag = false;
				} else
					sb.append(newLine + "\n");
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			return "Keine Map-Beschreibung verfügbar!";
		}
		return sb.toString();
	}

	public static String getNadeDesc(String path) {
		boolean flag = true;
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			br = new BufferedReader(new FileReader(path + "/desc.dat"));
			while (flag) {
				String newLine = br.readLine();
				if (newLine == null || newLine.equals("")) {
					flag = false;
				} else
					sb.append(newLine + "\n");
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			return "Keine Granaten-Beschreibung verfügbar!";
		}
		return sb.toString();
	}

	public static String getNadeName(String path) {
		boolean flag = true;
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			br = new BufferedReader(new FileReader(path + "/name.dat"));
			while (flag) {
				String newLine = br.readLine();
				if (newLine == null || newLine.equals("")) {
					flag = false;
				} else
					sb.append(newLine + "\n");
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			return "Kein Granaten-Name verfügbar!";
		}
		return sb.toString();
	}

	public static String getNadeSteps(String path) {
		boolean flag = true;
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			br = new BufferedReader(new FileReader(path + "/steps.dat"));
			while (flag) {
				String newLine = br.readLine();
				if (newLine == null || newLine.equals("")) {
					flag = false;
				} else
					sb.append(newLine + "\n");
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			return "Keine Schritte für diese Granate verfügbar!";
		}
		return sb.toString();
	}

	public static String getRoutineDesc(String path) {
		boolean flag = true;
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			br = new BufferedReader(new FileReader(path + "/desc.dat"));
			while (flag) {
				String newLine = br.readLine();
				if (newLine == null || newLine.equals("")) {
					flag = false;
				} else
					sb.append(newLine + "\n");
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			return "Keine Routinen Beschreibung verfügbar!";
		}
		return sb.toString();
	}

	public static String getRoutineName(String path) {
		boolean flag = true;
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			br = new BufferedReader(new FileReader(path + "/name.dat"));
			while (flag) {
				String newLine = br.readLine();
				if (newLine == null || newLine.equals("")) {
					flag = false;
				} else
					sb.append(newLine + "\n");
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			return "Kein Routinen-Name verfügbar!";
		}
		return sb.toString();
	}
}
