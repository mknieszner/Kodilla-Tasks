package com.crud.tasks.trello.client;

import com.crud.tasks.config.TrelloConfig;
import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

import static java.util.Optional.ofNullable;

/**
 * TrelloClient.
 */
@Component
public class TrelloClient {

  private static final Logger LOGGER = LoggerFactory.getLogger(TrelloClient.class);

  @Autowired
  private TrelloConfig trelloConfig;

  @Autowired
  private RestTemplate restTemplate;

  public List<TrelloBoardDto> getTrelloBoards() {
    try {
      return ofNullable(restTemplate.getForObject(getTrelloBoardsUrl(), TrelloBoardDto[].class)).map(Arrays::asList).orElseGet(Collections::emptyList);
    } catch (RestClientException e) {
      LOGGER.error(e.getMessage(), e);
          return new ArrayList<>();
    }
  }

  private URI getTrelloBoardsUrl() {
    return UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/members/" + trelloConfig.getTrelloUsername() + "/boards")
        .queryParam("key", trelloConfig.getTrelloAppKey())
        .queryParam("token", trelloConfig.getTrelloToken())
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
    return UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards")
        .queryParam("key", trelloConfig.getTrelloAppKey())
        .queryParam("token", trelloConfig.getTrelloToken())
        .queryParam("name", trelloCardDto.getName())
        .queryParam("desc", trelloCardDto.getDescription())
        .queryParam("pos", trelloCardDto.getPos())
        .queryParam("idList", trelloCardDto.getListId())
        .build().encode().toUri();
  }
}
