/**
 * Author   : Bacso Gaetan and Dutu Launay Marion
 * Source   : Olivier Liechti (MailRobot)
 * File     : SmtpClient.java
 * Compiler and executer : 
 * 		javac 1.8.0_201
 *      java version "1.8.0_201"
 *      Java(TM) SE Runtime Environment (build 1.8.0_201-b09)
 *      Java HotSpot(TM) 64-Bit Server VM (build 25.201-b09, mixed mode)
 */
package RES.SMTP_Client.smtp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

import RES.SMTP_Client.model.mail.Message;

/**
 * Client SMTP. Send smtp message.
 * 
 * @author Bacso Gaetan and Dutu Launay Marion
 *
 */
public class SmtpClient implements ISmtpClient{
	// The logger of the client (for infos messages)
	private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());

	private String smtpServerAddress;
	private int smtpServerPort = 25;
	
	private Socket socket;
	private PrintWriter writer;
	private BufferedReader reader;
	
	/**
	 * Constructor of the class
	 * 
	 * @param smtpServerAddress : address of the server
	 * @param port : port of the server
	 */
	public SmtpClient(String smtpServerAddress, int port) {
		this.smtpServerAddress = smtpServerAddress;
		this.smtpServerPort = port;
	}

	/**
	 * Send a message to the SMTP server
	 * 
	 * @param message : the message to send
	 */
	public void sendMessage(Message message) throws IOException {
		LOG.info("Sending message via SMTP");
		// Create the socket
		Socket socket = new Socket(smtpServerAddress, smtpServerPort);
		// Create writer and reader
		writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
		String line = reader.readLine();
		LOG.info(line);
		// Send HELO
		writer.printf("HELO localhost\r\n");
		// Get answer
		line = reader.readLine();
		LOG.info(line);
		if(!line.startsWith("250")) {
			throw new IOException("SMTP error: " + line);
		}
		while(line.startsWith("250-")) {
			line = reader.readLine();
			LOG.info(line);
		}
		// Specify the sender
		writer.write("MAIL FROM: ");
		writer.write(message.getFrom());
		writer.write("\r\n");
		writer.flush();
		line = reader.readLine();
		LOG.info(line);
		
		// Specify the recipients
		for(String to : message.getTo()) {
			writer.write("RCPT TO: ");
			writer.write(to);
			writer.write("\r\n");
			writer.flush();
			line = reader.readLine();
			LOG.info(line);
		}
		
		// Specify the CC
		for(String to : message.getCc()) {
			writer.write("RCPT TO: ");
			writer.write(to);
			writer.write("\r\n");
			writer.flush();
			line = reader.readLine();
			LOG.info(line);
		}
		
		// Specify the BCC
		for(String to : message.getBcc()) {
			writer.write("RCPT TO: ");
			writer.write(to);
			writer.write("\r\n");
			writer.flush();
			line = reader.readLine();
			LOG.info(line);
		}
		
		// Specify the content of the message
		writer.write("DATA");
		writer.write("\r\n");
		writer.flush();
		line = reader.readLine();
		LOG.info(line);
		writer.write("Content-Type: text/plain: charset=\"utf-8\"\r\n");
		writer.write("From: " + message.getFrom() + "\r\n");
		
		if(message.getTo().length != 0) {
			writer.write("To: " + message.getTo()[0]);
			for(int i = 1; i < message.getTo().length; i++) {
				writer.write(", " + message.getTo()[i]);
			}
			writer.write("\r\n");
		}
		
		if(message.getCc().length != 0) {
			writer.write("Cc: " + message.getCc()[0]);
			for(int i = 1; i < message.getCc().length; i++) {
				writer.write(", " + message.getCc()[i]);
			}
			writer.write("\r\n");
		}
		
		writer.flush();
		
		LOG.info(message.getBody());
		writer.write(message.getBody());
		writer.write("\r\n");
		writer.write(".");
		writer.write("\r\n");
		writer.flush();
		line = reader.readLine();
		LOG.info(line);
		
		// Close the connection
		writer.write("QUIT\r\n");
		writer.flush();
		reader.close();
		writer.close();
		socket.close();
	}
}
