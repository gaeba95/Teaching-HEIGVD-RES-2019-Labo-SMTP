/**
 * Author   : Bacso Gaetan and Dutu Launay Marion
 * Source   : Olivier Liechti (MailRobot)
 * File     : ISmtpClient.java
 * Compiler and executer : 
 * 		javac 1.8.0_201
 *      java version "1.8.0_201"
 *      Java(TM) SE Runtime Environment (build 1.8.0_201-b09)
 *      Java HotSpot(TM) 64-Bit Server VM (build 25.201-b09, mixed mode)
 */
package RES.SMTP_Client.smtp;

import java.io.IOException;

import RES.SMTP_Client.model.mail.*;


public interface ISmtpClient {
	public void sendMessage(Message message) throws IOException;
}
