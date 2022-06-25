package com.challengealkemy.challengealkemy.utils;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

@Service
public class EmailSend {

    @Value("${api-key-sendgrid}")
    private String apiKey;


    public String sendEmail(String email) throws IOException {
        Email from = new Email("gonzaya99@gmail.com");
        String subject = "Bienvendido API Disney";
        Email to = new Email(email);
        Content contenido = new Content("text/plain", "Te damos a la bienvenida a nuestra API del mundo maravilloso de Disney");
        Mail mail = new Mail(from, subject, to, contenido);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            return response.getBody();
        } catch (Exception e) {
            throw e;
        }


    }
}
