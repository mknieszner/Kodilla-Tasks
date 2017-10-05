package com.crud.tasks.service;

import org.springframework.stereotype.Component;

import java.util.function.Supplier;

/**
 * Database exception.
 */
public class TaskNotFoundException extends RuntimeException {
  public TaskNotFoundException(final Long id) {
    super(String.format("Task no. %d not found!", id));
  }
}
