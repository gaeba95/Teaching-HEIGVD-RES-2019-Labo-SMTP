package RES.SMTP_Client.smtp;

import java.io.IOException;

import RES.SMTP_Client.model.mail.*;


public interface ISmtpClient {
	public void sendMessage(Message message) throws IOException;
}
