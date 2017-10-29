package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Dto for Trello List.
 */
@Getter
@AllArgsConstructor
public class TrelloListDto {
  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("closed")
  private boolean isClosed;
}
