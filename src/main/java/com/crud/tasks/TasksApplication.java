package com.crud.tasks;

import com.crud.tasks.domain.TaskDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.lang.System.*;

/**
 * SpringBoot main class.
 */
@SpringBootApplication
public class TasksApplication extends SpringBootServletInitializer {
  public static void main(final String[] args) {
    SpringApplication.run(TasksApplication.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
    return application.sources(TasksApplication.class);
  }
}
