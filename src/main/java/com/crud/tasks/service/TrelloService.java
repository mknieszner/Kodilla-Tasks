package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloDto;
import com.crud.tasks.domain.mail.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.mail.MailCreatorService;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Trello Service class.
 */
@Service
public class TrelloService {
  @Autowired
  AdminConfig adminConfig;
  @Autowired
  private TrelloClient trelloClient;
  @Autowired
  private SimpleEmailService simpleEmailService;
  @Autowired
  private MailCreatorService mailCreatorService;

  private static final String SUBJECT = "Tasks: New Trello card";

  public List<TrelloBoardDto> fetchTrelloBoards() {
    return trelloClient.getTrelloBoards();
  }

  public CreatedTrelloDto createTrelloCard(final TrelloCardDto trelloCardDto) {
    final CreatedTrelloDto newCard = trelloClient.createNewCard(trelloCardDto);

    simpleEmailService.send(
        new Mail(
          adminConfig.getAdminMail(),
          SUBJECT,
          "New card: " + trelloCardDto.getName() + " has been created on your Trello account"),
        mailCreatorService);
    return newCard;
  }
}

