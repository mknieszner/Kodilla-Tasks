package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents Trello List.
 */
@Getter
@AllArgsConstructor
public class TrelloList {
  private String id;
  private String name;
  private boolean isClosed;
}
