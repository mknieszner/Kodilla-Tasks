package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Database service.
 */
@Service
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DbService {

  @Autowired
  private TaskRepository taskRepository;

  public List<Task> getAllTask() {
    return taskRepository.findAll();
  }

  public Task findById(final long id) {
    return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(Long.toString(id)));
  }
}
