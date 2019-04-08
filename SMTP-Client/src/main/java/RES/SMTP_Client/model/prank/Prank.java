package RES.SMTP_Client.model.prank;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import RES.SMTP_Client.model.mail.Message;
import RES.SMTP_Client.model.mail.Person;

public class Prank {
	private Person victimSender;
	private final List<Person> victimRecipients = new ArrayList<Person>();
	private final List<Person> witnessRecipients = new ArrayList<Person>();
	private String message;
	
	public Person getVictimSender() {
		return victimSender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void addVictimRecipients(List<Person> victims) {
		victimRecipients.addAll(victims);
	}
	
	public void addWitnessRecipients(List<Person> witness) {
		witnessRecipients.addAll(witness);
	}

	public List<Person> getVictimRecipients() {
		return victimRecipients;
	}

	public List<Person> getWitnessRecipients() {
		return witnessRecipients;
	}

	public void setVictimSender(Person victimSender) {
		this.victimSender = victimSender;
	}
	
	public Message generateMessage() {
		Message msg = new Message();
		
		msg.setBody(this.message + "\r\n");
		
		String[] to = victimRecipients
				.stream()
				.map(p -> p.getAddress())
				.collect(Collectors.toList())
				.toArray(new String[]{});
		msg.setTo(to);
		
		String[] cc = witnessRecipients
				.stream()
				.map(p -> p.getAddress())
				.collect(Collectors.toList())
				.toArray(new String[]{});
		msg.setCc(cc);
		
		msg.setFrom(victimSender.getAddress());
		
		return msg;
	}
	
}
