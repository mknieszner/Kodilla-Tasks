package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
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

  @GetMapping("boardsHeaders")//Zadanie 18.2
  public void getTrelloBoardsHeaders() {
    trelloClient
        .getTrelloBoards()
        .stream()
        .filter(trelloBoardDto -> trelloBoardDto.getName() != null && trelloBoardDto.getName().contains("Kodilla") && trelloBoardDto.getId() != null)
        .forEach(trelloBoardDto -> System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName()));
  }//koniec 18.2

  @GetMapping("boards")
  public void getTrelloBoards() {
    final List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

    trelloBoards.forEach(trelloBoardDto -> {
      System.out.println(trelloBoardDto.getName() + " - " + trelloBoardDto.getId());
      System.out.println("This board contains lists: ");
      trelloBoardDto.getLists().forEach(trelloList ->
          System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed()));
    });
  }

  @PostMapping("card")
  public CreatedTrelloCard createTrelloCard(@RequestBody final TrelloCardDto trelloCardDto) {
    return trelloClient.createNewCard(trelloCardDto);
  }
}