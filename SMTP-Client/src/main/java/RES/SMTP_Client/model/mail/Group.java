package RES.SMTP_Client.model.mail;

import java.util.ArrayList;
import java.util.List;

public class Group {
	private final List<Person> members = new ArrayList<Person>();
	
	public void addMember(Person person) {
		members.add(person);
	}
	
	public List<Person> getMembers(){
		return new ArrayList<Person>(members);
	}
}
