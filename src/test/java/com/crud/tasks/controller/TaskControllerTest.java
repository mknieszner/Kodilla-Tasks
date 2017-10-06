package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.TaskNotFoundException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.util.NestedServletException;


import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

  @Autowired
  TaskRepository taskRepository;

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void getTasksTest() throws Exception {
    mockMvc.perform(
        get("/v1/tasks/"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("\"title\":\"\",\"content\":\"\"}")));
  }

  @Test
  public void getTaskTest() throws Exception {
    mockMvc.perform(
        get("/v1/tasks/2"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content()
            .string(containsString("{\"id\":2,\"title\":\"\",\"content\":\"\"}")));
  }

  @Test
  public void updateTaskTest() throws Exception {
    mockMvc.perform(put("/v1/tasks/2")
        .contentType(TestUtil.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(new TaskDto((long) 2, "", ""))))
        .andDo(print()).andExpect(content().string(containsString("{\"id\":2,\"title\":\"\",\"content\":\"\"}"))).andExpect(status().isOk());
  }

  @Test
  public void deleteTaskTest() throws Exception {
    //Given
    final Task toDeleteTask = taskRepository.save(new Task(null, "", ""));

    //When Then
    mockMvc.perform(
        delete("/v1/tasks/" + toDeleteTask.getId()))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void createTaskTest() throws Exception {
    mockMvc.perform(
        post("/v1/tasks/")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(new TaskDto(null, "", ""))))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test(expected = NestedServletException.class)
  public void taskNotFoundExceptionTest() throws Exception {
    mockMvc.perform(get("/v1/tasks/-1"));

    // Exception should be thrown
  }

  private static class TestUtil {
    public static final MediaType APPLICATION_JSON_UTF8 =
        new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    public static byte[] convertObjectToJsonBytes(final Object object) throws IOException {
      final ObjectMapper mapper = new ObjectMapper();
      mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
      return mapper.writeValueAsBytes(object);
    }
  }
}
