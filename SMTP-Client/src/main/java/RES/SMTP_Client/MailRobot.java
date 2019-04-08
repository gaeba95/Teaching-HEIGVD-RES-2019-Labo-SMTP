package RES.SMTP_Client;

import java.io.IOException;

import RES.SMTP_Client.config.ConfigurationManager;
import RES.SMTP_Client.model.prank.Prank;
import RES.SMTP_Client.model.prank.PrankGenerator;
import RES.SMTP_Client.smtp.SmtpClient;

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
