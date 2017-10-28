package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


/**
 * TrelloClient.
 */
@Component
public class TrelloClient {
  @Value("${trello.api.endpoint.prod}")
  private String trelloApiEndpoint;

  @Value("${trello.app.key}")
  private String trelloAppKey;

  @Value("${trello.app.token}")
  private String trelloToken;

  @Value("${trello.username}")
  private String trelloUsername;


  @Autowired
  private RestTemplate restTemplate;

  public List<TrelloBoardDto> getTrelloBoards() {
    return Arrays.asList(
        Optional.ofNullable(
            restTemplate.getForObject(getTrelloBoardsUrl(), TrelloBoardDto[].class))
            .orElse(new TrelloBoardDto[]{}));
  }

  private URI getTrelloBoardsUrl() {
    return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/" + trelloUsername + "/boards")
        .queryParam("key", trelloAppKey)
        .queryParam("token", trelloToken)
        .queryParam("fields", "name,id")
        .queryParam("lists", "all")
        .build().encode().toUri();
  }

  public CreatedTrelloCard createNewCard(final TrelloCardDto trelloCardDto) {

    return restTemplate.postForObject(
        getCreatedTrelloCardUrl(trelloCardDto),
        null,
        CreatedTrelloCard.class);
  }

  private URI getCreatedTrelloCardUrl(final TrelloCardDto trelloCardDto) {
    return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/cards")
        .queryParam("key", trelloAppKey)
        .queryParam("token", trelloToken)
        .queryParam("name", trelloCardDto.getName())
        .queryParam("desc", trelloCardDto.getDescription())
        .queryParam("pos", trelloCardDto.getPos())
        .queryParam("idList", trelloCardDto.getListId())
        .build().encode().toUri();
  }
}
