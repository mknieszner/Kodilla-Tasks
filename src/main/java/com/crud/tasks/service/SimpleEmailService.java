package com.crud.tasks.service;

import com.crud.tasks.domain.mail.Mail;
import com.crud.tasks.service.mail.EmailCreator;
import com.crud.tasks.service.mail.MimeMessageCreator;
import com.crud.tasks.service.mail.NewCardEmailCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Email Service.
 */
@Service
public class SimpleEmailService {
  @Autowired
  private JavaMailSender javaMailSender;
  @Autowired
  private MimeMessageCreator mimeMessageCreator;

  private static final Logger LOGGER = LoggerFactory.getLogger(SimpleEmailService.class);

  public void send(final Mail mail, final EmailCreator emailCreator) {
    try {
      javaMailSender.send(mimeMessageCreator.createMimeMessage(mail, emailCreator));

      LOGGER.info("Email has been sent");

    } catch (MailException e) {
      LOGGER.error("Failed to process email sending: " + e.getMessage(), e);
    }
  }
}
