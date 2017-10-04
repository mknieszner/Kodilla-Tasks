package com.crud.tasks.service;

import org.springframework.stereotype.Component;

import java.util.function.Supplier;

/**
 * Database exception.
 */
public class TaskNotFoundException extends RuntimeException {
  public TaskNotFoundException(final String id) {
    super(String.format("Task no. %s not found!", id));
  }
}
