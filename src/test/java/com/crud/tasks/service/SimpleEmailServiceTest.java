package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.mail.Mail;
import com.crud.tasks.domain.mail.message.MessageGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test Suite for SimpleEmailService class.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleEmailServiceTest {
  @InjectMocks
  private SimpleEmailService simpleEmailService;
  @Autowired
  MessageGenerator messageGenerator;
  @Autowired
  private MailCreatorService mailCreatorService;

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

  @Test
  public void messageGeneratorTest() throws Exception {
    //Given
    final Long oneTaskQuantity = 1L;
    final Long multipleTaskQuantity = 5L;

    //When
    final String multipleTaskMessage = messageGenerator.generateMessage(multipleTaskQuantity);
    final String oneTaskMessage = messageGenerator.generateMessage(oneTaskQuantity);

    //Then
    assertEquals("Currently in your database you got: 5 tasks", multipleTaskMessage);
    assertEquals("Currently in your database you got: 1 task", oneTaskMessage);
  }



  @Test
  public void mailTemplateTest() throws Exception {
    //Given
     simpleEmailService.send(new Mail("mateusz.kodilla@gmail.com","Subject",mailCreatorService.buildTrelloCardEmail("message")));

    //When


    //Then

  }
}