package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
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
  private TrelloFacade trelloFacade;


  @GetMapping("boards")
  public List<TrelloBoardDto> getTrelloBoards() {
    return trelloFacade.fetchTrelloBoards();
  }

  @PostMapping("card")
  public CreatedTrelloDto createTrelloCard(@RequestBody final TrelloCardDto trelloCardDto) {
    return trelloFacade.createCard(trelloCardDto);
  }
}