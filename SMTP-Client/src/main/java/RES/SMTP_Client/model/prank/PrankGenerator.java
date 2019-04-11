/**
 * Author   : Bacso Gaetan and Dutu Launay Marion
 * Source   : Olivier Liechti (MailRobot)
 * File     : PrankGenerator.java
 * Compiler and executer :
 *   	javac 1.8.0_201
 *      java version "1.8.0_201"
 *      Java(TM) SE Runtime Environment (build 1.8.0_201-b09)
 *      Java HotSpot(TM) 64-Bit Server VM (build 25.201-b09, mixed mode)

 */

package RES.SMTP_Client.model.prank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import RES.SMTP_Client.config.IConfigurationManager;
import RES.SMTP_Client.model.mail.Group;
import RES.SMTP_Client.model.mail.Person;
import RES.SMTP_Client.smtp.SmtpClient;

/**
 * This class generates a list of pranks by looking into the configs/messages.utf8 file.
 *
 * @authors Bacso Gaetan and Dutu Launay Marion
 */
public class PrankGenerator {
	private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());
	private IConfigurationManager configurationManager;
	
	public PrankGenerator(IConfigurationManager configurationManager) {
		this.configurationManager = configurationManager;
	}

	/**
	 * Generates Pranks objects with its victims : for each group, the first member is the sender and everyone in the group
	 * is the recipient.
	 *
	 * @return the list of pranks
	 */
	public List<Prank> generatePranks(){
		List<Prank> pranks = new ArrayList<Prank>();

		// We get the messages, the number of groups and of victims via the configuration manager
		List<String> messages = configurationManager.getMessages();
		int messageIndex = 0;
		int numberOfGroups = configurationManager.getNumberOfGroups();
		int numberOfVictims = configurationManager.getVictims().size();

		// We need to have at least 2 victims per group
		if(numberOfVictims / numberOfGroups < 3) {
			numberOfGroups = numberOfVictims / 3;
			LOG.warning("There are not enougth victims to generate the desired of groups. We can only generate a max of " + numberOfGroups + " groups to have at least 3 victims per group.");
		}

		// Generating groups according to the parameter given by the user
		List<Group> groups = generateGroups(configurationManager.getVictims(), numberOfGroups);

		// Generating pranks for each group
		for(Group group : groups) {
			Prank prank = new Prank();
			
			List<Person> victims = group.getMembers();
			Collections.shuffle(victims);

			// The sender is the first victim
			Person sender = victims.remove(0);
			prank.setVictimSender(sender);

			// Everyone in the victim group receives the prank
			prank.addVictimRecipients(victims);
			prank.addWitnessRecipients(configurationManager.getWitnessesToCC());

			// Adding one of the messages of the list as a prank
			String message = messages.get(messageIndex);
			messageIndex = (messageIndex + 1) % messages.size();
			prank.setMessage(message);
			
			pranks.add(prank);
		}
		
		return pranks;
	}

	/**
	 * Generates n groups of victims, where n is the number given by the config file.
	 *
	 * @param victims the list of all the victims
	 * @param numberOfGroups the number of groups that should be formed
	 * @return the list of groups generated
	 */
	public List<Group> generateGroups(List<Person> victims, int numberOfGroups) {
		List<Person> avaliablesVictims = new ArrayList<Person>(victims);
		Collections.shuffle(avaliablesVictims);
		List<Group> groups = new ArrayList<Group>();

		// Creates n empty groups of victims
		for(int i = 0; i < numberOfGroups; i++) {
			Group group = new Group();
			groups.add(group);
		}

		// Fills every group
		int turn = 0;
		Group targetGroup;
		while(avaliablesVictims.size() > 0) {
			targetGroup = groups.get(turn);
			turn = (turn + 1) % groups.size();
			Person victim = avaliablesVictims.remove(0);
			targetGroup.addMember(victim);
		}
		return groups;
	}
}
