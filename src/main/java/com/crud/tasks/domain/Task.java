package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Represents task.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tasks")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;

  @Column(name = "name")
  public String title;

  @Column(name = "description")
  public String content;
}
