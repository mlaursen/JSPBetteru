package com.betteru.database;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class LocalSettings {
	static Properties p;
	static String url = "config.properties";
	
	public static void main(String[] _) {
		writeProperties();
	}
	
	public static void writeProperties() {
		p = new Properties();
		try {
			p.setProperty("database", "jdbc:oracle:thin:@localhost:1521:xe");
			p.setProperty("dbuser", "jspbetteru");
			p.setProperty("dbpswd", "abcd1234");
			
			p.store(new FileOutputStream(url), null);
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public static Properties getProperties() {
		p = new Properties();
		try {
			p.load(new FileInputStream(url));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}
}
