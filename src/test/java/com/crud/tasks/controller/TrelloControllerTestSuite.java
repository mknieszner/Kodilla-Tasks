package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.repository.TaskRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test Suite for Trello Controller.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TrelloControllerTestSuite {

  @Autowired
  TaskRepository taskRepository;

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void getTasksTest() throws Exception {
    mockMvc.perform(get("/v1/trello/boards")).andDo(print()).andExpect(status().isOk());
  }

  @Test
  public void createTrelloCardTest() throws Exception {
    mockMvc.perform(
        post("/v1/tasks/")
        .contentType(TrelloControllerTestSuite.TestUtil.APPLICATION_JSON_UTF8)
            .content(TrelloControllerTestSuite.TestUtil.convertObjectToJsonBytes(getTrelloCardDto())))
        .andDo(print())
        .andExpect(status().isOk());
  }

  private static class TestUtil {
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
        MediaType.APPLICATION_JSON.getType(),
        MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    public static byte[] convertObjectToJsonBytes(final Object object) throws IOException {
      final ObjectMapper mapper = new ObjectMapper();
      mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
      return mapper.writeValueAsBytes(object);
    }
  }

  private TrelloCardDto getTrelloCardDto() {
    return new TrelloCardDto("test_name", "test_description", "test_pos", "test_id");
  }

}
