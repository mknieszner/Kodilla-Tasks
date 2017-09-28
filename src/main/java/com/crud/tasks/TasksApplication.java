package com.crud.tasks;

import com.crud.tasks.domain.TaskDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.System.*;

/**
 * SpringBoot main class.
 */
@SpringBootApplication
public class TasksApplication {
  public static void main(final String[] args) {
    SpringApplication.run(TasksApplication.class, args);
  }
}
