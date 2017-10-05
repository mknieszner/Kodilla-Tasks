package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Database service.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DbService {

  private final TaskRepository taskRepository;

  public List<Task> getAllTask() {
    return (List<Task>) taskRepository.findAll();
  }

  public Task findById(final long id) {
    return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
  }

  public Task saveTask(final Task task) {
    return taskRepository.save(task);
  }

  public void deleteTask(final long taskId) {
    taskRepository.deleteById(taskId);
  }
}
