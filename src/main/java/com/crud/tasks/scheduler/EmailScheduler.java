package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.mail.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import com.crud.tasks.service.mail.TasksInfoEmailCreator;
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
  @Autowired
  TasksInfoEmailCreator tasksInfoEmailCreator;

  private static final String SUBJECT = "Tasks: Once a day email";

  @Scheduled(cron = "0 0 10 * * *")
  public void sendInformationEmail() {
    simpleEmailService.send(new Mail(
        adminConfig.getAdminMail(),
        SUBJECT,
        ""),
        tasksInfoEmailCreator);
  }
}
