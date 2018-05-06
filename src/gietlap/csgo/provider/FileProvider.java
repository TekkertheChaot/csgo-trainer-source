package gietlap.csgo.provider;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileProvider {
	public static String getFileContentAsUTF8(String filePath) {
		StringBuilder sb = new StringBuilder();
		String newLine = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF8"));
			sb.append(br.readLine());
			while ((newLine = br.readLine()) != null) {
				sb.append("\n" + newLine);
			}
			br.close();
		} catch (IOException e) {
			System.err.println("[ERROR] - " + e.getMessage());
			e = null;
			return null;
		}
		return sb.toString();
	}

}
