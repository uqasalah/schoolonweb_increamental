package alt.sow.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMailTLS {

	public boolean SendMail(String to, String contents) {

		final String username = "schoolonweblqa@gmail.com";
		final String password = "schoolonwebadmin";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {

			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setContent(contents, "text/html");
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);

			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress("mailer@SoWeb.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject("Web On School Login Details");

			message.setContent(mp, "text/html");

			// message.setText(contents);
			Transport.send(message);
			System.out.println("Done");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static String generatePassword() {
		int len = 8;
		char[] pwd = new char[len];
		int c = 'A';
		int rand = 0;
		for (int i = 0; i < 8; i++) {
			rand = (int) (Math.random() * 3);
			switch (rand) {
			case 0:
				c = '0' + (int) (Math.random() * 10);
				break;
			case 1:
				c = 'a' + (int) (Math.random() * 26);
				break;
			case 2:
				c = 'A' + (int) (Math.random() * 26);
				break;
			}
			pwd[i] = (char) c;
		}
		return new String(pwd);
	}

}
