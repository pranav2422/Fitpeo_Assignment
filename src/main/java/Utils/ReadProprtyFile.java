package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProprtyFile {
	
	static Properties prop = new Properties();
	
	public static String getproperty(String key) throws IOException {
		
		try {
			Properties prop = new Properties();
			FileInputStream input = new FileInputStream(".//src//test//resources//PropertiesFile//config.properties");
			prop.load(input);
			return prop.getProperty(key);
		} catch (FileNotFoundException e) {
			e.getCause();
			throw new RuntimeException ("not able to load property file");
		}
	}
}
