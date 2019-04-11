/**
 * Author   : Bacso Gaetan and Dutu Launay Marion
 * File     : MailRobot.java
 * Compiler and executer : 
 * 		javac 1.8.0_201
 *      java version "1.8.0_201"
 *      Java(TM) SE Runtime Environment (build 1.8.0_201-b09)
 *      Java HotSpot(TM) 64-Bit Server VM (build 25.201-b09, mixed mode)

 */
package RES.SMTP_Client;

import java.io.IOException;

import RES.SMTP_Client.config.ConfigurationManager;
import RES.SMTP_Client.model.prank.Prank;
import RES.SMTP_Client.model.prank.PrankGenerator;
import RES.SMTP_Client.smtp.SmtpClient;

/**
 * Main class of the program. This class get the configuration of the program. 
 * Create pranks with group and send it to SMTP server.
 * 
 * @author Bacso Gaetan and Dutu Launay Marion
 *
 */
public class MailRobot {
	public static void main(String[] args) {
		 try {
			ConfigurationManager manager = new ConfigurationManager();
			SmtpClient client = new SmtpClient(manager.getSmtpServerAddress(), manager.getSmtpServerPort());
			PrankGenerator pranks = new PrankGenerator(manager);
			
			for(Prank prank : pranks.generatePranks()) {
				client.sendMessage(prank.generateMessage());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
