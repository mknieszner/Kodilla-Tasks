package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents Created Trello Card.
 */
@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatedTrelloCard {

  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("shortUrl")
  private String shortUrl;

  @JsonProperty("badges")
  private Badges badges;
}

@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
class Badges {
  @JsonProperty("votes")
  private int votes;

  @JsonProperty("attachmentsByType")
  private AttachmentByType attachmentsByType;
}

@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
class AttachmentByType {
  @JsonProperty("trello")
  private Trello trello;
}

@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
class Trello {
  @JsonProperty("board")
  private int board;

  @JsonProperty("card")
  private int card;
}
