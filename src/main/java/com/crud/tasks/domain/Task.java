package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
//import lombok.Getter;

/**
 * Represents task.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tasks")
public class Task {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "name")
  private String title;

  @Column(name = "description")
  private String content;
}
