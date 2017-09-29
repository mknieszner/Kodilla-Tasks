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
@RequestMapping("/v1/task")
public class TaskController {

  @GetMapping(value = "getTasks")
  public ResponseEntity<List<TaskDto>> getTasks() {
    return new ResponseEntity<List<TaskDto>>(new ArrayList<TaskDto>(), HttpStatus.OK);
  }

  @GetMapping(value = "getTask/{taskId}")
  public ResponseEntity<TaskDto> getTask(@PathVariable final String taskId) {
    return new ResponseEntity<TaskDto>(
        new TaskDto(Long.parseLong(taskId), "Test title", "test_content"), HttpStatus.OK);
  }

  @DeleteMapping(value = "deleteTask/{taskId}")
  public ResponseEntity<Void> deleteTask(@PathVariable final String taskId) {
    return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
  }

  @PutMapping(value = "updateTask")
  public ResponseEntity<TaskDto> updateTask(@RequestBody final TaskDto taskDto) {
    return new ResponseEntity<TaskDto>(
        new TaskDto(taskDto.getId(), "Test updated title", "test_updated_content"), HttpStatus.OK);
  }

  @PostMapping(value = "createTask")
  public ResponseEntity<Void> createTask(@RequestBody final TaskDto taskDto) {
    return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
  }
}