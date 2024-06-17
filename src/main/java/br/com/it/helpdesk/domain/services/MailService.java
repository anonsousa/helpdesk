package br.com.it.helpdesk.domain.services;

import br.com.it.helpdesk.domain.dtos.UserSignUpDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendEmail(String user, String email) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message, "utf-8");

        String htmlMessage = generateHtmlMessage(user);

        messageHelper.setText(htmlMessage, true);
        messageHelper.setTo(email);
        messageHelper.setSubject("Bem-vindo " + user + "!");
        messageHelper.setFrom("noreply@gmail.com");

        mailSender.send(message);

    }



    public String generateHtmlMessage(String userName) {
        return "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<style>" +
                "  .container {" +
                "    font-family: Arial, sans-serif;" +
                "    padding: 20px;" +
                "    border: 1px solid #ccc;" +
                "    border-radius: 10px;" +
                "    background-color: #f9f9f9;" +
                "    max-width: 600px;" +
                "    margin: 0 auto;" +
                "  }" +
                "  .header {" +
                "    background-color: #4CAF50;" +
                "    color: white;" +
                "    padding: 10px 0;" +
                "    text-align: center;" +
                "    border-radius: 10px 10px 0 0;" +
                "  }" +
                "  .content {" +
                "    padding: 20px;" +
                "  }" +
                "  .footer {" +
                "    text-align: center;" +
                "    font-size: 12px;" +
                "    color: #777;" +
                "    padding: 10px 0;" +
                "    border-top: 1px solid #ccc;" +
                "    margin-top: 20px;" +
                "  }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class=\"container\">" +
                "  <div class=\"header\">" +
                "    <h1>Welcome to HelpDesk!</h1>" +
                "  </div>" +
                "  <div class=\"content\">" +
                "    <p>Dear " + userName + ",</p>" +
                "    <p>Thank you for joining our platform. We are excited to have you with us.</p>" +
                "    <p>If you have any questions or need assistance, feel free to reach out to our support team.</p>" +
                "    <p>Best Regards,</p>" +
                "    <p>HelpDesk</p>" +
                "  </div>" +
                "  <div class=\"footer\">" +
                "    <p>&copy; 2024 Antonio Sousa. All rights reserved.</p>" +
                "  </div>" +
                "</div>" +
                "</body>" +
                "</html>";
    }
}
