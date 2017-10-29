package com.crud.tasks.trello.client;

import com.crud.tasks.config.TrelloConfig;
import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


/**
 *  Test class for Trello Client.
 */
@RunWith(MockitoJUnitRunner.class)
public class TrelloClientTest {
  @InjectMocks
  private TrelloClient trelloClient;
  @Mock
  private RestTemplate restTemplate;
  @Mock
  private TrelloConfig trelloConfig;

  @Before
  public void init() {
    when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
    when(trelloConfig.getTrelloAppKey()).thenReturn("test");
    when(trelloConfig.getTrelloToken()).thenReturn("test");
    when(trelloConfig.getTrelloUsername()).thenReturn("mkx866");
  }

  @Test
  public void shouldFetchTrelloBoards() throws URISyntaxException {
    //Given
    final TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
    trelloBoards[0] = new TrelloBoardDto(
        "test_board",
        "test_id",
        new ArrayList<>()
    );
    final URI uri = new URI("http://test.com/members/mkx866/boards?key=test&token=test&fields=name,id&lists=all");
    when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(trelloBoards);

    //When
    final List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();

    //Then
    assertEquals(1, fetchedTrelloBoards.size());
    assertEquals("test_id", fetchedTrelloBoards.get(0).getId());
    assertEquals("test_board", fetchedTrelloBoards.get(0).getName());
    assertEquals(new ArrayList<>(), fetchedTrelloBoards.get(0).getLists());
  }

  @Test
  public void shouldReturnEmptyList() throws URISyntaxException {
    //Given
    final URI uri = new URI("http://test.com/members/mkx866/boards?key=test&token=test&fields=name,id&lists=all");
    when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(null);

    //When
    final List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();

    //Then
    assertEquals(0, fetchedTrelloBoards.size());
  }

  @Test
  public void shouldCreateCard() throws URISyntaxException {
    //Given
    final TrelloCardDto trelloCardDto = new TrelloCardDto(
        "Test task",
        "Test Description",
        "top",
        "test_id");

    final URI uri = new URI("http://test.com/cards?key=test&token=test&name=Test%20task&desc=Test%20Description&pos=top&idList=test_id");

    final CreatedTrelloCard createdTrelloCard = new CreatedTrelloCard(
        "1",
        "Test task",
        "http://test.com",
        null
    );
    when(restTemplate.postForObject(uri, null, CreatedTrelloCard.class)).thenReturn(createdTrelloCard);

    //When
    final CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);

    //Then
    assertEquals("1", newCard.getId());
    assertEquals("Test task", newCard.getName());
    assertEquals("http://test.com", newCard.getShortUrl());
    assertEquals(null, newCard.getBadges());
  }
}