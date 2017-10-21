package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Represents task.
 */
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TASKS")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "NAME")
  private String title;

  @Column(name = "DESCRIPTION")
  private String content;
}
