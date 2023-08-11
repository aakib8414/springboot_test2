package com.test.test1.email;

import org.springframework.stereotype.Service;


@Service
public interface EmailService {

    String sendSimpleMail(EmailDetails details);

    // Method
    // To send an email with attachment
    String sendMailWithAttachment(EmailDetails details);
}