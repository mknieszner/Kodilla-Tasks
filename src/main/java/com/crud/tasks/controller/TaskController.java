package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.crud.tasks.service.TaskNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Main Task controller.
 */
@CrossOrigin(origins = "*")
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

  @GetMapping("{taskId}")
  public TaskDto getTask(@PathVariable final Long taskId) {
    return taskMapper.mapToTaskDto(dbService.findById(taskId));
  }

  @DeleteMapping("{taskId}")
  public void deleteTask(@PathVariable final Long taskId) {
    dbService.deleteTask(taskId);
  }

  @PutMapping(value = "{taskId}", consumes = APPLICATION_JSON_VALUE)
  public TaskDto updateTask(@RequestBody final TaskDto taskDto) {
    return taskMapper.mapToTaskDto(dbService.saveTask(taskMapper.mapToTask(taskDto)));
  }

  @PostMapping(consumes = APPLICATION_JSON_VALUE)
  public void createTask(@RequestBody final TaskDto taskDto) {
    dbService.saveTask(taskMapper.mapToTask(taskDto));
  }

  @RequestMapping("test")
  public String connetionTest() {
    return "Connected";
  }
}