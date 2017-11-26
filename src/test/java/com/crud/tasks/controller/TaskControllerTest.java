package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;


import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test Suite for Task Controller.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
  @MockBean
  private DbService dbService;
  @MockBean
  private TaskMapper taskMapper;
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void getTasksTest() throws Exception {
    //Given
    final List<Task> tasks = new ArrayList<>();
    tasks.add(new Task(1L, "test_title", "test_content"));
    final List<TaskDto> taskDtos = new ArrayList<>();
    taskDtos.add(new TaskDto(1L, "test_title", "test_content"));

    when(dbService.getAllTask()).thenReturn(tasks);
    when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(taskDtos);

    //When & Then
    mockMvc.perform(get("/v1/tasks/"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].id", is(tasks.get(0).getId().intValue())))
        .andExpect(jsonPath("$[0].title", is(tasks.get(0).getTitle())))
        .andExpect(jsonPath("$[0].content", is(tasks.get(0).getContent())));
  }

  @Test
  public void getTaskTest() throws Exception {
    //Given
    final Task testTask = new Task(1L, "test_title", "test_content");
    when(dbService.findById(1)).thenReturn(testTask);
    final TaskDto testTaskDto = new TaskDto(1L, "test_title", "test_content");
    when(taskMapper.mapToTaskDto(testTask)).thenReturn(testTaskDto);

    //When & Then
    mockMvc.perform(get("/v1/tasks/" + testTask.getId()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(testTask.getId().intValue())))
        .andExpect(jsonPath("$.title", is(testTask.getTitle())))
        .andExpect(jsonPath("$.content", is(testTask.getContent())));

  }

  @Test
  public void updateTaskTest() throws Exception {
    //Given
    final Task testTask = new Task(1L, "test_title", "test_content");
    when(dbService.saveTask(ArgumentMatchers.any(Task.class))).thenReturn(testTask);
    final TaskDto testTaskDto = new TaskDto(1L, "test_title", "test_content");
    when(taskMapper.mapToTaskDto(ArgumentMatchers.any(Task.class))).thenReturn(testTaskDto);
    when(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(testTask);

    //When & Then
    mockMvc.perform(put("/v1/tasks/" + testTask.getId())
        .contentType(APPLICATION_JSON_UTF8)
        .content(toJson(testTask)))
        .andDo(print())
        .andExpect(status().isOk())
      //  .andExpect(jsonPath("$.id", is(testTask.getId().intValue())))
        .andExpect(jsonPath("$.title", is(testTask.getTitle())))
        .andExpect(jsonPath("$.content", is(testTask.getContent())));
  }

  @Test
  public void deleteTaskTest() throws Exception {
    //Given
    doNothing().when(dbService).deleteTask(ArgumentMatchers.any(Long.class));

    //When Then
    mockMvc.perform(delete("/v1/tasks/1"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void createTaskTest() throws Exception {
    //Given
    final Task testTask = new Task(1L, "test_title", "test_content");
    when(dbService.saveTask(ArgumentMatchers.any(Task.class))).thenReturn(testTask);
    when(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(testTask);

    //When & Then
    mockMvc.perform(post("/v1/tasks/")
        .contentType(APPLICATION_JSON_UTF8)
        .content(toJson(testTask)))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void methodArgumentTypeMismatchExceptionTest() throws Exception {
    //Given & When & Then
    mockMvc.perform(get("/v1/tasks/errorTest"))
        .andDo(print())
        .andExpect(status().is4xxClientError());
  }

  @Test
  public void taskBodyNotAttachedTest() throws Exception {
    //Given & When & Then
    mockMvc.perform(post("/v1/tasks/1"))
        .andDo(print())
        .andExpect(status().is4xxClientError());
  }

  private String toJson(final Object o) {
    final Gson gson = new Gson();
    return gson.toJson(o);
  }

  private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
      MediaType.APPLICATION_JSON.getType(),
      MediaType.APPLICATION_JSON.getSubtype(),
      Charset.forName("utf8"));
}



