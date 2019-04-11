/**
 * Author   : Bacso Gaetan and Dutu Launay Marion
 * Source   : Olivier Liechti (MailRobot)
 * File     : ConfigurationManager.java
 * Compiler and executer : 
 * 		javac 1.8.0_201
 *      java version "1.8.0_201"
 *      Java(TM) SE Runtime Environment (build 1.8.0_201-b09)
 *      Java HotSpot(TM) 64-Bit Server VM (build 25.201-b09, mixed mode)

 */
package RES.SMTP_Client.config;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import RES.SMTP_Client.model.mail.Person;

/**
 * This class parse the configuration files and store it.
 * It get the addresses of the victims, the configuration of the server,
 * the messages of pranks, the number of groups and the witnesses CC.
 * 
 * @author Bacso Gaetan and Dutu Launay Marion
 *
 */
public class ConfigurationManager implements IConfigurationManager {
	private String smtpServerAddress;
	private int smtpServerPort;
	private final List<Person> victims;
	private final List<String> messages;
	private int numberOfGroups;
	private List<Person> witnessesToCC;
	
	/**
	 * Constructor of the class. Get all configuration.
	 * The directory configuration must contains files
	 * 		victims.utf8
	 * 		messages.utf8
	 * 		config.properties
	 * 
	 * @param path : path of the configuration directory.
	 * @throws IOException
	 */
	public ConfigurationManager(String path) throws IOException {
		victims = loadAddressesFromFile(path + "victims.utf8");
		messages = loadMessagesFromFile(path + "messages.utf8");
		loadProperties(path + "config.properties");
	}
	
	/**
	 * Load the messages of pranks from a file.
	 * 
	 * @param filename : path of the file
	 * @return List of messages
	 * @throws IOException
	 */
	private List<String> loadMessagesFromFile(String filename) throws IOException {
		List<String> result;
		try(FileInputStream fis = new FileInputStream(filename)){
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			try(BufferedReader reader = new BufferedReader(isr)){
				result = new ArrayList<String>();
				String line = reader.readLine();
				while(line != null) {
					StringBuilder body = new StringBuilder();
					while((line != null) && (!line.equals("=="))){
						body.append(line);
						body.append("\r\n");
						line = reader.readLine();
					}
					result.add(body.toString());
					line = reader.readLine();
				}
			}
		}
		return result;
	}

	/**
	 * Load addresses of the victims from a file.
	 * 
	 * @param filename : path of the file
	 * @return List of person created with addresses
	 * @throws IOException
	 */
	private List<Person> loadAddressesFromFile(String filename) throws IOException {
		List<Person> result;
		try(FileInputStream fis = new FileInputStream(filename)){
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			try(BufferedReader reader = new BufferedReader(isr)){
				result = new ArrayList<Person>();
				String address = reader.readLine();
				while(address != null) {
					result.add(new Person(address));
					address = reader.readLine();
				}
			}
		}
		return result;
	}

	/**
	 * Load properties from a file. (Server address, server port, number of groups, witnesses)
	 * 
	 * @param filename : path to the file
	 * @throws IOException
	 */
	private void loadProperties(String filename) throws IOException {
		FileInputStream fis = new FileInputStream(filename);
		Properties properties = new Properties();
		properties.load(fis);
		this.smtpServerAddress = properties.getProperty("smtpServerAddress");
		this.smtpServerPort = Integer.parseInt(properties.getProperty("smtpServerPort"));
		this.numberOfGroups = Integer.parseInt(properties.getProperty("numberOfGroups"));
		
		this.witnessesToCC = new ArrayList<Person>();
		String witnesses = properties.getProperty("witnessesToCC");
		String[] witnessesAddresses = witnesses.split(",");
		for(String address : witnessesAddresses) {
			this.witnessesToCC.add(new Person(address));
		}
	}

	
	@Override
	public List<Person> getVictims() {
		return victims;
	}

	@Override
	public List<String> getMessages() {
		return messages;
	}

	@Override
	public List<Person> getWitnessesToCC() {
		return witnessesToCC;
	}

	@Override
	public int getNumberOfGroups() {
		return numberOfGroups;
	}

	@Override
	public String getSmtpServerAddress() {
		return smtpServerAddress;
	}

	@Override
	public int getSmtpServerPort() {
		return smtpServerPort;
	}
}
