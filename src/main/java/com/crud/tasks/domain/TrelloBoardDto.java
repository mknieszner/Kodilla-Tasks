package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Dto for Trello Board.
 */
@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloBoardDto {
  @JsonProperty("name")
  private String name;
  @JsonProperty("id")
  private String id;
  @JsonProperty("lists")
  private List<TrelloBoardDto> lists;

  @JsonProperty("closed")
  private boolean isClosed;
}
