package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Email scheduler.
 */
@Component
public class EmailScheduler {
  @Autowired
  private SimpleEmailService simpleEmailService;
  @Autowired
  private TaskRepository taskRepository;
  @Autowired
  private AdminConfig adminConfig;

  private static final String SUBJECT = "Tasks: Once a day email";

  @Scheduled(cron = "0 0 10 * * *")
  public void sendInformationEmail() {
    final long size = taskRepository.count();
    final StringBuilder message = new StringBuilder("Currently in your database you got: " + size + " task");
    if (size != 1) {
      message.append('s');
    }
    simpleEmailService.send(new Mail(
        adminConfig.getAdminMail(),
        SUBJECT,
        message.toString())
    );
  }
}
