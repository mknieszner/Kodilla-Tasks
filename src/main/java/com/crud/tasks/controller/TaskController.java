package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * Main Task controller.
 */
@RestController
@RequestMapping("/v1/tasks")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskController {


  private final DbService dbService;
  private final TaskMapper taskMapper;

  @GetMapping
  public List<TaskDto> getTasks() {
    return taskMapper.mapToTaskDtoList(dbService.getAllTask());
  }

  @GetMapping(value = "{taskId}")
  public TaskDto getTask(@PathVariable final String taskId) {
    return taskMapper.mapToTaskDto(dbService.findById(Long.parseLong(taskId)));
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