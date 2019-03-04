package utils;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public  class Runner {

	private static Properties props;
	
public static void loadProperties () {
	props = new Properties();
	InputStream stream = Runner.class.getClassLoader().getResourceAsStream(
			"slideworx.properties");
	try {
		props.load(stream);
	} catch (IOException e) {
		e.printStackTrace();
		System.out
				.println("Cannot locate file \"slideworx.properties\"");
		
	}
	
}

public static String getProperty(String key){
	if(props == null){
		loadProperties();
	}
	return props.getProperty(key);
}

public static WebDriver startFirefoxDriver () {
	
	System.out.println(System.getProperty("driver"));
	WebDriverManager.firefoxdriver().setup();
	return new FirefoxDriver();
	
}
public static WebDriver startDriver() {
	String drivertype = System.getProperty("driver");
	System.out.println(drivertype);
	if (drivertype != null) {
		return getDriver(drivertype);
	}else {
		System.out.println(getProperty("driver"));
		return getDriver(getProperty("driver"));
	}
	
	
	
}

public static WebDriver getDriver(String prop) {
	if (prop.equalsIgnoreCase("ie")) {
		WebDriverManager.iedriver().setup();
		return new InternetExplorerDriver();
	}else if (prop.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
		return new ChromeDriver();
	} else if (prop.equalsIgnoreCase("edge")) {
		WebDriverManager.edgedriver().setup();
		return new EdgeDriver();	
	}else {
		WebDriverManager.firefoxdriver().setup();
		return new FirefoxDriver();
	}
	
	
	
}
}
