package com.crud.tasks.service;

import com.crud.tasks.domain.mail.Mail;
import com.crud.tasks.service.mail.EmailCreator;
import com.crud.tasks.service.mail.MailCreatorService;
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
  MailCreatorService mailCreatorService;

  @Autowired
  private JavaMailSender javaMailSender;

  private static final Logger LOGGER = LoggerFactory.getLogger(SimpleEmailService.class);

  public void send(final Mail mail, final EmailCreator emailCreator) {
    try {
      javaMailSender.send(createMimeMessage(mail, emailCreator));

      LOGGER.info("Email has been sent");

    } catch (MailException e) {
      LOGGER.error("Failed to process email sending: " + e.getMessage(), e);
    }
  }

//  private SimpleMailMessage createMailMessage(final Mail mail) {
//    final SimpleMailMessage mailMessage = new SimpleMailMessage();
//    mailMessage.setTo(mail.getMailTo());
//    mailMessage.setSubject(mail.getSubject());
//    mailMessage.setText(mail.getMessage());
//    if (StringUtils.isNotBlank(mail.getToCc())) {
//      mailMessage.setCc(mail.getToCc());
//    }
//    return mailMessage;
//  }

  public MimeMessagePreparator createMimeMessage(final Mail mail, final EmailCreator emailCreator) {
    return mimeMessage -> {
      final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
      mimeMessageHelper.setTo(mail.getMailTo());
      mimeMessageHelper.setSubject(mail.getSubject());
      mimeMessageHelper.setText(emailCreator.buildEmail(mail.getMessage()), true);
    };
  }
}
