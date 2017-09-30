package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Main Task controller.
 */
@RestController
@RequestMapping("/v1/tasks")
public class TaskController {

  @GetMapping
  public List<TaskDto> getTasks() {
    return new ArrayList<TaskDto>();
  }

  @GetMapping(value = "{taskId}")
  public TaskDto getTask(@PathVariable final String taskId) {
    return new TaskDto(Long.parseLong(taskId), "Test title", "test_content");
  }

  @DeleteMapping(value = "{taskId}")
  public void deleteTask(@PathVariable final String taskId) {
  }

  @PutMapping(value = "{taskId}")
  public TaskDto updateTask(@RequestBody final TaskDto taskDto, @PathVariable final String taskId) {
    return new TaskDto(Long.parseLong(taskId), "Test updated title", "test_updated_content");
  }

  @PostMapping
  public void createTask(@RequestBody final TaskDto taskDto) {
  }
}