/**
 * Author   : Bacso Gaetan and Dutu Launay Marion
 * File     : IConfigurationManager.java
 * Compiler and executer : 
 * 		javac 1.8.0_201
 *      java version "1.8.0_201"
 *      Java(TM) SE Runtime Environment (build 1.8.0_201-b09)
 *      Java HotSpot(TM) 64-Bit Server VM (build 25.201-b09, mixed mode)

 */
package RES.SMTP_Client.config;

import java.util.List;

import RES.SMTP_Client.model.mail.Person;

/**
 * Interface for configuration manager.
 * It is a list of mandatory getter.
 * 
 * @author Bacso Gaetan and Dutu Launay Marion
 *
 */
public interface IConfigurationManager {
	public List<Person> getVictims();
	public List<String> getMessages();
	public List<Person> getWitnessesToCC();
	public int getNumberOfGroups();
	public String getSmtpServerAddress();
	public int getSmtpServerPort();
}
