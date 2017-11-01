package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test Suite for SimpleEmailService class.
 */
@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {
  @InjectMocks
  private SimpleEmailService simpleEmailService;

  @Mock
  private JavaMailSender javaMailSender;

  @Test
  public void shouldSendEmail() throws Exception {
    //Given
    final Mail mail = new Mail("test@test.com", "Test", "TEST MESSAGE");

    final SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setTo(mail.getMailTo());
    mailMessage.setSubject(mail.getSubject());
    mailMessage.setText(mail.getMessage());

    //When
    simpleEmailService.send(mail);

    //Then
    verify(javaMailSender, times(1)).send(mailMessage);
  }

  @Test
  public void shouldSendEmailwithCc() throws Exception {
    //Given
    final Mail mail = new Mail("test@test.com", "testCc@test.com", "Test", "TEST MESSAGE");

    final SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setTo(mail.getMailTo());
    mailMessage.setSubject(mail.getSubject());
    mailMessage.setCc(mail.getToCc());
    mailMessage.setText(mail.getMessage());


    //When
    simpleEmailService.send(mail);

    //Then
    verify(javaMailSender, times(1)).send(mailMessage);
  }

}