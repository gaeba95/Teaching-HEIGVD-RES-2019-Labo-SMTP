/**
 * Author   : Bacso Gaetan and Dutu Launay Marion
 * Source   : Olivier Liechti (MailRobot)
 * File     : Group.java
 * Compiler and executer :
 *   	javac 1.8.0_201
 *      java version "1.8.0_201"
 *      Java(TM) SE Runtime Environment (build 1.8.0_201-b09)
 *      Java HotSpot(TM) 64-Bit Server VM (build 25.201-b09, mixed mode)

 */

package RES.SMTP_Client.model.mail;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is useful to create a group of members, to whom the pranks have to be sent. The members of a group
 * are contained in a List of Person objects.
 *
 * @author Bacso Gaetan and Dutu Launay Marion
 */
public class Group {
	private final List<Person> members = new ArrayList<Person>();

	/**
	 * Adds a member in the list of members
	 *
	 * @param person the member which has to be added
	 */
	public void addMember(Person person) {
		members.add(person);
	}

	public List<Person> getMembers(){
		return new ArrayList<Person>(members);
	}
}
