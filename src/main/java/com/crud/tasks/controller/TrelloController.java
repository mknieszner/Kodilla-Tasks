package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Trello Controller.
 */
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

  @Autowired
  private TrelloClient trelloClient;

  @GetMapping("boardsHeaders")
  public void printTrelloBoardsHeaders() {
    trelloClient
        .getTrelloBoards()
        .stream()
        .filter(trelloBoardDto -> StringUtils.isNotBlank(trelloBoardDto.getName()) && StringUtils.isNotBlank(trelloBoardDto.getId()))
        .filter(trelloBoardDto -> trelloBoardDto.getName().contains("Kodilla"))
        .forEach(trelloBoardDto -> System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName()));
  }

  @GetMapping("boards")
  public List<TrelloBoardDto> getTrelloBoards() {
    return trelloClient.getTrelloBoards();
  }

  @PostMapping("card")
  public CreatedTrelloCard createTrelloCard(@RequestBody final TrelloCardDto trelloCardDto) {
    return trelloClient.createNewCard(trelloCardDto);
  }
}