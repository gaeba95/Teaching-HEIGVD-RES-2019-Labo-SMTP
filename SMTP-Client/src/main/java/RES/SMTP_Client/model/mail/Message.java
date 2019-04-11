/**
 * Author   : Bacso Gaetan and Dutu Launay Marion
 * Source   : Olivier Liechti (MailRobot)
 * File     : Message.java
 * Compiler and executer :
 *   	javac 1.8.0_201
 *      java version "1.8.0_201"
 *      Java(TM) SE Runtime Environment (build 1.8.0_201-b09)
 *      Java HotSpot(TM) 64-Bit Server VM (build 25.201-b09, mixed mode)

 */

package RES.SMTP_Client.model.mail;

/**
 * This class is useful to create messages and contains all of the informations that can be included in a message :
 * sender, recipient, cc recipients, bcc recipients, subject and body of the message. It has a setter and a getter for
 * each of these attributes.
 *
 * @author Bacso Gaetan and Dutu Launay Marion
 */
public class Message {
	private String from;
	private String[] to = new String[0];
	private String[] cc = new String[0];
	private String[] bcc = new String[0];
	private String subject;
	private String body;
	
	public String getFrom() {
		return from;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String[] getCc() {
		return cc;
	}

	public void setCc(String[] cc) {
		this.cc = cc;
	}

	public String[] getBcc() {
		return bcc;
	}

	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
}
