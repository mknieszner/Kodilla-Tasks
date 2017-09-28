package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Dto for Task class.
 */
@Getter
@AllArgsConstructor
public class TaskDto {
  private Long id;
  private String title;
  private String content;
}
