package RES.SMTP_Client.config;

import java.util.List;

import RES.SMTP_Client.model.mail.Person;

public interface IConfigurationManager {
	public List<Person> getVictims();
	public List<String> getMessages();
	public List<Person> getWitnessesToCC();
	public int getNumberOfGroups();
}
