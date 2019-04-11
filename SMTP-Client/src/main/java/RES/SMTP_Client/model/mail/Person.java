/**
 * Author   : Bacso Gaetan and Dutu Launay Marion
 * Source   : Olivier Liechti (MailRobot)
 * File     : Person.java
 * Compiler and executer :
 *   	javac 1.8.0_201
 *      java version "1.8.0_201"
 *      Java(TM) SE Runtime Environment (build 1.8.0_201-b09)
 *      Java HotSpot(TM) 64-Bit Server VM (build 25.201-b09, mixed mode)

 */

package RES.SMTP_Client.model.mail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is useful to create a person, which is either a sender either a recipient of a message. It contains
 * the firstname and the lastname of the person, and its e-mail address.
 *
 * @author Bacso Gaetan and Dutu Launay Marion
 */
public class Person {
	private String firstname;
	private String lastname;
	private final String address;
	
	public Person(String firstname, String lastname, String address) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
	}

	/**
	 * Finds the lastname and the firstname of a person with its address.
	 * Example : gaetan.bacso@test.ch : first name Gaetan, last name Bacso
	 * @param address The address of the person
	 */
	public Person(String address) {
		this.address = address;
		Pattern pattern = Pattern.compile("(.*)\\.(.*)@");
		Matcher matcher = pattern.matcher(address);
		boolean found = matcher.find(0);
		if(found) {
			this.firstname = matcher.group(0);
			firstname = firstname.substring(0,1).toUpperCase() + firstname.substring(1);
			this.lastname = matcher.group(1);
			lastname = lastname.substring(0,1).toUpperCase() + lastname.substring(1);
		}
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getAddress() {
		return address;
	}
	
}
