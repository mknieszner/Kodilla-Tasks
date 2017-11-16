package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTestSuite {
  @Autowired
  private TrelloMapper trelloMapper;
  @Autowired
  private TaskMapper taskMapper;

  @Test
  public void mapToTaskTest() {
    //Given
    TaskDto taskDto = new TaskDto(1L,"testTitle","testContent");

    //When
    Task resultTask = taskMapper.mapToTask(taskDto);

    //Then
    assertEquals(taskDto.getId(),resultTask.getId());
    assertEquals(taskDto.getTitle(),resultTask.getTitle());
    assertEquals(taskDto.getContent(),resultTask.getContent());
  }

  @Test
  public void mapToTaskDtoTest() {
    //Given
    Task task = new Task(1L,"testTitle","testContent");

    //When
    TaskDto resultTask = taskMapper.mapToTaskDto(task);

    //Then
    assertEquals(task.getId(),resultTask.getId());
    assertEquals(task.getTitle(),resultTask.getTitle());
    assertEquals(task.getContent(),resultTask.getContent());
  }

  @Test
  public void mapToTaskDtoListTest() {
    //Given
    List<Task> tasks = new ArrayList<>();
    tasks.add(new Task(1L,"testTitle","testContent"));
    tasks.add(new Task(2L,"testTitle2","testContent2"));

    //When
    List<TaskDto> resultTasks = taskMapper.mapToTaskDtoList(tasks);

    //Then
    System.out.println("rtsize"+tasks.size()+"rtsize"+resultTasks.size());
    assertEquals(tasks.size(),resultTasks.size());
    Iterator resultTaskIterator = resultTasks.iterator();
    Iterator tasksIterator = tasks.iterator();
    while(resultTaskIterator.hasNext() && tasksIterator.hasNext()){
      TaskDto resultTask = (TaskDto) resultTaskIterator.next();
      Task task = (Task) tasksIterator.next();
      assertEquals(task.getId(),resultTask.getId());
      assertEquals(task.getTitle(),resultTask.getTitle());
      assertEquals(task.getContent(),resultTask.getContent());
    }
  }

  @Test
  public void mapToCardDtoTest() {
    //Given
    TrelloCard trelloCard = new TrelloCard("testName","testDescription","testPos","testId");

    //When
    TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

    //Then
    assertEquals(trelloCard.getName(),trelloCardDto.getName());
    assertEquals(trelloCard.getDescription(),trelloCardDto.getDescription());
    assertEquals(trelloCard.getListId(),trelloCardDto.getListId());
    assertEquals(trelloCard.getPos(),trelloCardDto.getPos());
  }

  @Test
  public void mapToCardTest() {
    //Given
    TrelloCardDto trelloCardDto = new TrelloCardDto("testName","testDescription","testPos","testId");

    //When
    TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

    //Then
    assertEquals(trelloCardDto.getName(),trelloCard.getName());
    assertEquals(trelloCardDto.getDescription(),trelloCard.getDescription());
    assertEquals(trelloCardDto.getListId(),trelloCard.getListId());
    assertEquals(trelloCardDto.getPos(),trelloCard.getPos());
  }

  @Test
  public void mapToBoardsDtoTest() {
    List<TrelloBoard> trelloBoards = getTestTrelloBoards();

    List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);

    assertEquals(trelloBoards.get(0).getName(),trelloBoardsDto.get(0).getName());
    assertEquals(trelloBoards.get(0).getId(),trelloBoardsDto.get(0).getId());
    assertEquals(trelloBoards.get(0).getLists().get(0).getId(),trelloBoardsDto.get(0).getLists().get(0).getId());
    assertEquals(trelloBoards.get(0).getLists().get(0).getName(),trelloBoardsDto.get(0).getLists().get(0).getName());
    assertEquals(trelloBoards.get(0).getLists().get(0).isClosed(),trelloBoardsDto.get(0).getLists().get(0).isClosed());
  }

  @Test
  public void mapToBoardsTest() {
    List<TrelloBoardDto> trelloBoardsDto = getTestTrelloBoardsDto();

    List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);

    assertEquals(trelloBoardsDto.get(0).getName(),trelloBoards.get(0).getName());
    assertEquals(trelloBoardsDto.get(0).getId(),trelloBoards.get(0).getId());
    assertEquals(trelloBoardsDto.get(0).getLists().get(0).getId(),trelloBoards.get(0).getLists().get(0).getId());
    assertEquals(trelloBoardsDto.get(0).getLists().get(0).getName(),trelloBoards.get(0).getLists().get(0).getName());
    assertEquals(trelloBoardsDto.get(0).getLists().get(0).isClosed(),trelloBoards.get(0).getLists().get(0).isClosed());
  }

  private List<TrelloBoardDto> getTestTrelloBoardsDto() {
    List<TrelloListDto> trelloListsDto  = new ArrayList<>();
    trelloListsDto.add(new TrelloListDto("testId","testName",true));
    TrelloBoardDto trelloBoard = new TrelloBoardDto("testBoardId","testBoardName",trelloListsDto);
    List<TrelloBoardDto> trelloBoards = new ArrayList<>();
    trelloBoards.add(trelloBoard);
    return trelloBoards;

  }

  private List<TrelloBoard> getTestTrelloBoards(){
    List<TrelloList> trelloListsDto  = new ArrayList<>();
    trelloListsDto.add(new TrelloList("testId","testName",true));
    TrelloBoard trelloBoard = new TrelloBoard("testBoardId","testBoardName",trelloListsDto);
    List<TrelloBoard> trelloBoards = new ArrayList<>();
    trelloBoards.add(trelloBoard);
    return trelloBoards;
  }
}