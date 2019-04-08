package RES.SMTP_Client.model.mail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
	private String firstname;
	private String lastname;
	private final String address;
	
	public Person(String firstname, String lastname, String address) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
	}
	
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
