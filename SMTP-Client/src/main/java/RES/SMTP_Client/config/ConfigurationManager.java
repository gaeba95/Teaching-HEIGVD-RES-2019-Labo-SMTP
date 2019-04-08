package RES.SMTP_Client.config;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import RES.SMTP_Client.model.mail.Person;

public class ConfigurationManager implements IConfigurationManager {
	private String smtpServerAddress;
	private int smtpServerPort;
	private final List<Person> victims;
	private final List<String> messages;
	private int numberOfGroups;
	private List<Person> witnessesToCC;
	
	public ConfigurationManager() throws IOException {
		victims = loadAddressesFromFile("./configs/victims.utf8");
		messages = loadMessagesFromFile("./configs/messages.utf8");
		loadProperties("./configs/config.properties");
	}
	
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
		// TODO Auto-generated method stub
		return victims;
	}

	@Override
	public List<String> getMessages() {
		// TODO Auto-generated method stub
		return messages;
	}

	@Override
	public List<Person> getWitnessesToCC() {
		// TODO Auto-generated method stub
		return witnessesToCC;
	}

	@Override
	public int getNumberOfGroups() {
		// TODO Auto-generated method stub
		return numberOfGroups;
	}

	public String getSmtpServerAddress() {
		return smtpServerAddress;
	}

	public int getSmtpServerPort() {
		return smtpServerPort;
	}
}
