package RES.SMTP_Client.model.prank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import RES.SMTP_Client.config.IConfigurationManager;
import RES.SMTP_Client.model.mail.Group;
import RES.SMTP_Client.model.mail.Person;
import RES.SMTP_Client.smtp.SmtpClient;

public class PrankGenerator {
	private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());
	private IConfigurationManager configurationManager;
	
	public PrankGenerator(IConfigurationManager configurationManager) {
		this.configurationManager = configurationManager;
	}
	
	public List<Prank> generatePranks(){
		List<Prank> pranks = new ArrayList<Prank>();
		
		List<String> messages = configurationManager.getMessages();
		int messageIndex = 0;
		
		int numberOfGroups = configurationManager.getNumberOfGroups();
		int numberOfVictims = configurationManager.getVictims().size();
		
		if(numberOfVictims / numberOfGroups < 3) {
			numberOfGroups = numberOfVictims / 3;
			LOG.warning("There are not enougth victims to generate the desired of groups. We can only generate a max of " + numberOfGroups + " groups to have at least 3 victims per group.");
		}
		
		List<Group> groups = generateGroups(configurationManager.getVictims(), numberOfGroups);
		for(Group group : groups) {
			Prank prank = new Prank();
			
			List<Person> victims = group.getMembers();
			Collections.shuffle(victims);
			Person sender = victims.remove(0);
			prank.setVictimSender(sender);
			prank.addVictimRecipients(victims);
			
			prank.addWitnessRecipients(configurationManager.getWitnessesToCC());
			
			String message = messages.get(messageIndex);
			messageIndex = (messageIndex + 1) % messages.size();
			prank.setMessage(message);
			
			pranks.add(prank);
		}
		
		return pranks;
	}
	
	public List<Group> generateGroups(List<Person> victims, int numberOfGroups) {
		List<Person> avaliablesVictims = new ArrayList<Person>(victims);
		Collections.shuffle(avaliablesVictims);
		List<Group> groups = new ArrayList<Group>();
		for(int i = 0; i < numberOfGroups; i++) {
			Group group = new Group();
			groups.add(group);
		}
		
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
