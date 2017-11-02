package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.client.TrelloClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Trello Controller.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

  @Autowired
  private TrelloService trelloClient;


  @GetMapping("boards")
  public List<TrelloBoardDto> getTrelloBoards() {
    return trelloClient.fetchTrelloBoards();
  }

  @PostMapping("card")
  public CreatedTrelloCard createTrelloCard(@RequestBody final TrelloCardDto trelloCardDto) {
    return trelloClient.createTrelloCard(trelloCardDto);
  }
}