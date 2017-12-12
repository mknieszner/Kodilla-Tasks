package com.crud.tasks.service.mail;

import com.crud.tasks.domain.mail.Mail;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

/**
 * Mime Message Creator.
 */
@Component
public class MimeMessageCreator {
  public MimeMessagePreparator createMimeMessage(final Mail mail, final EmailCreator emailCreator) {
    return mimeMessage -> {
      final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
      mimeMessageHelper.setTo(mail.getMailTo());
      mimeMessageHelper.setSubject(mail.getSubject());
      mimeMessageHelper.setText(emailCreator.buildEmail(mail.getMessage()), true);
    };
  }
}
