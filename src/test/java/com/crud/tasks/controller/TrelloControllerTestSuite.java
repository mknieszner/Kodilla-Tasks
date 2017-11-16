package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.ResultMatcher;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static jdk.nashorn.internal.runtime.JSType.isString;
//import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.ResultMatcher.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test Suite for Trello Controller.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(TrelloController.class)
public class TrelloControllerTestSuite {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TrelloFacade trelloFacade;

  @Test
  public void shouldFetchEmptyTrelloBoards() throws Exception {
    //Given
    final List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
    when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoardsDto);

    //When & Then
    mockMvc.perform(get("/v1/trello/boards")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(0)));
  }

  @Test
  public void shouldFetchTrelloBoards() throws Exception {
    final List<TrelloListDto> trelloLists = new ArrayList<>();
    trelloLists.add(new TrelloListDto("1", "test_list", false));

    final List<TrelloBoardDto> trelloBoards = new ArrayList<>();
    trelloBoards.add(new TrelloBoardDto("test_name", "1", trelloLists));

    when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);

    //When & Then
    mockMvc.perform(get("/v1/trello/boards")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        // Trello Boards fields
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].id", is("1")))
        .andExpect(jsonPath("$[0].name", is("test_name")))
        // Trello List fields
        .andExpect(jsonPath("$[0].lists", hasSize(1)))
        .andExpect(jsonPath("$[0].lists.[0].id", is("1")))
        .andExpect(jsonPath("$[0].lists.[0].name", is("test_list")))
        .andExpect(jsonPath("$[0].lists.[0].closed", is(false)));
  }

  @Test
  public void shouldCreateTrelloCard() throws Exception {
    //Given
    final TrelloCardDto trelloCardDto = new TrelloCardDto("Test", "Test_Description", "top", "1");
    final CreatedTrelloDto createdTrelloDto = new CreatedTrelloDto("123", "Test", "http//test.com", null);
    when(trelloFacade.createCard(ArgumentMatchers.any(TrelloCardDto.class))).thenReturn(createdTrelloDto);
    final Gson gson = new Gson();
    final String jsonContent = gson.toJson(trelloCardDto);

    //When & Then
    mockMvc.perform(post("/v1/trello/card")
        .contentType(TrelloControllerTestSuite.TestUtil.APPLICATION_JSON_UTF8)
        .characterEncoding("UTF-8").content(jsonContent))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is("123")))
        .andExpect(jsonPath("$.name", is("Test")))
        .andExpect(jsonPath("$.shortUrl", is("http//test.com")));
  }

  private static class TestUtil {
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
        MediaType.APPLICATION_JSON.getType(),
        MediaType.APPLICATION_JSON.getSubtype(),
        Charset.forName("utf8"));

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
