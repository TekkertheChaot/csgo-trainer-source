package gietlap.csgo.provider;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
	public static void firstRun() throws IOException {
		Properties prop = new Properties();
		prop.put("dontshow", "false");
		try {
			prop.store(new FileWriter("data/" + System.getProperty("user.name") + "_profile.dat"),
					"Get out of ma Property!");
		} catch (IOException e) {
			throw e;
		}
	}

	public static Properties initProperties() throws IOException {
		Properties prop = new Properties();
		try {
			prop.load(new FileReader("data/" + System.getProperty("user.name") + "_profile.dat"));
		} catch (IOException e) {
			throw e;
		}
		return prop;
	}

	public static void writeProperties(Properties props) throws IOException {
		try {
			props.store(new FileWriter("data/" + System.getProperty("user.name") + "_profile.dat"),
					"Get out of ma Property!");
		} catch (IOException e) {
			throw e;
		}
	}

}
