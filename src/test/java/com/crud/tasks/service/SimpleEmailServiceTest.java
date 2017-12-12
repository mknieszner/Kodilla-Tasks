package com.crud.tasks.service;

import com.crud.tasks.domain.mail.Mail;
import com.crud.tasks.service.mail.EmailCreator;
import com.crud.tasks.service.mail.MimeMessageCreator;
import com.crud.tasks.service.mail.NewCardEmailCreator;
import com.crud.tasks.service.mail.TasksInfoEmailCreator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test Suite for SimpleEmailService class.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleEmailServiceTest {
  @InjectMocks
  private SimpleEmailService simpleEmailService;
  @Mock
  private JavaMailSender javaMailSender;
  @Mock
  private MimeMessageCreator mimeMessageCreator;
  @Autowired
  NewCardEmailCreator newCardEmailCreator;
  @Autowired
  TasksInfoEmailCreator tasksInfoEmailCreator;

  @Test
  public void shouldSendEmails() throws Exception {
    //Given
    final Mail mail = new Mail("test@test.com", "Test", "TEST MESSAGE");
    final MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
      final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
      mimeMessageHelper.setTo(mail.getMailTo());
      mimeMessageHelper.setSubject(mail.getSubject());
      mimeMessageHelper.setText(mail.getMessage());
    };
    when(mimeMessageCreator.createMimeMessage(eq(mail), any(EmailCreator.class))).thenReturn(mimeMessagePreparator);

    //When
    simpleEmailService.send(mail, newCardEmailCreator);
    simpleEmailService.send(mail, tasksInfoEmailCreator);

    //Then
    verify(javaMailSender, times(2)).send(mimeMessagePreparator);
  }
}