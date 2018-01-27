package com.Haborer.DB;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.security.auth.login.Configuration;

public class Utilities{
	public static String ClientURI;
	public static String DBName;
	public static String UsersCollectionName;
	public static String RequestCollectionName;
	static {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("C:\\Users\\Ido\\git\\Haborer-Server\\Haborer-Service\\config.properties");

			// load a properties file
			prop.load(input);

		    ClientURI =prop.getProperty("ClientURI");
		     DBName = prop.getProperty("DBName");
			 UsersCollectionName = prop.getProperty("UsersCollectionName");
		     RequestCollectionName=prop.getProperty("RequestCollectionName");
	}	 catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	}
}

